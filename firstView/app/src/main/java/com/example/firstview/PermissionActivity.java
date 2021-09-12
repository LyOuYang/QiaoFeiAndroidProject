package com.example.firstview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.FileNotFoundException;

public class PermissionActivity extends AppCompatActivity {
    private static final int STORAGE_REQ_CODE = 1;

    private static final int REQUEST_IMAGE_OPEN = 2;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);
        Button permissionButton = findViewById(R.id.request_permission);
        imageView = findViewById(R.id.req_img_view);
        permissionButton.setOnClickListener((view -> {
            // 1 判断一下是否已经有存储权限了
                // 是----》下一步
                // 否----》弹框让用户给予权限
            // 2 隐式跳转---》相册activity（非） 带一个reqId
            // 3 选择图片
            // 4 传到 PermissionActivity 判断是不是这个reqId给我
            isGetPermission();
            dumpToAlbum();

        }));
    }

    private boolean isGetPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        // 否----》弹框让用户给予权限
        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(PermissionActivity.this, permission, STORAGE_REQ_CODE);
        return false;
    }

    private void dumpToAlbum() {
        Intent intent = new  Intent(Intent.ACTION_PICK);
        //指定获取的是图片
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_OPEN) {
            if (data == null) {
                return;
            }
            Uri uris = data.getData();
            //Uri转化为Bitmap
            Bitmap bitmap = getBitmapFromUri(uris);
            imageView.setImageBitmap(bitmap);

        }
    }

    private Bitmap getBitmapFromUri(Uri uris) {
        Bitmap bitmap = null;
        try {
            bitmap =  BitmapFactory.decodeStream(getContentResolver().openInputStream(uris));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "FileNotFoundException", Toast.LENGTH_SHORT).show();
        }
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_REQ_CODE) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "权限获取成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "权限获取失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}