package com.star.app5.activity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

import com.star.app5.constant.Constant;
import com.star.app5.utils.StatusBarUtil;

/**
 * Created by thinkcool on 2015/11/20.
 */
public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, Color.parseColor("#ff0000"));
        init();
    }


    /**
     * 获取屏幕宽高
     *
     */
    private  void init(){
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constant.display.widthPixels = dm.widthPixels; // 屏幕宽（像素，如：480px）
        Constant.display.heightPixels = dm.heightPixels; // 屏幕高（像素，如：800px）
       /* float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        float scaledDensity=dm.scaledDensity;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;*/

    }
}
