package com.star.app5.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.app5.R;
import com.star.app5.activity.base.BaseFragment;

public class MainFragmentTab02 extends BaseFragment
{

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.fragment_main_tab_02, container, false);
		return messageLayout;
	}
	@Override
	protected void onFragmentVisibleChange(boolean isVisible) {
		super.onFragmentVisibleChange(isVisible);
		if(isVisible){

		}else{

		}
	}
}
