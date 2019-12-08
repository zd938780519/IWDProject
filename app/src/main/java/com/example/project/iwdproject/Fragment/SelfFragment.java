package com.example.project.iwdproject.Fragment;

import android.os.Bundle;

import com.example.project.iwdproject.R;

import static com.example.project.iwdproject.Fragment.HomePageFragment.FRAD_TITLE;

public class SelfFragment extends BaseFragment{

    public static SelfFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        SelfFragment fragment = new SelfFragment();
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}
