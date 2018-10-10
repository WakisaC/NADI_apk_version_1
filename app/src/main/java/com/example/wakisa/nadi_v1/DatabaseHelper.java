package com.example.wakisa.nadi_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class   DatabaseHelper extends SQLiteOpenHelper {

    public  static  final String DATABASE_NAME = "ChildData.db";
    public  static  final String TABLE_NAME = "generalResults";
    public  static  final String COL_1= "ID";
    public  static  final String COL_2 = "BIRTH_CERTIFICATE_NUMBER";
    public  static  final String COL_3= "MONTHS";
    public  static  final String COL_4 = "WEIGHT";
    public  static  final String COL_5 = "HEIGHT";
    public  static  final String COL_6= "WASTING";
    public  static  final String COL_7= "OEDEMA";
    public  static  final String COL_8= "MUAC";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME +"( ID INTEGER PRIMARY KEY AUTOINCREMENT,BIRTH_CERTIFICATE_NUMBER TEXT,MONTHS INTEGER,WEIGHT DOUBLE,HEIGHT DOUBLE,WASTING TEXT,OEDEMA TEXT,MUAC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public void deleteCreateTable(String tableName){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(" DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }

    public boolean insertData(String bcn,int months,double weight,double height,String wasting,String oedema,String muac){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,bcn);
        contentValues.put(COL_3,months);
        contentValues.put(COL_4,weight);
        contentValues.put(COL_5,height);
        contentValues.put(COL_6,wasting);
        contentValues.put(COL_7,oedema);
        contentValues.put(COL_8,muac);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public int delete(String id){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ? ",new String[]{id});
    }


   }
