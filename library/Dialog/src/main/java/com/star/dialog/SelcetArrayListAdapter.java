package com.star.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by star on 2016/10/21.
 */
public class SelcetArrayListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public SelcetArrayListAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.dialog_select_array_item, null);
            viewHolder = new ViewHolder();
            viewHolder.nameTV=(TextView) convertView.findViewById(R.id.city_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.nameTV.setText(stringList.get(i));
        return convertView;
    }

    private class ViewHolder{
        TextView nameTV;
    }

}
