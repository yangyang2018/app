package com.example.b2c.fragment.seller;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.adapter.seller.RefuseAllAdapter;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.base.PageListHasNextListener;
import com.example.b2c.net.response.seller.OrderList;
import com.example.b2c.widget.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 用途：
 * Created by milk on 16/10/30.
 * 邮箱：649444395@qq.com
 */

public class RefuseAllFragment extends TempleBaseFragment {
    private RefuseAllAdapter mAdapter;
    @Bind(R.id.courier_list)
    ListView mCourierList;
    @Bind(R.id.refresh)
    RefreshLayout mRefresh;
    private boolean hasNext = false;
    private List<OrderList> mOrderLists;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_sller_refuse_all;
    }

    @Override
    protected void initView(View rootView) {
        mOrderLists = new ArrayList<>();
        mAdapter = new RefuseAllAdapter(getActivity());
        mRefresh.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoadMore() {
                if (hasNext) {
                    initData(false);
                }
            }

            @Override
            public void onRefresh() {
                initData(true);
            }
        });
        mRefresh.setColorSchemeResources(R.color.red, R.color.orange, R.color.blue);
        mCourierList.setAdapter(mAdapter);
    }

    private void initData(final Boolean refresh) {
        int mPage = 0;


        if (!refresh) {
            if (!hasNext) {
                mRefresh.setLoading(false);
                return;
            }

            mPage = mOrderLists.size();
        }
        sellerRdm.getOrderList(ConstantS.BASE_URL + "seller/order/list.htm", mPage, 140);
        sellerRdm.mPageListHasNextListener = new PageListHasNextListener<OrderList>() {
            @Override
            public void onSuccess(List list, final boolean hasNests) {
                final List<OrderList> pageList = (List<OrderList>) list;
                if (pageList != null) {

                    if (refresh) {
                        if (mOrderLists != null) {
                            mOrderLists.clear();
                            mOrderLists = pageList;
                        }
                    } else {
                        mOrderLists.addAll(pageList);
                    }
                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (refresh) {
                                mRefresh.setRefreshing(false);
                            }
                            mRefresh.setLoading(false);
                            if (mOrderLists.isEmpty()) {
                                mEmpty.setVisibility(View.VISIBLE);
                            } else {
                                mEmpty.setVisibility(View.GONE);
                            }
                            mAdapter.setData(mOrderLists);
                            hasNext = hasNests;
                            mListCount.allCount(mOrderLists.size());
                        }
                    });
                }

            }

            @Override
            public void onError(int errorNO, final String errorInfo) {
                if (getActivity() == null) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (refresh) {
                            mRefresh.setRefreshing(false);
                        }
                        mRefresh.setLoading(false);
                        ToastHelper.makeErrorToast(errorInfo);
                    }
                });

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onLose() {
                if (getActivity() == null) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (refresh) {
                            mRefresh.setRefreshing(false);
                        }
                        mRefresh.setLoading(false);
                        ToastHelper.makeErrorToast(requestFailure);
                    }
                });

            }

        };

    }

    @Override
    public void onResume() {
        super.onResume();
        initData(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnAllListCount) {
            mListCount = (OnAllListCount) activity;
        }
    }

    public OnAllListCount mListCount;

    public interface OnAllListCount {
        void allCount(int watingCount);
    }
}
