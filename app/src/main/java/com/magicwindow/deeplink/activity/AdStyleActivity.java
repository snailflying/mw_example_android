package com.magicwindow.deeplink.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.base.BaseActivity;
import com.magicwindow.deeplink.config.Constant;
import com.magicwindow.deeplink.dialog.MWDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.magicwindow.AdManager;
import cn.magicwindow.MWAPI;
import cn.magicwindow.MWAPIFactory;
import cn.magicwindow.ad.domain.AdRenderListener;
import cn.magicwindow.ad.domain.RenderAd;

public class AdStyleActivity extends BaseActivity {

    private MWAPI mwapi;

    private RelativeLayout parentLayout;
    private ImageView imageView;
    private TextView title, desc;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // TODO Auto-generated method stub
        setContentView(com.magicwindow.deeplink.R.layout.activity_ad_style);

        mwapi = MWAPIFactory.createAPI(this);

        initView();
    }

    private void initView() {

        parentLayout = (RelativeLayout) findViewById(R.id.parent_layout);
        imageView = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        desc = (TextView) findViewById(R.id.desc);

        AdManager.display(this, Constant.MW_AD, new AdRenderListener() {
            @Override
            public void success(RenderAd ad) {

                //广告曝光统计
                AdManager.trackImpression(Constant.MW_AD);
                //展示
                ImageLoader.getInstance().displayImage(ad.imageUrl, imageView);
                title.setText(ad.title);
                desc.setText(ad.description);
            }

            @Override
            public void failed(String error) {
                //todo: title imageView等展示默认值
            }
        });

        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager.click(v, Constant.MW_AD);
            }
        });

    }

}
