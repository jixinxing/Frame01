package com.star.app5.adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.star.app5.R;

import java.util.List;

/**
 * Created by jixinxing on 16/11/14.
 */
public class MainFragmentTab01Adapter extends RecyclerView.Adapter<MainFragmentTab01Adapter.MyViewHolder> {

    private Context context;
    private List<String> dataList;
    private MyViewHolder.MyItemClickListener myItemClickListener;

    public MainFragmentTab01Adapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(
                LayoutInflater.from(context).inflate( R.layout.item_tab_01_recyleview , parent , false ),myItemClickListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nameTV.setText(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyViewHolder.MyItemClickListener listener){
        this.myItemClickListener = listener;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameTV;
        private MyItemClickListener myItemClickListener;

        public MyViewHolder(View itemView,MyItemClickListener myItemClickListener) {
            super(itemView);
            nameTV= (TextView) itemView.findViewById(R.id.id_num);

            this.myItemClickListener=myItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(myItemClickListener!=null){
                myItemClickListener.onItemClick(v,getPosition());
            }
        }

        public  interface MyItemClickListener{
             void onItemClick(View view,int postion);

        }

    }

}
