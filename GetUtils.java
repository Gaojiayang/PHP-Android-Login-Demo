package com.example.gaoming.byphplogin;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liu on 2021/5/12.
 */

public class GetUtils {
    public static String LOGIN_URL = "http://192.168.1.6/android_login.php";
    public static String LoginByGet(String number,String passwd)
    {
        String msg = "";
        try{
            LOGIN_URL = "http://192.168.1.6/android_login.php?user=" + number + "&pass=" + passwd;
            HttpURLConnection conn = (HttpURLConnection) new URL(LOGIN_URL).openConnection();
            //设置请求方式,请求超时信息
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                msg = new String(message.toByteArray());
                return msg;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return msg;
    }
}