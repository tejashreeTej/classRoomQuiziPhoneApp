package com.example.abhi.placesaroundme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;


public class SettingsActivity extends ActionBarActivity {

    Button b_SettingsActivity_Set;
    Context ctx=this;
    Spinner sp_SettingsActivity_Dist,sp_SettingsActivity_Cat;
    String [] arrayDist;
    String [] arrayCat;
    public static int iCatSpinner_pos;
    public static int iDistSpinner_pos;
    CheckBox ch_ettingsActivity_see;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sp_SettingsActivity_Dist=(Spinner)findViewById(R.id.spinner_settings_distance);
        sp_SettingsActivity_Cat=(Spinner)findViewById(R.id.spinner_settings_category);
        b_SettingsActivity_Set=(Button)findViewById(R.id.button_setting__set);
        ch_ettingsActivity_see=(CheckBox)findViewById(R.id.checkbox_settings_user);


        arrayDist=getResources().getStringArray(R.array.distance);
        arrayCat=getResources().getStringArray(R.array.setcategory);

        ArrayAdapter<String> distAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayDist);
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayCat);

        distAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        catAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        sp_SettingsActivity_Dist.setAdapter(distAdapter);
        sp_SettingsActivity_Cat.setAdapter(catAdapter);

        sp_SettingsActivity_Dist.setSelection(iDistSpinner_pos);
        sp_SettingsActivity_Cat.setSelection(iCatSpinner_pos);
        ch_ettingsActivity_see.setChecked(UserDataTable.MAIN_ISALL);

        sp_SettingsActivity_Dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = sp_SettingsActivity_Dist.getSelectedItemPosition();
                //Toast.makeText(getBaseContext(), "You have selected : " + arrayDist[pos], Toast.LENGTH_LONG).show();
                UserDataTable.MAIN_DISTANCE = arrayDist[pos];
                sp_SettingsActivity_Dist.setSelection(pos);
                iDistSpinner_pos = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sp_SettingsActivity_Cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos= sp_SettingsActivity_Cat.getSelectedItemPosition();
                //Toast.makeText(getBaseContext(), "You have category selected : "+arrayCat[pos], Toast.LENGTH_LONG).show();
                UserDataTable.MAIN_CATEGORY=arrayCat[pos];
                sp_SettingsActivity_Cat.setSelection(pos);
                iCatSpinner_pos=pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        b_SettingsActivity_Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataTable.MAIN_DISTANCE = arrayDist[iDistSpinner_pos];
                sp_SettingsActivity_Dist.setSelection(iDistSpinner_pos);
                iDistSpinner_pos = iDistSpinner_pos;

                UserDataTable.MAIN_CATEGORY = arrayCat[iCatSpinner_pos];
                sp_SettingsActivity_Cat.setSelection(iCatSpinner_pos);
                iCatSpinner_pos = iCatSpinner_pos;

                Toast.makeText(getBaseContext(), "Saved Setting", Toast.LENGTH_LONG).show();

                Intent myTabIntent = new Intent(v.getContext(), MyTabActivity.class);
                startActivity(myTabIntent);

            }
        });

        ch_ettingsActivity_see.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    UserDataTable.MAIN_ISALL=true;

                }else if(isChecked)
                {
                    UserDataTable.MAIN_ISALL=true;
                }
                else if(!buttonView.isChecked())
                {
                    UserDataTable.MAIN_ISALL=false;

                }else if(!isChecked)
                {
                    UserDataTable.MAIN_ISALL=false;
                }
                else
                {
                    UserDataTable.MAIN_ISALL=true;
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
