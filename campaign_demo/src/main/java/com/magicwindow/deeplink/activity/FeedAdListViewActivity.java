package com.magicwindow.deeplink.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.adapter.FeedAdListViewAdapter;
import com.magicwindow.deeplink.base.BaseActivity;
import com.magicwindow.deeplink.config.Constant;
import com.magicwindow.deeplink.domain.ListInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.magicwindow.AdManager;
import cn.magicwindow.advertisement.domain.FeedAdListener;
import cn.magicwindow.advertisement.domain.FeedAdViewPojo;

public class FeedAdListViewActivity extends BaseActivity {

    ListView mListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_feedad_listview);

        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);


        final FeedAdListViewAdapter listAdapter = new FeedAdListViewAdapter(this);
        mListView.setAdapter(listAdapter);

        ArrayList arrayList = new ArrayList<Object>();
        for (int i = 0; i <= 15; i++) {
            arrayList.add(new ListInfo(i, "内容标题: " + i, "内容摘要: " + i));
        }
        listAdapter.setData(arrayList);


        AdManager.requestFeedAdView(Constant.MW_AD,new FeedAdListener() {
            @Override
            public void success(List<FeedAdViewPojo> pojo) {
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(String error) {

            }
        });

    }

}
