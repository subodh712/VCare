package com.example.subodh.vcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sharan on 13-Jun-16.
 */
public class connection extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="lastvisit.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_CREATE="create table if not exists lastvisit(id integer primary key autoincrement,p_id integer ,date text,time text);";
   SQLiteDatabase db;
    public connection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }
    public void insertdate(ContactLastDocVisit c,int id){
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("p_id",id);
        values.put("date",c.getDate());
        values.put("time",c.getTime());
        db.insert("lastvisit", null, values);
        db.close();
    }

    public String[] read(int id) {


        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from lastvisit;", null);
        int index=1;
        if(c.moveToFirst()){
             do
            {
                if(c.getInt(c.getColumnIndex("p_id"))== id)
                {
                    index++;
                }

            }while(c.moveToNext());
        }
        String array[]= new String[index];
        if(c.moveToFirst()){
             index=0;
            do
            {
                if(c.getInt(c.getColumnIndex("p_id"))== id)
                {
                    array[index]=c.getString(c.getColumnIndex("date"))+" "+c.getString(c.getColumnIndex("time"));
                    index++;
                }

            }while(c.moveToNext());
        }

        db.close();
        return array;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
