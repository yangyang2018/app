package com.example.b2c.activity.seller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;

public class SupplyAreaActivity extends TempBaseActivity {

    private ImageView back;
    private ImageView shopping_car;
    private TextView tv_seate_name;
    private LinearLayout ll_select_area;
    private EditText et_seek;
    private FrameLayout fl_gongying;

    @Override
    public int getRootId() {
        return R.layout.activity_supply_area;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        shopping_car = (ImageView) findViewById(R.id.shopping_car);
        tv_seate_name = (TextView) findViewById(R.id.tv_seate_name);
        ll_select_area = (LinearLayout) findViewById(R.id.ll_select_area);
        et_seek = (EditText) findViewById(R.id.et_seek);
        fl_gongying = (FrameLayout) findViewById(R.id.fl_gongying);
    }


}
