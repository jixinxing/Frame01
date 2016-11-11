package com.star.app5.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.star.app5.R;


/**
 * 网络请求数据时的加载Dialog
 *
 * Created by star on 2015/11/11.
 *
 */
public class NetworkRequestDialog {
	
	/**
	 * http请求加载Dialgog
	 *
	 * @param context
	 * @return
	 */
	public static Dialog createDialog(Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		// 得到加载view
		View v = inflater.inflate(R.layout.dialog_network_request, null);
		// 加载布局
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
		// 进度条的image
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		// 提示文字
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
		// 加载动画
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.anim.dialog_network_request_animation);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		// 设置加载信息
		tipTextView.setText("正在加载");
		// 创建自定义样式dialog
		Dialog dialog = new Dialog(context, R.style.network_request_dialog_style);
		// 不可以用“返回键”取消
		dialog.setCancelable(false);
		// 设置布局
		dialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		return dialog;

	}


}
