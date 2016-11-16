package com.star.app5.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.star.app5.R;
import com.star.app5.activity.base.BaseFragment;
import com.star.app5.adapter.MainFragmentTab01Adapter;
import com.star.app5.utils.LogUtil;
import com.star.app5.widget.CustomToast;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentTab01 extends BaseFragment {
    private View rootView;
    private LRecyclerView recyclerView;

    private List<String> dataList;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_tab_01, container, false);

        recyclerView = (LRecyclerView) rootView.findViewById(R.id.id_recyclerview);

        activity = MainFragmentTab01.this.getActivity();
        initData();


        return rootView;
    }


    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            dataList.add("" + (char) i);
        }

        //第二个参数表示水平布局，第三个参数表示是否反转
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));
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


        final MainFragmentTab01Adapter mainFragmentTab01Adapter = new MainFragmentTab01Adapter(this.getActivity(), dataList);//自己的adapter,作为LRecyclerViewAdapter的参数
        final LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(mainFragmentTab01Adapter);//系统的
        recyclerView.setAdapter(lRecyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));//排列方向
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);//设置下拉刷新Progress的样式
        recyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);  //设置下拉刷新箭头
        recyclerView.setPullRefreshEnabled(true);//开启下拉刷新 默认是开启的




        //下拉刷新
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(recyclerView,LoadingFooter.State.Normal);
                recyclerView.refreshComplete();
				lRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        //上拉加载
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(recyclerView);
                if (state == LoadingFooter.State.Loading) {
                    LogUtil.i("aa", "the state is Loading, just wait..");
                    return;
                }


                // loading more
                RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, 10, LoadingFooter.State.Loading, null);

            }
        });


        //item 点击事件
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CustomToast.showToast(activity, position + "");
            }
        });

        recyclerView.setRefreshing(true);

    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {

        } else {

        }
    }
}
