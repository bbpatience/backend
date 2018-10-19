package com.walle.cplatform.classes.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_classes")
public class ClassBean implements Serializable {
    private static final long serialVersionUID = 9129370215157758832L;
    @Id
    private Long id;

    private String uid;

    private String name;

    private Integer state;

    private Integer type;

    private Date createDt;

    private Date updateDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
