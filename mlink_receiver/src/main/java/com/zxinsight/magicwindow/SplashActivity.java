package com.zxinsight.magicwindow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.zxinsight.magicwindow.base.BaseActivity;

import java.util.Map;

import cn.magicwindow.MLinkAPIFactory;
import cn.magicwindow.MWConfiguration;
import cn.magicwindow.MagicWindowSDK;
import cn.magicwindow.mlink.MLinkCallback;
import cn.magicwindow.mlink.MLinkIntentBuilder;

public class SplashActivity extends BaseActivity {

    private static String TAG = "SplashActivity";

    private static void register(Context context) {
        MLinkAPIFactory.createAPI(context).registerDefault(new MLinkCallback() {
            @Override
            public void execute(Map<String, String> map, Uri uri, Context context) {
                MLinkIntentBuilder.buildIntent(context, MainActivity.class);

            }
        });
        MLinkAPIFactory.createAPI(context).register("second", new MLinkCallback() {
            @Override
            public void execute(Map<String, String> map, Uri uri, Context context) {
                MLinkIntentBuilder.buildIntent(context, SecondActivity.class);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        initMW();
        if (getIntent().getData() != null) {
            MLinkAPIFactory.createAPI(this).router(this, getIntent().getData());
            //跳转后结束当前activity
            finish();
        } else {
            //todo: 应用宝跳转有两个方式,方式①
            MLinkAPIFactory.createAPI(this).checkYYB();
            gotoHomeActivity();

            //todo: 应用宝跳转有两个方式,方式②
//            MLinkAPIFactory.createAPI(this).checkYYB(this, new YYBCallback() {
//                @Override
//                public void onFailed(Context context) {
//                    gotoHomeActivity();
//                }
//            });
        }
        //跳转入口 end
    }

    private void initMW() {
        MWConfiguration config = new MWConfiguration(this);
        config.setLogEnable(true)
                //设置分享方式，如果之前有集成sharesdk，可在此设置为MWConfiguration.SHARE_SDK
                .setSharePlatform(MWConfiguration.ORIGINAL);
        MagicWindowSDK.initSDK(config);

        //3.8新增注解方式，利用此方式，需要在各activity添加相应注解，比如
        // MainActivity的@MLinkDefaultRouter
        //SecondActivity的@MLinkRouter(keys = "second")，其中second为mLink的key
//        MLinkAPIFactory.createAPI(this).registerWithAnnotation(this);
        register(this);
    }

    private void gotoHomeActivity() {
        //// TODO: 16/6/14
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

}