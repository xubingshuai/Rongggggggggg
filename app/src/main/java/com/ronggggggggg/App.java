package com.ronggggggggg;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2017/12/29.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
//        RongIMClient.init(this);
    }
}
