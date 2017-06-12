package com.zxinsight.magicwindow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zxinsight.magicwindow.base.BaseActivity;

import cn.magicwindow.MLink;

//@MLinkDefaultRouter
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "mw channel = " + MLink.getInstance(this).getLastChannelForMLink(), Toast.LENGTH_LONG).show();

        //// TODO: 16/9/5 场景还原的API 可以写在首页,也可以写在进入首页的startActivity后面（切记,一定要在其后）
        MLink.getInstance(this).deferredRouter();
    }

    public void toSecond(View view) {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }
}
