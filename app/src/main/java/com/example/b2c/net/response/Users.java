package com.example.b2c.net.response;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("loginName")
    private String loginName;
    @SerializedName("userType")
    private int userType;
    @SerializedName("userId")
    private int userId;
    /**
     * 10：商家信息提交
     * 20：公司负责人信息提交
     * 40：结算账号信息提交
     */
    @SerializedName("step")
    private int step;
    @SerializedName("token")
    private String token;
    @SerializedName("deliveryCompanyId")
    private int deliveryCompanyId;
    @SerializedName("nextStep")
    private int nextStep;//下一步要跳转到哪一步

    public int getNextStep() {
        return nextStep;
    }

    public void setNextStep(int nextStep) {
        this.nextStep = nextStep;
    }

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    @SerializedName("companyType")
    private int companyType;//卖家是个人还是商家
    public int getDeliveryCompanyId() {
        return deliveryCompanyId;
    }

    public void setDeliveryCompanyId(int deliveryCompanyId) {
        this.deliveryCompanyId = deliveryCompanyId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Users() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    private ResponseResult result;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ResponseResult getResult() {
        return result;
    }

    public void setResult(ResponseResult result) {
        this.result = result;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
