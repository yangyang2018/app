package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;

/**
 * 已签收
 */
public class MyReceivedOrderActivity extends MyOrderBaseActivity {

    @Override
    protected int getType() {
        return 141;
    }

    @Override
    protected String getActivityTitle() {
        return mTranslatesString.getSeller_homereceived();
    }
}
