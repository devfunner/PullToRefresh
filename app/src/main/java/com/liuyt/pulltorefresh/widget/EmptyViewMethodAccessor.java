package com.liuyt.pulltorefresh.widget;

import android.view.View;

/**
 * Created by user on 17-3-16.
 */
public interface EmptyViewMethodAccessor {
    /**
     * Calls upto AdapterView.setEmptyView()
     *
     * @param View to set as Empty View
     */
    public void setEmptyViewInternal(View emptyView);
        /**
         * Should call PullToRefreshBase.setEmptyView() which will then
         * automatically call through to setEmptyViewInternal()
         *
         * @param View to set as Empty View
         */
    public void setEmptyView(View emptyView);
}
