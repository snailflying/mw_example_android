package com.zxinsight.magicwindow;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.zxinsight.magicwindow.base.BaseActivity;

//@MLinkRouter(keys = "second")//second为mLink的key
public class SecondActivity extends BaseActivity {

    TextView id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        id = (TextView) findViewById(R.id.id);
        name = (TextView) findViewById(R.id.name);

        showDynamicParam();
    }

    //如果此页面需要动态参数，则需要在魔窗后台mLink服务配置内配置相应动态参数。
    //如：mwdemo://secondPage?name1=:name1
    private void showDynamicParam() {
        Intent intent = getIntent();
        if (intent != null) {
            if (!TextUtils.isEmpty(intent.getStringExtra("id"))) {
                id.setText(intent.getStringExtra("id"));
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("name"))) {
                name.setText(intent.getStringExtra("name"));
            }

        }
    }
}
