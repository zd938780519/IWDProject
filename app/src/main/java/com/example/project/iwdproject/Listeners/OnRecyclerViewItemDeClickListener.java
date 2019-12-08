package com.example.project.iwdproject.Listeners;

import android.support.v7.widget.RecyclerView;

/**
 * Created By zdd on 2018/8/9
 */
public interface OnRecyclerViewItemDeClickListener {
    void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder);

    void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder);
    void onRecyclerViewItemClicked();
}
