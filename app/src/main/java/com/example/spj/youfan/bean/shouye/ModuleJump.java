package com.example.spj.youfan.bean.shouye;

/**
 * Created by spj on 2016/9/29.
 * 首页的ModuleJump
 */
public class ModuleJump {
    private String id;
    private String type;
    private String is_h5;
    private String url;
    private String tid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIs_h5() {
        return is_h5;
    }

    public void setIs_h5(String is_h5) {
        this.is_h5 = is_h5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "ModuleJump{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", is_h5='" + is_h5 + '\'' +
                ", url='" + url + '\'' +
                ", tid='" + tid + '\'' +
                '}';
    }
}
