package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class TwoTeanAdapter extends RecyclerView.Adapter<TwoTeanAdapter.ViewHolder> {



    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<TeamBean.DataBean.ListBeanX.ListBean> mListBean;

    public TwoTeanAdapter(Context mContext, List<TeamBean.DataBean.ListBeanX.ListBean> mListBean, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mListBean = mListBean;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_twoteam, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String email= mListBean.get(i).getEmail();
        String phone = mListBean.get(i).getPhone();
        if (!email.equals("")){
            viewHolder.tvAcctese.setText(email);
        }else {
            viewHolder.tvAcctese.setText(phone);
        }
//        viewHolder.peopleNum.setText(mListBean.get(i).);
//        viewHolder.tvLeavel.setText(mListBean.get(i).);

    }

    @Override
    public int getItemCount() {
        //        return 3;
        return mListBean != null ? mListBean.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_acctese)
        TextView tvAcctese;
//        @BindView(R.id.tv_leavel)
//        TextView tvLeavel;
//        @BindView(R.id.people_num)
//        TextView peopleNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
