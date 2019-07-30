//đăng nhập
package com.example.foodonline.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonline.Data.DataBaseHelper;
import com.example.foodonline.R;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener { //Activity kế thừa Onclick, Onlcick lắng nghe các sự kiện click vào view

    TextView txttaikhoan,txtpass;
    ImageButton imglogin;
    Button btndk;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Init();
    }
    public void Init(){
        db=new DataBaseHelper(this);
        txttaikhoan=(TextView)findViewById(R.id.txttaikhoan);
        txtpass=(TextView)findViewById(R.id.txtpass);
        btndk=(Button)findViewById(R.id.btndangky);
        imglogin=(ImageButton)findViewById(R.id.ibtlogin);
        btndk.setOnClickListener(this);
        imglogin.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.ibtlogin:
                login();
                break;
            case R.id.btndangky:
                startActivity(new Intent(DangNhapActivity.this,DangkyActiviti.class));
                break;
        }
    }
    private void login(){
        String tendn=txttaikhoan.getText().toString();
        String mk=txtpass.getText().toString();
        if(db.getUser(tendn,mk)){// lấy dữ liệu truyền qua bên data xử lý
            startActivity(new Intent(DangNhapActivity.this, ListViewLoai.class));
            finish();
        }else{
            Toast.makeText(this, "Sai Tài Khoảng or Mật Khẩu", Toast.LENGTH_SHORT).show();
        }
    }
}
