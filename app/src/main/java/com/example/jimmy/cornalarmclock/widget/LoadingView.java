package com.example.jimmy.cornalarmclock.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.jimmy.cornalarmclock.R;

/**
 * author：cheikh.wang on 16/7/14 18:20
 * email：wanghonghi@126.com
 */
public class LoadingView extends FrameLayout {

    private ImageView mLoadingImg;
    private Animation mRotateAnimation;
    final int rotateAngle = 180;
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final int FLIP_ANIMATION_DURATION = 150;

    public LoadingView(Context context) {
        this(context, null, 0);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        mRotateAnimation = new RotateAnimation(0, rotateAngle, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        mRotateAnimation.setDuration(FLIP_ANIMATION_DURATION);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setFillAfter(true);

        mLoadingImg = new ImageView(context);
        mLoadingImg.setImageResource(R.drawable.ic_lifeindex_ultraviolet_rays);
        mLoadingImg.setLayoutParams(new LayoutParams(-2, -2));
        addView(mLoadingImg);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLoadingImg.startAnimation(mRotateAnimation);
    }
}
