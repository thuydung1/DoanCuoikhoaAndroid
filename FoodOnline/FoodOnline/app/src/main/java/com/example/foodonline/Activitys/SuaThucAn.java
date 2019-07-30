package com.example.foodonline.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodonline.Data.DataBaseHelper;
import com.example.foodonline.Model.ThucAn;
import com.example.foodonline.R;

import java.util.ArrayList;

public class SuaThucAn extends AppCompatActivity {
    ArrayList<ThucAn> thucAns=new ArrayList<>();
    EditText edttenmon,edtloai;
    Button btnsua;
    DataBaseHelper db;
    int mathucan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thuc_an);
        Init();
    }
    public void Init() {
        Bundle bundle=getIntent().getExtras();
        int mathucan=bundle.getInt("Id");
        edtloai = (EditText) findViewById(R.id.txtsualoai);
        edttenmon = (EditText) findViewById(R.id.txtsuatenmonan);
        btnsua = (Button) findViewById(R.id.btnsuathucan);
        db=new DataBaseHelper(this);
        thucAns=db.Laysuathucan(mathucan);
        edtloai.setText(thucAns.get(0).getMaloai()+"");//lấy giá trị từ select gắn vào
        edttenmon.setText(thucAns.get(0).getTenthucan());
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulysua();
            }
        });
    }
    public void xulysua(){
        if(edttenmon.getText().toString().trim().length()<=0)
        {
            Toast.makeText(SuaThucAn.this, "Vui Lòng Nhập Thông Tin", Toast.LENGTH_SHORT).show();
        }else {
            Bundle bundle=getIntent().getExtras();
            int mathucan=bundle.getInt("Id");
            ThucAn thucAn = new ThucAn();
            thucAn.setTenthucan(edttenmon.getText().toString().trim());
            thucAn.setMaloai(Integer.valueOf(edtloai.getText().toString().trim()));
            db.updateThucAn(thucAn, mathucan);
            Intent intent=new Intent(SuaThucAn.this,ListViewLoai.class);
            startActivity(intent);// quay về loại ban đầu
        }
    }
}
