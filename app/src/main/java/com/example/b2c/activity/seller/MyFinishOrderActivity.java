package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;

/**
 * 已完成
 */
public class MyFinishOrderActivity extends MyOrderBaseActivity {

    @Override
    protected int getType() {
        return 142;
    }

    @Override
    protected String getActivityTitle() {
        return  mTranslatesString.getSeller_homefinish();
    }
}
