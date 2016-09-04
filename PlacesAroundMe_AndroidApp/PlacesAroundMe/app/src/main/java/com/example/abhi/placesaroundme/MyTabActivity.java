package com.example.abhi.placesaroundme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyTabActivity extends ActionBarActivity {

    Button b_myTab_post,b_myTab_map,b_myTab_SignOut,b_myTab_Setting;
    //Button b_myTab_post,b_muTab
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytab);
        b_myTab_post=(Button)findViewById(R.id.button_mytab_post);
        b_myTab_map=(Button)findViewById(R.id.button_mytab_map);
        b_myTab_SignOut=(Button)findViewById(R.id.button_mytab_signOut);
        b_myTab_Setting=(Button)findViewById(R.id.button_mytab_setting);

        b_myTab_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent iPost=new Intent(v.getContext(),PostActivity.class);
                startActivity(iPost);
            }
        });

        b_myTab_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method;
                method="map";
               // UserDBOpetarions dbOperation=new UserDBOpetarions(v.getContext());
               // dbOperation.execute(method);

                MyUserDBOperations mydb=new MyUserDBOperations(ctx  );
                int res= mydb.getMapLocations();

                //MyUserDBOperations mydb=new MyUserDBOperations(ctx  );
               // int res= mydb.getMapLocations();
                /*try {
                    wait(1000);
                }catch(InterruptedException ex) {
                    ex.printStackTrace();
                }*/
            UserDataTable.TEMP_CTR++;
                String msg;
                msg="MyTabAct  = ";
                msg+=MyUserDBOperations.userPostList.size();
                double maxDistance=UserDataTable.getDistance();
                //Toast.makeText(getApplicationContext(), msg+" Distance "+maxDistance+"\n Cat :"+UserDataTable.MAIN_CATEGORY+" :: "+UserDataTable.getDistance(), Toast.LENGTH_LONG).show();
                for(int i=0; i<MyUserDBOperations.userPostList.size(); i++)
                {
                    msg="";
                    UserPosts p1= MyUserDBOperations.userPostList.get(i);
                    msg+=p1.getFirst_name();
                    msg+=p1.getLast_name();
                   if(p1.getCategory()==null)
                    msg+="null";
                    else
                       msg+=p1.getCategory();

                  //  Toast.makeText(getApplicationContext(),"msg "+i+" : "+ msg, Toast.LENGTH_SHORT).show();

                }

                Intent iPost=new Intent(v.getContext(),MapsActivity.class);
                startActivity(iPost);

            }
        });

        b_myTab_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSetting=new Intent(v.getContext(),SettingsActivity.class);
                startActivity(iSetting);
            }
        });
        b_myTab_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUserDatabase ludb=new loginUserDatabase(ctx);
                ludb.deleteUserOnLonOut(ludb);
                Toast.makeText(v.getContext(), "Sign Out successfull.", Toast.LENGTH_LONG).show();

                Intent iloginpg=new Intent(v.getContext(),LoginActivity.class);
                startActivity(iloginpg);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_tab, menu);
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
