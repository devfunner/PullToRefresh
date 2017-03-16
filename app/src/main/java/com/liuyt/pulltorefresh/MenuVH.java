package com.liuyt.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MenuVH extends RecyclerView.ViewHolder{

    public TextView mTitleView;
    private RelativeLayout mJump;
    private Context mContext;

    public MenuVH(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mTitleView = (TextView) itemView.findViewById(R.id.content);
        mJump = (RelativeLayout) itemView.findViewById(R.id.rl_click);
    }

    public void setMenu(String info) {
        if (info != null) {
            mTitleView.setText(info);
        }
    }

    public void onRecycled() {

    }

}
