package com.example.jimmy.cornalarmclock.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.model.Title;
import com.example.jimmy.cornalarmclock.util.StatusBarUtil;
import com.example.jimmy.cornalarmclock.widget.MultiStateView;

import butterknife.Bind;

/**
 * Created by Jimmy on 16/7/26.
 */
@ContentView(R.layout.layout_list)
public abstract class BaseListActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.layout_top)
    protected LinearLayout llTop;
    @Bind(R.id.img_right)
    protected ImageView imgRight;
    @Bind(R.id.img_left)
    protected ImageView imgLeft;
    @Bind(R.id.txt_title)
    protected TextView txtTitle;
    @Bind(R.id.layout_bottom)
    protected LinearLayout llBottom;
    @Bind(R.id.content_container)
    protected MultiStateView multiContainer;
    @Bind(R.id.rl_list)
    protected RelativeLayout relativeLayout;

    protected View content;
    public StatusBarUtil barUtil = StatusBarUtil.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        //设置导航栏透明
        barUtil.setStatusBarTranslucent(relativeLayout, this);

        multiContainer.setState(MultiStateView.STATE_LOADING);

        addView();
    }

    private void addView() {
        if (getContent() != 0) {
            content = LayoutInflater.from(this).inflate(getContent(), null);
            multiContainer.addView(content);
        }
    }

    public void setTitle(Title title) {
        llTop.setVisibility(View.VISIBLE);
        txtTitle.setText(title.title);
        if (title.leftView != 0) {
            imgLeft.setVisibility(View.VISIBLE);
            imgLeft.setImageResource(title.leftView);
        }
        if (title.rightView != 0) {
            imgRight.setVisibility(View.VISIBLE);
            imgRight.setImageResource(title.rightView);
        }
    }

    public void setLlBottom(int a) {
        llBottom.setVisibility(View.VISIBLE);
        View cc = LayoutInflater.from(this).inflate(a, null);
        llBottom.addView(cc);
    }

    public void setListener() {
        if (imgLeft != null) {
            imgLeft.setOnClickListener(this);
        }
        if (imgRight != null) {
            imgLeft.setOnClickListener(this);
        }
    }

    protected abstract int getContent();

    public abstract void clickLeft();

    public abstract void clickRight();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                clickLeft();
                break;
            case R.id.img_right:
                clickRight();
                break;
            default:
                break;
        }
    }
}
