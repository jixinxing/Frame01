package com.star.app5.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.star.app5.R;
import com.star.app5.widget.CustomToast;
import com.star.app5.widget.NetworkRequestDialog;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * xutils 网络封装
 *
 * Created by star on 2016/11/8.
 */
public class HttpClient {

    private Context context;
    private String url;
    private Map<String,String> paramsMap;
    private HttpCallBack callBack;
    private Dialog netDialog;

    public HttpClient(Context context,  String url, Map<String,String> paramsMap, HttpCallBack callBack) {
        this.context = context;
        this.url=url;
        this.paramsMap = paramsMap;
        this.callBack = callBack;
    }

    public static HttpClient obtain(Context context, String url,Map<String,String> paramsMap, HttpCallBack callBack){
        return new HttpClient( context, url,paramsMap, callBack);
    }


    /**
     * post请求
     */
    public void sendPost() {

        //判断网络
        if(isNetworkAvailable((Activity) context)==false){
            CustomToast.showToast(context,context.getString(R.string.no_net));
            return;
        }
        RequestParams requestParams=new RequestParams(url);
        String token=SharedPreferencesUtil.obtain(context).fetchToken();
        if(!token.equals("")){
            requestParams.addBodyParameter("token",token);
        }

        if(paramsMap!=null){
            for(Map.Entry<String, String> entry: paramsMap.entrySet()) {
                requestParams.addBodyParameter(entry.getKey(),entry.getValue());
            }
        }

        netDialog=NetworkRequestDialog.createDialog(context);

        //以下代码中展示了将RequestCallBack的回调方法，转化为咱们自定义的回调方法
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                netDialog.dismiss();
                JsonObject jsonObject=GsonUtil.obtain().toJsonObject(result);
                String message=jsonObject.get("message").getAsString();
                LogUtil.i("aa","message="+message);

                LogUtil.i("aa","result="+result);
                if (callBack != null){
                    callBack.onSuccess(result);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.i("aa","ex="+ex.getMessage());
                netDialog.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                netDialog.dismiss();
            }

            @Override
            public void onFinished() {
                netDialog.dismiss();
            }
        });
    }


    /**
     * get请求
     */
    public void sendGet() {
        //判断网络
        if(isNetworkAvailable((Activity) context)==false){
            CustomToast.showToast(context,context.getString(R.string.no_net));
            return;
        }

        RequestParams requestParams=new RequestParams(url);
        String token=SharedPreferencesUtil.obtain(context).fetchToken();
        if(!token.equals("")){
            requestParams.addBodyParameter("token",token);
        }

        if(paramsMap!=null){
            for(Map.Entry<String, String> entry: paramsMap.entrySet()) {
                requestParams.addBodyParameter(entry.getKey(),entry.getValue());
            }
        }
        netDialog=NetworkRequestDialog.createDialog(context);

        //以下代码中展示了将RequestCallBack的回调方法，转化为咱们自定义的回调方法
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                netDialog.dismiss();

                LogUtil.i("aa","result="+result);
                if (callBack != null){
                    callBack.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                netDialog.dismiss();
                LogUtil.i("aa","ex="+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                netDialog.dismiss();
            }

            @Override
            public void onFinished() {
                netDialog.dismiss();
            }
        });
    }

    /**
     * 检查当前网络是否可用
     *
     * @param activity
     * @return
     */

    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    LogUtil.i("aa","===状态===" + networkInfo[i].getState());
                    LogUtil.i("aa","===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }




    // 回调接口
    public interface HttpCallBack {
         void onSuccess(String result);
    }

}
