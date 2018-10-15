package com.walle.cplatform.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class OutputUserInfo {

    @JsonProperty("id")
    private String uid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("state")
    private Integer state;

    @JsonProperty("type")
    private Integer type;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}
