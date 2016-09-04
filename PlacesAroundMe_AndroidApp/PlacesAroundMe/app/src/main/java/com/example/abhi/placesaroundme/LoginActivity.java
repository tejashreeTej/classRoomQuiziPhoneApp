package com.example.abhi.placesaroundme;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class LoginActivity extends ActionBarActivity {

    EditText t_LoginActivity_UserName,t_LoginActivity_Pass;
    Button b_LoginActivity_Login,b_LoginActivity_SignUp;
    String strUserName,strPass;
    Context ctx=this;
    public static  boolean bstatuschanged=false;
    public static  boolean bstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t_LoginActivity_UserName=(EditText)findViewById(R.id.edit_login_userName);
        t_LoginActivity_Pass=(EditText)findViewById(R.id.edit_login_pass);
        b_LoginActivity_Login=(Button)findViewById(R.id.button_login_login);
        b_LoginActivity_SignUp=(Button)findViewById(R.id.button_login_signup);

        b_LoginActivity_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strUserName = t_LoginActivity_UserName.getText().toString();
                strPass = t_LoginActivity_Pass.getText().toString();

                //String method="login";


                //Toast.makeText(getApplicationContext(), "msg "+method+" , "+strUserName+" ,"+strPass, Toast.LENGTH_LONG).show();

               // UserDBOpetarions dbOperation=new UserDBOpetarions(v.getContext());
                //dbOperation.execute(method,strUserName,strPass);
                //Toast.makeText(getApplicationContext(), "msg "+method+" , "+strUserName+" ,"+strPass, Toast.LENGTH_LONG).show();
                /*strUserName
                UserDatabaseOperations dop = new UserDatabaseOperations(ctx);
                Cursor CR = dop.getInformation(dop);
                if(CR.getCount()>0) {
                    CR.moveToFirst();

                    do {

                        if (strUserName.equals(CR.getString(3)) && strPass.equals(CR.getString(4))) {
                            first_name = CR.getString(0);
                            last_name = CR.getString(1);
                            user_name = CR.getString(2);
                            user_pass = CR.getString(3);
                            bstatus = true;
                        }
                    } while (CR.moveToNext());
                }else
                {
                    Toast.makeText(v.getContext(), "Please ,register first.", Toast.LENGTH_LONG).show();
                    t_LoginActivity_UserName.setText("");
                    t_LoginActivity_Pass.setText("");
                    Intent iSignUp=new Intent(v.getContext(),SignUpActivity.class);
                    startActivity(iSignUp);
                }

*/       // while(LoginActivity.bstatuschanged==false)
                //{
                   // Toast.makeText(v.getContext(), "wait Login to process.", Toast.LENGTH_LONG).show();
                //}
                //Toast.makeText(getApplicationContext(), "LoginActivity.bstatuschanged = "+LoginActivity.bstatuschanged+" ,  bstatus = "+bstatus, Toast.LENGTH_LONG).show();
             MyUserDBOperations mydb=new MyUserDBOperations(ctx  );
             int res= mydb.loginUser(strUserName,strPass);
                if (res==1)
                {
                    bstatus=true;
                    //Toast.makeText(v.getContext(), " Login done.", Toast.LENGTH_LONG).show();

                }
                else
                {
                    bstatus=false;
                   // Toast.makeText(v.getContext(), "login Not done", Toast.LENGTH_LONG).show();
                }
                if (bstatus) {
                    loginUserDatabase ludb=new loginUserDatabase(ctx);

                    ludb.putInformation(ludb,UserDataTable.MAIN_FIRST_NAME,UserDataTable.MAIN_LAST_NAME,UserDataTable.MAIN_USER_NAME, UserDataTable.MAIN_USER_PASS,UserDataTable.MAIN_GROUPID);

                    Toast.makeText(v.getContext(), "Login successfull.", Toast.LENGTH_LONG).show();
                    Intent iTab = new Intent(v.getContext(),MyTabActivity.class);
                    startActivity(iTab);
                } else
                {
                    Toast.makeText(v.getContext(), "Login failed.", Toast.LENGTH_LONG).show();
                    t_LoginActivity_UserName.setText("");
                    t_LoginActivity_Pass.setText("");
                   // Intent iSignUp=new Intent(v.getContext(),SignUpActivity.class);
                   // startActivity(iSignUp);
                    //Toast.makeText(v.getContext(), "Login failed.", Toast.LENGTH_LONG).show();
                }
            }
        });


        b_LoginActivity_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iSignUp=new Intent(v.getContext(),SignUpActivity.class);
                startActivity(iSignUp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
