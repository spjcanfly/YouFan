package com.example.spj.youfan.bean.shop;

import java.io.Serializable;

/**
 * Created by spj on 2016/10/12.
 * 商品的bean类
 */
public class Goods implements Serializable{
    /**
     * 数量
     */
    private int number;
    /**
     * 商品ID
     */
    private String goodsID;
    /**
     * 商品名字
     */
    private String name;
    /**
     * 商品价格
     */
    private int sale_price;
    /**
     * 商品图片
     */
    private String mainImage;


    public Goods() {

    }

    public Goods(int number, String goodsID, String name, int sale_price, String mainImage) {
        this.number = number;
        this.goodsID = goodsID;
        this.name = name;
        this.sale_price = sale_price;
        this.mainImage = mainImage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * 是否是编辑状态
     */

    private boolean isEditing;
    /**
     * 是否被选中
     */
    private boolean isSelected;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
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

    @Override
    public String toString() {
        return "Goods{" +
                "number=" + number +
                ", goodsID='" + goodsID + '\'' +
                ", name='" + name + '\'' +
                ", sale_price=" + sale_price +
                ", mainImage='" + mainImage + '\'' +
                '}';
    }
}
