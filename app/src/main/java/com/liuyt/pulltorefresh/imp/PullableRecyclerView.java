package com.liuyt.pulltorefresh.imp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.liuyt.pulltorefresh.R;

/**
 * Created by liuyt on 17-3-31.
 */

public class PullableRecyclerView extends PullToRefreshRecyclerView {
    boolean mRecyclerVisible;
    LoadingView mLoadingView;

    public PullableRecyclerView(Context context) {
        this(context, null);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        hiddenChilds();
        mLoadingView = new LoadingView(context);
        mLoadingView.setId(R.id.prv_loading);
        addView(mLoadingView, 0,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void registerAdapterDataObserver() {
        Log.d("LYT", "registerAdapterDataObserver");
//        getRefreshableView().getAdapter().registerAdapterDataObserver(mAdapterDataObserver);
    }

    /**
     * set RetryListener
     *
     * @param retryListener
     */
    public void setOnRetryListener(RetryView.OnRetryLoadListener retryListener) {
        if (null != mLoadingView) {
            mLoadingView.setOnRetryListener(retryListener);
        }
    }

    /**
     * set has reach end
     */
    public void setEnd(String msg) {
        showChilds();
        if (null != mLoadingView) {
            setWorkMode(MODE_PULL_UP_TO_REFRESH);
            setFooterEndLabel(msg);
        }
    }

    /**
     * set loading state
     */
    public void setLoading() {
        if (null != mLoadingView) {
            setWorkMode(MODE_BOTH_NONE);
            mLoadingView.setLoading();
        }
    }

    /**
     * set empty
     */
    public void setEmpty(String msg) {
        if (null != mLoadingView) {
            hiddenChilds();
            setWorkMode(MODE_BOTH_NONE);
            mLoadingView.setEmpty(msg);
        }
    }

    /**
     * set No Net
     */
    public void setNoNetWork() {
        if (null != mLoadingView) {
            hiddenChilds();
            setWorkMode(MODE_BOTH_NONE);
            mLoadingView.setNoNetwork();
        }
    }

    private void hiddenChilds() {
        mRecyclerVisible = false;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            if (v.getId() != R.id.prv_loading) {
                v.setVisibility(GONE);
            }
        }
    }

    public void showChilds() {
        mRecyclerVisible = true;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            if (v.getId() != R.id.prv_loading) {
                v.setVisibility(VISIBLE);
            } else {
                v.setVisibility(GONE);
            }
        }
        setWorkMode(MODE_PULL_UP_TO_REFRESH);
    }

}
