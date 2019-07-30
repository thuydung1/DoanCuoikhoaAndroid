package com.example.foodonline.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.foodonline.Model.LoaiThucAn;
import com.example.foodonline.Model.ThucAn;
import com.example.foodonline.R;

import java.util.ArrayList;

public class ListViewLoai extends AppCompatActivity {
    ArrayList<LoaiThucAn> loaiThucAns=new ArrayList<>();
    ListView lvloai;
    DataBaseHelper db;
    CustomAdapterLOAI customAdapterLOAI;
    Button btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_loai);
        Init();
    }
    public void Init(){
        lvloai=(ListView)findViewById(R.id.lvloai);
        btnthoat=(Button)findViewById(R.id.btnthoat);
        db=new DataBaseHelper(this);
        loaiThucAns=db.layallLoaiThucAn();
        customAdapterLOAI=new CustomAdapterLOAI(loaiThucAns,this);//
        lvloai.setAdapter(customAdapterLOAI);
        lvloai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogDelete(loaiThucAns.get(position).getIdloai(),position);
                return false;
            }
        });
        lvloai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maloai=loaiThucAns.get(position).getIdloai();
                Intent intent=new Intent(ListViewLoai.this,ListViewThucAn.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Id",maloai);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListViewLoai.this)
                        .setTitle("Thông Báo")
                        .setMessage("Bạn Có Muốn Thoát Ứng Dụng?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(1);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ListViewLoai.this,"Chưa Thoát", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false);
                alertDialogBuilder.create().show();
            }
        });
    }

    public void dialogDelete(final int id,final int dong){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setTitle("Thông Báo")
                .setMessage("Bạn Có Chắc Muốn Xóa ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteLoaiThucAn(id);
                        //db.deleteThucAntheoLoai(id);
                        loaiThucAns.remove(dong);//xóa trên arraylist
                        customAdapterLOAI.notifyDataSetChanged();//cập nhật lại dữ liệu listview
                        Toast.makeText(ListViewLoai.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ListViewLoai.this,"Chưa Xóa", Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false);
            alertDialogBuilder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//lấy cái nút add gắn lên màn hình
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.themloai,menu);//gọi vào gian diện nút thêm
        return true;//có meunu ở trên
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//khi bấm vào nút thêm thì chuyển qua màn hình thêm
        switch (item.getItemId()){
            case R.id.add:
                Intent intent=new Intent(ListViewLoai.this, ThemLoai.class);//chuyển qua màn hình thêm loại
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
