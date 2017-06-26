package com.example.b2c.fragment.seller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.activity.seller.HandleReturnActivity;
import com.example.b2c.activity.seller.MyShopDetailsActivity;
import com.example.b2c.adapter.base.XRcycleViewAdapterBase;
import com.example.b2c.adapter.seller.MyNewOrderAdapter;
import com.example.b2c.adapter.seller.MySealesReturnAdapter;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.ResponseListener;
import com.example.b2c.net.listener.base.PageListHasNextListener;
import com.example.b2c.net.response.seller.OrderList;
import com.example.b2c.net.response.seller.TuiHuoBean;
import com.example.b2c.net.response.seller.TuiHuoOrder;
import com.example.b2c.widget.util.ListDividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 退货的fragment
 */
@SuppressLint("ValidFragment")
public class MySalesReturnFragment extends TempleBaseFragment {
    private XRecyclerView recycle_new_order;
    private List<TuiHuoBean> mOrderLists;
    private boolean ishasNest;
    private MySealesReturnAdapter myNewOrderAdapter;
    private RelativeLayout empty;
    private int type;
public MySalesReturnFragment(){}
    public MySalesReturnFragment(int type){
        this.type=type;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_new_order;
    }

    @Override
    protected void initView(View rootView) {
        RelativeLayout top= (RelativeLayout) rootView.findViewById(R.id.top);
        top.setVisibility(View.GONE);
        recycle_new_order = (XRecyclerView) rootView.findViewById(R.id.recycle_new_order);
        empty = (RelativeLayout) rootView.findViewById(R.id.empty);
        recycle_new_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_new_order.addItemDecoration(new ListDividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL, 0, getResources().getColor(R.color.background)));
        initData(0,true);

    }
    private void initLinster(){
        recycle_new_order.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                jilu=0;
                initData(0,false);
            }

            @Override
            public void onLoadMore() {
                if (ishasNest){
                    //还有数据
                    jilu=jilu+20;
                    initData(jilu,false);
                }else{
                    recycle_new_order.loadMoreComplete();
                }
            }
        });
        if (myNewOrderAdapter!=null){
            //条目点击
            myNewOrderAdapter.setOnItemClickListener(new XRcycleViewAdapterBase.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //跳转到详情
                    Intent intent=new Intent(getActivity(), MyShopDetailsActivity.class);
                    intent.putExtra("id",mOrderLists.get(position).getId()+"");
                    intent.putExtra("type",type);
                    startActivity(intent);

                }
            });
        }

    }
    //ConstantS.BASE_URL + "seller/order/list.htm"
    private int jilu;
    private void initData(final int mPage, final boolean isJiazai){
        showLoading();
        mOrderLists = new ArrayList<>();
        sellerRdm.tuihuoOrderList(getActivity(),ConstantS.BASE_URL+"seller/refund/list.htm" , mPage,type);
        sellerRdm.mPageListHasNextListener=new PageListHasNextListener() {
            @Override
            public void onSuccess(List list, boolean hasNest) {
                if (mPage==0){
                    mOrderLists.clear();
                }
                mOrderLists.addAll(list);
                ishasNest=hasNest;
                if (isJiazai){
                    if (mOrderLists.size()==0){
                        recycle_new_order.setVisibility(View.GONE);
                        empty.setVisibility(View.VISIBLE);
                    }else {
                        recycle_new_order.setVisibility(View.VISIBLE);
                        empty.setVisibility(View.GONE);
                        //设置适配器
                        myNewOrderAdapter = new MySealesReturnAdapter(getActivity(), mOrderLists, mTranslatesString,type) {
                            @Override
                            public void lister(int id, int type, final int position) {
                                if (type==0) {
                                    Intent i_yes = new Intent(getActivity(), HandleReturnActivity.class);
                                    i_yes.putExtra("id", id);
                                    i_yes.putExtra("function", 1);
                                    startActivity(i_yes);
                                }else{
                                    Intent i_yes = new Intent(getActivity(), HandleReturnActivity.class);
                                    i_yes.putExtra("id",id);
                                    i_yes.putExtra("function",0);
                                    startActivity(i_yes);
                                }
                            }

                            };
                        recycle_new_order.setAdapter(myNewOrderAdapter);
                    }
                }else{
                    //刷新适配器
                    myNewOrderAdapter.notifyDataSetChanged();

                }
                recycle_new_order.refreshComplete();
                recycle_new_order.loadMoreComplete();
                initLinster();
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
}
