package com.star.app5.constant;

import android.app.Application;
import android.util.DisplayMetrics;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by star on 2016/11/8.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

    }




}
