package com.star.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 *
 * 下拉选择  -- 数组
 *
 * Created by star on 2016/10/21.
 */
public class SelectArrayDialog extends Dialog {
    private Context activity;
    private List<String> dataList;
    private ListView listview;
    private Button cancleBtn;
    private int height=0;

    private OnCancleOnclickListener onCancleOnclickListener;
    private OnContentOnclickListener onContentOnclickListener;

    public SelectArrayDialog(Context context) {
        super(context,R.style.SelectArrayDialogStyle);
        activity=context;

        //屏幕高度
        DisplayMetrics  metric = new DisplayMetrics();
        ((Activity)activity).getWindowManager().getDefaultDisplay().getMetrics(metric);
        height=metric.heightPixels;

    }

    /**
     * 数据
     * @param dataList
     */
    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    /**
     * 取消
     * @param onCancleOnclickListener
     */
    public void setOnCancleOnclickListener(OnCancleOnclickListener onCancleOnclickListener) {
        this.onCancleOnclickListener = onCancleOnclickListener;
    }

    /**
     * listview item点击事件
     * @param onContentOnclickListener
     */
    public void setOnContentOnclickListener(OnContentOnclickListener onContentOnclickListener) {
        this.onContentOnclickListener = onContentOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_array);
        listview = (ListView) findViewById(R.id.city_listview);
        cancleBtn = (Button) findViewById(R.id.cancle_btn);

        SelcetArrayListAdapter selcetArrayListAdapter=new SelcetArrayListAdapter(activity,dataList);
        listview.setAdapter(selcetArrayListAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(onContentOnclickListener!=null){
                    onContentOnclickListener.onContentClick(i);
                }
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onCancleOnclickListener!=null){
                    onCancleOnclickListener.onCancleClick();
                }
            }
        });

        //listview的高度
        int listviewHeight=setListViewHeightBasedOnChildren(listview);
        Window window = getWindow();
        //设置显示动画
        window.setWindowAnimations(R.style.dialog_in_out_animstyle);
        WindowManager.LayoutParams w = window.getAttributes();
        w.x = 0;
        w.y = height;
        // 以下这两句是为了保证按钮可以水平满屏
        w.width = ViewGroup.LayoutParams.MATCH_PARENT;
        w.height = listviewHeight+dip2px(activity,60);
        //点击外部dialog消失
        setCanceledOnTouchOutside(true);
    }

    //动态设置listview的高度
    public  int  setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {//如果adapter的数据为空也应该返回。 建议listAdapter.getCount() == 0
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int listHeight=totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        if(listHeight>= height/3){
            params.height=height/3;
        }else{
            params.height = listHeight;
        }

        listView.setLayoutParams(params);
        return params.height;
    }


    /**
     * dip转px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }




    /**
     * 设置取消被点击的接口   和内容被选择点击
     */
    public interface OnContentOnclickListener {
        public void onContentClick(int i);
    }

    public interface OnCancleOnclickListener {
        public void onCancleClick();
    }

}
