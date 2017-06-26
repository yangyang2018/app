package com.example.b2c.net.listener;

import com.example.b2c.net.listener.base.RequestfinishListener;

public interface LoginListener  extends RequestfinishListener {
	void onSuccess(int userId, String token, String type,int nextStep,int companyType);
	void onError(int errorNO, String errorInfo);
}
