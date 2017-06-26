package com.example.b2c.net.response.seller;

import java.io.Serializable;
import java.util.List;

/**
 * 跳转到发布页面进行编辑的bean
 */

public class EditShopBean implements Serializable{
    private String  address;
    private String  brand;
    private int   categoryId;
    private String  categoryName;
    private String  code;
    private int  deliveryType;
    private int  id;
    private List<HouseList>houseList;
    private List imageList;
    private String name;
    private int price;
    private List<CategoryDetailModule>proList;
    private String unit;
    private String weight;
    private int inventory;

    private SampleDetail sampleDetail;
    private List<SampleProInfosModule>sampleSKUList;
    private List<Integer> shopWarehouseIds;

    public List<SampleProInfosModule> getSampleSKUList() {
        return sampleSKUList;
    }

    public void setSampleSKUList(List<SampleProInfosModule> sampleSKUList) {
        this.sampleSKUList = sampleSKUList;
    }

    public List<CategoryDetailModule> getProList() {
        return proList;
    }

    public void setProList(List<CategoryDetailModule> proList) {
        this.proList = proList;
    }
    public List<Integer> getShopWarehouseIds() {
        return shopWarehouseIds;
    }

    public void setShopWarehouseIds(List<Integer> shopWarehouseIds) {
        this.shopWarehouseIds = shopWarehouseIds;
    }



    public SampleDetail getSampleDetail() {
        return sampleDetail;
    }

    public void setSampleDetail(SampleDetail sampleDetail) {
        this.sampleDetail = sampleDetail;
    }

    public List getImageList() {
        return imageList;
    }

    public void setImageList(List imageList) {
        this.imageList = imageList;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<HouseList> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<HouseList> houseList) {
        this.houseList = houseList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 产品的库存列表
     */
    public class SampleSKUList{
        private String price;
        private int amount;
        private String proDetailIds;
        private String propertyIds;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getProDetailIds() {
            return proDetailIds;
        }

        public void setProDetailIds(String proDetailIds) {
            this.proDetailIds = proDetailIds;
        }

        public String getPropertyIds() {
            return propertyIds;
        }

        public void setPropertyIds(String propertyIds) {
            this.propertyIds = propertyIds;
        }
    }
    /**
     * 产品详情
     */
    public class SampleDetail{
        private List detailImageList;
        private String detailMessage;

        public List getDetailImageList() {
            return detailImageList;
        }

        public void setDetailImageList(List detailImageList) {
            this.detailImageList = detailImageList;
        }

        public String getDetailMessage() {
            return detailMessage;
        }

        public void setDetailMessage(String detailMessage) {
            this.detailMessage = detailMessage;
        }
    }
    /**
     * 类目属性列表，也就是颜色，大小等等属性
     */
    public class ProList{
        private List<PropertyDetails> propertyDetails;
        private int propertyId;
        private String propertyName;

        public List<PropertyDetails> getPropertyDetails() {
            return propertyDetails;
        }

        public void setPropertyDetails(List<PropertyDetails> propertyDetails) {
            this.propertyDetails = propertyDetails;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }
    }
    /**
     * 对应属性列表的详情，如果是颜色就是红色，蓝色等等一些详细属性
     */
//    public class PropertyDetails{
//            private int id;
//            private int propertyId;
//            private String name;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getPropertyId() {
//            return propertyId;
//        }
//
//        public void setPropertyId(int propertyId) {
//            this.propertyId = propertyId;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
    /**
     * 店铺仓库列表
     */
    public class HouseList{
            private String address;
            private String cityName;
            private int id;
            private String provinceName;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }
    }

}
