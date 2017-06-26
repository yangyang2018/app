package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.config.ConstantS;
import com.example.b2c.net.listener.base.PageListListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代金券
 */
public class DiscountCouponActivity extends TempBaseActivity {

    private XRecyclerView recycleView_discount;
    private RelativeLayout empty;

    @Override
    public int getRootId() {
        return R.layout.activity_discount_coupon;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData(0);
    }

    private void initView() {
        recycleView_discount = (XRecyclerView) findViewById(R.id.recycleView_discount);
        empty = (RelativeLayout) findViewById(R.id.empty);
    }
    private void initData(int startRow){
        Map<String,String>map=new HashMap<>();
        map.put("startRow",""+startRow);
//       TODO DiscountCouponAdapter设配器类   请求的数据解析要去写bean
        sellerRdm.getVoucher(getApplication(), ConstantS.BASE_URL+"shopVoucher/list.htm",map);
        sellerRdm.mPageListListener=new PageListListener() {
            @Override
            public void onSuccess(List list) {

            }

            @Override
            public void onError(int errorNO, String errorInfo) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onLose() {

            }
        };
    }
}
