package com.example.jimmy.cornalarmclock.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jimmy.cornalarmclock.R;
import com.example.jimmy.cornalarmclock.model.Title;

import butterknife.Bind;

/**
 * Created by yx on 16/4/3.
 */
@ContentView(R.layout.fragment_base)
public abstract class BaseCornFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.ll_base_content)
    protected LinearLayout llContent;
    @Bind(R.id.img_left)
    protected ImageView imgLeft;
    @Bind(R.id.img_right)
    protected ImageView imgRight;
    @Bind(R.id.ll_base_title)
    protected LinearLayout llTitle;
    @Bind(R.id.txt_title)
    protected TextView tvTitle;

    protected View content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addView();
    }

    protected void addView() {
        if (getContent() != 0) {
            content = LayoutInflater.from(getActivity()).inflate(getContent(), null);
            llContent.addView(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    protected abstract int getContent();

    protected abstract void clickLeft();

    protected abstract void clickRight();

    public void setTitle(Title title) {
        llTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title.title);
        if (title.leftView != 0) {
            imgLeft.setVisibility(View.VISIBLE);
            imgLeft.setImageResource(title.leftView);
        }
        if (title.rightView != 0) {
            imgRight.setVisibility(View.VISIBLE);
            imgRight.setImageResource(title.rightView);
        }
    }

    public void setListenr() {
        if (imgLeft != null) {
            imgLeft.setOnClickListener(this);
        }
        if (imgRight != null) {
            imgRight.setOnClickListener(this);
        }
    }

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
