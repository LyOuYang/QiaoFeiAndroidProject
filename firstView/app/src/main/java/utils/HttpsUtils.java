package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsUtils {
    public static String sendGetHttpsRequest(String address) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // connection.setRequestProperty("token", token);//设置请求头
            // OutputStream outputStream = connection.getOutputStream() // 设置上传流
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                //得到响应流
                InputStream inputStream = connection.getInputStream();

                // 1010101转string
                String json = is2String(inputStream);
                return json;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求失败");
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return "";
    }

    private static String is2String(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
