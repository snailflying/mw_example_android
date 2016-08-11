package com.zxinsight.magicwindow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.zxinsight.MLink;
import com.zxinsight.MWConfiguration;
import com.zxinsight.MagicWindowSDK;
import com.zxinsight.magicwindow.base.BaseActivity;
import com.zxinsight.mlink.MLinkCallback;
import com.zxinsight.mlink.MLinkIntentBuilder;

import java.util.Map;

public class SplashActivity extends BaseActivity {

    private static String TAG = "SplashActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        initMW();
        if (getIntent().getData() != null) {
            MLink.getInstance(this).router(this, getIntent().getData());
            //跳转后结束当前activity
            finish();
        } else {
            //跳转到首页
            gotoHomeActivity();
        }
        //跳转入口 end
    }

    private void initMW() {
        MWConfiguration config = new MWConfiguration(this);
//设置渠道，非必须（渠道推荐在AndroidManifest.xml内填写）
        config.setDebugModel(true)
                //设置分享方式，如果之前有集成sharesdk，可在此开启
                .setSharePlatform(MWConfiguration.ORIGINAL);
        MagicWindowSDK.initSDK(config);

        //3.8新增注解方式，利用此方式，需要在各activity添加相应注解，比如
        // MainActivity的@MLinkDefaultRouter
        //SecondActivity的@MLinkRouter(keys = "second")，其中second为mLink的key
//        MLink.getInstance(this).registerWithAnnotation(this);
        register();
    }

    private static void register() {
        MagicWindowSDK.getMLink().registerDefault(new MLinkCallback() {
            @Override
            public void execute(Map<String, String> map, Uri uri, Context context) {
                MLinkIntentBuilder.buildIntent(context, MainActivity.class);

            }
        });
        MagicWindowSDK.getMLink().register("second", new MLinkCallback() {
            @Override
            public void execute(Map<String, String> map, Uri uri, Context context) {
                MLinkIntentBuilder.buildIntent(context, SecondActivity.class);
            }
        });
    }

    private void gotoHomeActivity() {
        //// TODO: 16/6/14
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

}