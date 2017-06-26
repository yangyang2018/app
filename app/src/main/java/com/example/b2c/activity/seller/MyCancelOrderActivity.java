package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;

/**
 * 已取消的订单
 */
public class MyCancelOrderActivity extends MyOrderBaseActivity {

    @Override
    protected int getType() {
        return 100;
    }

    @Override
    protected String getActivityTitle() {
        return mTranslatesString.getCommon_yetcancel();
    }
}
