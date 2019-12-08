package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder>{
    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;

    public HotAdapter(Context mContext,  OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }


    @NonNull
    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_hot, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
