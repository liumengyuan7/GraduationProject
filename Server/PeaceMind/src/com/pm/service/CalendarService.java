package com.pm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class CalendarService {
	public static final String URL_News = "http://v.juhe.cn/calendar/day?date=%s&key=%s";

    //申请接口的请求key
    public static final String KEY = "2ef30b125229bf67d1e976d4dbde5b6e";
    
    public String findCalendar(String date) {
    	String result = printJson(date);
    	return result;
    }
    
    public static String printJson(String date) {
        //发送http请求的url
        String url = String.format(URL_News, date, KEY);
        String response = doPost(url);
        System.out.println("接口返回：" + response);
        String result = "";
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            int error_code = (int) jsonObject.get("error_code");
            if (error_code == 0) {
                System.out.println("调用接口成功");
                JSONObject object = jsonObject.getJSONObject("result");
                String data = object.getString("data");
                System.out.println("data:"+data);
                result = JSON.toJSONString(data);
            } else {
                System.out.println("调用接口失败：" + jsonObject.getString("reason"));
                result = jsonObject.getString("reason");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }

	public static String doPost(String httpUrl) {
		HttpURLConnection connection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 通过连接对象获取一个输出流
            outputStream = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            //outputStream.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder sbf = new StringBuilder();
                String temp;
                // 循环遍历一行一行读取数据
                while ((temp = bufferedReader.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append(System.getProperty("line.separator"));
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
	}
}
