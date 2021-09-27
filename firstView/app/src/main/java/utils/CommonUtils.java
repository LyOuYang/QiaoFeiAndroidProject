package utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class CommonUtils {
    public static byte [] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
