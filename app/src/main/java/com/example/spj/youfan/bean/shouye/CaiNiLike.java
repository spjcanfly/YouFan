package com.example.spj.youfan.bean.shouye;

import java.util.List;

/**
 * Created by spj on 2016/10/5.
 * 首页猜你喜欢
 */
public class CaiNiLike {
    private DataBean data;
    private String info;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private int code;
        private int count;
        private List<ListBean> list;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String product_sys_code;
            private String market_price;
            private String product_url;
            private String brand_id;
            private String product_name;
            private int status;
            private String on_sale_date;
            private String brandUrl;
            private String brandName;
            private String activity_rule;
            private String activity_icon;
            private String price;
            private int stock_num;
            private List<String> spec_price_list;

            private List<ProdClsTagBean> prodClsTag;

            public String getProduct_sys_code() {
                return product_sys_code;
            }

            public void setProduct_sys_code(String product_sys_code) {
                this.product_sys_code = product_sys_code;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getProduct_url() {
                return product_url;
            }

            public void setProduct_url(String product_url) {
                this.product_url = product_url;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getOn_sale_date() {
                return on_sale_date;
            }

            public void setOn_sale_date(String on_sale_date) {
                this.on_sale_date = on_sale_date;
            }

            public String getBrandUrl() {
                return brandUrl;
            }

            public void setBrandUrl(String brandUrl) {
                this.brandUrl = brandUrl;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getActivity_rule() {
                return activity_rule;
            }

            public void setActivity_rule(String activity_rule) {
                this.activity_rule = activity_rule;
            }

            public String getActivity_icon() {
                return activity_icon;
            }

            public void setActivity_icon(String activity_icon) {
                this.activity_icon = activity_icon;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getStock_num() {
                return stock_num;
            }

            public void setStock_num(int stock_num) {
                this.stock_num = stock_num;
            }

            public List<String> getSpec_price_list() {
                return spec_price_list;
            }

            public void setSpec_price_list(List<String> spec_price_list) {
                this.spec_price_list = spec_price_list;
            }

            public List<ProdClsTagBean> getProdClsTag() {
                return prodClsTag;
            }

            public void setProdClsTag(List<ProdClsTagBean> prodClsTag) {
                this.prodClsTag = prodClsTag;
            }

            public static class ProdClsTagBean {
                private String tagName;
                private String tagUrl;
                private int tagType;
                private String remark;

                public String getTagName() {
                    return tagName;
                }

                public void setTagName(String tagName) {
                    this.tagName = tagName;
                }

                public String getTagUrl() {
                    return tagUrl;
                }

                public void setTagUrl(String tagUrl) {
                    this.tagUrl = tagUrl;
                }

                public int getTagType() {
                    return tagType;
                }

                public void setTagType(int tagType) {
                    this.tagType = tagType;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }
            }
        }
    }
}
