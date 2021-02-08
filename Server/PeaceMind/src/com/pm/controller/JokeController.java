package com.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pm.entity.Joke;
import com.pm.entity.User;
import com.pm.service.JokeService;

@Controller
@RequestMapping("joke")
public class JokeController {
	@Autowired
	private JokeService jokeService;
	
	//请求地址设置
    String url = "https://api.apishop.net/common/joke/getJokesByRandom";
    //请求方法设置
    String requestMethod = "POST";
    //请求头部设置
    Map<String, String> headers = new HashMap<String, String>();
	
	@RequestMapping("list")
	public String findAll(Model model) {
		insertJoke();
		List<Joke> users=jokeService.findAll();
		System.out.println(users.toString());
		//model.addAttribute("users", users);
		System.out.println("jokes:"+JSON.toJSONString(users));
		return JSON.toJSONString(users);
	}
	
	public void insertJoke() {
		List<Joke> jokes = new ArrayList<>();
	    //请求参数设置
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("apiKey","LCAYEKK8e1276e8d8e4efcb2486b44f06717882e01232f7");
	    params.put("pageSize", "20");
	    
	    String result = proxyToDesURL(requestMethod, url, headers, params);
        if (result != null) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            
            String status_code = jsonObject.getString("statusCode");
            if (status_code.equals("000000")) {
            	String string = jsonObject.toJSONString(jsonObject.getString("result"));
            	String strs = string.substring(1,string.length()-1);
            	String str = StringEscapeUtils.unescapeJava(strs);
            	JSONArray jsonArray = JSONArray.parseArray(str);
            	System.out.println("jsonArray:"+jsonArray);
            	for(int i = 0;i<jsonArray.size();i++) {
            		JSONObject object = (JSONObject) jsonArray.get(i);
            		System.out.println("jsonObject:"+object);
            		Joke joke = new Joke();
            		joke.setLaugh_id(object.getInteger("id"));
            		joke.setLaugh_content(object.getString("content"));
            		System.out.println("joke:"+joke.toString());
            		Joke joke2 = jokeService.findJokeById(joke.getLaugh_id());
            		if (joke2 == null) {
						int num = jokeService.insertJoke(joke);
						System.out.println("num:"+num);
					}
            		//jokes.add(joke);
            	}
                // 状态码为000000, 说明请求成功
                System.out.println("请求成功：" + jsonObject.getString("result"));
            } else {
                // 状态码非000000, 说明请求失败
                System.out.println("请求失败：" + jsonObject.getString("desc"));
            }
        } else {
            // 返回内容异常，发送请求失败，以下可根据业务逻辑自行修改
            System.out.println("发送请求失败");
        }
	}
	
	
	/**
     * 请求接口
     * @param method 请求方法
     * @param url 请求地址
     * @param headers 请求头部
     * @param params 请求参数
     * @return
     */
      public static String proxyToDesURL(String method, String url, Map<String, String> headers,
          Map<String, String> params) {
    	  try {
              SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
              RestTemplate restTemplate = new RestTemplate(requestFactory);
              //处理请求头部
              HttpHeaders requestHeaders = new HttpHeaders();
              if (headers != null && !headers.isEmpty()) {
                  Set<String> set = headers.keySet();
                  for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
                      String key = iterator.next();
                      String value = headers.get(key);
                      requestHeaders.add(key, value);
                  }
              }
              //处理请求参数
              MultiValueMap<String, String> paramList = new LinkedMultiValueMap<String, String>();
              if (params != null && !params.isEmpty()) {
                  if(method.equalsIgnoreCase("GET"))
                  {
                      url += "?";
                      Set<String> set = params.keySet();
                      for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
                          String key = iterator.next();
                          String value = params.get(key);
                          url += key+"="+value+"&";
                      }
                      url = url.substring(0, url.length() - 1);
                  }
                  else
                  {
                      Set<String> set = params.keySet();
                      for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
                          String key = iterator.next();
                          String value = params.get(key);
                          paramList.add(key, value);
                      }
                  }
              }
              requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
              HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
                      paramList, requestHeaders);
              //处理请求方法
              HttpMethod requestType = HttpMethod.GET;
              method = method.toUpperCase();
              switch (method) {
              case "GET":
                  requestType = HttpMethod.GET;
                  break;
              case "POST":
                  requestType = HttpMethod.POST;
                  break;
              case "PUT":
                  requestType = HttpMethod.PUT;
                  break;
              case "DELETE":
                  requestType = HttpMethod.DELETE;
                  break;
              case "HEAD":
                  requestType = HttpMethod.HEAD;
                  break;
              case "OPTIONS":
                  requestType = HttpMethod.OPTIONS;
                  break;
              default:
                  requestType = HttpMethod.GET;
                  break;
              }
              ResponseEntity<String> responseEntity = restTemplate.exchange(url, requestType, requestEntity,
                      String.class, params);
              //获取返回结果
              return responseEntity.getBody();
          } catch (Exception e) {
              // TODO: handle exception
              e.printStackTrace();
          }
          return null;
      }
}