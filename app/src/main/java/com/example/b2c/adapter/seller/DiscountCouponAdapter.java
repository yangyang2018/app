package com.example.b2c.adapter.seller;

import android.content.Context;
import android.view.View;

import com.example.b2c.R;
import com.example.b2c.adapter.base.XRcycleViewAdapterBase;

/**
 * Created by ThinkPad on 2017/4/27.
 */

public class DiscountCouponAdapter extends XRcycleViewAdapterBase{
    @Override
    public int getItemPostion() {
        return 0;
    }

    @Override
    public int getLayoutID() {
        return R.layout.adapter_discount;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void initData(MyViewHolder holder, int position) {

    }

    @Override
    public void initView(View itemView) {

    }
}
