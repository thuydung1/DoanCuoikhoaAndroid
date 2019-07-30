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
import com.example.foodonline.Model.ThucAn;
import com.example.foodonline.R;

public class ThemThucAn extends AppCompatActivity {
    EditText edttenmon,edtloai;
    Button btnthem;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thuc_an);
        Init();
    }
    public void Init(){
        edtloai=(EditText)findViewById(R.id.txtloai);
        edttenmon=(EditText)findViewById(R.id.txttenmonan);
        btnthem=(Button)findViewById(R.id.btnthemloai);
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
            Toast.makeText(ThemThucAn.this, "Vui Lòng Nhập Thông Tin", Toast.LENGTH_SHORT).show();
        }else{
            ThucAn thucAn=new ThucAn();
            thucAn.setTenthucan(edttenmon.getText().toString().trim());
            thucAn.setMaloai(Integer.valueOf(edtloai.getText().toString().trim()));
            db.createMonAn(thucAn);
            Toast.makeText(ThemThucAn.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            String s=edtloai.getText().toString().trim();
            int maloai=Integer.parseInt(s);
            Intent intent=new Intent(ThemThucAn.this,ListViewThucAn.class);
            Bundle bundle=new Bundle();
            bundle.putInt("Id",maloai);
            intent.putExtras(bundle);
            startActivity(intent);
            db.closeDB();
        }
    }
}
