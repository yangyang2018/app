package com.example.b2c.activity.seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ImageHelper;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.base.OneDataListener;
import com.example.b2c.net.response.seller.OrderDeailsBean;


public class MyShopDetailsActivity extends TempBaseActivity {

    private TextView tv_shop_name;
    private ListView lv_shop_zhongjian;
    private TextView tv_commonity_jiage_title;
    private TextView tv_commonity_jiage;
    private TextView tv_freight_title;
    private TextView tv_freight_express;
    private TextView tv_heji_title;
    private TextView tv_total_jiage;
    private TextView tv_shouuoren_title;
    private TextView tv_shouhuo_anme;
    private TextView tv_shouhuodizhi_title;
    private TextView tv_receipe_address;
    private TextView tv_shouhuo_phone_title;
    private TextView tv_shouhuo_phone;
    private TextView tv_shoukuanren_title;
    private TextView tv_shoukuanren_anme;
    private TextView tv_shoukuandizhi_title;
    private TextView tv_shoukuan_address;
    private TextView tv_shoukuan_phone_title;
    private TextView tv_shoukuan_phone;
    private TextView tv_tuihuo_title;
    private TextView tv_return_order_num;
    private TextView tv_time_title;
    private TextView tv_order_time;
    private OrderDeailsBean orderDeailsBean;
    private View view;
    private ViewHolder viewHolder;
    private MyAdapter myAdapter;
    private LinearLayout ll_kuaidi_phone;
    private LinearLayout ll_qianshoutime;
    private TextView tv_kauidinum_title;
    private TextView tv_courier_phone;
    private TextView tv_qianshou_title;
    private TextView tv_qianshou_time;
    private TextView tv_jujueliyou;
    private String id;

    @Override
    public int getRootId() {
        return R.layout.activity_my_shop_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);
        lv_shop_zhongjian = (ListView) findViewById(R.id.lv_shop_zhongjian);
        tv_commonity_jiage_title = (TextView) findViewById(R.id.tv_commonity_jiage_title);
        tv_commonity_jiage = (TextView) findViewById(R.id.tv_commonity_jiage);
        tv_freight_title = (TextView) findViewById(R.id.tv_freight_title);
        tv_freight_express = (TextView) findViewById(R.id.tv_freight_express);
        tv_heji_title = (TextView) findViewById(R.id.tv_heji_title);
        tv_total_jiage = (TextView) findViewById(R.id.tv_total_jiage);
        tv_shouuoren_title = (TextView) findViewById(R.id.tv_shouuoren_title);
        tv_shouhuo_anme = (TextView) findViewById(R.id.tv_shouhuo_anme);
        tv_shouhuodizhi_title = (TextView) findViewById(R.id.tv_shouhuodizhi_title);
        tv_receipe_address = (TextView) findViewById(R.id.tv_receipe_address);
        tv_shouhuo_phone_title = (TextView) findViewById(R.id.tv_shouhuo_phone_title);
        tv_shouhuo_phone = (TextView) findViewById(R.id.tv_shouhuo_phone);
        tv_shoukuanren_title = (TextView) findViewById(R.id.tv_shoukuanren_title);
        tv_shoukuanren_anme = (TextView) findViewById(R.id.tv_shoukuanren_anme);
        tv_shoukuandizhi_title = (TextView) findViewById(R.id.tv_shoukuandizhi_title);
        tv_shoukuan_address = (TextView) findViewById(R.id.tv_shoukuan_address);
        tv_shoukuan_phone_title = (TextView) findViewById(R.id.tv_shoukuan_phone_title);
        tv_shoukuan_phone = (TextView) findViewById(R.id.tv_shoukuan_phone);
        tv_tuihuo_title = (TextView) findViewById(R.id.tv_tuihuo_title);
        tv_return_order_num = (TextView) findViewById(R.id.tv_return_order_num);
        tv_time_title = (TextView) findViewById(R.id.tv_time_title);
        tv_order_time = (TextView) findViewById(R.id.tv_order_time);
        tv_jujueliyou = (TextView) findViewById(R.id.tv_jujueliyou);

