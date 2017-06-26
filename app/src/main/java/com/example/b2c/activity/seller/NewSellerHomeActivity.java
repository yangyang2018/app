package com.example.b2c.activity.seller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.config.ConstantS;
import com.example.b2c.fragment.seller.HomeMineFragment;
import com.example.b2c.fragment.seller.SellerHomeFragment;
import com.example.b2c.fragment.seller.SellerOrderFragment;
import com.example.b2c.fragment.seller.SellerShopFragment;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.base.PageListListener;
import com.example.b2c.net.response.seller.SellerShopTabBean;

import java.util.ArrayList;
import java.util.List;

public class NewSellerHomeActivity extends TempBaseActivity implements View.OnClickListener {

    private ImageButton toolbar_back;
    private TextView toolbar_title;
    private TextView toolbar_right;
    private FrameLayout tabcontent;
    private RadioButton rbt_home;
    private RadioButton rbt_order;
    private RadioButton rbt_shop;
    private RadioButton rbt_mine;
    private RadioGroup main_tab_group;
    private List<TempleBaseFragment> fragmentList;
    private List<SellerShopTabBean.Rows> leiMu;

    @Override
    public int getRootId() {
        return R.layout.activity_new_seller_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initLister();
    }

    private void initView() {
        toolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_right = (TextView) findViewById(R.id.toolbar_right_text);
        tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        rbt_home = (RadioButton) findViewById(R.id.rbt_home);
        rbt_order = (RadioButton) findViewById(R.id.rbt_order);
        rbt_shop = (RadioButton) findViewById(R.id.rbt_shop);
        rbt_mine = (RadioButton) findViewById(R.id.rbt_mine);
        main_tab_group = (RadioGroup) findViewById(R.id.main_tab_group);
        toolbar_back.setVisibility(View.GONE);
        rbt_home.setText(mTranslatesString.getCommon_home());
        rbt_order.setText(mTranslatesString.getCommon_dingdan());
        rbt_shop.setText(mTranslatesString.getCommon_shop());
        rbt_mine.setText(mTranslatesString.getCommon_mine());
        toolbar_right.setText(mTranslatesString.getCommon_setting());
    }
    private void initData(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new SellerHomeFragment());
        fragmentList.add(new SellerOrderFragment());
        fragmentList.add(new SellerShopFragment());
        fragmentList.add(new HomeMineFragment());
        loagFragment(null, new SellerHomeFragment());
    }
    private void initLister(){
        toolbar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent(NewSellerHomeActivity.this, SecurityActivity.class);
            }
        });
        main_tab_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbt_home:
                        toolbar_right.setVisibility(View.GONE);
                        loagFragment(currentFragment, fragmentList.get(0));
                        break;
                    case R.id.rbt_order:
                        toolbar_right.setVisibility(View.GONE);
                        loagFragment(currentFragment, fragmentList.get(1));
                        break;
                    case R.id.rbt_shop:
                        toolbar_right.setVisibility(View.GONE);
                        loagFragment(currentFragment, fragmentList.get(2));
                        break;
                    case R.id.rbt_mine:
                        toolbar_right.setVisibility(View.VISIBLE);
                        loagFragment(currentFragment, fragmentList.get(3));
                        setTitle(mTranslatesString.getSeller_managermanager());
                        break;
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    //定义存储当前位置的变量
    private TempleBaseFragment currentFragment;

    //点击按钮切换fragment
    private void loagFragment(TempleBaseFragment from, TempleBaseFragment to) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (from == null) {
            //第一次进入时添加默认的fragment
            ft.add(R.id.tabcontent, to).commit();
            currentFragment = to;
        } else {

            if (from != to) {
                //如果不相等的时候说明是切换fragment
                if (to.isAdded()) {
                    //没有添加过了就将点击处的fragment替换之前的
                    ft.hide(from).show(to).commit();
                } else {
                    ft.hide(from).add(R.id.tabcontent, to).commit();
                }
                currentFragment = to;
            }
        }
    }


}
