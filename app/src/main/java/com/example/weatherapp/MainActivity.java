package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextCityName;
    TextView textViewWeather,textViewCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextCityName = findViewById(R.id.editTextCityName);
        textViewWeather = findViewById(R.id.textViewWeather);
        textViewCityName = findViewById(R.id.textViewCityName);
    }

    public void checkWeather(View view){
        String cityName = editTextCityName.getText().toString();
        Log.i("Miasto",cityName);
        if(!(cityName.trim().length()==0)) {
            DownloadTask downloadTask = new DownloadTask(textViewWeather);
            downloadTask.execute("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=ed98224037c6a8f3bf6f70f162061d79");
            textViewCityName.setText(cityName);
            textViewWeather.setVisibility(View.VISIBLE);
            textViewCityName.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(this,"Podaj miasto",Toast.LENGTH_SHORT).show();

        }
    }


}