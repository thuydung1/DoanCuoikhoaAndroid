package com.example.foodonline.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodonline.Data.DataBaseHelper;
import com.example.foodonline.Model.LoaiThucAn;
import com.example.foodonline.R;

public class ThemLoai extends AppCompatActivity {
    EditText edtloai;
    Button btnthem;
    DataBaseHelper db;// gọi DBA , biến toàn cục
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai);
        Init();
    }
    public void Init(){
        edtloai=(EditText)findViewById(R.id.txttenloai);
        btnthem=(Button)findViewById(R.id.btnthem);
        db=new DataBaseHelper(this);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xylythem();
            }
        });

    }
    public void xylythem(){

        if(edtloai.getText().toString().trim().length()<=0)
        {
            Toast.makeText(ThemLoai.this, "Vui Lòng Nhập Thông Tin", Toast.LENGTH_SHORT).show();
        }else{
            LoaiThucAn loaiThucAn=new LoaiThucAn();//tạo class new
            loaiThucAn.setTenloai(edtloai.getText().toString().trim());// lấy dữ liệu nhập vào truyền vào set ben class
            db.createLoaiThucAn(loaiThucAn);
            edtloai.setText("");
            Toast.makeText(ThemLoai.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
            db.closeDB();// đóng kết nối , k sever nó lấy liên tục bị chậm

            Intent intent = new Intent(ThemLoai.this, ListViewLoai.class);//chuyen sang trang load loai thuc an khi them xong
            startActivity(intent);
        }
    }
}
