package com.example.spj.youfan.bean;

/**
 * Created by spj on 2016/9/29.
 */
public class ShouYeModuleData {
    private String title;
    private String jump_id;
    private String replace_param;
    private String img;
    private String sort;
    private String module_id;
    /**
     * id : 209119
     * name : 页面跳转
     * type : 6
     * is_h5 : 1
     * url : http://m.funwear.com/wx-sp/special-insp.html?p=15617
     * tid :
     * ext :
     * jump_type : 6
     */

    private DataJump jump;
    private String price_tag;
    private String product_price;

    public String getPrice_tag() {
        return price_tag;
    }

    public void setPrice_tag(String price_tag) {
        this.price_tag = price_tag;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJump_id() {
        return jump_id;
    }

    public void setJump_id(String jump_id) {
        this.jump_id = jump_id;
    }

    public String getReplace_param() {
        return replace_param;
    }

    public void setReplace_param(String replace_param) {
        this.replace_param = replace_param;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public DataJump getJump() {
        return jump;
    }

    public void setJump(DataJump jump) {
        this.jump = jump;
    }
}
