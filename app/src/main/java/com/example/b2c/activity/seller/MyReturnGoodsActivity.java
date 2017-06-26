package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;

import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.config.ConstantS;
import com.example.b2c.fragment.seller.MySalesReturnFragment;

/**
 * 退货订单
 */
public class MyReturnGoodsActivity extends MyOrderErrorActivity {


    @Override
    protected int zuoType() {
        return 0;
    }

    @Override
    protected int youType() {
        return 1;
    }

    @Override
    protected TempleBaseFragment fragment(int type) {
        return new MySalesReturnFragment(type);
    }


    @Override
    protected String getActivityTitle() {
        return mTranslatesString.getCommon_returnorder();
    }
}
