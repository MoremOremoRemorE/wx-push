package com.aguo.wxpush.service.impl;

import com.aguo.wxpush.constant.ConfigConstant;
import com.aguo.wxpush.service.ProverbService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 14:50
 * @Description: TODO
 */
@Service
public class ProverbServiceImpl implements ProverbService {
    @Autowired
    private ConfigConstant configConstant;

    @Override
    public String getOneProverbRandom() {
        String proverb;
        do {
            proverb = null;
            try {
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                Request request = new Request.Builder()
                        .url("https://api.xygeng.cn/one")
                        .get()
                        .addHeader("Content-Type", "")
                        .build();
                Response response = client.newCall(request).execute();
                //解析
                JSONObject jsonObject = JSONObject.parseObject(response.body().string());
                JSONObject content = jsonObject.getJSONObject("data");
                proverb = content.getString("content");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (proverb.length() > 25);
        return proverb;
    }

    @Override
    public String translateToEnglish(String sentence) {
        String result = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url("https://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + sentence)
                    .get()
                    .addHeader("Content-Type", "")
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
            //解析
            JSONObject jsonObject = JSONObject.parseObject(result);
            result = jsonObject.getJSONArray("translateResult").getJSONArray(0).getJSONObject(0).getString("tgt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getOneNormalProverb() {
        String proverb = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "titleID=" + new Random().nextInt(9));
            Request request = new Request.Builder()
                    .url("https://eolink.o.apispace.com/myjj/common/aphorism/getAphorismList")
                    .method("POST", body)
//                    .addHeader("X-APISpace-Token",configConstant.getToken())
                    .addHeader("Authorization-Type", "apikey")
                    .addHeader("Content-Type", "")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            //随机取出一条句子
            String s = (String) JSONObject.parseArray((String) jsonObject.getJSONArray("result").getJSONObject(0).get("words")).get(new Random().nextInt(100));
            //去除无关元素
            proverb = s.replaceAll("^.*、", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return proverb;
    }

    @Override
    public String getGoodTextRandom() {
        //java环境中文传值时，需特别注意字符编码问题
        String httpUrl = "http://api.tianapi.com/caihongpi/index";
        String proverb;
        proverb = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url(httpUrl + "?key=" + configConstant.chKey)
                    .get()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            //解析
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            if (null != jsonObject && Integer.parseInt(jsonObject.getString("code")) == 200) {
                JSONArray list = jsonObject.getJSONArray("newslist");
                JSONObject jsonObject1 = (JSONObject) list.get(0);
                proverb = jsonObject1.getString("content");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return proverb;
    }
}
