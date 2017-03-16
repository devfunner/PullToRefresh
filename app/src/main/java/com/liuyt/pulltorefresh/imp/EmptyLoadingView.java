package com.liuyt.pulltorefresh.imp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liuyt.pulltorefresh.R;

/**
 * Created by liuyt on 17-3-16.
 */
public class EmptyLoadingView extends LinearLayout implements RetryView.OnRetryLoadListener {
    public EmptyLoadingView(Context context) {
        super(context);
        init(context);
    }

    public EmptyLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmptyLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EmptyLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private RetryView.OnRetryLoadListener mRetryListener;

    private View loading_layout;
    private TextView mTextView;
    private ProgressBar progress;
    private RetryView   view_retry;
    private Handler     mMainHanlder;
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.load_view, this);
        loading_layout = this.findViewById(R.id.loading_layout);
        progress = (ProgressBar) this.findViewById(R.id.progress);
        view_retry = (RetryView)this.findViewById(R.id.view_retry);
        view_retry.setOnRetryLoadListener(this);
        mTextView = (TextView) this.findViewById(R.id.hint_text);
        mMainHanlder = new Handler();
    }

    private void updateStyle(boolean hasData) {
        if (hasData) {
            getLayoutParams().height = LayoutParams.WRAP_CONTENT;
        } else {
            getLayoutParams().height = LayoutParams.MATCH_PARENT;
            if(Build.VERSION.SDK_INT >= 16)
                setBackground(null);
            else
                setBackgroundDrawable(null);
        }
    }

    public void startLoading(final boolean hasData) {
        mMainHanlder.post(new Runnable() {
            @Override
            public void run() {
                updateStyle(hasData);
                EmptyLoadingView.this.setVisibility(VISIBLE);
                loading_layout.setVisibility(VISIBLE);
                //progress.setIndeterminate(true);
                view_retry.setVisibility(GONE);
            }
        });
    }

    public void stopLoading(final boolean hasData, final boolean hasNext) {
        mMainHanlder.post(new Runnable() {
            @Override
            public void run() {
                if (!hasNext) {
                    updateStyle(hasData);
                    if (hasData) {
                        hideView(loading_layout);
                        //progress.setIndeterminate(false);
                        view_retry.setVisibility(GONE);
                        EmptyLoadingView.this.setVisibility(GONE);
                    } else {
                        EmptyLoadingView.this.setVisibility(VISIBLE);
                        view_retry.setVisibility(VISIBLE);
                        //progress.setIndeterminate(false);
                        hideView(loading_layout);
                    }
                }
            }
        });
    }

    private void showView(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() == GONE) {
            view.setVisibility(VISIBLE);
        }
    }

    private void hideView(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() == VISIBLE) {
            view.setVisibility(GONE);
        }

    }

    public void init(boolean hasData, boolean isLoading) {
        updateStyle(hasData);
        if (isLoading) {
            loading_layout.setVisibility(VISIBLE);
            //progress.setIndeterminate(true);
            view_retry.setVisibility(GONE);
        } else {

            if (hasData) {
                loading_layout.setVisibility(GONE);
                //progress.setIndeterminate(false);
                view_retry.setVisibility(VISIBLE);
            } else {
                //progress.setIndeterminate(true);
                loading_layout.setVisibility(VISIBLE);
                view_retry.setVisibility(GONE);
            }
        }
    }

    public void setOnRetryListener(RetryView.OnRetryLoadListener retryListener){
        mRetryListener = retryListener;
    }

    @Override
    public void OnRetryLoad(View vClicked) {
        //progress.setIndeterminate(true);
        showView(this);
        if(mRetryListener != null){
            mRetryListener.OnRetryLoad(vClicked);
        }
    }

}
