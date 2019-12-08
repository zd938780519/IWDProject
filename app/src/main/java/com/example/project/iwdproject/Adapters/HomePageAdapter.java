package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.Utils.WrappableGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    public static final int TYPE_4 = 0xff04;
    public static final int TYPE_MAIN = 0xffff;

    private Context mContext;
    private OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener;


    public HomePageAdapter(Context mContext, OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener) {
        this.mContext = mContext;
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
//        this.mAccessToken = mAccessToken;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new ViewHolderBanner(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banner_layout, viewGroup, false));
            case TYPE_2:
                return new ViewHolderHot(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hot_layout, viewGroup, false));
            case TYPE_3:
                return new ViewHolderCoin(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coin_layout, viewGroup, false));
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
        if (viewHolder instanceof ViewHolderBanner) {
            bindTypeBnner((ViewHolderBanner) viewHolder, position);
        } else if (viewHolder instanceof ViewHolderHot) {
            bindTypeHot((ViewHolderHot) viewHolder, position);
        } else if (viewHolder instanceof ViewHolderCoin) {
            bindTypeCoin((ViewHolderCoin) viewHolder, position);
        }
    }


    /**
     * banner轮播图
     *
     * @param holder
     * @param position
     */
    private void bindTypeBnner(final ViewHolderBanner holder, int position) {

    }


    public class ViewHolderBanner extends RecyclerView.ViewHolder {

        public ViewHolderBanner(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    /**
     * 增幅
     */
    private void bindTypeHot(final ViewHolderHot holder, final int position) {
        /**
         * 项目空投
         */
//        dropRecycle = holder.findViewById(R.id.drop_recycle);
//        mIndexProjectBean = mHomePageData.get(0).getData().getIndex_project();
        final LinearLayoutManager mdropLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.recycleHot.setLayoutManager(mdropLinearLayoutManager);
        holder.recycleHot.setHasFixedSize(true);
        final WrappableGridLayoutManager manager = new WrappableGridLayoutManager(mContext, 3);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);     //防止瀑布流图片闪烁
        holder.recycleHot.setLayoutManager(manager);
        HotAdapter mDropAdapter = new HotAdapter(mContext, onDropRecyclerViewItemClickListener);
        holder.recycleHot.setAdapter(mDropAdapter);

//        holder.tvtopMore.setOnClickListener(new View.OnClickListener() {   //项目空投更多
//            @Override
//            public void onClick(View v) {
//                onRecyclerViewItemClickListener.onRecyclerViewItemClicked(-2,holder);
//            }
//        });
    }


    //首页-项目空投的item的点击事件
    private OnRecyclerViewItemDeClickListener onDropRecyclerViewItemClickListener = new OnRecyclerViewItemDeClickListener() {

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





    public class ViewHolderHot extends RecyclerView.ViewHolder {
        @BindView(R.id.recycle_hot)
        RecyclerView recycleHot;

        public ViewHolderHot(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }





    /**
     * 币种列表
     */
    private void bindTypeCoin(final ViewHolderCoin holder, final int position) {

    }

    public class ViewHolderCoin extends RecyclerView.ViewHolder {


        public ViewHolderCoin(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else if (position == 1) {
            return TYPE_2;
        } else if (position == 2) {
            return TYPE_3;
        } else {
            return TYPE_MAIN;
        }

    }
}
