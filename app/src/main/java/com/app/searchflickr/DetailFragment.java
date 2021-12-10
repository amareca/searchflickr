package com.app.searchflickr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailFragment extends Fragment {

    private String title;
    private String author;
    private String urlImage;
    private String description;
    private String date;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        title = requireArguments().getString("title");
        author = requireArguments().getString("author");
        urlImage = requireArguments().getString("image");
        description = requireArguments().getString("description");
        date = requireArguments().getString("date");
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem mSearchMenuItem = menu.findItem(R.id.search);
        mSearchMenuItem.setVisible(false);
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txt_detail_title = getView().findViewById(R.id.txt_detail_title);
        TextView txt_detail_author = getView().findViewById(R.id.txt_detail_author);
        TextView txt_detail_desc = getView().findViewById(R.id.txt_detail_desc);
        TextView txt_detail_date = getView().findViewById(R.id.txt_detail_date);
        txt_detail_title.setText(title);
        txt_detail_author.setText(author);
        txt_detail_desc.setText(description);
        txt_detail_date.setText(date);

        ImageView img_detail_image = getView().findViewById(R.id.img_detail_image);
        Glide.with(requireContext())
                .load(urlImage)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(img_detail_image);
    }
}