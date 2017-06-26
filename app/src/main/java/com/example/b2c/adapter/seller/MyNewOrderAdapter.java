package com.example.b2c.adapter.seller;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.adapter.base.XRcycleViewAdapterBase;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ImageHelper;
import com.example.b2c.net.response.seller.OrderDetail;
import com.example.b2c.net.response.seller.OrderList;
import com.example.b2c.net.response.translate.MobileStaticTextCode;
import com.example.b2c.widget.nestlistview.NestFullListView;
import com.example.b2c.widget.nestlistview.NestFullListViewAdapter;
import com.example.b2c.widget.nestlistview.NestFullViewHolder;

import java.util.List;

/**
 * Created by ThinkPad on 2017/4/21.
 */

public abstract class MyNewOrderAdapter extends XRcycleViewAdapterBase {
    private Context context;
    private List<OrderList> mOrderLists;
    private MobileStaticTextCode mTranslatesString;
    private TextView tv_new_order_title;
    private TextView tv_new_order_num;
    private TextView tv_new_order_quxiao;
    private TextView tv_new_order_queding;
    private TextView xidanshijian;
    private TextView tv_xiadan_time;
    private NestFullListView lv_order;
    private LinearLayout rl_order_dianji;
    private LinearLayout ll_order_buyes_message;
    private LinearLayout ll_order_time;
    private LinearLayout ll_zuixiamian;
    private TextView tv_order_buyersname;
    private TextView tv_order_contact;
    private TextView quanbu_money;
    private TextView tv_order_money;
private int type;
    public MyNewOrderAdapter(Context context, List<OrderList> mOrderLists,
                             MobileStaticTextCode mTranslatesString,int type) {
        this.context = context;
        this.mOrderLists = mOrderLists;
        this.mTranslatesString=mTranslatesString;
        this.type=type;
    }

    @Override
    public int getItemPostion() {
        return mOrderLists.size();
    }

    @Override
    public int getLayoutID() {
        return R.layout.new_order_adapter;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void initData(MyViewHolder holder, final int position) {
        tv_new_order_title.setText(mTranslatesString.getOrder_no()+":");
        xidanshijian.setText(mTranslatesString.getSeller_ordertime()+": ");
        tv_new_order_queding.setText(mTranslatesString.getCommon_makesure());
        tv_new_order_quxiao.setText(mTranslatesString.getNotice_cancel());
        quanbu_money.setText(mTranslatesString.getCommon_totalprice()+": ");
        tv_order_money.setText("Ks "+mOrderLists.get(position).getTotalPrice()+
                "("+mTranslatesString.getCommon_hanyunfei()+" Ks "+mOrderLists.get(position).getDeliveryFee()+")");
        tv_new_order_num.setText(mOrderLists.get(position).getCode());
        tv_xiadan_time.setText(mOrderLists.get(position).getCreateTime());
        switch (type){
            case 100:
                //说明是取消订单的页面
                ll_order_buyes_message.setVisibility(View.VISIBLE);
                rl_order_dianji.setVisibility(View.GONE);
                ll_order_time.setVisibility(View.GONE);
                tv_order_buyersname.setText(mTranslatesString.getCommon_buyer()+": "+mOrderLists.get(position).getReceiveLinkman());
                tv_order_contact.setText(mTranslatesString.getCommon_linkway()+": "+mOrderLists.get(position).getReceiveMobile());
                break;
            case 20:
                //待发货
                tv_new_order_queding.setVisibility(View.GONE);
                break;
            case 30:
                //已发货
                ll_zuixiamian.setVisibility(View.GONE);
                break;
            case 141:
                //已签收
                ll_zuixiamian.setVisibility(View.GONE);
                break;
            case 142:
                //已完成
                ll_zuixiamian.setVisibility(View.GONE);
                break;
            case 130:
                tv_new_order_quxiao.setVisibility(View.GONE);
                ll_order_time.setVisibility(View.GONE);
                break;
            case 140:
                rl_order_dianji.setVisibility(View.GONE);
                break;

        }
        lv_order.setAdapter(new NestFullListViewAdapter<OrderDetail>(R.layout.new_order_two_adapter,mOrderLists.get(position).getOrderDetails()) {
            @Override
            public void onBind(int pos, OrderDetail orderDetail, NestFullViewHolder holder) {
                holder.setText(R.id.tv_new_order_biaoti,orderDetail.getName());
                holder.setText(R.id.tv_new_order_content,orderDetail.getSpecification());
                holder.setText(R.id.tv_new_order_jiage,"Ks "+orderDetail.getPrice());
                holder.setText(R.id.tv_new_order_shuliang,"×"+orderDetail.getAmount());

                holder.setIamge(R.id.iv_new_order,ConstantS.BASE_PIC_URL+orderDetail.getSampleImage());
            }
        });
        tv_new_order_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type==130){
                    lister(mOrderLists.get(position).getId(),2,position);
                }else{
                lister(mOrderLists.get(position).getId(),0,position);
                }
            }
        });
        tv_new_order_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    lister(mOrderLists.get(position).getId(),1,position);


            }
        });
        //
//        ImageHelper.display();
    }

    @Override
    public void initView(View rootView) {
        tv_new_order_title = (TextView) rootView.findViewById(R.id.tv_new_order_title);
        tv_new_order_num = (TextView) rootView.findViewById(R.id.tv_new_order_num);
        tv_new_order_quxiao = (TextView) rootView.findViewById(R.id.tv_new_order_quxiao);
        tv_new_order_queding = (TextView) rootView.findViewById(R.id.tv_new_order_queding);
        xidanshijian = (TextView) rootView.findViewById(R.id.xidanshijian);
        tv_xiadan_time = (TextView) rootView.findViewById(R.id.tv_xiadan_time);
        lv_order = (NestFullListView) rootView.findViewById(R.id.lv_order);
        rl_order_dianji = (LinearLayout) rootView.findViewById(R.id.rl_order_dianji);
        ll_order_buyes_message = (LinearLayout) rootView.findViewById(R.id.ll_order_buyes_message);
        ll_order_time = (LinearLayout) rootView.findViewById(R.id.ll_order_time);
        ll_zuixiamian = (LinearLayout) rootView.findViewById(R.id.ll_zuixiamian);
        tv_order_buyersname = (TextView) rootView.findViewById(R.id.tv_order_buyersname);
        tv_order_contact = (TextView) rootView.findViewById(R.id.tv_order_contact);
        quanbu_money = (TextView) rootView.findViewById(R.id.quanbu_money);
        tv_order_money = (TextView) rootView.findViewById(R.id.tv_order_money);
    }
    public abstract void lister (int id,int type,int position);
}
