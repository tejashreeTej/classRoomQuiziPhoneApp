package com.example.abhi.placesaroundme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends ActionBarActivity {

    EditText t_SignUpActivity_FirstName,t_SignUpActivity_LastName, t_SignUpActivity_UserName,t_SignUpActivity_Pass,t_SignUpActivity_ConfirmPass;
    EditText t_SignUpActivity_GroupId;
    String first_name,last_name,user_name,user_pass,confirm_pass,group_id;
    Button btnRegister;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        t_SignUpActivity_FirstName=(EditText)findViewById(R.id.edit_signup_firstName);
        t_SignUpActivity_LastName=(EditText)findViewById(R.id.edit_signup_lastName);
        t_SignUpActivity_UserName=(EditText)findViewById(R.id.edit_signup_userName);
        t_SignUpActivity_Pass=(EditText)findViewById(R.id.edit_signup_pass);
        t_SignUpActivity_ConfirmPass=(EditText)findViewById(R.id.edit_signup_confirmpass);
        t_SignUpActivity_GroupId=(EditText)findViewById(R.id.edit_signup_groupid);

        btnRegister=(Button)findViewById(R.id.button_signup_signup);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first_name=t_SignUpActivity_FirstName.getText().toString();
                last_name=t_SignUpActivity_LastName.getText().toString();
                user_name = t_SignUpActivity_UserName.getText().toString();
                user_pass = t_SignUpActivity_Pass.getText().toString();
                confirm_pass = t_SignUpActivity_ConfirmPass.getText().toString();
                group_id=t_SignUpActivity_GroupId.getText().toString();

                if(group_id.isEmpty() || group_id.equals(""))
                {
                    group_id="1";
                }


                if(first_name.isEmpty() || first_name.length()<2)
                {
                    Toast.makeText(getBaseContext(), "First Name is not Valid", Toast.LENGTH_SHORT).show();
                    t_SignUpActivity_FirstName.setText("");
                }
                else if(last_name.isEmpty()  || last_name.length()<2)
                {
                    Toast.makeText(getBaseContext(), "Last Name is not Valid", Toast.LENGTH_SHORT).show();
                    t_SignUpActivity_LastName.setText("");
                }
                else if (!(user_pass.equals(confirm_pass)))
                {
                    Toast.makeText(getBaseContext(), "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    t_SignUpActivity_Pass.setText("");
                    t_SignUpActivity_ConfirmPass.setText("");

                } else
                {

                    //UserDatabaseOperations db = new UserDatabaseOperations(ctx);//old
                    //db.putInformation(db, first_name, last_name, user_name, user_pass);//old

                    String method;//="checkuser";

                    UserDBOpetarions dbOperation=new UserDBOpetarions(v.getContext());
                    //dbOperation.execute(method, user_name);

                   // if(!dbOperation.bISUserNameAvailable) {
                        method = "register";
                        dbOperation.execute(method, first_name, last_name, user_name, user_pass, group_id);

                        //Toast.makeText(getBaseContext(), "Registration successfull 1.", Toast.LENGTH_LONG).show();

                        //put user entry in logged in database
                        loginUserDatabase ludb = new loginUserDatabase(ctx);
                        ludb.putInformation(ludb, first_name, last_name, user_name, user_pass,group_id);

                        //Toast.makeText(getBaseContext(), "Registration successfull.", Toast.LENGTH_LONG).show();
                    UserDataTable.MAIN_FIRST_NAME=first_name;
                    UserDataTable.MAIN_LAST_NAME=last_name;
                    UserDataTable.MAIN_USER_NAME= user_name;
                    UserDataTable.MAIN_USER_PASS=user_pass;
                    UserDataTable.MAIN_GROUPID=group_id;

                    Intent iTab = new Intent(v.getContext(), MyTabActivity.class);
                        startActivity(iTab);
                    //}
                    //else {
                      //  t_SignUpActivity_UserName.setText("");
                    //}
                }
            }
        });


    }

    /*public boolean validateUserName(String userName)
    {
        if(userName.isEmpty()) {
            Toast.makeText(getBaseContext(), "UserName Should not empty.", Toast.LENGTH_LONG).show();
            return false;
        }
       UserDatabaseOperations dop = new UserDatabaseOperations(ctx);
        Cursor CR = dop.getInformation(dop);
        if(CR==null)
            return false;
        if(CR.getCount()>0)
        {
        CR.moveToFirst();

        do {
            if (userName.equals(CR.getString(2))) {
                Toast.makeText(getBaseContext(), "UserName Not available.", Toast.LENGTH_LONG).show();
                t_SignUpActivity_UserName.setText("");
                return false;
            }
        } while (CR.moveToNext());
        }

        return true;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
