package com.example.project.iwdproject.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.iwdproject.Adapters.CommonTabPagerAdapter;
import com.example.project.iwdproject.R;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.project.iwdproject.Fragment.HomePageFragment.FRAD_TITLE;

public class FindFragment extends BaseFragment implements CommonTabPagerAdapter.TabPagerListener{

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;
    private Context instance;
    private  CommonTabPagerAdapter adapter;

    public static FindFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        FindFragment fragment = new FindFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        instance = getActivity();
        adapter = new CommonTabPagerAdapter(getChildFragmentManager()
                , 2, Arrays.asList("推荐", "最新"), instance);
        adapter.setListener(this);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        TabLayoutUtil.reflex(tabLayout);   //设置下划线长度

    }

    @Override
    protected void loadData() {

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Override
    public Fragment getFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RecommendFragment().newInstance(position);//推荐
                break;
            case 1:
                fragment = new RecommendFragment().newInstance(position);//最新
//                fragment = new LatestFragment().newInstance(position);//最新
                break;


        }
        return fragment;

    }
}
