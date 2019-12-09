package com.example.project.iwdproject.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.iwdproject.Adapters.HomePageAdapter;
import com.example.project.iwdproject.Adapters.LeaseAdapter;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.project.iwdproject.Fragment.HomePageFragment.FRAD_TITLE;

public class LeaseFragment extends BaseFragment {

    @BindView(R.id.recycle_lease)
    RecyclerView recycleLease;
    Unbinder unbinder;
    private Context instance;

    public static LeaseFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        LeaseFragment fragment = new LeaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lease;
    }

    @Override
    protected void initView() {
        instance  = getActivity();
        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        recycleLease.setLayoutManager(mTwoLinearLayoutManager);
        recycleLease.setNestedScrollingEnabled(false);
//        recyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        recycleLease.setHasFixedSize(true);
        LeaseAdapter mLeaseAdapter = new LeaseAdapter(instance,  onRecyclerViewItemClickListener);
        recycleLease.setAdapter(mLeaseAdapter);

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
    protected void loadData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
