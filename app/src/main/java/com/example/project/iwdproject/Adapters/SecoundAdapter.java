package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Activitys.RentalAssetsActivity;
import com.example.project.iwdproject.Beans.MyBalanceListBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecoundAdapter extends RecyclerView.Adapter<SecoundAdapter.ViewHolder> {


    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<MyBalanceListBean.DataBean.ListBean> mMyBalanceListData;

    public SecoundAdapter(Context mContext, List<MyBalanceListBean.DataBean.ListBean> mMyBalanceListData, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mMyBalanceListData = mMyBalanceListData;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_secound, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final int flag = mMyBalanceListData.get(i).getFlag();

            viewHolder.llRentalassets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i==0){
                    Intent RentalAssetsIntent = new Intent(mContext, RentalAssetsActivity.class);
                        RentalAssetsIntent.putExtra("postion",i);
                        RentalAssetsIntent.putExtra("flag",flag);
                    mContext.startActivity(RentalAssetsIntent);
                    }else if (i==1){
                        Intent RentalAssetsIntent = new Intent(mContext, RentalAssetsActivity.class);
                        RentalAssetsIntent.putExtra("flag",flag);
                        RentalAssetsIntent.putExtra("postion",i);
                        mContext.startActivity(RentalAssetsIntent);
                    }else if (i==2){
                        Intent RentalAssetsIntent = new Intent(mContext, RentalAssetsActivity.class);
                        RentalAssetsIntent.putExtra("postion",i);
                        RentalAssetsIntent.putExtra("flag",flag);
                        mContext.startActivity(RentalAssetsIntent);

                    }else if (i==3){
                        Intent RentalAssetsIntent = new Intent(mContext, RentalAssetsActivity.class);
                        RentalAssetsIntent.putExtra("postion",i);
                        RentalAssetsIntent.putExtra("flag",flag);
                        mContext.startActivity(RentalAssetsIntent);

                    }
                }
            });

//

        viewHolder.tvCoinname.setText(mMyBalanceListData.get(i).getName());
        viewHolder.tvSecoundprice.setText("$"+mMyBalanceListData.get(i).getTotal());
        viewHolder.tvNum.setText(mMyBalanceListData.get(i).getNum()+"");
        viewHolder.tvNewprice.setText("$"+mMyBalanceListData.get(i).getPrice());
        viewHolder.tvZenfu.setText(mMyBalanceListData.get(i).getChange()+"%");
    }

    @Override
    public int getItemCount() {
        return mMyBalanceListData != null ? mMyBalanceListData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_coinname)
        TextView tvCoinname;
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
        @BindView(R.id.ll_rentalassets)
        LinearLayout llRentalassets;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
