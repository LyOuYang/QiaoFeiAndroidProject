package com.example.firstview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import ai.baidu.Animal;
import domain.baidu.AnimalDomain;
import utils.CommonUtils;

public class PhotographActivity extends AppCompatActivity {
    private final int TEXT_UPDATE = 1;
    ImageView view;
    TextView resultView;
    // 子线程更新东西到主线程
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == TEXT_UPDATE) {
                // 这个不能走子线程
                resultView.setText(msg.getData().getString("animalName"));  // 更新ui， handle
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sotre_layout);
        Button button = findViewById(R.id.buttonpic);
        view = findViewById(R.id.image);
        resultView = findViewById(R.id.result_text_view);
        button.setOnClickListener((b)->{
            Intent image = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(image, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Bitmap pic = data.getParcelableExtra("data");
        view.setImageBitmap(pic);
        if(requestCode == 1){
            new Thread(() -> {
                byte[] image = CommonUtils.bitmapToByte(pic);
                String json = Animal.animal(image);
                AnimalDomain animalDomain = JSON.parseObject(json, AnimalDomain.class);
                String name = animalDomain.getResult().get(0).getName();

                // 发送消息到主线程
                Message message = new Message();
                message.what = TEXT_UPDATE;
                Bundle bundle = new Bundle();
                bundle.putString("animalName", name);
                message.setData(bundle);
                handler.sendMessage(message);
            }).start();
        }
    }
}

