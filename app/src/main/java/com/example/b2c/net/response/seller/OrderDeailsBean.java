package com.example.b2c.net.response.seller;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情的bean
 */

public class OrderDeailsBean implements Serializable{
    private String actualPayPrice;
    private String createTime;
    private String deliveryFee;
    private String goodsTotalPrice;
    private int id;
    private String payAddress;
    private String payLinkman;
    private String payMobile;
    private String receiveAddress;
    private String receiveLinkman;
    private String receiveMobile;
    private String remark;
    private int shopId;
    private String shopLogo;
    private String shopName;
    private String totalPrice;
    private String deliveryAccountMobile;
    private String receiptTime;

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    private String rejectReason;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeliveryAccountMobile() {
        return deliveryAccountMobile;
    }

    public void setDeliveryAccountMobile(String deliveryAccountMobile) {
        this.deliveryAccountMobile = deliveryAccountMobile;
    }

    public String getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
    }

    private String code;
    private List<OrderDetails>orderDetails;

    public String getActualPayPrice() {
        return actualPayPrice;
    }

    public void setActualPayPrice(String actualPayPrice) {
        this.actualPayPrice = actualPayPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(String goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public String getPayLinkman() {
        return payLinkman;
    }

    public void setPayLinkman(String payLinkman) {
        this.payLinkman = payLinkman;
    }

    public String getPayMobile() {
        return payMobile;
    }

    public void setPayMobile(String payMobile) {
        this.payMobile = payMobile;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveLinkman() {
        return receiveLinkman;
    }

    public void setReceiveLinkman(String receiveLinkman) {
        this.receiveLinkman = receiveLinkman;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public class OrderDetails{
        private int amount;
        private int id;
        private String name;
        private String price;
        private String samplePic;
        private String specification;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSamplePic() {
            return samplePic;
        }

        public void setSamplePic(String samplePic) {
            this.samplePic = samplePic;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }
    }
}
