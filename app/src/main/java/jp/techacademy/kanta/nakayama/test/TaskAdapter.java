package jp.techacademy.kanta.nakayama.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nhk2204 on 2016/10/04.
 */
public class TaskAdapter extends BaseAdapter{
    private LayoutInflater mLayoutInflater;
    private ArrayList<Task> mTaskArrayList;

    public TaskAdapter(Context context){
        mLayoutInflater=(LayoutInflater)context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
    }

    public void setTaskArrayList(ArrayList<Task> taskArrayList){
        mTaskArrayList=taskArrayList;
    }
    @Override
    public int getCount() {
        return mTaskArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTaskArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mLayoutInflater.inflate(android.R.layout.simple_list_item_2,null);
        }

        TextView textView1=(TextView)convertView.findViewById(android.R.id.text1);
        TextView textView2=(TextView)convertView.findViewById(android.R.id.text2);

        textView1.setText(mTaskArrayList.get(position).getTaskName1());
        textView2.setText(mTaskArrayList.get(position).getTaskName2());

        return convertView;
    }
}
