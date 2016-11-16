package com.star.app5.activity;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.star.app5.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.star.app5.adapter.MainViewPagerAdapter;
import com.star.app5.constant.URL;
import com.star.app5.utils.HttpClient;
import com.star.app5.utils.LogUtil;
import com.star.app5.widget.CustomToast;
import com.star.app5.widget.CustomViewPager;

import org.xutils.http.RequestParams;

/**
 * Created by star on 2016/11/7.
 */
public class MainFragment extends Fragment {
    private Activity activity;
    private CustomViewPager mViewPager;
    private TabLayout tabLayout;
    private MainViewPagerAdapter adapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    /**
     * 页面title list
     **/
    private List<String> titleList = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main,null);
        initViewPager(view);

        request();

        CustomToast.showToast(this.getActivity(),"MainFragment");

        return view;
    }

    private void initViewPager(View view) {
        mViewPager = (CustomViewPager)view. findViewById(R.id.id_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_FindFragment_title);

        MainFragmentTab01 tab01 = new MainFragmentTab01();
        MainFragmentTab02 tab02 = new MainFragmentTab02();
        MainFragmnetTab03 tab03 = new MainFragmnetTab03();
        MainFragmentTab01 tab04 = new MainFragmentTab01();
        MainFragmentTab01 tab05 = new MainFragmentTab01();
        MainFragmentTab01 tab06 = new MainFragmentTab01();
        MainFragmentTab01 tab07 = new MainFragmentTab01();
        MainFragmentTab01 tab08 = new MainFragmentTab01();
        MainFragmentTab01 tab09 = new MainFragmentTab01();
        fragmentList.add(tab01);
        fragmentList.add(tab02);
        fragmentList.add(tab03);
        fragmentList.add(tab04);
        fragmentList.add(tab05);
        fragmentList.add(tab06);
        fragmentList.add(tab07);
        fragmentList.add(tab08);
        fragmentList.add(tab09);


        titleList.add("标题1111");
        titleList.add("标题22");
        titleList.add("标题3333");
        titleList.add("标题44");
        titleList.add("标题55");
        titleList.add("标题66");
        titleList.add("标题77");
        titleList.add("标题88");
        titleList.add("标题99");

        mViewPager.setScrollble(false);

        /**
         * 初始化Adapter
         */
        adapter = new MainViewPagerAdapter(getFragmentManager(),fragmentList, titleList);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void request(){
        Map<String ,String> map=new HashMap<>();
        map.put("type","shunfeng");
        map.put("postid","102290224058");

        HttpClient.obtain(MainFragment.this.getActivity(), URL.SEARCH_URL,map, new HttpClient.HttpCallBack() {
            @Override
            public void onSuccess(String result) {
            }
        }).sendPost();
    }


}
