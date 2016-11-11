package com.star.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class MainActivity extends AppCompatActivity {
   /* private Activity activity;


    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    TextView btn2;

    List<String> stringList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity=this;

        stringList=new ArrayList<>();
        stringList.add("苏州1");
        stringList.add("无锡2");
        stringList.add("苏州3");
        stringList.add("无锡4");
        stringList.add("苏州5");
        stringList.add("无锡6");
        stringList.add("苏州7");
        stringList.add("无锡8");
        stringList.add("苏州9");
        stringList.add("无锡10");

    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                final SelectArrayDialog dialog=new SelectArrayDialog(activity);
                dialog.setDataList(stringList);
                dialog.setOnCancleOnclickListener(new SelectArrayDialog.OnCancleOnclickListener() {
                    @Override
                    public void onCancleClick() {
                        dialog.dismiss();
                    }
                });
                dialog.setOnContentOnclickListener(new SelectArrayDialog.OnContentOnclickListener() {
                    @Override
                    public void onContentClick(int i) {
                        Toast.makeText(activity,stringList.get(i),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        btn1.setText(stringList.get(i));
                    }
                });
                dialog.show();

                break;
            case R.id.btn2:
                final DatePickerDialog datePickerDialog=new DatePickerDialog(activity,btn2,true,2);
                datePickerDialog.setOnCancleOnClick(new DatePickerDialog.OnCancleOnClick() {
                    @Override
                    public void cancleOnClick() {
                        datePickerDialog.dismiss();
                    }
                });

                datePickerDialog.setOnOkOnClick(new DatePickerDialog.OnOkOnClick() {

                    @Override
                    public void okOnClick(String dateStr) {
                        btn2.setText(dateStr);
                        datePickerDialog.dismiss();
                    }
                });

                datePickerDialog.show();
                break;
        }
    }*/

}
