package com.pm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pm.entity.Joke;
import com.pm.entity.News;
import com.pm.service.NewsService;

@Controller
@RequestMapping("news")
public class NewsController {
	@Resource
	private NewsService newsService;
	
	public static final String URL_News = "http://v.juhe.cn/toutiao/index?key=%s&pagesize=%d&type=%s";

    //申请新闻接口的请求key
    public static final String KEY = "ccd8293f1c594288fe512f3f824e4edc";
	
    //查询所有新闻
    @RequestMapping(value="allNews",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findAll() {
    	List<News> list = this.newsService.findAll();
    	return JSON.toJSONString(list);
    }
    
	//查询某一类型新闻
	@RequestMapping(value="list",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findReadByType(@RequestParam("type") String type) {
		insertRead(20,type);
		List<News> reads = this.newsService.findNewsByType(type);
		System.out.println(reads.toString());
		System.out.println("reads:"+JSON.toJSONString(reads));
		return JSON.toJSONString(reads);
	}
	
		/**
	   	* 对新闻进行点赞
	   * @param readId 新闻id
	   * @param userId 用户id
	   * @param zanNumAfter 当前点赞总数
	   * @return 返回true操作成功，false操作失败
	   */
	  @ResponseBody
	  @RequestMapping("/addZanNum")
	  public  String addZanNum(@RequestParam("newsId") int readId,
	                   @RequestParam("userId") int userId,
	                   @RequestParam("zanNumAfter") int zanNumAfter){
		  String result = this.newsService.addZanNumByNews(readId,userId,zanNumAfter);
		  return result;
	  }	
	  
	  /**
	    * 对新闻取消点赞
	   * @param readId 新闻id
	   * @param userId 用户id
	   * @param zanNumAfter 当前点赞总数
	   * @return true操作成功，false操作失败
	   */
	   @ResponseBody
	   @RequestMapping("/decZanNum")
	  public  String decZanNum(@RequestParam("newsId") int readId,
	                       @RequestParam("userId") int userId,
	                       @RequestParam("zanNumAfter") int zanNumAfter){
	      String result = this.newsService.decZanNumByNews(readId,userId,zanNumAfter);
	      return result;
	  }

	private void insertRead(int pageSize,String type) {
		//发送http请求的url
        String url = String.format(URL_News, KEY, pageSize,type);
        String response = doPost(url);
        System.out.println("接口返回：" + response);
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            int error_code = (int) jsonObject.get("error_code");
            if (error_code == 0) {
                System.out.println("调用接口成功");
                JSONArray result = jsonObject.getJSONObject("result").getJSONArray("data");
                for(int i = 0;i<result.size();i++) {
            		JSONObject object = (JSONObject) result.get(i);
            		System.out.println("jsonObject:"+object);
            		News read = new News();
            		read.setUniquekey(object.getString("uniquekey"));
            		read.setAuther_name(object.getString("author_name"));
            		read.setCategory(object.getString("category"));
            		read.setType(type);
            		read.setRead_commentNum(0);
            		read.setRead_zan(0);
            		read.setTitle(object.getString("title"));
            		read.setUrl(object.getString("url"));
            		if (object.getString("thumbnail_pic_s") != null) {
						read.setPicurl(object.getString("thumbnail_pic_s"));
					}
            		News read2 = this.newsService.findNewsByUniqueKey(read.getUniquekey());
            		if (read2 == null) {
						int num = this.newsService.insertNews(read);
						System.out.println("数据插入成功");
					}
            	}

            } else {
                System.out.println("调用接口失败：" + jsonObject.getString("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
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
