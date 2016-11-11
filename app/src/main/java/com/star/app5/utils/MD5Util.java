package com.star.app5.utils;


import org.apaches.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * md5加密算法
 * <p/>
 * Created by star on 2015/11/14.
 */

public class MD5Util {

    public static String encode(String origin, String charsetname) {
        String resultString = null;
        resultString = new String(origin);
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (charsetname == null || "".equals(charsetname)) {
            resultString = Hex.encodeHexString(md.digest(resultString.getBytes()));
        } else {
            //encodeHexString(md.digest(resultString.getBytes(charsetname)));
            try {
                resultString = Hex.encodeHexString(md.digest(resultString.getBytes(charsetname)));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return resultString;
    }

    /**
     * 提供一个MD5多次加密方法
     */
    public static String getMD5ofStr(String origString, int times) {
        String md5 = encode(origString, "utf-8");
        for (int i = 0; i < times - 1; i++) {
            md5 = encode(md5, "utf-8");
        }
        return md5;
    }

}