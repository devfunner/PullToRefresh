package com.liuyt.pulltorefresh.imp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.liuyt.pulltorefresh.R;
import com.liuyt.pulltorefresh.widget.PullToRefreshBase;

/**
 * Created by liuyt on 17-3-16.
 */

public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView rv = new RecyclerView(context);
        rv.setId(R.id.recyclerview);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        rv.setLayoutParams(params);
        return rv;
    }

    @Override
    protected boolean isReadyForPullDown() {
        int position = getFirstVisibleItemPosition();
        View view = getRefreshableView().getLayoutManager().findViewByPosition(position);
        int count = getRefreshableView().getAdapter().getItemCount();
        if (0 == count)
        {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (null != view && view.getTop() == 0 && position == 0){
            // 滑到顶部了
            return true;
        } else
            return false;
    }

    @Override
    protected boolean isReadyForPullUp() {
        int lastPos = getLastVisibleItemPosition();
        View view = getRefreshableView().getLayoutManager().findViewByPosition(lastPos);
        int count = getRefreshableView().getAdapter().getItemCount();
        if (0 == count)
        {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (lastPos == (count - 1))
        {
            // 滑到底部了
            if (view != null && view.getBottom() <= getMeasuredHeight())
            {
                return true;
            }
        }
        return false;
    }

    public int getFirstVisibleItemPosition(){
        RecyclerView.LayoutManager lm = getRefreshableView().getLayoutManager();
        int firstVisibleItemPosition = 0;
        if (lm instanceof GridLayoutManager)
        {
            firstVisibleItemPosition = ((GridLayoutManager) lm).findFirstVisibleItemPosition();
        } else if (lm instanceof LinearLayoutManager)
        {
            firstVisibleItemPosition = ((LinearLayoutManager) lm).findFirstVisibleItemPosition();
        } else if (lm instanceof StaggeredGridLayoutManager)
        {
            int positions[] = ((StaggeredGridLayoutManager) lm).findFirstVisibleItemPositions(null);
            firstVisibleItemPosition = positions[0];
        }
        return firstVisibleItemPosition;
    }

    /**
     * 获取底部可见项的位置
     *
     * @return
     */
    public int getLastVisibleItemPosition()
    {
        RecyclerView.LayoutManager lm = getRefreshableView().getLayoutManager();
        int lastVisibleItemPosition = 0;
        if (lm instanceof GridLayoutManager)
        {
            lastVisibleItemPosition = ((GridLayoutManager) lm).findLastVisibleItemPosition();
        } else if (lm instanceof LinearLayoutManager)
        {
            lastVisibleItemPosition = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
        } else if (lm instanceof StaggeredGridLayoutManager)
        {
            int positions[] = ((StaggeredGridLayoutManager) lm).findLastVisibleItemPositions(null);
            lastVisibleItemPosition = positions[0];
        }
        return lastVisibleItemPosition;
    }

}
