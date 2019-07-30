package com.example.foodonline.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foodonline.Adapter.CustomAdapterLOAI;
import com.example.foodonline.Adapter.CustomAdapterThucAn;
import com.example.foodonline.Data.DataBaseHelper;
import com.example.foodonline.Model.ThucAn;
import com.example.foodonline.R;

import java.util.ArrayList;

public class ListViewThucAn extends AppCompatActivity {

    ArrayList<ThucAn> thucans = new ArrayList<>();
    Button btntrove;
    ListView lvthucan;
    DataBaseHelper db;
    CustomAdapterThucAn customAdapterThucAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_hucan);
        init();
    }
    public void init(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int maloai=bundle.getInt("Id");
        lvthucan=(ListView)findViewById(R.id.lvthucan);
        btntrove=(Button)findViewById(R.id.btntrove);
        db=new DataBaseHelper(this);
        thucans=db.LayThucAnTheoLoai(maloai);
        customAdapterThucAn=new CustomAdapterThucAn(thucans,this);
        lvthucan.setAdapter(customAdapterThucAn);
        lvthucan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogDelete(thucans.get(position).getIdthucan(),position);// id truyền vào , và vtri trên mảng
                return false;
            }
        });
        lvthucan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int mathucan=thucans.get(position).getIdthucan();
                Intent intent=new Intent(ListViewThucAn.this,SuaThucAn.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Id",mathucan);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewThucAn.this,ListViewLoai.class);
                startActivity(intent);
            }
        });
    }
    public void dialogDelete(final int id,final int dong){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this) //khởi tạo
                .setTitle("Thông Báo")
                .setMessage("Bạn Có Chắc Muốn Xóa ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {//positive là dương nằm bên phải
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteThucAn(id);// id này là getpostion ở trên, xóa trong DBA
                        thucans.remove(dong);//xóa trên arraylist
                        customAdapterThucAn.notifyDataSetChanged();//cập nhật lại dữ liệu listview
                        Toast.makeText(ListViewThucAn.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//NEga nằm trái
                        Toast.makeText(ListViewThucAn.this,"Chưa Xóa", Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false);
        alertDialogBuilder.create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//lấy cái nút add gắn lên màn hình
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.themloai,menu);//gọi vào gian diện nút thêm
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//khi bấm vào nút thêm thì chuyển qua màn hình thêm
        switch (item.getItemId()){
            case R.id.add:
                Intent intent=new Intent(this, ThemThucAn.class);//chuyển qua màn hình thêm loại
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ActionBar {
    }
}

