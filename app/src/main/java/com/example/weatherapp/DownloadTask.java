package com.example.weatherapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String,Void,String> {

    static String message;
    TextView textView;
    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try{

            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while(data != -1){
                char current = (char) data;
                result += current;
                data = reader.read();
            }

            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("JSON","" + s);

        try {
            JSONObject jsonObject = new JSONObject(s);

             String weatherInfo = jsonObject.getString("weather");

            JSONArray jsonArray = new JSONArray(weatherInfo);
             message ="";
           for(int i =0; i < jsonArray.length(); i++){
               JSONObject jsonPart = jsonArray.getJSONObject(i);
               String main = jsonPart.getString("main");
               String description = jsonPart.getString("description");

               if(!main.equals("") && !description.equals("")){
                   message += main + ": " + description;
                   Log.i("message",message);
                    Update();

               }
                
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public DownloadTask(TextView tv){
        textView = tv;
    }
    public void Update(){
        textView.setText(message);
    }
}

