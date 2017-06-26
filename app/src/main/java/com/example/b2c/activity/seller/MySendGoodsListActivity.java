package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;
/**
 *已发货
 */
public class MySendGoodsListActivity extends MyOrderBaseActivity {

    @Override
    protected int getType() {
        return 30;
    }

    @Override
    protected String getActivityTitle() {
        return mTranslatesString.getSeller_homesendtitle();
    }
}
