package com.star.app5.activity.left;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.star.app5.R;


public class MenuLeftFragment extends Fragment
{
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_menu_left, container, false);
		}
		return mView;
	}
}
