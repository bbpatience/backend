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
@Table(name = "t_role_permission")
public class RolePermissionBean {

    @Id
    private Long id;
    @Column
    private Long roleid;
    @Column
    private Long permid;
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

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getPermid() {
        return permid;
    }

    public void setPermid(Long permid) {
        this.permid = permid;
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
