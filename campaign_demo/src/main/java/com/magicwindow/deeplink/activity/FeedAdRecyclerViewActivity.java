package com.magicwindow.deeplink.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.adapter.FeedAdRecycleViewAdapter;
import com.magicwindow.deeplink.config.Constant;
import com.magicwindow.deeplink.domain.ListInfo;

import java.util.ArrayList;
import java.util.List;

import cn.magicwindow.AdManager;
import cn.magicwindow.advertisement.domain.FeedAdListener;
import cn.magicwindow.advertisement.domain.FeedAdViewPojo;

public class FeedAdRecyclerViewActivity extends Activity {
    public static final int INTERVAL = 6;
    private RecyclerView mRecyclerView;
    private List<Object> mList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedad_recycleview);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Specify a linear layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mList = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            mList.add(new ListInfo(i, "内容标题: " + i, "内容摘要: " + i));
        }

        final RecyclerView.Adapter mAdapter =
                new FeedAdRecycleViewAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);


        AdManager.requestFeedAdView(Constant.MW_AD, new FeedAdListener() {
            @Override
            public void success(List<FeedAdViewPojo> pojo) {
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(String error) {

            }
        });
    }


}
