package com.progulov.progulovnet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.progulov.progulovnet.LessonModel;
import com.progulov.progulovnet.R;

import java.util.List;

public class ListViewAdapterForLessons extends BaseAdapter {
    Activity activity;
    List<LessonModel> listLessons;
    LayoutInflater inflater;

    public ListViewAdapterForLessons(Activity activity, List<LessonModel> listLessons) {
        this.activity = activity;
        this.listLessons= listLessons;
    }

    @Override
    public int getCount() {
        return listLessons.size();
    }

    @Override
    public Object getItem(int i) {
        return listLessons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        inflater = (LayoutInflater) activity
                .getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.cardview, null);
        TextView txtName= (TextView) itemView.findViewById(R.id.subject_name);
        TextView lecturer = (TextView) itemView.findViewById(R.id.lecturer_name) ;
        txtName.setText(listLessons.get(i).subject_name);
        lecturer.setText(listLessons.get(i).lecturer_name);
        return  itemView;
    }
}
