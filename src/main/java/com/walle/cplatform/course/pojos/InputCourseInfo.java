package com.walle.cplatform.course.pojos;

/**
 * @author: bbpatience
 * @date: 2018/10/19
 * @description: InputCourseInfo
 **/
public class InputCourseInfo {
    // customer id
    private String uid;

    // class id.
    private String cid;

    // teacher id.
    private String tid;

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
}
