package com.aguo.wxpush.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 12:51
 * @Description: TODO
 */
@Component
@ConfigurationProperties(prefix = "wx.config")
public class ConfigConstant {
    @Value("${wx.config.appId}")
    public String appId;
    @Value("${wx.config.appSecret}")
    public String appSecret;
    @Value("${wx.config.templateId}")
    public String templateId;

    public ArrayList<String> openidList;

    public String getAppId() {
        return appId;
    }

    @Value("${weather.config.appid}")
    public String weatherAppId;

    @Value("${weather.config.appSecret}")
    public String weatherAppSecret;
    @Value("${weather.config.city}")
    public String city;
    @Value("${message.config.togetherDate}")
    public String togetherDate;
    @Value("${message.config.birthday1}")
    public  String birthday1;
    @Value("${chap.key}")
    public  String chKey;

    @Value("${message.config.message}")
    public  String message;

//    @Value("${ApiSpace.token}")
//    public String token;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getChKey() {
        return chKey;
    }

    public void setChKey(String chKey) {
        this.chKey = chKey;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }


    public String getWeatherAppId() {
        return weatherAppId;
    }

    public void setWeatherAppId(String weatherAppId) {
        this.weatherAppId = weatherAppId;
    }

    public String getWeatherAppSecret() {
        return weatherAppSecret;
    }

    public void setWeatherAppSecret(String weatherAppSecret) {
        this.weatherAppSecret = weatherAppSecret;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTogetherDate() {
        return togetherDate;
    }

    public void setTogetherDate(String togetherDate) {
        this.togetherDate = togetherDate;
    }

    public String getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(String birthday1) {
        this.birthday1 = birthday1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getOpenidList() {
        return openidList;
    }

    public void setOpenidList(ArrayList<String> openidList) {
        this.openidList = openidList;
    }
}
