package com.it18.crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context,"MyUserData.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    DB.execSQL("create table userData(NAME Text,CONTACT Text,DOB Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB,int i,int i1) {
    DB.execSQL("drop table if exists userData");
    }
    public boolean insertData(String name,String contact,String DOB){
    //establish connection to database
        SQLiteDatabase db=this.getWritableDatabase();
     //write values in database
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("CONTACT",contact);
        contentValues.put("DOB",DOB);
        long result=db.insert("userData",null,contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }//end of insert
    public boolean updateData(String name,String contact,String DOB){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //contentValues.put("NAME",name);
        contentValues.put("CONTACT",contact);
        contentValues.put("DOB",DOB);
        //find current user record
        Cursor currentRecord=db.rawQuery("select * from userData where NAME=?",new String[]{name});
        if(currentRecord.getCount()>0){
            int result=db.update("userData",contentValues,"NAME=?",new String[]{name});
            if (result == -1) {
            return false;
            }
            else{return true;}
            }
        else{return true;}
        }
        //Delete Query
        public boolean delete(String name){
        //establish connection to database
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor deleteRecord=db.rawQuery("select * from userData where NAME=?",new String[]{name});
        if (deleteRecord.getCount()>0){
            int result=db.delete("userData","NAME=?",new String[]{name});
            if (result==-1){return false;}
            else{return true;}
            }
        else {return false;}
        }//end Delete
        //REad Data Start
    public Cursor ReadData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor ReadAllData=db.rawQuery("select * from userData",null);
        return ReadAllData;
    }//end read
}//end main
