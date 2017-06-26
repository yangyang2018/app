package com.example.b2c.fragment.seller;

import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.activity.seller.DiscountCouponActivity;
import com.example.b2c.activity.seller.ReleaseProductActivity;
import com.example.b2c.activity.seller.SampleSDActivity;
import com.example.b2c.activity.seller.SupplyAreaActivity;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ImageHelper;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.base.OneDataListener;
import com.example.b2c.net.response.seller.SellerHomeBean;
import com.example.b2c.widget.RoundImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用途：卖家订单管理
 * Created by milk on 16/10/27.
 * 邮箱：649444395@qq.com
 */

public class SellerHomeFragment extends TempleBaseFragment {

    private RoundImageView seller_home_yuan;
    private TextView tv_order_num;
    private TextView order_title;
    private RelativeLayout rl_order_today;
    private TextView tv_turnover_num;
    private TextView tv_turnover_title;
    private RelativeLayout rl_orders_turnover;
    private GridView seller_gv_leibie;
    private int[] icon = new int[]{R.drawable.fabu, R.drawable.cangku,
            R.drawable.pingjia, R.drawable.cuxiao, R.drawable.gongying,
            R.drawable.gongyingchanpin};

    private List<Map<String, Object>> data_list;
    private SellerHomeBean request;
    private String[] iconName;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected void initView(View rootView) {
        seller_home_yuan= (RoundImageView) rootView.findViewById(R.id.seller_home_yuan);
        tv_order_num= (TextView) rootView.findViewById(R.id.tv_order_num);
        order_title= (TextView) rootView.findViewById(R.id.order_title);
        rl_order_today= (RelativeLayout) rootView.findViewById(R.id.rl_order_today);
        tv_turnover_num= (TextView) rootView.findViewById(R.id.tv_turnover_num);
        tv_turnover_title= (TextView) rootView.findViewById(R.id.tv_turnover_title);
        rl_orders_turnover= (RelativeLayout) rootView.findViewById(R.id.rl_orders_turnover);
        seller_gv_leibie= (GridView) rootView.findViewById(R.id.seller_gv_leibie);
        jiacu(tv_order_num);
        jiacu(tv_turnover_num);
        order_title.setText(mTranslatesString.getToday_title());
        tv_turnover_title.setText(mTranslatesString.getCommon_yingyemonney());
        iconName = new String[]{mTranslatesString.getCommon_publishgoods(),
                mTranslatesString.getCommon_depot(),mTranslatesString.getCommon_comment(),
                mTranslatesString.getCommon_cuxiao(),mTranslatesString.getCommon_gongying(),
                mTranslatesString.getCommon_ongyingchanpin()};
        initData();
        initLinter();
    }
    private void initData(){
        data_list = new ArrayList<Map<String, Object>>();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image1, R.id.text1};
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        SimpleAdapter sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.adapter_gridview, from, to);
        //配置适配器
        seller_gv_leibie.setAdapter(sim_adapter);
        //请求服务器进行数据设置
        showLoading();
        sellerRdm.sellerHome(getActivity(),ConstantS.BASE_URL+"seller /sellerHomePage.htm");
        sellerRdm.mOneDataListener=new OneDataListener() {
            @Override
            public void onSuccess(Object data, String successInfo) {
                request = (SellerHomeBean) data;
                ImageHelper.display(ConstantS.BASE_PIC_URL+ request.getShopLogo(),seller_home_yuan);
                tv_order_num.setText(request.getTodayOrderNum());
                tv_turnover_num.setText(request.getTodayTurnover());
                dissLoading();
            }
            @Override
            public void onError(int errorNO, String errorInfo) {
                ToastHelper.makeToast(errorInfo);
                dissLoading();
            }
            @Override
            public void onFinish() {
                dissLoading();
            }
            @Override
            public void onLose() {
                dissLoading();
            }
        };
    }
    //监听
    private void initLinter(){
        seller_gv_leibie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        //发布商品
                    if (request.isHasWareHouse()){
                        getIntent(getActivity(), ReleaseProductActivity.class);
                    }else{
                        ToastHelper.makeErrorToast(mTranslatesString.getCommon_pleaseselect() + mTranslatesString.getCommon_newinsertdepot());
                    }
                        break;
                    case 1:
                        //仓库中
                        Intent intent = new Intent(getActivity(), SampleSDActivity.class);
                        intent.putExtra("type", 1);
                        startActivity(intent);
                        break;
                    case 2:
                        //评价
                        break;
                    case 3:
                        //促销

                        break;
                    case 4:
                        //跳转到供应
                        getIntent(getActivity(),SupplyAreaActivity.class);
                        break;
                    case 5:
                        //供应产品
                        break;
                }
            }
        });
    }
    //字体加粗
    private void jiacu(TextView tv){
        TextPaint paint = tv.getPaint();
        paint.setFakeBoldText(true);
    }

}
