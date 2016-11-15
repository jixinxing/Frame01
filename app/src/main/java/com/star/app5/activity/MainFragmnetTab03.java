package com.star.app5.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.app5.R;
import com.star.app5.activity.base.BaseFragment;

public class MainFragmnetTab03 extends BaseFragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.fragment_main_tab_03, container, false);
		return newsLayout;
	}

	@Override
	protected void onFragmentVisibleChange(boolean isVisible) {
		super.onFragmentVisibleChange(isVisible);
		if(isVisible){

		}else{

		}
	}
}
