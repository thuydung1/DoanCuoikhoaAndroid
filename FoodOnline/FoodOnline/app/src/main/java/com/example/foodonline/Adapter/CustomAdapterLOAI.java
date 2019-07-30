package com.example.foodonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodonline.Model.LoaiThucAn;
import com.example.foodonline.R;

import java.util.ArrayList;

public class CustomAdapterLOAI extends BaseAdapter {
    ArrayList<LoaiThucAn> loai=new ArrayList<>();
    Context context;// class goi tới
    LayoutInflater inflater; // đọc các thuộc tính thành 1 view, thổi giao diện dòng vào listview.xml

    public CustomAdapterLOAI(ArrayList<LoaiThucAn> loai, Context context) {//truy xuất khi gọi
        this.loai = loai;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//kích hoạt inflater lên
    }

    @Override
    public int getCount() {// trả về số dòng, sồ do2g = sptu arraylist
        return loai.size();
    }

    @Override
    public Object getItem(int position) {// lấy giá trị ra
        return loai.get(position);
    }

    @Override
    public long getItemId(int position) {//lấy vị trí nào
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView=inflater.inflate(R.layout.activity_dong__loai,parent,false);// lấy giao diện dòng vào listview
        }
        //ImageView hinh=(ImageView)convertView.findViewById(R.id.imageHinh);
        TextView tenloai=(TextView)convertView.findViewById(R.id.hienthitenloai);//kết nối

        tenloai.setText(loai.get(position).tenloai);// gắn dữ liệu vào, lấy dữ liệu tên loại gắn vào
        return convertView;
    }
}
