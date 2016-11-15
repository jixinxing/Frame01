package com.star.app5.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.star.app5.R;

/**
 * 导航栏 , 包括返回键,标题,右侧按钮.其中:
 * </br>返回键已经实现按键监听
 * </br>右侧按钮已实现按键监听
 */
public class TopBarView extends RelativeLayout
        implements OnClickListener {

    private ImageView backView;
    private ImageView rightView;
    private TextView titleView;

    private String titleTextStr;
    private Float titleTextSize;
    private int titleTextColor;

    private Drawable leftImage;
    private Drawable rightImage;

    public TopBarView(Context context) {
        this(context, null);
    }


    public TopBarView(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.AppTheme);


    }

    public TopBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getConfig(context, attrs);
        initView(context);
    }

    /**
     * 从xml中获取配置信息
     */
    private void getConfig(Context context, AttributeSet attrs) {
        //TypedArray是一个数组容器用于存放属性值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);

        int count = ta.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.Topbar_titleText:
                    titleTextStr = ta.getString(R.styleable.Topbar_titleText);
                    break;
                case R.styleable.Topbar_titleColor:
                    // 默认颜色设置为黑色
                    titleTextColor = ta.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.Topbar_titleSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    int tempSize = ta.getDimensionPixelSize(attr, 16);
                    float r = context.getResources().getDisplayMetrics().density;
                    titleTextSize = tempSize / r;
                    break;

                case R.styleable.Topbar_leftBtn:

                    leftImage = ta.getDrawable(R.styleable.Topbar_leftBtn);
                    break;
                case R.styleable.Topbar_rightBtn:
                    rightImage = ta.getDrawable(R.styleable.Topbar_rightBtn);
                    break;
            }
        }

        //用完务必回收容器
        ta.recycle();

    }


    private void initView(Context context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_top_bar,
                this, true);

        backView = (ImageView) layout.findViewById(R.id.back_image);
        titleView = (TextView) layout.findViewById(R.id.text_title);
        rightView = (ImageView) layout.findViewById(R.id.right_image);
        backView.setOnClickListener(this);
        rightView.setOnClickListener(this);

        if (null != leftImage)
            backView.setImageDrawable(leftImage);
        if (null != rightImage)
            rightView.setImageDrawable(rightImage);
        if (null != titleTextStr) {
            titleView.setText(titleTextStr);
            titleView.setTextSize(titleTextSize);
            titleView.setTextColor(titleTextColor);
        }
    }

    /**
     * 获取返回按钮
     *
     * @return
     */
    public ImageView getBackView() {
        return backView;
    }

    /**
     * 获取标题控件
     *
     * @return
     */
    public TextView getTitleView() {
        return titleView;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleView.setText(title);
    }

    /**
     * 获取右侧按钮,默认不显示
     *
     * @return
     */
    public ImageView getRightView() {
        return rightView;
    }

    private onTitleBarClickListener onMyClickListener;

    /**
     * 设置按钮点击监听接口
     *
     * @param listener
     */
    public void setClickListener(onTitleBarClickListener listener) {
        this.onMyClickListener = listener;
    }

    /**
     * 导航栏点击监听接口
     */
    public static interface onTitleBarClickListener {
        /**
         * 点击返回按钮回调
         */
        void onBackClick();

        void onRightClick();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back_image:
                if (null != onMyClickListener)
                    onMyClickListener.onBackClick();
                break;
            case R.id.right_image:
                if (null != onMyClickListener)
                    onMyClickListener.onRightClick();
                break;
        }
    }
}