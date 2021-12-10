package com.app.searchflickr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.searchflickr.api.model.Item;
import com.bumptech.glide.Glide;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context context;
    private List<Item> listItems;
    private OnItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Item> listItems, OnItemClickListener itemClickListener) {
        this.context = context;
        this.listItems = listItems;
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        public void onClickItem(Item item);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new MainViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (holder instanceof GenericViewHolder) {
            ((GenericViewHolder<Item>) holder).bind(listItems.get(position), position);
        }
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MainViewHolder extends GenericViewHolder<Item> {

        public MainViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Item item, int position) {
            TextView txt_title = itemView.findViewById(R.id.txt_title);
            TextView txt_author = itemView.findViewById(R.id.txt_author);
            txt_title.setText(item.getTitle());
            txt_author.setText(item.getAuthor());

            ImageView img_flickr = itemView.findViewById(R.id.img_flickr);
            String imgUrl = item.getImage();
            Glide.with(context)
                    .load(imgUrl)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img_flickr);

            //Evento de hacer click en la row
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickItem(item);
                }
            });
        }
    }
}
