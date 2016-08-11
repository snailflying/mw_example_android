package com.zxinsight.magicwindow;

import android.os.Bundle;
import android.widget.Toast;

import com.zxinsight.MLink;
import com.zxinsight.magicwindow.base.BaseActivity;
import com.zxinsight.mlink.annotation.MLinkDefaultRouter;

//@MLinkDefaultRouter
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"mw channel = "+ MLink.getInstance(this).getLastChannelForMLink(),Toast.LENGTH_LONG).show();
    }
}
