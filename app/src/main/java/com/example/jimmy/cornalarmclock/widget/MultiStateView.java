package com.example.jimmy.cornalarmclock.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jimmy.cornalarmclock.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 一个提供多种状态切换显示的 layout 组件
 * email：sei_zjm2010@126.com
 * 修改自:https://github.com/Kennyc1012/MultiStateView
 */
public class MultiStateView extends FrameLayout {

    public static final int STATE_LOADING = 0;
    public static final int STATE_ERROR = 1;
    public static final int STATE_EMPTY = 2;
    public static final int STATE_CONTENT = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            STATE_LOADING,
            STATE_ERROR,
            STATE_EMPTY,
            STATE_CONTENT
    })
    public @interface State {
    }

    private View mContentView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    private LayoutInflater mInflater;

    @State
    private int mState = STATE_CONTENT;


    public MultiStateView(Context context) {
        this(context, null);
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MultiStateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mInflater = LayoutInflater.from(getContext());
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MultiStateView);
        // 加载中视图布局
        int loadingViewResId = a.getResourceId(R.styleable.MultiStateView_msv_loadingView, -1);
        if (loadingViewResId > -1) {
            mLoadingView = mInflater.inflate(loadingViewResId, this, false);
        }
        // 空视图布局
        int emptyViewResId = a.getResourceId(R.styleable.MultiStateView_msv_emptyView, -1);
        if (emptyViewResId > -1) {
            mEmptyView = mInflater.inflate(emptyViewResId, this, false);
        }
        // 错误视图布局
        int errorViewResId = a.getResourceId(R.styleable.MultiStateView_msv_errorView, -1);
        if (errorViewResId > -1) {
            mErrorView = mInflater.inflate(errorViewResId, this, false);
        }
        // 默认状态.由于mViewState变量使用了注解限制,所以不能直接赋值
        int viewState = a.getInt(R.styleable.MultiStateView_msv_viewState, STATE_CONTENT);
        switch (viewState) {
            case STATE_CONTENT:
                mState = STATE_CONTENT;
                break;
            case STATE_ERROR:
                mState = STATE_ERROR;
                break;
            case STATE_EMPTY:
                mState = STATE_EMPTY;
                break;
            case STATE_LOADING:
                mState = STATE_LOADING;
                break;
            default:
                // nothing to do
                break;
        }

        a.recycle();

        initDefaultViews();
    }

    /**
     * 初始化各个状态对应的默认视图
     */
    private void initDefaultViews() {
        if (mLoadingView == null) {
            mLoadingView = mInflater.inflate(R.layout.layout_loading_hint, this, false);
        }
        if (mEmptyView == null) {
            mEmptyView = mInflater.inflate(R.layout.layout_empty_hint, this, false);
        }
        if (mErrorView == null) {
            mErrorView = mInflater.inflate(R.layout.layout_error_hint, this, false);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (getChildCount() > 1) {
            throw new RuntimeException("can only have one child view");
        }

        mContentView = getChildAt(0);
        if (mContentView == null) {
            throw new RuntimeException("must be have one child view");
        }

        // 再依层次添加各个状态的视图
        addView(mEmptyView, 0);
        addView(mErrorView);
        addView(mLoadingView);

        setView();
    }

    @Nullable
    public View getView(@State int state) {
        switch (state) {
            case STATE_LOADING:
                return mLoadingView;
            case STATE_CONTENT:
                return mContentView;
            case STATE_EMPTY:
                return mEmptyView;
            case STATE_ERROR:
                return mErrorView;
            default:
                return null;
        }
    }

    /**
     * 切换显示的状态视图的逻辑
     */
    private void setView() {
        switch (mState) {
            case STATE_LOADING:
                mLoadingView.setVisibility(View.VISIBLE);
                mErrorView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.GONE);
                break;
            case STATE_EMPTY:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.VISIBLE);
                break;
            case STATE_CONTENT:
            default:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mEmptyView.setVisibility(GONE);
                break;
        }
    }


    ///////////////////////////////////////////////
    ////          以下是供外部操作的公有方法        ////
    ///////////////////////////////////////////////
    /**
     * 设置指定状态的视图
     * @param view
     * @param state
     * @param switchToState
     */
    public MultiStateView setViewForState(View view, @State int state, boolean switchToState) {
        switch (state) {
            case STATE_LOADING:
                if (mLoadingView != null) {
                    removeView(mLoadingView);
                }
                mLoadingView = view;
                addView(mLoadingView);
                break;

            case STATE_ERROR:
                if (mErrorView != null) {
                    removeView(mErrorView);
                }
                mErrorView = view;
                addView(mErrorView, indexOfChild(mLoadingView));
                break;

            case STATE_CONTENT:
                if (mContentView != null) {
                    removeView(mContentView);
                }
                mContentView = view;
                addView(mContentView, indexOfChild(mErrorView));
                break;

            case STATE_EMPTY:
                if (mEmptyView != null) {
                    removeView(mEmptyView);
                }
                mEmptyView = view;
                addView(mEmptyView, indexOfChild(mContentView));
                break;

            default:
                // nothing to do
                break;
        }
        setView();
        // 是否立即切换状态
        if (switchToState) {
            setState(state);
        }

        return this;
    }

    /**
     * 设置指定状态的视图
     * @param view
     * @param state
     */
    public MultiStateView setViewForState(View view, @State int state) {
        return setViewForState(view, state, false);
    }

    /**
     * 设置指定状态的视图
     * @param layoutRes
     * @param state
     * @param switchToState
     */
    public MultiStateView setViewForState(@LayoutRes int layoutRes, @State int state, boolean switchToState) {
        View view = mInflater.inflate(layoutRes, this, false);
        return setViewForState(view, state, switchToState);
    }

    /**
     * 设置指定状态的视图
     * @param layoutRes
     * @param state
     */
    public MultiStateView setViewForState(@LayoutRes int layoutRes, @State int state) {
        return setViewForState(layoutRes, state, false);
    }

    /**
     * 获取当前的状态
     * @return
     */
    @State
    public int getState() {
        return mState;
    }

    /**
     * 切换显示的状态
     * @param state
     * @return
     */
    public MultiStateView setState(@State int state) {
        if (state != mState) {
            mState = state;
            setView();
        }

        return this;
    }

    /**
     * 设置当前状态视图的图标
     * @param state
     * @param iconResId
     * @return
     */
    public MultiStateView setIcon(@State int state, @DrawableRes int iconResId) {
        View view = getView(state);
        if (view != null) {
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            if (icon != null) {
                icon.setImageResource(iconResId);
            } else {
                throw new IllegalArgumentException("view must have an id of named icon");
            }
        }

        return this;
    }

    /**
     * 设置当前状态视图的标题
     * @param titleResId
     * @return
     */
    public MultiStateView setTitle(@StringRes int titleResId) {
        return setTitle(getContext().getString(titleResId));
    }

    /**
     * 设置当前状态视图的标题
     * @param title
     * @return
     */
    public MultiStateView setTitle(String title) {
        View view = getView(mState);
        if (view != null) {
            TextView titleTxt = (TextView) view.findViewById(R.id.title);
            if (titleTxt != null) {
                titleTxt.setVisibility(View.VISIBLE);
                titleTxt.setText(title);
            } else {
                throw new IllegalArgumentException(view.getClass().getSimpleName() + " must have an id of named title");
            }
        }

        return this;
    }

}