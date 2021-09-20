package com.example.firstview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import domain.baidu.LocalDomain;
import domain.baidu.Weather;
import utils.HttpsUtils;

public class WebViewActivity extends AppCompatActivity {
    private static final int ROUTE_ONE = 1;
    EditText shengEditText;
    EditText shiEditText;
    TextView tempView;
    TextView weatherView;
    Button button;

    // 子线程更新东西到主线程
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
//            switch (msg.what) {
//                case ROUTE_ONE:
//                    // 这个不能走子线程
//                    tempView.setText(msg.getData().getString("temp"));
//                    weatherView.setText(msg.getData().getString("weather"));
//                    break;
//            }
            if (msg.what == ROUTE_ONE) {
                    // 这个不能走子线程
                    tempView.setText(msg.getData().getString("temp"));
                    weatherView.setText(msg.getData().getString("weather"));;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        shengEditText = findViewById(R.id.sheng_edit_text);
        shiEditText = findViewById(R.id.shi_edit_text);
        tempView = findViewById(R.id.temp_view);
        weatherView = findViewById(R.id.weather_view);
        button = findViewById(R.id.location_button);

        Runnable r1 = () -> {
            // 网络请求必须走子线程
            String localUrl = "https://geoapi.qweather.com/v2/city/lookup?location=" + shiEditText.getText() + "&adm="+ shengEditText.getText() +"&key=91c31619980e46018902aaf185acc2cc";
            String localJson = HttpsUtils.sendGetHttpsRequest(localUrl);
            LocalDomain location = JSON.parseObject(localJson, LocalDomain.class);
            String locationId = location.getLocation().get(0).getId();

            String weatherUrl = "https://devapi.qweather.com/v7/weather/now?location=" +locationId+ "&key=91c31619980e46018902aaf185acc2cc";
            String weatherJson = HttpsUtils.sendGetHttpsRequest(weatherUrl);
            Weather weather = JSON.parseObject(weatherJson, Weather.class);

            // 发送消息到主线程
            Message message = new Message();
            message.what = ROUTE_ONE;
            Bundle bundle = new Bundle();
            bundle.putString("temp", weather.getNow().getTemp());
            bundle.putString("weather", weather.getNow().getText());
            message.setData(bundle);
            handler.sendMessage(message);
        };

        button.setOnClickListener((v) -> {
            Thread thread = new Thread(r1);
            thread.start();
        });


    }
}