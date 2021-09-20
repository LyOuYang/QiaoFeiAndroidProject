package com.example.firstview;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import domain.baidu.Weather;
import utils.HttpsUtils;

public class WebViewActivity extends AppCompatActivity {
    EditText shengEditText;
    EditText shiEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        shengEditText = findViewById(R.id.sheng_edit_text);
        shiEditText = findViewById(R.id.shi_edit_text);
        String url = "https://geoapi.qweather.com/v2/city/lookup?location=" + shiEditText.getText() + "&adm="+ shiEditText.getText() +"&key=91c31619980e46018902aaf185acc2cc";
        String json = HttpsUtils.sendGetHttpsRequest(url);
        // json ---> 对象
        Weather weather = JSON.parseObject(json, Weather.class);
        String s = weather.getNow().getTemp();
    }
}