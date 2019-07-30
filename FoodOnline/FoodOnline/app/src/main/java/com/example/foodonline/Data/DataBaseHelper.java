package com.example.foodonline.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodonline.Model.LoaiThucAn;
import com.example.foodonline.Model.ThucAn;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // tạo Database verison
    public static final int DATABASE_VERSION = 1;// không cho đối tượng bên ngoài lấy đc nó
    // tạo Database name
    public static final String DATABASE_NAME = "FOODONLINE.db";

    // Tạo user login
    public static final String USER_TABLE = "usersss";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TENDN = "tendangnhap";
    public static final String COLUMN_PASS = "password";
    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + USER_TABLE
                    + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                    + COLUMN_TENDN + " TEXT ,"
                    + COLUMN_PASS + " TEXT "
                    + ")";

    //tạo table và colum bảng Loại
    public static final String TABLE_LOAI = "LoaiThucAn";
    public static final String COLUMN_LOAI_ID = "id";// bên class tạo các thuộc tính sao thì bên đây phải tạo các cột giống v
    public static final String COLUMN_LOAI_TEN = "ten_loai";


    private static final String CREATE_TABLE_LOAITHUCAN = "CREATE TABLE " + TABLE_LOAI + "("
            + COLUMN_LOAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LOAI_TEN + " VARCHAR"
            +");";


    //tạo table và colum bảng MonAn
    public static final String TABLE_MONAN = "MonAn";
    public static final String COLUMN_MONAN_ID = "id";
    public static final String COLUMN_MONAN_TEN = "ten_monan";
    public static final String COLUMN_MONAN_LOAI = "ma_loai";


    private static final String CREATE_TABLE_THUCAN = "CREATE TABLE " + TABLE_MONAN + "("
            + COLUMN_MONAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MONAN_TEN + " VARCHAR ,"
            + COLUMN_MONAN_LOAI + " INTERGER "
            +");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);//thực hiện câu lệnh tạo bảng
        db.execSQL(CREATE_TABLE_LOAITHUCAN);
        db.execSQL(CREATE_TABLE_THUCAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//khi thay đổi version DBA thì gọi
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_LOAITHUCAN);//xóa bảng nếu nó đã tồn tại
        onCreate(db);

    }

    public void createLoaiThucAn(LoaiThucAn sv) {
        SQLiteDatabase db = this.getWritableDatabase();//thêm dùng getWritable,/sqllite là thư viện để thêm
        ContentValues values = new ContentValues();//dùng để chứa giá trị
        values.put(COLUMN_LOAI_ID, sv.getIdloai());//put đặt giá trị vào hàng đợi, tên cột , giá trị trong class
        values.put(COLUMN_LOAI_TEN, sv.getTenloai());
        db.insert(TABLE_LOAI, null, values);// tên bảng , null , values
    }

    public ArrayList<LoaiThucAn> layallLoaiThucAn(){
        ArrayList<LoaiThucAn> loai=new ArrayList<>();
        String selectQueryLoai ="select * from " + TABLE_LOAI;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQueryLoai,null);
        if(cursor.moveToFirst()){//nếu dòng đầu tiên có dữ liệu thì đọc tiếp dòng 2,3
            do{
                LoaiThucAn loaiThucAn=new LoaiThucAn();
                loaiThucAn.setIdloai(cursor.getInt(cursor.getColumnIndex(COLUMN_LOAI_ID)));
                loaiThucAn.setTenloai(cursor.getString(cursor.getColumnIndex(COLUMN_LOAI_TEN)));
                loai.add(loaiThucAn);//add vào mảng
            }while (cursor.moveToNext());//có dữ liệu đọc tiếp
        }
        return loai;
    }

    public void deleteLoaiThucAn (int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_LOAI,COLUMN_LOAI_ID + " = " + id,null);
    }


    public void addUsers(String tendn, String password) {
        SQLiteDatabase db = this.getWritableDatabase();//thêm dùng getWritable
        ContentValues values = new ContentValues();//dùng để chứa giá trị
        values.put(COLUMN_TENDN, tendn);
        values.put(COLUMN_PASS, password);
        db.insert(USER_TABLE, null, values);
    }

    public boolean getUser(String tendn, String password) {
        String selectQuery = "select * from " + USER_TABLE + " where " + COLUMN_TENDN + " = " + "'" + tendn + "'"
                + " and " + COLUMN_PASS + " = " + "'" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); //phương thức ramQuery sẽ truy vấn và trả về 1 đối tượng cursor ,
        //các bản trả về sẽ đc lưu tại cursos theo dạng danh sách và trong cursor có con trỏ, con trỏ sẽ trỏ tới từng bản ghii
        cursor.moveToFirst();// di chuyển con trỏ đến kết quả đầu tiên
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
    // Tạo loại thức ăn
    public void createMonAn(ThucAn sv) {
        SQLiteDatabase db = this.getWritableDatabase();//thêm dùng getWritable
        ContentValues values = new ContentValues();//dùng để chứa giá trị
        values.put(COLUMN_MONAN_ID, sv.getIdthucan());//put đặt giá trị vào hàng đợi
        values.put(COLUMN_MONAN_TEN, sv.getTenthucan());
        values.put(COLUMN_MONAN_LOAI,sv.getMaloai());
        db.insert(TABLE_MONAN, null, values);
    }
    public ArrayList<ThucAn> LayThucAnTheoLoai(int maloai){
        ArrayList<ThucAn> thucAns= new ArrayList<>();
        String query="select * from " + TABLE_MONAN + " where " + COLUMN_MONAN_LOAI + " = '" + maloai + "' ";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){//nếu dòng đầu tiên có dữ liệu thì đọc tiếp dòng 2,3
            do{
                ThucAn thucAn=new ThucAn();
                thucAn.setIdthucan(cursor.getInt(cursor.getColumnIndex(COLUMN_MONAN_ID)));
                thucAn.setTenthucan(cursor.getString(cursor.getColumnIndex(COLUMN_MONAN_TEN)));
                thucAn.setMaloai(cursor.getInt(cursor.getColumnIndex(COLUMN_MONAN_LOAI)));
                thucAns.add(thucAn);//add vào mảng
            }while (cursor.moveToNext());//có dữ liệu đọc tiếp
        }
        return thucAns;
    }

    public void deleteThucAn (int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_MONAN,COLUMN_MONAN_ID + " = " + id,null);
    }
    public void deleteThucAntheoLoai (int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_MONAN,COLUMN_MONAN_LOAI+ " = " + id,null);
    }
    //sửa thức ăn
    public ArrayList<ThucAn> Laysuathucan(int id){
        ArrayList<ThucAn> thucAns= new ArrayList<>();
        String query="select * from " + TABLE_MONAN + " where " + COLUMN_MONAN_ID + " = '" + id + "' ";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor != null){//ktra có dữ liệu k
            cursor.moveToFirst();
                ThucAn thucAn=new ThucAn();
                thucAn.setIdthucan(cursor.getInt(cursor.getColumnIndex(COLUMN_MONAN_ID)));
                thucAn.setTenthucan(cursor.getString(cursor.getColumnIndex(COLUMN_MONAN_TEN)));
                thucAn.setMaloai(cursor.getInt(cursor.getColumnIndex(COLUMN_MONAN_LOAI)));
                thucAns.add(thucAn);//add vào mảng
        }
        return thucAns;
    }
    public int updateThucAn(ThucAn sv,int id){//id để
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COLUMN_MONAN_ID, sv.getIdthucan());
        values.put(COLUMN_MONAN_TEN, sv.getTenthucan());
        values.put(COLUMN_MONAN_LOAI,sv.getMaloai());
        return db.update(TABLE_MONAN,values,COLUMN_MONAN_ID + " = '" + id + "' ",null);

    }

    public void closeDB(){//dong ket noi  data
        SQLiteDatabase db=this.getReadableDatabase();
        if(db!=null&&db.isOpen()){
            db.close();
        }
    }
}
