package com.walle.cplatform.shiro.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author zxx
 * @Description
 * @Date Created on 2017/11/10
 */
@Table(name = "t_permission")
public class PermissionBean {

    @Id
    private Long id;
    @Column
    private String perm_name;
    @Column
    private String descinfo;
    @Column
    private Integer is_deleted;
    @Column
    private Date create_dt;
    @Column
    private Date update_dt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerm_name() {
        return perm_name;
    }

    public void setPerm_name(String perm_name) {
        this.perm_name = perm_name;
    }

    public String getDescinfo() {
        return descinfo;
    }

    public void setDescinfo(String descinfo) {
        this.descinfo = descinfo;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
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
}
