package com.example.b2c.net.listener.base;

/**
 * 用途：
 * Created by milk on 16/11/4.
 * 邮箱：649444395@qq.com
 */

public interface TwoStringListener extends RequestfinishListener {

    void onSuccess(String succes1, String success2);

    void onError(int errorNO, String errorInfo);
}