package com.star.app5.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;


import java.util.List;

/**
 * Created by star on 2016/11/1.
 */

/**
 * 定义适配器
 *
 * @author gxwu@lewatek.com 2012-11-15
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String>   titleList;

    public MainViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList, List<String> titleList){
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    /**
     * 每个页面的title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }


}
