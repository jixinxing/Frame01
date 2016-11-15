package com.star.app5.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.math.BigDecimal;

/**
 * 获取手机相关信息
 *
 * Created by summer.he on 2016/1/21.
 */
public class SystemInfoUtil {

    /**
     * 获取手机的品牌以及型号
     *
     * @return Xiaomi/MI 3
     */
    public static String getBankAndType(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String phoneBrand = android.os.Build.BRAND;//手机品牌Xiaomi
        String phoneType = android.os.Build.MODEL; // 手机型号MI
        LogUtil.i("aa", "手机型号：" + phoneType + "手机品牌：" + phoneBrand);
        return phoneBrand + "/" + phoneType;
    }

    /**
     * @param ctx 上下文
     * @return 尺寸 例如 5.0,3.5
     */
    public static double getScreenPhysicalSize(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        double f = diagonalPixels / (160 * dm.density);
        BigDecimal b = new BigDecimal(f);
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取用户手机号
     * @return
     */
    public static String getSDNumber(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
         return  tm.getLine1Number()==null?"":tm.getLine1Number();//获取本机号码
    }

    /**
     * 获取app的版本号
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return  versionName ;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 判断当前有没有网络连接
     *
     * @param context
     * @return
     */
    public static boolean checkNetWork(Context context) {
        //获得连接设备管理器
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null || !ni.isAvailable()) {
            return false;
        }
        return true;
    }

    /**
     * SD卡是否挂载
     *
     * @return
     */
    public static boolean mountedSdCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


}
