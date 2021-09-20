package com.example.firstview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PhotographActivity extends AppCompatActivity {
    ImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sotre_layout);
        Button button = findViewById(R.id.buttonpic);
        view = findViewById(R.id.image);
        button.setOnClickListener((b)->{
            Intent image = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(image, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Bitmap pic = data.getParcelableExtra("data");
            view.setImageBitmap(pic);
        }
    }
}

