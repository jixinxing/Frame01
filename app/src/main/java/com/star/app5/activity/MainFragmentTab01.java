package com.star.app5.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.app5.R;
import com.star.app5.activity.base.BaseFragment;

public class MainFragmentTab01 extends BaseFragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return  inflater.inflate(R.layout.fragment_main_tab_01, container, false);
	}

	@Override
	protected void onFragmentVisibleChange(boolean isVisible) {
		super.onFragmentVisibleChange(isVisible);
		if(isVisible){

		}else{

		}
	}
}
