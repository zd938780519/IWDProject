package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Beans.FirstHomePageBean;
import com.example.project.iwdproject.Beans.MarketBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.MainActivity;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.Utils.WrappableGridLayoutManager;
import com.example.project.iwdproject.View.NoticeView;
import com.example.project.iwdproject.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int TYPE_1 = 0xff01;
    public final int TYPE_2 = 0xff02;
    public final int TYPE_3 = 0xff03;
    public final int TYPE_4 = 0xff04;
    public final int TYPE_MAIN = 0xffff;


    private Context mContext;
    private OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener;
    private FirstHomePageBean.DataBean mFirstHomeData;
    private List<FirstHomePageBean.DataBean.BannerBean> mBannerList = new ArrayList<>();
    private List<FirstHomePageBean.DataBean.NoticeBean> mNoticeList = new ArrayList<>();
    private List<MarketBean.DataBean> mMarketData = new ArrayList<>();

    public HomePageAdapter(Context mContext,FirstHomePageBean.DataBean mFirstHomeData, List<MarketBean.DataBean> mMarketData,OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener) {
        this.mContext = mContext;
        this.mFirstHomeData = mFirstHomeData;
        this.mMarketData = mMarketData;
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

        List<String> mImageList = new ArrayList<>();
        mBannerList = mFirstHomeData.getBanner();  //轮播图列表
        mNoticeList = mFirstHomeData.getNotice();

        for (int i=0 ;i< mBannerList.size(); i++){
            mImageList.add(mBannerList.get(i).getImg());
        }
        if (mImageList.size()!=0){
            holder.banner.setViewUrls(mImageList);
        }


        // 首先，模拟一个公告的集合。需要字符串泛型的list
        final List<String> list = new ArrayList<>();
        for (int i=0 ;i< mNoticeList.size(); i++){
            list.add(mNoticeList.get(i).getContent());
        }
//        list.add("推荐歌曲：Eyelis - 絆にのせて");
//        list.add("挺好听的，我听了快100遍了");
//        list.add("好想回宿舍打游戏=。=");
        holder.netText.setNoticeList(list);

        holder.netText.start();

        // 这里就是监听点击事件，TextView是点中的那个公告，position是位置。
        // 比如点击之后想该条公告边灰色，就可以view.setTextColor();实现了
        holder.netText.setOnItemClickListener(new NoticeView.OnItemClickListener() {
            @Override
            public void onItemClick(TextView view, int position) {
                String s = list.get(position);
//                view.setTextColor(Color.WHITE);
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
            }
        });


    }





    public class ViewHolderBanner extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        BannerLayout banner;
        @BindView(R.id.net_text)
        NoticeView netText;

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
        HotAdapter mDropAdapter = new HotAdapter(mContext,mMarketData, onDropRecyclerViewItemClickListener);
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
        final LinearLayoutManager mHotLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recycleCoin.setLayoutManager(mHotLinearLayoutManager);
        holder.recycleCoin.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));   //添加分割线
//        holder.mRecycleHot.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        holder.recycleCoin.setHasFixedSize(true);
        holder.recycleCoin.setFocusableInTouchMode(false);//不需要焦点

        CoinAdapter mCoinAdapter = new CoinAdapter(mContext,mMarketData, mRecyclerViewItemClickListener);
        holder.recycleCoin.setAdapter(mCoinAdapter);

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


    public class ViewHolderCoin extends RecyclerView.ViewHolder {

        @BindView(R.id.recycle_coin)
        RecyclerView recycleCoin;

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
