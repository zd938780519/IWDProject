package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.TeamBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {



    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<TeamBean.DataBean.ListBeanX> mListBeanX;

    public TeamAdapter(Context mContext, List<TeamBean.DataBean.ListBeanX> mListBeanX, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mListBeanX = mListBeanX;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_team, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String email= mListBeanX.get(i).getEmail();
        String phone = mListBeanX.get(i).getPhone();
        if (!email.equals("")){
            viewHolder.tvAcctese.setText(email);
        }else {
            viewHolder.tvAcctese.setText(phone);
        }
//        viewHolder.tvLeavel.setText(mListBeanX.get(i).);
        viewHolder.peopleNum.setText(mListBeanX.get(i).getTotal_num()+"");

        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        viewHolder.recycleTwoteam.setLayoutManager(mTwoLinearLayoutManager);
        viewHolder.recycleTwoteam.setNestedScrollingEnabled(false);
        viewHolder.recycleTwoteam.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        viewHolder.recycleTwoteam.setHasFixedSize(true);


        TwoTeanAdapter mTwoTeanAdapter = new TwoTeanAdapter(mContext, mListBeanX.get(i).getList(), onRecyclerViewItemClickListener);
        viewHolder.recycleTwoteam.setAdapter(mTwoTeanAdapter);

    }




    private OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener = new OnRecyclerViewItemDeClickListener() {

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




    @Override
    public int getItemCount() {
//        return 3;
        return mListBeanX != null ? mListBeanX.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_acctese)
        TextView tvAcctese;
//        @BindView(R.id.tv_leavel)
//        TextView tvLeavel;
        @BindView(R.id.people_num)
        TextView peopleNum;
        @BindView(R.id.recycle_twoteam)
        RecyclerView recycleTwoteam;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
