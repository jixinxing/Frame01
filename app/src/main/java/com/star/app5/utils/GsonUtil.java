package com.star.app5.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * gson操作
 * <p/>
 * Created by star on 2015/11/24.
 */
public class GsonUtil {

    public static GsonUtil obtain(){
        return  new GsonUtil();
    }

    /**
     * string转成JsonObject
     *
     * @param str
     * @return
     */
    public JsonObject toJsonObject(String str) {
        JsonParser jp = new JsonParser();
        JsonElement message = jp.parse(str);
        JsonObject jsObject = message.getAsJsonObject();
        return jsObject;
    }

}
