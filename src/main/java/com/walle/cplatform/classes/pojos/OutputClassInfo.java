package com.walle.cplatform.classes.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walle.cplatform.classes.bean.ClassBean;
import java.util.Date;

public class OutputClassInfo {
    @JsonProperty("id")
    private String uid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("state")
    private Integer state;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("create_dt")
    private Date createDt;

    @JsonProperty("update_dt")
    private Date updateDt;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public OutputClassInfo(ClassBean classBean) {
        this.uid = classBean.getUid();
        this.name = classBean.getName();
        this.state = classBean.getState();
        this.type = classBean.getType();
        this.createDt = classBean.getCreateDt();
        this.updateDt = classBean.getUpdateDt();
    }
}
