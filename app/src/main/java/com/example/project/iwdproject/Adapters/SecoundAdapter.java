package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecoundAdapter extends RecyclerView.Adapter<SecoundAdapter.ViewHolder> {


    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;

    public SecoundAdapter(Context mContext, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_secound, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
//        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_secoundprice)
        TextView tvSecoundprice;
        @BindView(R.id.iv_back)
        ImageView ivBack;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_newprice)
        TextView tvNewprice;
        @BindView(R.id.tv_zenfu)
        TextView tvZenfu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
