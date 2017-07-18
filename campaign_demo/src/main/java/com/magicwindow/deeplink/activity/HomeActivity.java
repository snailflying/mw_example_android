package com.magicwindow.deeplink.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cn.magicwindow.TrackAgent;
import cn.magicwindow.analytics.domain.UserProfile;

//import android.app.FragmentTransaction;

public class HomeActivity extends ListActivity {


//    public static final String[] options = {"普通样式", "自定义魔窗样式", "广告样式", "信息流广告ListView", "信息流广告RecycleView", "自定义事件"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Options.getAll()));
    }

    // 登录的时候，可以将用户的相关信息传给魔窗。
    private void login() {
        // 用户唯一标识，比如登录名
        UserProfile user = new UserProfile("aaron123");
        // 手机号
        user.setTelephone("13555555555");
        // 邮件地址
        user.setEmail("zhangsan@126.com");
        // 生日的时间戳。可以是10位或者13位
        user.setBirthday("1441115494");
        // 国家
        user.setCountry("China");
        // 省份
        user.setProvince("Shanghai");
        // 城市
        user.setCity("Shanghai");
        // 用户名，比如微信名
        user.setDisplayName("Aaron");
        // 性别：1为男性，2为女侠，0为未知
        user.setGender("1");
        // 用户评级，
        user.setRemark("0");

        TrackAgent.currentEvent().setUserProfile(user);

        // 或者只把用户的userid传给我们（手机号与邮箱或者登录名都可以作为userid）
        // TrackAgent.currentEvent().setUserProfile("userid");
        TrackAgent.currentEvent().setUserProfile("13585859554");
    }

    // 登录的时候，可以将用户的相关信息传给魔窗。
    private void logOut() {
        // 退出的时候调用下面这个接口。告诉后台用户已经退出。
        TrackAgent.currentEvent().cancelUserProfile();
        ;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent;

        switch (Options.get(position)) {
            default:
            case NARMAL:
                intent = new Intent(this, NormalStyleActivity.class);
                break;
            case CUSTOM:
                intent = new Intent(this, CustomStyleActivity.class);
                break;
            case AD:
                intent = new Intent(this, AdStyleActivity.class);
                break;
            case AD_LIST:
                intent = new Intent(this, FeedAdListViewActivity.class);
                break;
            case AD_RECYCLE:
                intent = new Intent(this, FeedAdRecyclerViewActivity.class);
                break;
            case TRACK:
                intent = new Intent(this, CustomEventActivity.class);
                break;

        }

        startActivity(intent);
    }

    private enum Options {
        NARMAL("普通魔窗", 0), CUSTOM("自定义魔窗", 1), AD("广告", 2), AD_LIST("信息流广告ListView", 3), AD_RECYCLE("信息流广告RecycleView", 4), TRACK("事件统计", 5);
        private String name;
        private int index;

        Options(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public static ArrayList<String> getAll() {
            ArrayList<String> list = new ArrayList<>();
            list.add(NARMAL.name);
            list.add(CUSTOM.name);
            list.add(AD.name);
            list.add(AD_LIST.name);
            list.add(AD_RECYCLE.name);
            list.add(TRACK.name);
            return list;
        }

        public static Options get(int index) {
            for (Options c : Options.values()) {
                if (c.getIndex() == index) {
                    return c;
                }
            }
            return null;
        }

        public int getIndex() {
            return index;
        }

    }
}
