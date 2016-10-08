package com.example.spj.youfan.bean;

import java.util.List;

/**
 * Created by spj on 2016/10/8.
 * 热门品牌故事
 */
public class PinPaiStory {

    /**
     * id : 633
     * temp_id : 633
     * english_name : The 2nd Law
     * logo_img : http://7xjir4.com2.z0.glb.qiniucdn.com/FhpHxfDG3RPACrWsHuZL-K9ODVwr
     * brand_code : The_2nd_Law
     * ename : The 2nd Law
     * cname :
     * first_letter : T
     * pic_img : http://metersbonwe.qiniucdn.com/meitesibangweitup.jpg
     * story : “THE 2ND LAW”,直译为第二定律或第二法则，强调年轻人挣脱桎梏，追求个性突破现有圈子的潮流品位。
     THE 2ND LAW名字源于英国著名摇滚组合“MUSE”2012年创作的专辑。The sixth studio album from Muse, and the latest release since 2009's Resistance. The 2nd Law features the official Olympic theme tune "Survival".专辑”THE 2ND LAW”强调突破，强调直面束缚。另类前卫的曲风给我们呈现完全不一样的世界。
     * look_num : 12410
     * brandStoryUrl :
     * youfan_img : http://7xjir4.com2.z0.glb.qiniucdn.com/FvLw6i5kNC2dEjYs_TrdQj8991K8
     * little_img : http://7xjir4.com2.z0.glb.qiniucdn.com/FhpHxfDG3RPACrWsHuZL-K9ODVwr
     * story_img : http://7xjir4.com2.z0.glb.qiniucdn.com/FhpHxfDG3RPACrWsHuZL-K9ODVwr
     * is_love : 0
     * tab : [{"id":0,"key":"A","img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FvLw6i5kNC2dEjYs_TrdQj8991K8","home_url":null,"story_url":"","is_choose":false},{"id":1,"key":"M","img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FvLw6i5kNC2dEjYs_TrdQj8991K8","home_url":"","story_url":"","is_choose":true}]
     */

    private DataBean data;
    /**
     * data : {"id":"633","temp_id":"633","english_name":"The 2nd Law","logo_img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FhpHxfDG3RPACrWsHuZL-K9ODVwr","brand_code":"The_2nd_Law","ename":"The 2nd Law","cname":"","first_letter":"T","pic_img":"http://metersbonwe.qiniucdn.com/meitesibangweitup.jpg","story":"\u201cTHE 2ND LAW\u201d,直译为第二定律或第二法则，强调年轻人挣脱桎梏，追求个性突破现有圈子的潮流品位。\r\nTHE 2ND LAW名字源于英国著名摇滚组合\u201cMUSE\u201d2012年创作的专辑。The sixth studio album from Muse, and the latest release since 2009's Resistance. The 2nd Law features the official Olympic theme tune \"Survival\".专辑\u201dTHE 2ND LAW\u201d强调突破，强调直面束缚。另类前卫的曲风给我们呈现完全不一样的世界。","look_num":12410,"brandStoryUrl":"","youfan_img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FvLw6i5kNC2dEjYs_TrdQj8991K8","little_img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FhpHxfDG3RPACrWsHuZL-K9ODVwr","story_img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FhpHxfDG3RPACrWsHuZL-K9ODVwr","is_love":0,"tab":[{"id":0,"key":"A","img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FvLw6i5kNC2dEjYs_TrdQj8991K8","home_url":null,"story_url":"","is_choose":false},{"id":1,"key":"M","img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FvLw6i5kNC2dEjYs_TrdQj8991K8","home_url":"","story_url":"","is_choose":true}]}
     * info :
     * status : 1
     */

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
        private String temp_id;
        private String english_name;
        private String logo_img;
        private String brand_code;
        private String ename;
        private String cname;
        private String first_letter;
        private String pic_img;
        private String story;
        private int look_num;
        private String brandStoryUrl;
        private String youfan_img;
        private String little_img;
        private String story_img;
        private int is_love;
        /**
         * id : 0
         * key : A
         * img : http://7xjir4.com2.z0.glb.qiniucdn.com/FvLw6i5kNC2dEjYs_TrdQj8991K8
         * home_url : null
         * story_url :
         * is_choose : false
         */

        private List<TabBean> tab;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTemp_id() {
            return temp_id;
        }

        public void setTemp_id(String temp_id) {
            this.temp_id = temp_id;
        }

        public String getEnglish_name() {
            return english_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }

        public String getLogo_img() {
            return logo_img;
        }

        public void setLogo_img(String logo_img) {
            this.logo_img = logo_img;
        }

        public String getBrand_code() {
            return brand_code;
        }

        public void setBrand_code(String brand_code) {
            this.brand_code = brand_code;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getFirst_letter() {
            return first_letter;
        }

        public void setFirst_letter(String first_letter) {
            this.first_letter = first_letter;
        }

        public String getPic_img() {
            return pic_img;
        }

        public void setPic_img(String pic_img) {
            this.pic_img = pic_img;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public int getLook_num() {
            return look_num;
        }

        public void setLook_num(int look_num) {
            this.look_num = look_num;
        }

        public String getBrandStoryUrl() {
            return brandStoryUrl;
        }

        public void setBrandStoryUrl(String brandStoryUrl) {
            this.brandStoryUrl = brandStoryUrl;
        }

        public String getYoufan_img() {
            return youfan_img;
        }

        public void setYoufan_img(String youfan_img) {
            this.youfan_img = youfan_img;
        }

        public String getLittle_img() {
            return little_img;
        }

        public void setLittle_img(String little_img) {
            this.little_img = little_img;
        }

        public String getStory_img() {
            return story_img;
        }

        public void setStory_img(String story_img) {
            this.story_img = story_img;
        }

        public int getIs_love() {
            return is_love;
        }

        public void setIs_love(int is_love) {
            this.is_love = is_love;
        }

        public List<TabBean> getTab() {
            return tab;
        }

        public void setTab(List<TabBean> tab) {
            this.tab = tab;
        }

        public static class TabBean {
            private int id;
            private String key;
            private String img;
            private Object home_url;
            private String story_url;
            private boolean is_choose;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getHome_url() {
                return home_url;
            }

            public void setHome_url(Object home_url) {
                this.home_url = home_url;
            }

            public String getStory_url() {
                return story_url;
            }

            public void setStory_url(String story_url) {
                this.story_url = story_url;
            }

            public boolean isIs_choose() {
                return is_choose;
            }

            public void setIs_choose(boolean is_choose) {
                this.is_choose = is_choose;
            }
        }
    }
}
