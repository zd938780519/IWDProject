package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.Utils.RecycleViewDivider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public final int TYPE_1 = 0xff01;
    public final int TYPE_2 = 0xff02;
    public final int TYPE_3 = 0xff03;
    public final int TYPE_4 = 0xff04;
    public final int TYPE_MAIN = 0xffff;


    private Context mContext;
    private OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener;


    public LeaseAdapter(Context mContext, OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener) {
        this.mContext = mContext;
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
//        this.mAccessToken = mAccessToken;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new ViewHolderFirst(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.first_layout, viewGroup, false));
            case TYPE_2:
                return new ViewHolderScound(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scound_layout, viewGroup, false));
//            case TYPE_3:
//                return new HomePageAdapter.ViewHolderCoin(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coin_layout, viewGroup, false));
//            case TYPE_4:
////                return new ViewHolderCircle(LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_layout, viewType, false));
//            case TYPE_MAIN:
//                return new ViewHolderHostpost(LayoutInflater.from(parent.getContext()).inflate(R.layout.hostpost_layout, viewType, false));
            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolderFirst) {
            bindTypeFirst((ViewHolderFirst) viewHolder, position);
        } else if (viewHolder instanceof ViewHolderScound) {
            bindTypeScound((ViewHolderScound) viewHolder, position);
        }
    }


    private void bindTypeFirst(ViewHolderFirst holder, int position) {

    }


    public class ViewHolderFirst extends RecyclerView.ViewHolder {

        public ViewHolderFirst(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    private void bindTypeScound(ViewHolderScound holder, int position) {
        final LinearLayoutManager mHotLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recycleSecound.setLayoutManager(mHotLinearLayoutManager);
//        holder.recycleSecound.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));   //添加分割线
//        holder.recycleSecound.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        holder.recycleSecound.setHasFixedSize(true);
        holder.recycleSecound.setFocusableInTouchMode(false);//不需要焦点

        SecoundAdapter mSecoundAdapter = new SecoundAdapter(mContext, mRecyclerViewItemClickListener);
        holder.recycleSecound.setAdapter(mSecoundAdapter);
    }






    private OnRecyclerViewItemDeClickListener mRecyclerViewItemClickListener = new OnRecyclerViewItemDeClickListener() {


        @Override
        public void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void onRecyclerViewItemClicked() {

        }


    };



    public class ViewHolderScound extends RecyclerView.ViewHolder {
        @BindView(R.id.recycle_secound)
        RecyclerView recycleSecound;

        public ViewHolderScound(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else if (position == 1) {
            return TYPE_2;
        } else {
            return TYPE_MAIN;
        }

    }
}
