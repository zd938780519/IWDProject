package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import butterknife.ButterKnife;

public class RecommendAdapter  extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private Context mContext;
    private com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;

    public RecommendAdapter(Context mContext, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }



    @NonNull
    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_recommend, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
//        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
