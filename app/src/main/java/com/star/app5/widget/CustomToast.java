package com.star.app5.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.star.app5.R;


/**
 * 自定义Toast
 *
 * Created by star on 2016/5/11.
 */
public class CustomToast {
    private static Toast toast;

    /**
     * 显示Toast
     * @param context
     * @param tvString
     */

    public static void  showToast(Context context, String tvString){

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;    //得到宽度
        int height = dm.heightPixels;  //得到高度

        View layout = LayoutInflater.from(context).inflate(R.layout.toast_custom,null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(tvString);
        text.setTextColor(Color.parseColor("#ffffff"));
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.TOP, 0, (height/4)*3);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        ((Activity)context).runOnUiThread(new Runnable() {
            public void run() {
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
            }
        });
    }
}
