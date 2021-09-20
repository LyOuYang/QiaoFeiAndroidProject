package com.example.firstview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;

import domain.Weather;
import utils.HttpsUtils;

public class WebViewActivity extends AppCompatActivity {
    TextView locationTextView;
    Button locationButton;
    TextView TempTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        locationTextView = findViewById(R.id.location_view);
        locationButton = findViewById(R.id.location_button);
        TempTextView = findViewById(R.id.temp_view);
        locationButton.setOnClickListener((view -> {
            String location = locationTextView.getText().toString();
            String url = "https://devapi.qweather.com/v7/weather/now?location=" + location + "&key=91c31619980e46018902aaf185acc2cc";
            String weather = HttpsUtils.sendGetHttpsRequest(url);
            Weather weatherObject = (Weather) JSONObject.parse(weather);
            TempTextView.setText("度");
        }));
    }
}