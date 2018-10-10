package com.example.wakisa.nadi_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


////////////// helper class in making under-five graphs to auto generate//// not completed though

public class MyHelper extends SQLiteOpenHelper {
    private Context con;
    public MyHelper(Context context) {
        super(context,"MyDatabase", null, 1);
        con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable ="create table MyTable(xValues INTEGER,yValues INTEGER);";
        db.execSQL(createTable);
        Toast.makeText(con, "Table Created successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertData(int x, int y){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("xValues",x);
        contentValues.put("yValues",y);

        db.insert("MyTable",null,contentValues);
        Toast.makeText(con, "Data Succeffully inserted", Toast.LENGTH_SHORT).show();
    }
}
