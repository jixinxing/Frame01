package com.star.app5.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.app5.R;
import com.star.app5.activity.base.BaseFragment;

public class MainFragmentTab02 extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener{

	private SwipeRefreshLayout swipeRefreshLayout;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootvView = inflater.inflate(R.layout.fragment_main_tab_02, container, false);

		swipeRefreshLayout= (SwipeRefreshLayout) rootvView.findViewById(R.id.swipe_content);
		swipeRefreshLayout.setOnRefreshListener(this);
		return rootvView;
	}
	@Override
	protected void onFragmentVisibleChange(boolean isVisible) {
		super.onFragmentVisibleChange(isVisible);
		if(isVisible){

		}else{

		}
	}

	@Override
	public void onRefresh() {

	}
}