        ll_kuaidi_phone = (LinearLayout) findViewById(R.id.ll_kuaidi_phone);
        ll_qianshoutime = (LinearLayout) findViewById(R.id.ll_qianshoutime);
        tv_kauidinum_title = (TextView) findViewById(R.id.tv_kauidinum_title);
        tv_courier_phone = (TextView) findViewById(R.id.tv_courier_phone);
        tv_qianshou_title = (TextView) findViewById(R.id.tv_qianshou_title);
        tv_qianshou_time = (TextView) findViewById(R.id.tv_qianshou_time);
    }
    private String url;
    private void initData() {
        setTitle(mTranslatesString.getCommon_orderdetail());
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        int type = intent.getIntExtra("type",12220);
        showKongjian(type);
        showLoading();
        if (type==0||type==1){
            url= ConstantS.BASE_URL + "seller/refund/detail/" + id + ".htm";
        }else{
            url= ConstantS.BASE_URL + "seller/order/detail/" + id + ".htm";
        }
        sellerRdm.getOrederDeails(getApplication(),url);
        sellerRdm.mOneDataListener = new OneDataListener() {
            @Override
            public void onSuccess(Object data, String successInfo) {
                orderDeailsBean = (OrderDeailsBean) data;
                showData(orderDeailsBean);
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

    private void showData(OrderDeailsBean orderDeailsBean) {
        tv_commonity_jiage_title.setText(mTranslatesString.getCommon_sampleprice() + ": ");
        tv_freight_title.setText(mTranslatesString.getCommon_freight() + ": ");
        tv_heji_title.setText(mTranslatesString.getCommon_sum() + ": ");
        tv_shouuoren_title.setText(mTranslatesString.getCommon_receiveman() + ": ");
        tv_shouhuodizhi_title.setText(mTranslatesString.getCommon_receivelocation() + ": ");
        tv_shouhuo_phone_title.setText(mTranslatesString.getCommon_lianxiphone() + ": ");
        tv_shoukuanren_title.setText(mTranslatesString.getCommon_cashman() + ": ");
        tv_shoukuandizhi_title.setText(mTranslatesString.getConnom_shoukuandizhi() + ": ");
        tv_shoukuan_phone_title.setText(mTranslatesString.getCommon_lianxiphone() + ": ");
        tv_tuihuo_title.setText(mTranslatesString.getOrder_no() + ": ");
        tv_time_title.setText(mTranslatesString.getSeller_ordertime() + ": ");
        tv_kauidinum_title.setText(mTranslatesString.getCommon_courierphone()+": ");
        tv_qianshou_title.setText(mTranslatesString.getCommon_qianshoutime()+": ");

        tv_shop_name.setText(orderDeailsBean.getShopName());
        tv_commonity_jiage.setText("Ks " + orderDeailsBean.getGoodsTotalPrice());
        tv_freight_express.setText("Ks " + orderDeailsBean.getDeliveryFee());
        tv_total_jiage.setText("Ks " + orderDeailsBean.getActualPayPrice());
        tv_shouhuo_anme.setText(orderDeailsBean.getReceiveLinkman());
        tv_receipe_address.setText(orderDeailsBean.getReceiveAddress());
        tv_shouhuo_phone.setText(orderDeailsBean.getReceiveMobile());
        tv_shoukuanren_anme.setText(orderDeailsBean.getPayAddress());
        tv_shoukuan_address.setText(orderDeailsBean.getPayLinkman());
        tv_shoukuan_phone.setText(orderDeailsBean.getPayMobile());
        tv_order_time.setText(orderDeailsBean.getCreateTime());
        tv_return_order_num.setText(orderDeailsBean.getCode());
        tv_courier_phone.setText(orderDeailsBean.getDeliveryAccountMobile());
        tv_qianshou_time.setText(orderDeailsBean.getReceiptTime());

        //设置适配器
        myAdapter = new MyAdapter();
        lv_shop_zhongjian.setAdapter(myAdapter);

    }
    //通过是哪个姐买呢来进行不同的选择
    private void showKongjian(int type){
        switch (type){
            case 30:
                ll_qianshoutime.setVisibility(View.VISIBLE);
                break;
            case 141:
            case 142:
                ll_qianshoutime.setVisibility(View.VISIBLE);
                ll_kuaidi_phone.setVisibility(View.VISIBLE);
                break;
            case 0:
                //拒绝订单待处理
                tv_jujueliyou.setVisibility(View.VISIBLE);
                tv_jujueliyou.setText(orderDeailsBean.getRejectReason());
                break;


        }
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return orderDeailsBean.getOrderDetails().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getApplication()).inflate(R.layout.new_order_two_adapter, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            ImageHelper.display(ConstantS.BASE_PIC_URL+orderDeailsBean.getOrderDetails().get(i).getSamplePic(),viewHolder.iv_new_order);
            viewHolder.tv_new_order_biaoti.setText(orderDeailsBean.getOrderDetails().get(i).getName());
            viewHolder.tv_new_order_content.setText(orderDeailsBean.getOrderDetails().get(i).getSpecification());
            viewHolder.tv_new_order_jiage.setText("Ks "+orderDeailsBean.getOrderDetails().get(i).getPrice());
            viewHolder.tv_new_order_shuliang.setText("×"+orderDeailsBean.getOrderDetails().get(i).getAmount());
            return view;
        }


    }
    public static class ViewHolder {
        public View rootView;
        public ImageView iv_new_order;
        public TextView tv_new_order_biaoti;
        public TextView tv_new_order_content;
        public TextView tv_new_order_jiage;
        public TextView tv_new_order_shuliang;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_new_order = (ImageView) rootView.findViewById(R.id.iv_new_order);
            this.tv_new_order_biaoti = (TextView) rootView.findViewById(R.id.tv_new_order_biaoti);
            this.tv_new_order_content = (TextView) rootView.findViewById(R.id.tv_new_order_content);
            this.tv_new_order_jiage = (TextView) rootView.findViewById(R.id.tv_new_order_jiage);
            this.tv_new_order_shuliang = (TextView) rootView.findViewById(R.id.tv_new_order_shuliang);
        }

    }
}
