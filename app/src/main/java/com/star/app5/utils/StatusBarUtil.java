package com.star.app5.utils;
import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;
/**
 *
 * 状态栏工具类
 * Created by star on 2016/11/8.
 */
public class StatusBarUtil {

    public static final int DEFAULT_STATUS_BAR_ALPHA = 0;

    /**
     * 设置状态栏颜色
     *
     * @param activity 需要状态栏颜色的activity
     * @param color    状态栏颜色值
     */
    public static void setColor(Activity activity, int color) {
        setColor(activity, color, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity       需要设置的activity
     * @param color          状态栏颜色值
     * @param statusBarAlpha 状态栏透明度
     */
    public static void setColor(Activity activity, int color, int statusBarAlpha) {
        //操作系统的api版本大于21，才能改变状态栏的颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色
            activity.getWindow().setStatusBarColor(color);
        }
    }
}
