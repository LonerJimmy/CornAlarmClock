package com.example.jimmy.cornalarmclock.components;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Jimmy on 16/7/25.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private View view;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);

        this.view = itemView;
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    protected View getView() {
        return view;
    }

    protected AppCompatActivity getActivity() {
        return (AppCompatActivity) getContext();
    }

    protected Context getContext() {
        return context;
    }

    protected String getString(int res) {
        return context.getString(res);
    }
}
