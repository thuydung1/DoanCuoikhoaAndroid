package com.example.foodonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodonline.R;
import com.example.foodonline.Model.ThucAn;

import java.util.ArrayList;

public class CustomAdapterThucAn extends BaseAdapter {
    ArrayList<ThucAn> thucans=new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public CustomAdapterThucAn(ArrayList<ThucAn> thucans, Context context) {
        this.thucans = thucans;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return thucans.size();
    }

    @Override
    public Object getItem(int position) {
        return thucans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView=inflater.inflate(R.layout.activity__dong__thuc_an,parent,false);
        }
        TextView tenthucan=(TextView)convertView.findViewById(R.id.hienthitenthucan);
        tenthucan.setText(thucans.get(position).tenthucan);
        return convertView;
    }
}
