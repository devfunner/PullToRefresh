package com.liuyt.pulltorefresh;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.liuyt.pulltorefresh.imp.PullToRefreshRecyclerView;
import com.liuyt.pulltorefresh.widget.PullToRefreshBase;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    PullToRefreshRecyclerView recycler;
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recycler = (PullToRefreshRecyclerView) findViewById(R.id.prv);
        adapter = new MenuAdapter(this);
        recycler.getRefreshableView().setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recycler.getRefreshableView().setAdapter(adapter);
//        recycler.setRefreshing(true);//显示加载情况下的UI
        recycler.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG,"onRefresh() ");
            }

            @Override
            public void onLoadMore() {
                Log.d(TAG,"onLoadMore() ");
            }
        });
//        recycler.setRefreshing(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            recycler.onRefreshComplete();//完成加载
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
