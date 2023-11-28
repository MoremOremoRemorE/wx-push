package com.aguo.wxpush.service;

/**
 * @Desceiption TODO
 * @Author jinchaoqun
 * @Date 2023/3/30 11:38
 * @Version 1.0
 **/
public interface SendSms {
    //发送手机验证码
    boolean send(String phone, String code);
}
