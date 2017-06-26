package com.example.b2c.net.response.seller;

import java.io.Serializable;

/**
 * 卖家首页的解析
 */

public class SellerHomeBean implements Serializable{
    private String shopLogo;
    private String todayOrderNum;
    private boolean hasWareHouse;

    public boolean isHasWareHouse() {
        return hasWareHouse;
    }

    public void setHasWareHouse(boolean hasWareHouse) {
        this.hasWareHouse = hasWareHouse;
    }

    public String getTodayTurnover() {
        return todayTurnover;
    }

    public void setTodayTurnover(String todayTurnover) {
        this.todayTurnover = todayTurnover;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getTodayOrderNum() {
        return todayOrderNum;
    }

    public void setTodayOrderNum(String todayOrderNum) {
        this.todayOrderNum = todayOrderNum;
    }

    private String todayTurnover;
}
