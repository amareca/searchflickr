package com.app.searchflickr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.searchflickr.api.Utils;
import com.app.searchflickr.api.WebService;
import com.app.searchflickr.api.model.InfoResponse;
import com.app.searchflickr.api.model.Item;
import com.app.searchflickr.api.model.SearchResponse;
import com.app.searchflickr.api.model.SearchResponsePhoto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment implements MainAdapter.OnItemClickListener {

    private ArrayList<Item> data;
    private SearchView searchView;
    private List<Call> requestList;

    private MainAdapter mAdapter;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        requestList = new ArrayList<>();
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem mSearchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) mSearchMenuItem.getActionView();
        setupSearchView(searchView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (data.isEmpty()) {
            setVisibilityNotFound(View.VISIBLE);
        }
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.rv_images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        mAdapter = new MainAdapter(requireContext(), data, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void setupSearchView(SearchView searchView) {
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getData(new OnSearchResponse() {
                    @Override
                    public void getInfoPhoto(SearchResponsePhoto sr) {
                        WebService service = Utils.getRetrofitInstance().create(WebService.class);
                        Call<InfoResponse> callInfo = service.getInfoByPhotoId(sr.getId());
                        requestList.add(callInfo);
                        callInfo.enqueue(new Callback<InfoResponse>() {

                            @Override
                            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                                String author = response.body().getPhoto().getOwner().getUsername();
                                String description = response.body().getPhoto().getDescription().get_content();
                                String date = response.body().getPhoto().getDates().getTaken();
                                String title = sr.getTitle();
                                String urlImage = Utils.getImageFromData(sr.getId()
                                        , sr.getSecret()
                                        , sr.getFarm()
                                        , sr.getServer());

                                Item item = new Item();
                                if (!title.trim().isEmpty()) {
                                    item.setTitle(title);
                                } else {
                                    item.setTitle("Untitle");
                                }
                                item.setAuthor(author);
                                if (!description.trim().isEmpty()) {
                                    item.setDescription(description);
                                } else {
                                    item.setDescription("No description available.");
                                }
                                item.setImage(urlImage);
                                item.setDate("Date: " + date);
                                data.add(item);
                                mAdapter.notifyItemInserted(data.indexOf(item));
                            }

                            @Override
                            public void onFailure(Call<InfoResponse> call, Throwable t) {
                                if (!call.isCanceled()) {
                                    Toast.makeText(requireContext(), "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private String getSearchQuery() {
        return searchView.getQuery().toString();
    }

    @Override
    public void onClickItem(Item item) {
        Bundle bundle = new Bundle();
        bundle.putString("title", item.getTitle());
        bundle.putString("author", item.getAuthor());
        bundle.putString("description", item.getDescription());
        bundle.putString("image", item.getImage());
        bundle.putString("date", item.getDate());

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        Navigation.findNavController(getView()).navigate(R.id.detailFragment, bundle);
    }

    public interface OnSearchResponse {
        void getInfoPhoto(SearchResponsePhoto sr);
    }

    public void getData(OnSearchResponse callback) {
        requestList.stream().forEach( r -> r.cancel());
        requestList.clear();
        data.clear();
        WebService service = Utils.getRetrofitInstance().create(WebService.class);
        Call<SearchResponse> call = service.getData(getSearchQuery());
        requestList.add(call);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                List<SearchResponsePhoto> photoList = response.body().getPhotos().getPhotoList();
                if (photoList == null || photoList.isEmpty()) {
                    setVisibilityNotFound(View.VISIBLE);
                } else {
                    setVisibilityNotFound(View.GONE);
                    photoList.stream().forEach( photo -> callback.getInfoPhoto(photo));
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                setVisibilityNotFound(View.VISIBLE);
                if (call.isCanceled()) {
                    Toast.makeText(requireContext(), "Updating results... ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Something went wrong with Flickr server " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setVisibilityNotFound(int state) {
        ImageView img_not_found = getView().findViewById(R.id.img_not_found);
        TextView txt_not_found = getView().findViewById(R.id.txt_not_found);
        img_not_found.setVisibility(state);
        txt_not_found.setVisibility(state);
    }
}