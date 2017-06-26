package com.example.b2c.fragment.seller;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.activity.seller.ReleaseProductActivity;
import com.example.b2c.adapter.seller.SellerShopListAdapter;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.base.NoDataListener;
import com.example.b2c.net.listener.base.PageListHasNextListener;
import com.example.b2c.net.response.seller.ShopListBean;
import com.example.b2c.widget.util.ListDividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MyFabuShopFragment extends TempleBaseFragment {

    private int leimuId ;
    private XRecyclerView xr_seller_shop;
    private SellerShopListAdapter sellerShopListAdapter;
    private RelativeLayout emay;
    private List<ShopListBean.Rows> shopListBean;

    public MyFabuShopFragment() {
    }
    public MyFabuShopFragment(int  leimuId) {
        this.leimuId=leimuId;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_my_fabu_shop;
    }
//SellerShopListAdapter适配器的名字
    @Override
    protected void initView(View rootView) {
        xr_seller_shop = (XRecyclerView) rootView.findViewById(R.id.xr_seller_shop);
        xr_seller_shop.setLayoutManager(new LinearLayoutManager(getActivity()));
        xr_seller_shop.addItemDecoration(new ListDividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL, 0, getResources().getColor(R.color.background)));
        emay = (RelativeLayout) rootView.findViewById(R.id.empty);
        initDatas(0,true);
        initLinster();
    }
    private void initDatas(final int startRow, final boolean isJiazai){
        Map<String,String>map=new HashMap<>();
        map.put("startRow",startRow+"");
        map.put("categoryId",leimuId+"");
        sellerRdm.getShopList(getActivity(), ConstantS.BASE_URL+"seller/sample/list.htm",map);
        sellerRdm.mPageListHasNextListener=new PageListHasNextListener() {
            @Override
            public void onSuccess(List list, boolean hasNest) {
                isHasNest=hasNest;
                //TODO,设置适配器显示即可
                shopListBean = list;
                if (isJiazai){
                    if (shopListBean.size()==0){
                        emay.setVisibility(View.VISIBLE);
                        xr_seller_shop.setVisibility(View.GONE);
                    }else{
                        emay.setVisibility(View.GONE);
                        xr_seller_shop.setVisibility(View.VISIBLE);
                        sellerShopListAdapter = new SellerShopListAdapter(getActivity(), shopListBean, mTranslatesString,sellerRdm) {
                            @Override
                            public void bianji(int id) {
                                //跳转到编辑界面
                                Intent intent=new Intent(getActivity(), ReleaseProductActivity.class);
                                intent.putExtra("id",id+"");
                                startActivity(intent);
                            }

                            @Override
                            public void editZhuangtai(final int type, int id, final int position, final TextView tv_shop_xiajia) {
                                Map<String,String> map=new HashMap<>();
                                map.put("ids",id+"");
                                if (type==1){
                                    //执行下架操作
                                    sellerRdm.caozuo(getActivity(),ConstantS.BASE_URL+"seller/sample/batchOffSample.htm",map);
                                }else{
                                    //执行上架操作
                                    sellerRdm.caozuo(getActivity(),ConstantS.BASE_URL+"seller/sample/batchOnSample.htm",map);
                                }
                                sellerRdm.mNodataListener=new NoDataListener() {
                                    @Override
                                    public void onSuccess(String success) {
                                        initDatas(jilu,true);
                                        ToastHelper.makeToast(success);
                                    }

                                    @Override
                                    public void onError(int errorNo, String errorInfo) {
                                        ToastHelper.makeToast(errorInfo);
                                    }

                                    @Override
                                    public void onFinish() {

                                    }

                                    @Override
                                    public void onLose() {
                                    }
                                };
                            }
                        };
                        xr_seller_shop.setAdapter(sellerShopListAdapter);
                        xr_seller_shop.refreshComplete();
                    }
                }else{
                    sellerShopListAdapter.notifyDataSetChanged();
                    xr_seller_shop.loadMoreComplete();
                }
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
    private int jilu;
    private boolean isHasNest;
    private void initLinster(){
        xr_seller_shop.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                jilu=0;
                initDatas(0,true);
            }

            @Override
            public void onLoadMore() {
                if (isHasNest){
                    //还有数据
                    jilu=jilu+20;
                    initDatas(jilu,false);
                }else{
                    xr_seller_shop.loadMoreComplete();
                }
            }
        });
    }
}
