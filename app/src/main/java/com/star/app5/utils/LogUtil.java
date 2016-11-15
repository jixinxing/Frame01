package com.star.app5.utils;

import android.util.Log;

/**
 * log
 */
public class LogUtil {
	public static int LOWEST_LOG_LEVEL =0;// 0--全部显示  7--全部隐藏，最低日志显示级别
	private static int SYSTEM = 1;
	private static int VERBOS = 2;
	private static int DEBUG = 3;
	private static int INFO = 4;
	private static int WARN = 5;
	private static int ERROR = 6;

	public static void i(String tag, String message) {
		if(message==null){
			return;
		}
		if (LOWEST_LOG_LEVEL <= INFO) {//
			Log.i(tag, message);
		}
	}

	public static void e(String tag, String message) {
		if(message==null){
			return;
		}
		if (LOWEST_LOG_LEVEL <= ERROR) {
			Log.e(tag, message);
		}
	}

	public static void d(String tag, String message) {
		if(message==null){
			return;
		}
		if (LOWEST_LOG_LEVEL <= DEBUG) {
			Log.d(tag, message);
		}
	}

	public static void w(String tag, String message) {
		if(message==null){
			return;
		}
		if (LOWEST_LOG_LEVEL <= WARN) {
			Log.w(tag, message);
		}
	}

	public static void v(String tag, String message) {
		if(message==null){
			return;
		}
		if (LOWEST_LOG_LEVEL <= VERBOS) {
			Log.v(tag, message);
		}
	}

	public static void s(String message) {
		if(message==null){
			return;
		}
		if (LOWEST_LOG_LEVEL <= SYSTEM) {
			System.out.println(message);
		}
	}

}
