package com.zxinsight.magicwindow;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.zxinsight.magicwindow.base.BaseActivity;
import cn.magicwindow.mlink.annotation.MLinkRouter;

//@MLinkRouter(keys = "second")//second为mLink的key
public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        showDynamicParam();
    }

    //如果此页面需要动态参数，则需要在魔窗后台mLink服务配置内配置相应动态参数。
    //如：mwdemo://secondPage?name1=:name1
    private void showDynamicParam() {
        Intent intent = getIntent();
        if (intent != null) {
            if (!TextUtils.isEmpty(intent.getStringExtra("name"))) {
                String name = "动态参数为：" + intent.getStringExtra("name");
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
