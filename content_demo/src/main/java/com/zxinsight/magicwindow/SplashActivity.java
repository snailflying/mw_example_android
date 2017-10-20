package com.zxinsight.magicwindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import cn.magicwindow.MLinkAPIFactory;
import cn.magicwindow.MWConfiguration;
import cn.magicwindow.MagicWindowSDK;
import cn.magicwindow.TrackAgent;
import cn.magicwindow.mlink.YYBCallback;

/**
 * Author：Aaron
 * Time：20/10/2017:2:35 PM
 */
public class SplashActivity extends Activity {

    private static String TAG = "SplashActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        //mw: 初始化魔窗
        initMw();
        //回流入口
        if (getIntent().getData() != null) {
            MLinkAPIFactory.createAPI(this).router(this, getIntent().getData());
            //跳转后结束当前activity
            finish();
        } else {
            MLinkAPIFactory.createAPI(this).checkYYB(this, new YYBCallback() {
                @Override
                public void onFailed(Context context) {
                    gotoHomeActivity();
                }

                @Override
                public void onSuccess() {
                    finish();
                }
            });
        }
    }

    private void initMw() {
        // TODO:初始化魔窗，此函数在第一个activity或者application类内调用。
        MWConfiguration config = new MWConfiguration(this);
        config.setLogEnable(true)
                .setSharePlatform(MWConfiguration.ORIGINAL);

        MagicWindowSDK.initSDK(config);
        MLinkAPIFactory.createAPI(this).registerWithAnnotation(this);
    }

    private void gotoHomeActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent event) {
        /* compiled code */
        if (event.getAction() == MotionEvent.ACTION_UP) {
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onPause() {
        TrackAgent.currentEvent().onPageEnd("引导页");
        super.onPause();
    }

    @Override
    public void onResume() {
        TrackAgent.currentEvent().onPageStart("引导页");

        super.onResume();
    }


}