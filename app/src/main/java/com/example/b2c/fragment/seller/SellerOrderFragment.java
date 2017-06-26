package com.example.b2c.fragment.seller;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.activity.seller.MyCancelOrderActivity;
import com.example.b2c.activity.seller.MyFinishOrderActivity;
import com.example.b2c.activity.seller.MyNewOrderActivity;
import com.example.b2c.activity.seller.MyOrderBaseActivity;
import com.example.b2c.activity.seller.MyReceivedOrderActivity;
import com.example.b2c.activity.seller.MyRefuseGoodsListActivity;
import com.example.b2c.activity.seller.MyReturnGoodsActivity;
import com.example.b2c.activity.seller.MySendGoodsListActivity;
import com.example.b2c.activity.seller.MyWaitingGoodsListActivity;
import com.example.b2c.activity.seller.NewTodayOrderActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerOrderFragment extends TempleBaseFragment {
    private GridView gv_seller_order1;
    private GridView gv_seller_order2;
    private int[] icon1 = new int[]{R.drawable.daifahuo, R.drawable.yes_fahuo,
            R.drawable.qianhou, R.drawable.wancheng, R.drawable.jujue_order,
            R.drawable.tuihuo};
    private int[] icon2 = new int[]{R.drawable.today_order, R.drawable.new_order,
            R.drawable.quxiao_order};
    private String[] iconName1;
    private String[] iconName2;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_seller_order;
    }

    @Override
    protected void initView(View rootView) {
        gv_seller_order1= (GridView) rootView.findViewById(R.id.gv_seller_order1);
        gv_seller_order2= (GridView) rootView.findViewById(R.id.gv_seller_order2);
        iconName1 = new String[]{mTranslatesString.getSeller_homewaitingtitle(),
                mTranslatesString.getSeller_homesendtitle(),mTranslatesString.getSeller_homereceived(),
                mTranslatesString.getSeller_homefinish(),mTranslatesString.getSeller_homerefuse(),
                mTranslatesString.getCommon_returngoods()};
        iconName2 = new String[]{mTranslatesString.getToday_title(),
                mTranslatesString.getSeller_homenewtitle(),mTranslatesString.getCommon_cancelorder()};
        initData();
        initLinster();
    }
    private void initData(){
       List<Map<String, Object>> data_list1 =  new ArrayList<Map<String, Object>>();
       List<Map<String, Object>> data_list2 =  new ArrayList<Map<String, Object>>();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image1, R.id.text1};
        for (int i = 0; i < icon1.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon1[i]);
            map.put("text", iconName1[i]);
            data_list1.add(map);
        }
        for (int i = 0; i < icon2.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon2[i]);
            map.put("text", iconName2[i]);
            data_list2.add(map);
        }

        SimpleAdapter sim_adapter1 = new SimpleAdapter(getActivity(), data_list1, R.layout.adapter_gridview, from, to);
        SimpleAdapter sim_adapter2 = new SimpleAdapter(getActivity(), data_list2, R.layout.adapter_gridview, from, to);
        //配置适配器
        gv_seller_order1.setAdapter(sim_adapter1);
        gv_seller_order2.setAdapter(sim_adapter2);

    }
    private void initLinster(){
        gv_seller_order1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        //待发货
                        getIntent(getActivity(), MyWaitingGoodsListActivity.class);
                        break;
                    case 1:
                        //已发货
                        getIntent(getActivity(), MySendGoodsListActivity.class);
                        break;
                    case 2:
                        //已签收
                        getIntent(getActivity(), MyReceivedOrderActivity.class);
                        break;
                    case 3:
                        //已完成
                        getIntent(getActivity(), MyFinishOrderActivity.class);
                        break;
                    case 4:
                        //拒绝订单
                        getIntent(getActivity(), MyRefuseGoodsListActivity.class);
                        break;
                    case 5:
                        //退货
                        getIntent(getActivity(), MyReturnGoodsActivity.class);
                        break;
                }
            }
        });
        gv_seller_order2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:
                            //今日订单
                            getIntent(getActivity(), NewTodayOrderActivity.class);
                            break;
                        case 1:
                            //新订单
                            getIntent(getActivity(), MyNewOrderActivity.class);
                            break;
                        case 2:
                            //取消的订单
                            getIntent(getActivity(), MyCancelOrderActivity.class);
                            break;
                    }
            }
        });
    }
}
