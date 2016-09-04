package com.example.abhi.placesaroundme;

import android.provider.BaseColumns;

/**
 * Created by abhi on 15/07/15.
 */
public class UserDataTable {
    public static  String MAIN_FIRST_NAME;
    public static String MAIN_LAST_NAME;
    public static  String MAIN_USER_NAME;
    public static String MAIN_USER_PASS;
    public static  String MAIN_DISTANCE="10 mile";
    public static  String MAIN_GROUPID="1";
    public static  boolean MAIN_ISALL=true;
    public static String MAIN_CATEGORY="All";
    public static int TEMP_CTR=0;
    public UserDataTable()
    {

    }
    public  static double getDistance()
    {
        double distance=8047;
        if(MAIN_DISTANCE.equals("5 mile"))
        {
            distance=8047;
        }
        else  if(MAIN_DISTANCE.equals("10 mile"))
        {
            distance=16094;
        }
        else  if(MAIN_DISTANCE.equals("15 mile"))
        {
            distance=24140;
        }
        else  if(MAIN_DISTANCE.equals("20 mile"))
        {
            distance=32187;
        }
        else
        {
            distance=16094;
        }
        return distance;
    }
    public  static  abstract class UserTableInfo implements BaseColumns
    {
        public static final  String FIRST_NAME="first_name";
        public static final  String LAST_NAME="last_name";
        public static final  String USER_NAME="user_name";
        public static final  String USER_PASS="user_pass";
        public static final  String GROUP_ID="group_id";
        public static final  String DATABASE_NAME="users_info";
        public static final  String TABLE_NAME="alluser_info";
    }
}
