package com.star.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.star.dialog.wheelview.OnWheelScrollListener;
import com.star.dialog.wheelview.WheelView;
import com.star.dialog.wheelview.adapter.NumericWheelAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 *
 * 日期选择器
 *
 * Created by star on 2016/10/24.
 */
public class DatePickerDialog extends Dialog {
    private static  final  int INIT_DATE=1950;//起始年份
    private static  final  int ADD_DATE_NUM=1000;//年份跨度  当前年+1000
    private TextView dateTV;
    private Context context;
    private WheelView yearWV;//年
    private WheelView monthWV;//月
    private WheelView dayWV;//日

    private int curYear;//当前的年
    private int curMonth;//当前的月
    private int curDay;//当前的日
    private int selectYear;//选择的年
    private int selectMonth;//选择的月
    private int selectDay;//选择的日

    private TextView cancleTV;//取消tv
    private TextView selectDateTV;//显示选择的日期的tv
    private TextView sureTV;//确定tv

    private Boolean flag;//true--不限制  false--限制不能选择当前日期之后
    private int numFlag=0;//1--年  2--年月  3--年月日
    private int height=0;

    //取消
    public OnCancleOnClick onCancleOnClick;
    //确定
    public OnOkOnClick onOkOnClick;
    //滚动
    public OnScroller onScroller;



    public DatePickerDialog(Context context,TextView dateTV,Boolean flag,int numFlag) {
        super(context,R.style.SelectArrayDialogStyle);
        this.context=context;
        this.dateTV=dateTV;
        this.flag=flag;
        this.numFlag=numFlag;
        //屏幕高度
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        height=metric.heightPixels;

    }



    public void setOnCancleOnClick(OnCancleOnClick onCancleOnClick) {
        this.onCancleOnClick = onCancleOnClick;
    }


    public void setOnOkOnClick(OnOkOnClick onOkOnClick) {
        this.onOkOnClick = onOkOnClick;
    }

    public void setOnScroller(OnScroller onScroller) {
        this.onScroller = onScroller;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_date);
        //得到当前的年月日
        Calendar c = Calendar.getInstance();
        curYear = c.get(Calendar.YEAR);//年
        curMonth = c.get(Calendar.MONTH) + 1;//月
        curDay = c.get(Calendar.DATE);//日

        LinearLayout datePickerLayout = (LinearLayout) findViewById(R.id.llayout);
        cancleTV = (TextView) findViewById(R.id.cancle_tv);
        selectDateTV = (TextView) findViewById(R.id.select_date_tv);
        sureTV = (TextView) findViewById(R.id.sure_tv);

        datePickerLayout.addView(getDataPick());

        if (!"".equals(dateTV.getText().toString())) {
            String date[] = dateTV.getText().toString().split("-");

            //只有年
            if(numFlag==1){
                yearWV.setCurrentItem(Integer.parseInt(date[0]) - INIT_DATE);
            }
            //只有年 -月
            if(numFlag==2){
                yearWV.setCurrentItem(Integer.parseInt(date[0]) - INIT_DATE);
                monthWV.setCurrentItem(Integer.parseInt(date[1]) - 1);
            }

            if(numFlag==3){
                yearWV.setCurrentItem(Integer.parseInt(date[0]) - INIT_DATE);
                monthWV.setCurrentItem(Integer.parseInt(date[1]) - 1);
                dayWV.setCurrentItem(Integer.parseInt(date[2]) - 1);
            }
        } else {
            String dateStr="";
            //只有年
            if(numFlag==1){
                yearWV.setCurrentItem(curYear - INIT_DATE);
                dateStr = (yearWV.getCurrentItem() + INIT_DATE)+"" ;
            }
            //只有年 -月
            if(numFlag==2){
                yearWV.setCurrentItem(curYear - INIT_DATE);
                monthWV.setCurrentItem(curMonth - 1);
                dateStr = (yearWV.getCurrentItem() + INIT_DATE) + "-" + (monthWV.getCurrentItem() + 1) ;
            }

            if(numFlag==3){
                yearWV.setCurrentItem(curYear - INIT_DATE);
                monthWV.setCurrentItem(curMonth - 1);
                dayWV.setCurrentItem(curDay - 1);
                dateStr = (yearWV.getCurrentItem() + INIT_DATE) + "-" + (monthWV.getCurrentItem() + 1) + "-" + (dayWV.getCurrentItem() + 1);
            }
            selectDateTV.setText(getDateSimpleFormt(dateStr));
        }


        cancleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onCancleOnClick!=null){
                    onCancleOnClick.cancleOnClick();
                }
            }
        });
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateStr=selectDateTV.getText().toString().trim();
                if(onOkOnClick!=null){
                    onOkOnClick.okOnClick(dateStr);
                }

            }
        });

        Window window = getWindow();
        //设置显示动画
        window.setWindowAnimations(R.style.dialog_in_out_animstyle);
        WindowManager.LayoutParams w = window.getAttributes();
        w.x = 0;
        w.y = height;
        // 以下这两句是为了保证按钮可以水平满屏
        w.width = ViewGroup.LayoutParams.MATCH_PARENT;
        w.height =  ViewGroup.LayoutParams.WRAP_CONTENT;
        //点击外部dialog消失
        setCanceledOnTouchOutside(true);
    }

    /**
     * 得到时间滚动的view
     *
     * @return
     */
    private View getDataPick() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.wheel_date_picker, null);
        //年
        yearWV = (WheelView) view.findViewById(R.id.year);
        NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(context, INIT_DATE, curYear + ADD_DATE_NUM);
        numericWheelAdapter1.setLabel("年");
        yearWV.setViewAdapter(numericWheelAdapter1);
        yearWV.setCyclic(true);//是否可循环滑动
        yearWV.addScrollingListener(scrollListener);
        //月
        monthWV = (WheelView) view.findViewById(R.id.month);
        //两位数
        NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(context, 1, 12, "%02d");
        numericWheelAdapter2.setLabel("月");
        monthWV.setViewAdapter(numericWheelAdapter2);
        monthWV.setCyclic(true);
        monthWV.addScrollingListener(scrollListener);
        //根据年和月判断日
        dayWV = (WheelView) view.findViewById(R.id.day);
        initDay(curYear, curMonth);
        dayWV.setCyclic(true);
        dayWV.addScrollingListener(scrollListener);

        yearWV.setVisibleItems(7);//设置显示行数
        monthWV.setVisibleItems(7);
        dayWV.setVisibleItems(7);

        yearWV.setCurrentItem(curYear - 1950);
        monthWV.setCurrentItem(curMonth - 1);
        dayWV.setCurrentItem(curDay - 1);

        //只有年
        if(numFlag==1){
            monthWV.setVisibility(View.GONE);
            dayWV.setVisibility(View.GONE);
        }

        //只有年-月
        if(numFlag==2){
            dayWV.setVisibility(View.GONE);
        }


        return view;
    }



    //天数需要计算
    private void initDay(int year, int month) {
        //两位数
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, 1, getDay(year, month), "%02d");
        numericWheelAdapter.setLabel("日");
        dayWV.setViewAdapter(numericWheelAdapter);
        dayWV.setCurrentItem(dayWV.getCurrentItem());
    }

    /**
     * 根据年月得到当前月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public  static int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        //闰年的判断
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    /**
     * 滚动事件
     */
    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            if(onScroller!=null){
                onScroller.wheelScroller();
            }else{
                //年
                if(numFlag==1){
                    selectYear = yearWV.getCurrentItem() + INIT_DATE;//年
                }

                //年 --月
                if(numFlag==2){
                    selectYear = yearWV.getCurrentItem() + INIT_DATE;//年
                    selectMonth = monthWV.getCurrentItem() + 1;//月
                }

                //年-月-日
                if(numFlag==3){
                    selectYear = yearWV.getCurrentItem() + INIT_DATE;//年
                    selectMonth = monthWV.getCurrentItem() + 1;//月
                    initDay(selectYear, selectMonth);
                    selectDay = dayWV.getCurrentItem() + 1;//日
                }

                //不能大于当前日期
                if(flag==false){
                    if(numFlag==1){
                        //选择的年大于当前的年
                        if (selectYear > curYear) {
                            yearWV.setCurrentItem(curYear - INIT_DATE);
                            selectYear = curYear;
                        }
                    }
                    if(numFlag==2){
                        //选择的年大于当前的年
                        if (selectYear > curYear) {
                            yearWV.setCurrentItem(curYear - INIT_DATE);
                            selectYear = curYear;
                        }
                        //选择的月大于当前的月
                        if (selectYear == curYear && selectMonth > curMonth) {
                            monthWV.setCurrentItem(curMonth - 1);
                            selectMonth = curMonth;
                        }

                    }

                    if(numFlag==3){
                        //选择的年大于当前的年
                        if (selectYear > curYear) {
                            yearWV.setCurrentItem(curYear - INIT_DATE);
                            selectYear = curYear;
                        }
                        //选择的月大于当前的月
                        if (selectYear == curYear && selectMonth > curMonth) {
                            monthWV.setCurrentItem(curMonth - 1);
                            selectMonth = curMonth;
                        }
                        //选择的日大于当前的日
                        if (selectYear == curYear && selectMonth == curMonth && selectDay > curDay) {
                            dayWV.setCurrentItem(curDay - 1);
                            selectDay = curDay;
                        }
                        initDay((yearWV.getCurrentItem() + INIT_DATE), (monthWV.getCurrentItem() + 1));
                    }
                }

                String dateStr="";
                //只有年
                if(numFlag==1){
                    dateStr = (yearWV.getCurrentItem() + INIT_DATE)+"" ;
                }
                //只有年 -月
                if(numFlag==2){
                    dateStr = (yearWV.getCurrentItem() + INIT_DATE) + "-" + (monthWV.getCurrentItem() + 1) ;
                }

                if(numFlag==3){
                    dateStr = (yearWV.getCurrentItem() + INIT_DATE) + "-" + (monthWV.getCurrentItem() + 1) + "-" + (dayWV.getCurrentItem() + 1);
                }
                selectDateTV.setText(getDateSimpleFormt(dateStr));
            }
        }
    };

    /**
     * 将字符串转换成yyyy-MM-dd的标准格式
     *
     * @param string
     * @return
     * @throws ParseException
     */
    public static String getDateSimpleFormt(String string)  {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTrans = null;
        String tempDate="";
        try {
            //2016-02
            if(string.split("-").length==2){
                String tempString=string+"-01";
                dateTrans = format.parse(tempString);
                String resultDate = format.format(dateTrans);
                tempDate  = resultDate.split("-")[0] + "-"+resultDate.split("-")[1];
            }
            //2017-02-02
            if(string.split("-").length==3){
                dateTrans = format.parse(string);
                tempDate = format.format(dateTrans);
            }
            if(!string.contains("-")){
                tempDate=string;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tempDate;
    }


    public interface  OnCancleOnClick{
        void cancleOnClick();
    }
    public interface  OnOkOnClick{
        void okOnClick(String dateStr);
    }

    public interface  OnScroller{
        void wheelScroller();
    }

}
