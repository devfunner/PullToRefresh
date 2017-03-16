package com.liuyt.pulltorefresh;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17-3-3.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuVH>{
    private final static String TAG = "MenuAdapter";
    private List<Boolean> isClicks;
    private int mSelPos = 0;
//    private ArrayList<MenuInfo> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;

    public ViewPager mPager;

    private List<String> mFeedList;

    public MenuAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mFeedList = new ArrayList<String>();
        for (int i=0;i<0;i++){
            mFeedList.add("测试"+i);
        }
    }


    @Override
    public MenuVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.menu_item, parent, false);
        return new MenuVH(mContext, view);
    }

    @Override
    public void onBindViewHolder(MenuVH holder, int position) {
        holder.setMenu(mFeedList.get(position));
    }

    @Override
    public int getItemCount() {
        return mFeedList == null ? 0 : mFeedList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

}
