package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;

import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.config.ConstantS;
import com.example.b2c.fragment.seller.MyRefuseWaitingFragment;

/**
 * 拒绝订单
 */
public class MyRefuseGoodsListActivity extends MyOrderErrorActivity {

    @Override
    protected int zuoType() {
        return 130;
    }

    @Override
    protected int youType() {
        return 140;
    }

    @Override
    protected TempleBaseFragment fragment(int type) {
        return new MyRefuseWaitingFragment(type);
    }


    @Override
    protected String getActivityTitle() {
        return mTranslatesString.getSeller_homerefuse();
    }
}
