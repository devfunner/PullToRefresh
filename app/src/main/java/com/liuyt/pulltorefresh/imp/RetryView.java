package com.liuyt.pulltorefresh.imp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liuyt.pulltorefresh.R;

/**
 * Created by liuyt on 17-3-16.
 */

public class RetryView extends FrameLayout {

    public OnRetryLoadListener onRetryLoadListener;
    private TextView mTitle;

    public interface OnRetryLoadListener {
        public void OnRetryLoad(View vClicked);
    }

    public RetryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public RetryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RetryView(Context context) {
        this(context, null, 0);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.no_network_view, this);
        this.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if( onRetryLoadListener != null) {
                    onRetryLoadListener.OnRetryLoad(v);
                }
            }
        });
    }

    public void setTitle(int res){
        if(mTitle != null){
            mTitle.setText(res);
        }
    }

    public void setOnRetryLoadListener(OnRetryLoadListener onRetryLoadListener) {
        this.onRetryLoadListener = onRetryLoadListener;
    }
}
