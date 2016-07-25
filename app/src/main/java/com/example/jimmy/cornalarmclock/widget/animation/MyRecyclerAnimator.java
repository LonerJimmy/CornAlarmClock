package com.example.jimmy.cornalarmclock.widget.animation;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.LinearInterpolator;

import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;

/**
 * Created by panwankun on 16/6/30.
 */
public class MyRecyclerAnimator extends OvershootInRightAnimator {

    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, -(float) holder.itemView.getRootView().getWidth());
    }

    @Override
    protected void animateAddImpl(RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView).translationX(0.0F).setDuration(this.getAddDuration()).setInterpolator(new LinearInterpolator()).setListener(new DefaultAddVpaListener(holder)).start();
    }
}
