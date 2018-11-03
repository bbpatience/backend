package com.walle.cplatform.customer.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_customer")
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 9129371215147758834L;

    @Id
    private Long id;

    @Column
    private String uid;

    @Column
    private Long no;

	@Column
    private String kindergarten;

    @Column
	private Date create_dt;

    @Column
    private Date update_dt;

    @Column
    private Integer card_type;

    @Column
    private Integer fee;

    @Column
    private Integer discount;

    @Column
    private String gift;

    @Column
    private Integer primary_class_hour;

    @Column
    private Integer secondary_class_hour;

    @Column
    private Date buy_dt;

    @Column
    private Date expire_dt;

    @Column
    private String primary_learn;

    @Column
    private String comment;

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

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(String kindergarten) {
        this.kindergarten = kindergarten;
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

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public Integer getPrimary_class_hour() {
        return primary_class_hour;
    }

    public void setPrimary_class_hour(Integer primary_class_hour) {
        this.primary_class_hour = primary_class_hour;
    }

    public Integer getSecondary_class_hour() {
        return secondary_class_hour;
    }

    public void setSecondary_class_hour(Integer secondary_class_hour) {
        this.secondary_class_hour = secondary_class_hour;
    }

    public Date getBuy_dt() {
        return buy_dt;
    }

    public void setBuy_dt(Date buy_dt) {
        this.buy_dt = buy_dt;
    }

    public Date getExpire_dt() {
        return expire_dt;
    }

    public void setExpire_dt(Date expire_dt) {
        this.expire_dt = expire_dt;
    }

    public String getPrimary_learn() {
        return primary_learn;
    }

    public void setPrimary_learn(String primary_learn) {
        this.primary_learn = primary_learn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

