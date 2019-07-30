package com.example.foodonline.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.foodonline.Data.DataBaseHelper;
import com.example.foodonline.R;

public class DangkyActiviti extends AppCompatActivity implements View.OnClickListener {

   private Button btndangnhap;
   private ImageButton imgsubmit;
   private EditText edttentk,pass;
   private DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        Init();

    }
    public void Init(){
        db=new DataBaseHelper(this);
        edttentk=(EditText)findViewById(R.id.txttaikhoandk);
        pass=(EditText)findViewById(R.id.txtpassdk);
        btndangnhap=(Button)findViewById(R.id.btndangnhapcb);
        imgsubmit=(ImageButton)findViewById(R.id.ibtdangky);
        btndangnhap.setOnClickListener(this);
        imgsubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btndangnhapcb:
            startActivity(new Intent(DangkyActiviti.this, DangNhapActivity.class));
            break;
        case R.id.ibtdangky:
            dangky();
            break;
    }
    }
    private void dangky(){
        String tentk=edttentk.getText().toString();
        String mk=pass.getText().toString();
        if(tentk.isEmpty() && mk.isEmpty())//nếu rỗng thì toast
        {
            Toast.makeText(DangkyActiviti.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
        }
        else{
            db.addUsers(tentk,mk);
            Toast.makeText(this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
