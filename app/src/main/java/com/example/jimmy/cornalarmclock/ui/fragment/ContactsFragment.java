package com.example.jimmy.cornalarmclock.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.components.BaseFragment;

/**
 * Created by yx on 16/4/3.
 */
public class ContactsFragment extends BaseFragment implements ITabClickListener {
    @Override
    public void fetchData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_layout, container, false);
        return view;
    }


    @Override
    public void onMenuItemClick() {

    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
