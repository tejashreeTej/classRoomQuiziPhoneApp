package com.example.abhi.placesaroundme;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by abhi on 06/08/15.
 */
public class MyUserDBOperations {

    public static ArrayList<UserPosts> userPostList = new ArrayList();
    public boolean bISUserNameAvailable = false;
    Context ctx;
    MyUserDBOperations(Context ctx) {
        this.ctx = ctx;
    }
    public int checkUser(String UserDBOpetarions_user_name) {
        String usercheck_url = "http://52.11.244.234/checkuser.php";

        try {
            URL url = new URL(usercheck_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_user_name, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            InputStream in = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            if (result.equals("yes")) {
                bISUserNameAvailable = true;
            } else {
                bISUserNameAvailable = false;
            }
            bufferedReader.close();
            in.close();
            httpURLConnection.disconnect();
            return 1;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    public int registerUser(String UserDBOpetarions_first_name, String UserDBOpetarions_last_name, String UserDBOpetarions_user_name, String UserDBOpetarions_user_pass,String UserDBOpetarions_groupId) {
        String reg_url = "http://52.11.244.234/register.php";


        try {
            URL url = new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_first_name, "UTF-8") + "&" +
                    URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_last_name, "UTF-8") + "&" +
                    URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_user_name, "UTF-8") + "&" +
                    URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_user_pass, "UTF-8")+ "&" +
                    URLEncoder.encode("group_id", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_groupId, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
            httpURLConnection.disconnect();
            return 1;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int loginUser(String UserDBOpetarions_user_name, String UserDBOpetarions_user_pass) {
        String login_url = "http://52.11.244.234/login.php";

        try {

            URL loginURL = new URL(login_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) loginURL.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_user_name, "UTF-8") + "&" +
                    URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_user_pass, "UTF-8");
            bufferedWriter.write(data);

            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            InputStream in = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            //if (!result.equals("") && !result.equals("null")) {
            JSONArray jArray = new JSONArray(result);
            if (jArray.length() > 0) {
                JSONObject jsonbj = jArray.getJSONObject(0);

                UserPosts userPosts = new UserPosts();

                UserDataTable.MAIN_FIRST_NAME = jsonbj.getString("first_name");
                UserDataTable.MAIN_LAST_NAME = jsonbj.getString("last_name");
                UserDataTable.MAIN_USER_NAME = jsonbj.getString("user_name");
                UserDataTable.MAIN_USER_PASS = jsonbj.getString("user_pass");
                UserDataTable.MAIN_GROUPID=jsonbj.getString("group_id");

                result = "login";
                LoginActivity.bstatus = true;
                LoginActivity.bstatuschanged = true;
            } else {
                result = "fail";
                LoginActivity.bstatus = false;
                LoginActivity.bstatuschanged = true;
            }
            // } else {
            //   result = "fail";
            // LoginActivity.bstatus = false;
            //LoginActivity.bstatuschanged = true;
            //}

            bufferedReader.close();
            in.close();
            httpURLConnection.disconnect();

            return 1;
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public int postLocation(String UserDBOpetarions_first_name, String UserDBOpetarions_last_name, String UserDBOpetarions_longitude, String UserDBOpetarions_latitude, String UserDBOpetarions_message, String UserDBOpetarions_date,String UserDBOpetarions_category, String UserDBOpetarions_groupId) {
        String post_url = "http://52.11.244.234/postmasg.php";


        try {
            URL url = new URL(post_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_first_name, "UTF-8") + "&" +
                    URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_last_name, "UTF-8") + "&" +
                    URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_longitude, "UTF-8") + "&" +
                    URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_latitude, "UTF-8") + "&" +
                    URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_message, "UTF-8") + "&" +
                    URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_date, "UTF-8")+ "&" +
                    URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_category, "UTF-8")+ "&" +
                    URLEncoder.encode("group_id", "UTF-8") + "=" + URLEncoder.encode(UserDBOpetarions_groupId, "UTF-8");
            bufferedWriter.write(data);

            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
            httpURLConnection.disconnect();
            return 1;

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getMapLocations() {
        String getmap_url = "http://52.11.244.234/getmap.php";

        HttpURLConnection httpURLConnection;
        try {
            URL mapURL = new URL(getmap_url);
            httpURLConnection = (HttpURLConnection) mapURL.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            InputStream in = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            JSONArray jArray = new JSONArray(result);
            userPostList.clear();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonbj = jArray.getJSONObject(i);

                UserPosts userPosts = new UserPosts();

                //userPosts.setUser_id(jsonbj.getInt("user_id"));
                userPosts.setFirst_name(jsonbj.getString("first_name"));
                userPosts.setLast_name(jsonbj.getString("last_name"));
                userPosts.setLatitude(jsonbj.getDouble("latitude"));
                userPosts.setLongitude(jsonbj.getDouble("longitude"));
                userPosts.setMessage(jsonbj.getString("message"));
                userPosts.setDate(jsonbj.getString("date"));
                userPosts.setCategory(jsonbj.getString("category"));
                userPosts.setGroupId(jsonbj.getString("group_id"));

                userPostList.add(userPosts);
            }
            bufferedReader.close();
            in.close();
            httpURLConnection.disconnect();

            return 1;
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        return 0;
    }

}

