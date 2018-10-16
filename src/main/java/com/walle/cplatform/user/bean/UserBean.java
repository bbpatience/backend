package com.walle.cplatform.user.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

@Table(name="t_user")
public class UserBean implements Serializable {

    private static final long serialVersionUID = 9129370215157758832L;

    @Id
    private Long id;

    @Column
    private String uid;

	@Column
    private String username;

	@Column
    private String password;

    @Column
	private Date create_dt;

    @Column
    private Date update_dt;

    @Column
    private String pwdSalt;

    @Column
    private Date last_login_dt;

    @Column
    private String name;

    @Column
    private String mobile;

    @Column
    private Integer state;

    @Column
    private Integer type;

    @Column
    private String keyword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(Date create_dt) {
        this.create_dt = create_dt;
    }

    public Date getUpdate_dt() {
        return update_dt;
    }

    public void setUpdate_dt(Date update_dt) {
        this.update_dt = update_dt;
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt;
    }

    public Date getLast_login_dt() {
        return last_login_dt;
    }

    public void setLast_login_dt(Date last_login_dt) {
        this.last_login_dt = last_login_dt;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

