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
public class LoadingView extends LinearLayout implements RetryView.OnRetryLoadListener {
    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private RetryView.OnRetryLoadListener mRetryListener;

    private View loading_layout;
    private TextView mTextView;
    private ProgressBar progress;
    private RetryView view_retry;
    private Handler mMainHanlder;
    private TextView mTxtNoData;

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.load_view, this);
        loading_layout = this.findViewById(R.id.loading_layout);
        progress = (ProgressBar) this.findViewById(R.id.progress);
        view_retry = (RetryView) this.findViewById(R.id.view_retry);
        view_retry.setOnRetryLoadListener(this);
        mTextView = (TextView) this.findViewById(R.id.hint_text);
        mTxtNoData = (TextView) this.findViewById(R.id.txt_nodata);
        mMainHanlder = new Handler();
    }

    private void updateStyle() {
        getLayoutParams().height = LayoutParams.MATCH_PARENT;
        if (Build.VERSION.SDK_INT >= 16)
            setBackground(null);
        else
            setBackgroundDrawable(null);
    }

    public void setLoading() {
        mMainHanlder.post(new Runnable() {
            @Override
            public void run() {
                updateStyle();
                showView(LoadingView.this);
                showView(loading_layout);
                hideView(view_retry);
                hideView(mTxtNoData);
            }
        });
    }

    public void setNoNetwork() {
        mMainHanlder.post(new Runnable() {
            @Override
            public void run() {
                updateStyle();
                showView(LoadingView.this);
                hideView(loading_layout);
                hideView(mTxtNoData);
                showView(view_retry);
            }
        });
    }

    public void setEmpty(final String tip) {
        mMainHanlder.post(new Runnable() {
            @Override
            public void run() {
                updateStyle();
                showView(LoadingView.this);
                hideView(loading_layout);
                hideView(view_retry);
                showView(mTxtNoData);
                mTxtNoData.setText(tip);
            }
        });
    }

    public void setOnRetryListener(RetryView.OnRetryLoadListener retryListener) {
        mRetryListener = retryListener;
    }

    @Override
    public void OnRetryLoad(View vClicked) {
        showView(this);
        if (mRetryListener != null) {
            mRetryListener.OnRetryLoad(vClicked);
        }
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
}
