package com.example.abhi.placesaroundme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by abhi on 16/07/15.
 */
public class loginUserDatabase extends SQLiteOpenHelper
{
    public static String TABLE_NAME ="loggedInUser";
    public static String DATABASE_NAME="loggedInUserDatabase";
    public static final int database_version=1;
    public static String CREATE_QUERY="create table "+ TABLE_NAME+"("+ UserDataTable.UserTableInfo.FIRST_NAME+" TEXT,"+ UserDataTable.UserTableInfo.LAST_NAME +" TEXT,"+ UserDataTable.UserTableInfo.USER_NAME+" TEXT,"+ UserDataTable.UserTableInfo.USER_PASS+" TEXT,"+UserDataTable.UserTableInfo.GROUP_ID+" TEXT)";

    public loginUserDatabase(Context context ){
        super(context,DATABASE_NAME, null, database_version);
        Log.d("Database Operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        //Toast.makeText(,CREATE_QUERY , Toast.LENGTH_LONG).show();
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(loginUserDatabase dop,String name,String pass,String fname,String lname,String grpId)//,int islogin)
    {
        SQLiteDatabase Sq=dop.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(UserDataTable.UserTableInfo.FIRST_NAME,fname);
        cv.put(UserDataTable.UserTableInfo.LAST_NAME, lname);
        cv.put(UserDataTable.UserTableInfo.USER_NAME,name);
        cv.put(UserDataTable.UserTableInfo.USER_PASS, pass);
        cv.put(UserDataTable.UserTableInfo.GROUP_ID,grpId);

        long res=Sq.insert(TABLE_NAME,null,cv);

        Log.d("Database Operations","one row inserted.");

    }
    public Cursor getInformation(loginUserDatabase dop)
    {
        SQLiteDatabase SQ=dop.getReadableDatabase();
        String [] columns={UserDataTable.UserTableInfo.FIRST_NAME,UserDataTable.UserTableInfo.LAST_NAME,UserDataTable.UserTableInfo.USER_NAME, UserDataTable.UserTableInfo.USER_PASS,UserDataTable.UserTableInfo.GROUP_ID};//,UserDataTable.UserTableInfo.ISLOGIN};
        Cursor CR=SQ.query(TABLE_NAME, columns, null, null, null, null, null);
        return CR;
    }

    public void deleteUserOnLonOut(loginUserDatabase dop)
    {
        SQLiteDatabase SQ=dop.getWritableDatabase();
        SQ.delete(TABLE_NAME,null,null);
    }

}
