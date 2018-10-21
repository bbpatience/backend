package com.walle.cplatform.user.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walle.cplatform.user.bean.UserBean;
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

    @JsonProperty("last_login_dt")
    private Date loginDt;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("birthday")
    private Date birthday;

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

    public Date getLoginDt() {
        return loginDt;
    }

    public void setLoginDt(Date loginDt) {
        this.loginDt = loginDt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public OutputUserInfo(UserBean bean) {
        this.mobile = bean.getMobile();
        this.name = bean.getName();
        this.state = bean.getState();
        this.type = bean.getType();
        this.birthday = bean.getBirthday();
        this.gender = bean.getGender();
        this.loginDt = bean.getLast_login_dt();
    }
}
