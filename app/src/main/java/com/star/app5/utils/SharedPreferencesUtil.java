package com.star.app5.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;
import android.view.WindowManager;

import com.star.app5.constant.Constant;


/**
 * 存储token的sharePreferences
 * <p/>
 * Created by star on 2015/11/24.
 */
public class SharedPreferencesUtil {

    public Context context;

    public SharedPreferencesUtil(Context context) {
        super();
        this.context = context;
    }

    public static SharedPreferencesUtil obtain(Context context){
        return  new SharedPreferencesUtil(context);
    }


    /**
     * 从SharedPreferences获取token的值
     */
    public String fetchToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SHAREDPREFERENCES, Activity.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        return token;
    }


    /**
     * 返回到的token存储到SharedPreferences
     * 存储前先清空
     *
     * @param token
     */
    public void storageToken(String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SHAREDPREFERENCES, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.putString("token", token);
        edit.commit();
    }

}
