package com.app.searchflickr;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {
    public GenericViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T item, int position);
}
