package com.example.abhi.placesaroundme;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Context ctx=this;
   // private static final int STOPSPLASH = 0;
    //time in milliseconds
    //private static final long SPLASHTIME = 1;
    private static  boolean bTest=true;
    private ImageView splash;

    //handler for splash screen
  /*  private Handler splashHandler = new Handler() {
        /* (non-Javadoc)
         * @see android.os.Handler#handleMessage(android.os.Message)
         *//*
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STOPSPLASH:
                   bTest=true;
                    //remove SplashScreen from view
                    //splash.setVisibility(View.GONE);
                    break;
            }
            super.handleMessage(msg);
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);


        //Test any User is already Logged In
        UserDataTable.MAIN_ISALL=false;
        if(bTest==true) {
            loginUserDatabase lUdb = new loginUserDatabase(ctx);
            Cursor CR = lUdb.getInformation(lUdb);
            String Str_fname = "";
            Toast toast1 = Toast.makeText(getApplicationContext(), "Checking login", Toast.LENGTH_SHORT);
            toast1.show();
            if (CR.getCount() > 0) {
                CR.moveToFirst();
                UserDataTable.MAIN_FIRST_NAME = CR.getString(0);
                UserDataTable.MAIN_LAST_NAME = CR.getString(1);
                UserDataTable.MAIN_GROUPID=CR.getString(4);

                Context context = getApplicationContext();
                String message = "Welcome " + UserDataTable.MAIN_FIRST_NAME + " .";
                Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                toast.show();

                Intent iTab = new Intent(this, MyTabActivity.class);
                startActivity(iTab);
            } else {//else show login form
                Intent iLogin = new Intent(this, LoginActivity.class);
                startActivity(iLogin);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
