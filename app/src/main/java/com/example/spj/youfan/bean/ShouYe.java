package com.example.spj.youfan.bean;

import java.util.List;

/**
 * Created by spj on 2016/9/29.
 * 首页的json数据解析后的
 */
public class ShouYe {
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
        private String id;
        private String name;
        private String title;
        private String is_delete;
        private String create_time;
        private String page;
        private UpdateNumBean updateNum;


        private List<ModuleBean> module;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public UpdateNumBean getUpdateNum() {
            return updateNum;
        }

        public void setUpdateNum(UpdateNumBean updateNum) {
            this.updateNum = updateNum;
        }

        public List<ModuleBean> getModule() {
            return module;
        }

        public void setModule(List<ModuleBean> module) {
            this.module = module;
        }

        public static class UpdateNumBean {
            private int productNum;
            private int specialNum;

            public int getProductNum() {
                return productNum;
            }

            public void setProductNum(int productNum) {
                this.productNum = productNum;
            }

            public int getSpecialNum() {
                return specialNum;
            }

            public void setSpecialNum(int specialNum) {
                this.specialNum = specialNum;
            }
        }

        public static class ModuleBean {
            private String id;
            private String c_title;
            private String e_title;
            private String module_key;
            private String sort;
            private String is_more;
            private String more_jump_id;
            /**
             * title : 有范运动馆
             * jump_id : 209119
             * replace_param :
             * img : http://7xjir4.com2.z0.glb.qiniucdn.com/FjZy7_eWjkPjO3I7uzaNDVoueveY
             * sort : 1
             * module_id : 1
             * jump : {"id":"209119","name":"页面跳转","type":"6","is_h5":"1","url":"http://m.funwear.com/wx-sp/special-insp.html?p=15617","tid":"","ext":"","jump_type":"6"}
             */

            private List<ShouYeModuleData> data;
            private ModuleJump jumps;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getC_title() {
                return c_title;
            }

            public void setC_title(String c_title) {
                this.c_title = c_title;
            }

            public String getE_title() {
                return e_title;
            }

            public void setE_title(String e_title) {
                this.e_title = e_title;
            }

            public String getModule_key() {
                return module_key;
            }

            public void setModule_key(String module_key) {
                this.module_key = module_key;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getIs_more() {
                return is_more;
            }

            public void setIs_more(String is_more) {
                this.is_more = is_more;
            }

            public String getMore_jump_id() {
                return more_jump_id;
            }

            public void setMore_jump_id(String more_jump_id) {
                this.more_jump_id = more_jump_id;
            }

            public List<ShouYeModuleData> getData() {
                return data;
            }

            public void setData(List<ShouYeModuleData> data) {
                this.data = data;
            }

        }
    }
}



