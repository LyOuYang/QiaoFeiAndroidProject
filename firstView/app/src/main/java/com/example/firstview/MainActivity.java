package com.example.firstview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button storeButton = findViewById(R.id.store_button);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhotographActivity.class);
                startActivity(intent);

            }
        });

        Button listViewButton = findViewById(R.id.list_view_button);
        listViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        Button permissionButton = findViewById(R.id.permission_button);
        permissionButton.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, PermissionActivity.class);
            startActivity(intent);
        });

        Button baiduButton = findViewById(R.id.weather_button);
        baiduButton.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        });
    }
}