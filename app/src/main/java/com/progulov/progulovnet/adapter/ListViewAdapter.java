package com.progulov.progulovnet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.progulov.progulovnet.LecturerModel;
import com.progulov.progulovnet.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    Activity activity;
    List<LecturerModel> listLecturers;
    LayoutInflater inflater;

    public ListViewAdapter(Activity activity, List<LecturerModel> listLecturers) {
        this.activity = activity;
        this.listLecturers= listLecturers;
    }

    @Override
    public int getCount() {
        return listLecturers.size();
    }

    @Override
    public Object getItem(int i) {
        return listLecturers.get(i);
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


        txtName.setText(listLecturers.get(i).getName());


        return  itemView;
    }
}