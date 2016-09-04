package com.example.abhi.placesaroundme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends ActionBarActivity {

    Context ctx=this;
    TextView t_detail_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        t_detail_msg=(TextView)findViewById(R.id.text_Detail_Msg);

        double longitude,latitude;

        Intent i1 =getIntent();
        latitude=i1.getDoubleExtra("markerLat",0.0);
        longitude=i1.getDoubleExtra("markerLng",0.0);

        MyUserDBOperations mydb=new MyUserDBOperations(ctx);
        int res= mydb.getMapLocations();

        String msg="";
        if(MyUserDBOperations.userPostList!=null) {
            //Toast.makeText(getApplicationContext(), "UserDBOpetarions.userPostList.size() "+MyUserDBOperations.userPostList.size(), Toast.LENGTH_LONG).show();

            for(int i=0; i<MyUserDBOperations.userPostList.size(); i++)
            {
                UserPosts user=MyUserDBOperations.userPostList.get(i);
                double userlatitude=user.getLatitude();
                double userlongitude=user.getLongitude();
                if(latitude==userlatitude &&  longitude==userlongitude) {
                     msg+=""+user.getFirst_name()+"  "+user.getLast_name()+"  :\nTime : "+user.getDate()+"\n"+user.getMessage()+"\n\n";

                }
            }
           if (msg.equals("")||msg.isEmpty())
           {

           }
            else {
               t_detail_msg.setText(msg);
           }
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "UserDBOpetarions.userPostList==null", Toast.LENGTH_LONG).show();

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
