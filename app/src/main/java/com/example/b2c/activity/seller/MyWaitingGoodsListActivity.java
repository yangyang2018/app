package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;

/**
 * 待发货
 */
public class MyWaitingGoodsListActivity extends MyOrderBaseActivity {

    @Override
    protected int getType() {
        return 20;
    }

    @Override
    protected String getActivityTitle() {
        return mTranslatesString.getSeller_homewaitingtitle();
    }
}
