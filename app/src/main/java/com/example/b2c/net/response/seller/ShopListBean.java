package com.example.b2c.net.response.seller;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺的产品列表
 */

public class ShopListBean implements Serializable{
    private boolean hasNext;

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    private List<Rows>rows;
    public class Rows{
        private int id;
        private int isSelling;
        private int sampleSKU;
        private String name;
        private String price;

        public String getSamplePic() {
            return samplePic;
        }

        public void setSamplePic(String samplePic) {
            this.samplePic = samplePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsSelling() {
            return isSelling;
        }

        public void setIsSelling(int isSelling) {
            this.isSelling = isSelling;
        }

        public int getSampleSKU() {
            return sampleSKU;
        }

        public void setSampleSKU(int sampleSKU) {
            this.sampleSKU = sampleSKU;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        private String samplePic;
    }
}
