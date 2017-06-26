package com.example.b2c.net.response.seller;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 用途：
 * Created by milk on 16/11/1.
 * 邮箱：649444395@qq.com
 */

public class TuiHuoBean implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("code")
    private String code;//订单编号
    @SerializedName("deliveryNo")
    private String deliveryNo;
    @SerializedName("status")
    private int status;
    @SerializedName("auditFeedback")
    private String auditFeedback;//反馈
    @SerializedName("rejectReason")
    private String rejectReason;//拒收理由
    @SerializedName("createTime")
    private String createTime;//下单时间
    @SerializedName("receiveLinkman")
    private String receiveLinkman;//买家姓名
    @SerializedName("receiveAddress")
    private String receiveAddress;
    @SerializedName("receiveMobile")
    private String receiveMobile;
    @SerializedName("name")
    private String name;
    @SerializedName("userName")
    private String userName;
    @SerializedName("totalPrice")
    private String totalPrice;
    @SerializedName("deliveryFee")
    private String deliveryFee;
    @SerializedName("remark")
    private String remark;


    @SerializedName("samplePic")
    private String samplePic;
    @SerializedName("sampleNum")
    private String sampleNum;

    public String getSamplePic() {
        return samplePic;
    }

    public void setSamplePic(String samplePic) {
        this.samplePic = samplePic;
    }

    public String getSampleNum() {
        return sampleNum;
    }

    public void setSampleNum(String sampleNum) {
        this.sampleNum = sampleNum;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSamplePrice() {
        return samplePrice;
    }

    public void setSamplePrice(String samplePrice) {
        this.samplePrice = samplePrice;
    }

    public String getSampleProDetail() {
        return sampleProDetail;
    }

    public void setSampleProDetail(String sampleProDetail) {
        this.sampleProDetail = sampleProDetail;
    }

    @SerializedName("sampleName")

    private String sampleName;
    @SerializedName("samplePrice")
    private String samplePrice;
    @SerializedName("sampleProDetail")
    private String sampleProDetail;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAuditFeedback() {
        return auditFeedback;
    }

    public void setAuditFeedback(String auditFeedback) {
        this.auditFeedback = auditFeedback;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReceiveLinkman() {
        return receiveLinkman;
    }

    public void setReceiveLinkman(String receiveLinkman) {
        this.receiveLinkman = receiveLinkman;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getRemark() {
        return remark;
    }
}
