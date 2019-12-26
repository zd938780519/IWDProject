package com.example.project.iwdproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.project.iwdproject.Fragment.FindFragment;
import com.example.project.iwdproject.Fragment.FindTwoFragment;
import com.example.project.iwdproject.Fragment.HomePageFragment;
import com.example.project.iwdproject.Fragment.LeaseFragment;
import com.example.project.iwdproject.Fragment.SelfFragment;
import com.example.project.iwdproject.Utils.EyesUtils;
import com.example.project.iwdproject.View.CustomViewPager;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;

    private String[] titles = {"首页", "租赁", "发现", "我的"};
    private MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        ButterKnife.bind(this);
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        initView();
    }


    private void initView() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainAdapter);
        viewPager.setOffscreenPageLimit(4);
        alphaIndicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(mainAdapter);

        alphaIndicator.getTabView(0).removeShow();
        alphaIndicator.getTabView(1).removeShow();
        alphaIndicator.getTabView(2).removeShow();
        alphaIndicator.getTabView(3).removeShow();

    }


    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {



        public List<Fragment> fragments = new ArrayList<>();
        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(HomePageFragment.newInstance(titles[0]));  //首页
            fragments.add(LeaseFragment.newInstance(titles[1]));   //租赁
            fragments.add(FindTwoFragment.newInstance(titles[2]));  //发现
//            fragments.add(FindFragment.newInstance(titles[2]));  //发现
            fragments.add(SelfFragment.newInstance(titles[3]));   //我的
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            if (0 == position) {
//                alphaIndicator.getCurrentItemView().removeShow();
//            } else if (1 == position) {
//                alphaIndicator.getCurrentItemView().removeShow();
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }




    private long timeMillis;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - timeMillis) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            timeMillis = System.currentTimeMillis();
        } else {
            BaseActivity.finishAllActivity();
            System.exit(0);
        }

    }



}
