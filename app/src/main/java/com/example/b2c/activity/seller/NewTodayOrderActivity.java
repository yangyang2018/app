package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.config.Configs;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.seller.TodayOrderListener;

import java.util.List;

/**
 * 今日订单
 */
public class NewTodayOrderActivity extends TempBaseActivity {

    private TextView toolbar_title;
    private TextView tv_today_order_money;
    private TextView tv_money_title;
    private TextView order_num;
    private TextView order_num_title;
    private TextView buyer_num;
    private TextView buyer_num_title;
    private TextView commonity_num;
    private TextView commonity_num_title;

    @Override
    public int getRootId() {
        return R.layout.activity_new_today_order;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        tv_today_order_money = (TextView) findViewById(R.id.tv_today_order_money);
        tv_money_title = (TextView) findViewById(R.id.tv_money_title);
        order_num = (TextView) findViewById(R.id.order_num);
        order_num_title = (TextView) findViewById(R.id.order_num_title);
        buyer_num = (TextView) findViewById(R.id.buyer_num);
        buyer_num_title = (TextView) findViewById(R.id.buyer_num_title);
        commonity_num = (TextView) findViewById(R.id.commonity_num);
        commonity_num_title = (TextView) findViewById(R.id.commonity_num_title);

        tv_money_title.setText(mTranslatesString.getGoods_merchandise());
        order_num_title.setText(mTranslatesString.getToday_paynum());
        buyer_num_title.setText(mTranslatesString.getToday_buyernum());
        commonity_num_title.setText(mTranslatesString.getToday_goodsnum());
    }
    private void initData() {
        showLoading();
        sellerRdm.getTodayOrderList(ConstantS.BASE_URL + "seller/order/today/list.htm");
        sellerRdm.mTodayOrderListener = new TodayOrderListener() {
            @Override
            public void onSuccess(List mList, final String order, final int buyer, final int sample, final int money) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_today_order_money.setText( Configs.CURRENCY_UNIT + order);
                        buyer_num.setText(sample + "");
                        commonity_num.setText(money + "");
                        order_num.setText(buyer + "");
                    }
                });
            }
            @Override
            public void onError(int errorNO, String errorInfo) {
                ToastHelper.makeErrorToast(errorInfo);

            }
            @Override
            public void onFinish() {
                dissLoading();
            }
            @Override
            public void onLose() {
                ToastHelper.makeErrorToast(request_failure);
            }
        };
    }
}
