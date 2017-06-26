package com.example.b2c.net.response.seller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ThinkPad on 2017/4/26.
 */

public class SellerShopTabBean implements Serializable{
    private List<Rows> rows;

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public class Rows{
        private String name;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
