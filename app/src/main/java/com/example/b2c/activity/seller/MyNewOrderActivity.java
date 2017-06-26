package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;

/**
 * 新订单
 */
public class MyNewOrderActivity extends MyOrderBaseActivity {


    @Override
    protected int getType() {
        return 10;
    }

    @Override
    protected String getActivityTitle() {
        return mTranslatesString.getSeller_homenewtitle();
    }
}
