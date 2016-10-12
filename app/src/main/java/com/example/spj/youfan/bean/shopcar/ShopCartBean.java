package com.example.spj.youfan.bean.shopcar;

import java.util.ArrayList;

/**
 * Created by spj on 2016/10/12.
 */
public class ShopCartBean {
    /** 是否处于编辑状态 */
    private boolean isEditing;
//    /** 组是否被选中 */
//    private boolean isGroupSelected;
    /**
     * 具体的商品信息详情
     */
    private ArrayList<Goods> goods;

    /** 购物车商品数量 */
    public static final String KEY_NUM = "num";

    /** 购物车规格ID */
    public static final String KEY_PRODUCT_ID = "product_id";

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

//    public boolean isGroupSelected() {
//        return isGroupSelected;
//    }
//
//    public void setIsGroupSelected(boolean isGroupSelected) {
//        this.isGroupSelected = isGroupSelected;
//    }

    public ArrayList<Goods> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Goods> goods) {
        this.goods = goods;
    }

    private class Goods {
        /** 数量 */
        private String number = "1";
        /** 商品ID */
        private String goodsID;
        /** 商品名字 */
        private String name;
        /** 商品价格 */
        private String sale_price;
        /** 商品图片 */
        private String mainImage;
        /** 是否是编辑状态 */
        private boolean isEditing;
        /** 是否被选中 */
        private boolean isChildSelected;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(String goodsID) {
            this.goodsID = goodsID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSale_price() {
            return sale_price;
        }

        public void setSale_price(String sale_price) {
            this.sale_price = sale_price;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }

        public boolean isEditing() {
            return isEditing;
        }

        public void setIsEditing(boolean isEditing) {
            this.isEditing = isEditing;
        }

        public boolean isChildSelected() {
            return isChildSelected;
        }

        public void setIsChildSelected(boolean isChildSelected) {
            this.isChildSelected = isChildSelected;
        }
    }
}
