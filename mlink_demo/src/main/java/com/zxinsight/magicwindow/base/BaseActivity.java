package com.zxinsight.magicwindow.base;

import android.app.Activity;

import cn.magicwindow.Session;

/**
 * User: Aaron.Liu
 * Date: 15/7/6
 * Time: 17:46
 */
public class BaseActivity extends Activity {


    @Override
    protected void onPause() {
        //TODO：必加
        super.onPause();
        Session.onPause(this);
    }

    @Override
    protected void onResume() {
        //TODO：必加
        super.onResume();
        Session.onResume(this);
    }
}
