package com.star.app5.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;


import com.star.app5.R;
import com.star.app5.activity.left.MenuLeftFragment;
import com.star.app5.activity.right.MenuRightFragment;
import com.star.app5.widget.CustomDialog;

public class MainActivity extends FragmentActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout mainLayout;
    private LinearLayout leftLayout;
    private LinearLayout rightLayout;
    private int leftDrawerWidth;
    private int rightDrawerWidth;
    private float scrollWidth;

    private MainFragment mainFragment;
    private MenuLeftFragment menuLeftFragment;
    private MenuRightFragment menuRightFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainLayout= (LinearLayout) findViewById(R.id.main_layout);
        leftLayout= (LinearLayout) findViewById(R.id.left_drawer);
        rightLayout= (LinearLayout) findViewById(R.id.right_drawer);

        measureView(leftLayout);
        measureView(rightLayout);
        leftDrawerWidth=leftLayout.getMeasuredWidth();
        rightDrawerWidth=rightLayout.getMeasuredWidth();

        //drawerLayout.setDrawerLockMode();
        drawerLayout.setScrimColor(Color.TRANSPARENT);//去除遮罩的颜色
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(drawerView.getTag().equals("left")){
                    scrollWidth=slideOffset*leftDrawerWidth;
                    mainLayout.setScrollX((int)(-1*scrollWidth));
                }else{
                    scrollWidth=slideOffset*rightDrawerWidth;
                    mainLayout.setScrollX((int)(1*scrollWidth));
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        addFragment(mainFragment);
        addLeftFragment();
        addRightFragment();


    }

    /**
     * 初始化 fragmnet
     */
    private void initFragment(){
        mainFragment=new MainFragment();
        menuLeftFragment=new MenuLeftFragment();
        menuRightFragment=new MenuRightFragment();
    }


    /**
     * 中间 fragment切换
     * @param fragment
     */
    private void addFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_layout, fragment);
        transaction.commit();
    }

    /**
     * 左边抽屉
     */
    private void addLeftFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.left_drawer, menuLeftFragment);
        transaction.commit();
    }

    /**
     * 右边抽屉
     */
    private void addRightFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.right_drawer, menuRightFragment);
        transaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showLoginOutDialog();
        }
        return true;
    }
    /**
     * 退出登录的弹框提示
     */
    private void showLoginOutDialog() {
        new CustomDialog.Builder(this)
                .setTitle("提示")
                .setMessage("是否退出登录？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whitch) {
                        MainActivity.this.finish();
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whitch) {
                dialog.dismiss();
            }
        }).create().show();
    }


    /**
     * 左点击
     *
     * @param view
     */
    public void showLeftMenu(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    /**
     * 右点击
     *
     * @param view
     */
    public void showRightMenu(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    /**
     * 此方法可以多次被不同的View对象调用。
     * 在调用该方法后，
     * 使用View对象的getMessuredHeight()获高度（单位px）
     * @param child 需要测量高度和宽度的View对象，
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,

                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
}
