package com.example.b2c.net.response.seller;

import com.example.b2c.net.response.ResponseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 用途：
 * Created by milk on 16/11/1.
 * 邮箱：649444395@qq.com
 */

public class TuiHuoOrder {
    private ResponseResult result;

    public ResponseResult getResult() {

        return result;
    }

    public void setResult(ResponseResult result) {
        this.result = result;
    }

    @SerializedName("hasNext")
    private boolean hasNext;
    @SerializedName("rows")
    private List<TuiHuoBean> rows;

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNest(boolean hasNest) {
        this.hasNext = hasNest;
    }

    public List<TuiHuoBean> getRows() {
        return rows;
    }

    public void setRows(List<TuiHuoBean> rows) {
        this.rows = rows;
    }
}
