package com.magicwindow.deeplink.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.base.BaseActivity;
import com.magicwindow.deeplink.config.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.magicwindow.MWAPI;
import cn.magicwindow.MWAPIFactory;
import cn.magicwindow.MarketingHelper;

public class CustomStyleActivity extends BaseActivity {

    private MWAPI marketingHelper;

    private Context mContext;

    private RelativeLayout topLayout5;
    private TextView topTitle5;
    private ImageView topImg5;

    private RelativeLayout customLayout1;
    private ImageView customImg1;
    private TextView customTitle1;
    private TextView customDescription1;

    private RelativeLayout parent;
    private ImageView customImg2;
    private TextView customTitle2;
    private TextView customDescription2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // TODO Auto-generated method stub
        mContext = this;
        setContentView(R.layout.activity_custom_style);

        marketingHelper = MWAPIFactory.createAPI(this);

        initView();

    }

    private void initView() {

        // TODO: 自定义魔窗位方式1
        topLayout5 = (RelativeLayout) findViewById(R.id.top_5_layout);
        topTitle5 = (TextView) findViewById(R.id.top_5);
        topImg5 = (ImageView) findViewById(R.id.ic_top_5);
        if (marketingHelper.isActive(Constant.MW_CUSTOM_STYLE01)) {
            // 获取标题
            topTitle5.setText(marketingHelper.getTitle(Constant.MW_CUSTOM_STYLE01));
            // 获取背景图
            ImageLoader.getInstance().displayImage(
                    marketingHelper.getImageURL(Constant.MW_CUSTOM_STYLE01), topImg5);
            topLayout5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到相应的webView
                    marketingHelper.click(mContext, Constant.MW_CUSTOM_STYLE01);
                }
            });
        }
        // end

        // end



    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
