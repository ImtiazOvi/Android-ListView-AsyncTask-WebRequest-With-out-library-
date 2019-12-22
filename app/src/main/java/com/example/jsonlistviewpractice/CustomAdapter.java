package com.example.jsonlistviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jsonlistviewpractice.model.Model_Student;

import java.util.List;

class CustomAdapter extends BaseAdapter {
    Context applicationContext;
    int simple;
    List<Model_Student> s;

    LayoutInflater inflater;


    public CustomAdapter(Context applicationContext, int simple, List<Model_Student> s) {

        this.applicationContext = applicationContext;
        this.simple = simple;
        this.s = s;
    }

    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int position) {
        return s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.simple, parent, false);

        }

        TextView name, department, district;
        name = convertView.findViewById(R.id.tv_name);
        department = convertView.findViewById(R.id.tv_department);
        district = convertView.findViewById(R.id.tv_district);

        name.setText(s.get(position).getName());
        department.setText(s.get(position).getDepartment());
        district.setText(s.get(position).getDistrict());
        return convertView;

    }

}
