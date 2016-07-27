package com.example.jimmy.cornalarmclock.components;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jimmy.cornalarmclock.model.TabItem;

import java.util.ArrayList;

/**
 * Created by Jimmy on 16/7/22.
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {

    ArrayList<TabItem> tabs;
    BaseCornFragment fragment;

    public BaseFragmentAdapter(FragmentManager fm, ArrayList<TabItem> tabs) {
        super(fm);
        this.tabs = tabs;
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
