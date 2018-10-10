package com.example.wakisa.nadi_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class   followUpDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="child.db";
    private static final String TABLE_CHILD ="child";
    private static final String OEDEMA ="Oedema";
    private static final String WEIGHT = "Weight(kg)";
    private static final String HEIGHT="Height(cm)";
    private static final String WASTING="Wasting";
    private static final String MUAC="MUAC";
    private static final int DATABASE_VERSION = 1;

    followUpDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CHILD +
                "(_Months integer PRIMARY KEY,"+OEDEMA+ " TEXT, "+ WEIGHT+ " TEXT, " +HEIGHT + " TEXT, " +WASTING+ " TEXT, "+MUAC + " TEXT);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
//Handle database upgrade as needed
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_CHILD);
        onCreate(db);
    }

    public boolean insertData(String oedema,String weight,String height,String wasting,String muac){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OEDEMA,oedema);
        contentValues.put(WEIGHT,weight);
        contentValues.put(HEIGHT,height);
        contentValues.put(WASTING,muac);
        contentValues.put(MUAC,muac);
        long result = db.insert(TABLE_CHILD,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

}
