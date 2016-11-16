package com.star.app5.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.star.app5.R;
import com.star.app5.activity.base.BaseFragment;
import com.star.app5.adapter.MainFragmentTab01Adapter;
import com.star.app5.widget.CustomToast;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentTab01 extends BaseFragment {
	private View  rootView;
	private LRecyclerView recyclerView;

	private List<String> dataList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView=inflater.inflate(R.layout.fragment_main_tab_01, container, false);

		recyclerView= (LRecyclerView) rootView.findViewById(R.id.id_recyclerview);


		initData();


		return  rootView;
	}


	private void  initData(){
		dataList=new ArrayList<>();
		for (int i = 'A'; i < 'z'; i++) {
			dataList.add("" + (char) i);
		}

//		//第二个参数表示水平布局，第三个参数表示是否反转
//		//recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));
//		recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//		final MainFragmentTab01Adapter mainFragmentTab01Adapter=new MainFragmentTab01Adapter(this.getActivity(),dataList);
//		mainFragmentTab01Adapter.setOnItemClickListener(new MainFragmentTab01Adapter.MyViewHolder.MyItemClickListener() {
//			@Override
//			public void onItemClick(View view, int postion) {
//				CustomToast.showToast(MainFragmentTab01.this.getActivity(),postion+"");
//				if(mainFragmentTab01Adapter!=null){
//					String i=dataList.get(postion);
//					dataList.set(postion,i+":new");
//					mainFragmentTab01Adapter.setDataList(dataList);
//					//单行刷新
//					mainFragmentTab01Adapter.notifyItemChanged(postion);
//
//				}
//			}
//		});
//		recyclerView.setAdapter(mainFragmentTab01Adapter);


		MainFragmentTab01Adapter mainFragmentTab01Adapter=new MainFragmentTab01Adapter(this.getActivity(),dataList);

		LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(mainFragmentTab01Adapter);
		recyclerView.setAdapter(lRecyclerViewAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

		recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
		recyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);

		recyclerView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {

			}
		});

		recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore() {

			}
		});

		//recyclerView

	}





	@Override
	protected void onFragmentVisibleChange(boolean isVisible) {
		super.onFragmentVisibleChange(isVisible);
		if(isVisible){

		}else{

		}
	}
}
