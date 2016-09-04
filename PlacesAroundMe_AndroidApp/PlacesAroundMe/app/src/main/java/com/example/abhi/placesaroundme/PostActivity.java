package com.example.abhi.placesaroundme;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;
import java.util.Calendar;



public class PostActivity extends ActionBarActivity {

    Button b_post_post;
    EditText e_post_desc;
    Spinner sp_post_category;
    String [] arrayCategory;
    String str_post_category="Other";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        b_post_post=(Button)findViewById(R.id.button_post_post);
        e_post_desc=(EditText)findViewById(R.id.edit_post_description);

        sp_post_category=(Spinner)findViewById(R.id.spinner_post_category);

        arrayCategory=getResources().getStringArray(R.array.category);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayCategory);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        sp_post_category.setAdapter(categoryAdapter);

        sp_post_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = sp_post_category.getSelectedItemPosition();
               // Toast.makeText(getBaseContext(), "You have selected : " + arrayCategory[pos], Toast.LENGTH_LONG).show();
                str_post_category = arrayCategory[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b_post_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationManager locationManager;
                String provider;
                String context = Context.LOCATION_SERVICE;
                locationManager = (LocationManager)getSystemService(context);

                Criteria criteria = new Criteria();

                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                criteria.setAltitudeRequired(false);
                criteria.setBearingRequired(false);
                criteria.setCostAllowed(true);
                criteria.setPowerRequirement(Criteria.POWER_LOW);
                provider = locationManager.getBestProvider(criteria, true);

                if(provider==null)
                {
                    provider=locationManager.GPS_PROVIDER;
                }
                Location location = locationManager.getLastKnownLocation(provider);

                String msg;
                msg=e_post_desc.getText().toString();
                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
               // msg+=\\\\;

                String method,first_name,last_name;
                double longitude=-121.967543,latitude=37.557328;

                if(location!=null) {
                    longitude=location.getLongitude();
                    latitude=location.getLatitude();
                }

                first_name = UserDataTable.MAIN_FIRST_NAME;
                last_name = UserDataTable.MAIN_LAST_NAME;

                if(UserDataTable.MAIN_GROUPID.isEmpty() || UserDataTable.MAIN_GROUPID.equals(""))
                {
                    UserDataTable.MAIN_GROUPID="1";
                }
                UserDBOpetarions dbOperation=new UserDBOpetarions(v.getContext());

                method = "post";
                //Toast.makeText(getApplicationContext(), method+" : "+first_name+" : "+last_name + " : " + msg + " : " + mydate, Toast.LENGTH_LONG).show();

                dbOperation.execute(method, first_name, last_name, String.valueOf(longitude), String.valueOf(latitude), msg, mydate,str_post_category,UserDataTable.MAIN_GROUPID);

                Intent myTabIntent=new Intent(v.getContext(),MyTabActivity.class);
                startActivity(myTabIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
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
