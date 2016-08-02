package com.example.jimmy.cornalarmclock.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseActivity;
import com.example.jimmy.cornalarmclock.components.BaseFragmentAdapter;
import com.example.jimmy.cornalarmclock.components.ContentView;
import com.example.jimmy.cornalarmclock.model.TabItem;
import com.example.jimmy.cornalarmclock.service.WakeServiceOne;
import com.example.jimmy.cornalarmclock.ui.alarm.AlarmFragment;
import com.example.jimmy.cornalarmclock.ui.more.MoreFragment;
import com.example.jimmy.cornalarmclock.ui.note.NoteFragment;
import com.example.jimmy.cornalarmclock.ui.weather.WeatherFragment;
import com.example.jimmy.cornalarmclock.widget.TabLayout;

import java.util.ArrayList;

import butterknife.Bind;

@ContentView(R.layout.activity_main)
public class HomeActivity extends BaseActivity implements TabLayout.OnTabClickListener {

    @Bind(R.id.tab_layout)
    protected TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    protected ViewPager mViewPager;
    @Bind(R.id.rl_activity_home)
    protected RelativeLayout relativeLayout;

    ArrayList<TabItem> tabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置导航栏透明
        barUtil.setStatusBarTranslucent(relativeLayout, this);
        initData();

        startService(new Intent(this, WakeServiceOne.class));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initData() {

        tabs = new ArrayList<>();
        tabs.add(new TabItem(R.drawable.select_tab_clock, R.string.title_alarm, AlarmFragment.class));
        tabs.add(new TabItem(R.drawable.selelct_tab_weather, R.string.title_weather, WeatherFragment.class));
        tabs.add(new TabItem(R.drawable.select_tab_time, R.string.title_note, NoteFragment.class));
        tabs.add(new TabItem(R.drawable.select_tab_more, R.string.title_more, MoreFragment.class));

        mTabLayout.initData(tabs, this);
        mTabLayout.setCurrentTab(0);

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), tabs);
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

}
