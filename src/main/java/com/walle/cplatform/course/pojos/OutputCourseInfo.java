package com.walle.cplatform.course.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class OutputCourseInfo {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("customer_id")
    private String uid;

    @JsonProperty("class_id")
    private String cid;

    @JsonProperty("teacher_id")
    private String tid;

    @JsonProperty("date")
    private Date date;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OutputCourseInfo(String uid, String cid, String tid, Date date, long id) {
        this.uid = uid;
        this.cid = cid;
        this.tid = tid;
        this.date = date;
        this.id = id;
    }
}
