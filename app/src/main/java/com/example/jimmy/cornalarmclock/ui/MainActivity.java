package com.example.jimmy.cornalarmclock.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseActivity;
import com.example.jimmy.cornalarmclock.components.BaseFragment;
import com.example.jimmy.cornalarmclock.components.ContentView;
import com.example.jimmy.cornalarmclock.model.TabItem;
import com.example.jimmy.cornalarmclock.ui.fragment.ContactsFragment;
import com.example.jimmy.cornalarmclock.ui.fragment.DiscoverFragment;
import com.example.jimmy.cornalarmclock.ui.fragment.ProfileFragment;
import com.example.jimmy.cornalarmclock.ui.fragment.WechatFragment;
import com.example.jimmy.cornalarmclock.widget.TabLayout;

import java.util.ArrayList;

import butterknife.Bind;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements TabLayout.OnTabClickListener {

    @Bind(R.id.tab_layout)
    protected TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    protected ViewPager mViewPager;

    ArrayList<TabItem> tabs;
    BaseFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

        tabs = new ArrayList<>();
        tabs.add(new TabItem(R.drawable.select_tab_clock, R.string.title_alarm, WechatFragment.class));
        tabs.add(new TabItem(R.drawable.selelct_tab_weather, R.string.title_weather, ContactsFragment.class));
        tabs.add(new TabItem(R.drawable.select_tab_time, R.string.title_time, DiscoverFragment.class));
        tabs.add(new TabItem(R.drawable.select_tab_more, R.string.title_more, ProfileFragment.class));

        mTabLayout.initData(tabs, this);
        mTabLayout.setCurrentTab(0);

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onTabClick(TabItem tabItem) {
        mViewPager.setCurrentItem(tabs.indexOf(tabItem));
    }


    public class FragAdapter extends FragmentPagerAdapter {


        public FragAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            try {
                return tabs.get(arg0).tagFragmentClz.newInstance();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return tabs.size();
        }

    }
}
