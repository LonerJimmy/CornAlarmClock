package com.example.jimmy.cornalarmclock.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Jimmy on 15/5/28.
 */
public class RecycleViewWithHeaderAndFooter extends RecyclerView {

    public static final int MODE_NORMAL = 100;
    public static final int MODE_HEADER = 101;
    public static final int MODE_FOOTER = 102;
    public static final int MODE_BOTH = 103;

    public static final int TYPE_HEADER = -1001;
    public static final int TYPE_NORMAL = -1002;
    public static final int TYPE_FOOTER = -1003;


    private View mHeaderView;
    private View mFooterView;
    private int mMode = MODE_NORMAL;

    public RecycleViewWithHeaderAndFooter(Context context) {
        super(context);
    }

    public RecycleViewWithHeaderAndFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecycleViewWithHeaderAndFooter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 根据mode返回count
     */
    public int getItemCount(int dataCount) {
        if (mMode == MODE_HEADER || mMode == MODE_FOOTER) {
            return dataCount + 1;
        } else if (mMode == MODE_BOTH) {
            return dataCount + 2;
        } else {
            return dataCount;
        }
    }

    /**
     * 如果有header就返回position-1
     */
    public int getItemPosition(int position) {
        if (mMode == MODE_HEADER || mMode == MODE_BOTH) {
            return position - 1;
        } else {
            return position;
        }
    }

    /**
     * 根据position判断item类型
     *
     * @param position
     * @return
     */
    public int getItemViewType(int position) {
        int type = TYPE_NORMAL;
        switch (mMode) {
            case MODE_HEADER:
                if (position == 0) {
                    type = TYPE_HEADER;
                }
                break;
            case MODE_FOOTER:
                if (position == getLayoutManager().getItemCount() - 1) {
                    type = TYPE_FOOTER;
                }
                break;
            case MODE_BOTH:
                if (position == 0) {
                    type = TYPE_HEADER;
                } else if (position == getLayoutManager().getItemCount() - 1) {
                    type = TYPE_FOOTER;
                }
                break;
            default:
                type = TYPE_NORMAL;
                break;
        }
        return type;
    }

    /**
     * 根据viewType返回对应的viewholder，如果返回为null，则为普通item
     *
     * @param viewType
     * @return
     */
    public RecyclerView.ViewHolder onCreateViewHolder(int viewType) {
        if (viewType == TYPE_HEADER && mHeaderView != null) {
            return new VHHeader(mHeaderView);
        } else if (viewType == TYPE_FOOTER && mFooterView != null) {
            return new VHFooter();
        }
        return null;
    }

    public void addHeaderView(View view) {
        mHeaderView = view;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(-1, -2);
        mHeaderView.setLayoutParams(lp);
        refreshMode();
    }

    public void addHeaderView(View view, LayoutParams layoutParams) {
        mHeaderView = view;
        mHeaderView.setLayoutParams(layoutParams);
        refreshMode();
    }

    public void removeHeaderView() {
        mHeaderView = null;
        refreshMode();
    }

    public void addFooterView(View view) {
        mFooterView = view;
        refreshMode();
    }

    public void removeFooterView() {
        mFooterView = null;
        refreshMode();
    }

    public boolean haveFooterView() {
        return mFooterView != null;
    }

    private void refreshMode() {
        if (mHeaderView != null && mFooterView == null) {
            mMode = MODE_HEADER;
        } else if (mHeaderView == null && mFooterView != null) {
            mMode = MODE_FOOTER;
        } else if (mHeaderView != null && mFooterView != null) {
            mMode = MODE_BOTH;
        } else {
            mMode = MODE_NORMAL;
        }
    }

    public class VHHeader extends ViewHolder {

        public VHHeader(View itemView) {
            super(itemView);
        }
    }

    public class VHFooter extends ViewHolder {

        public VHFooter() {
            super(mFooterView);
        }
    }
}
