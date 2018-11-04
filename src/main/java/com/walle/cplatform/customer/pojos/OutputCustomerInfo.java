package com.walle.cplatform.customer.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.walle.cplatform.customer.bean.CustomerBean;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.pojos.OutputUserInfo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OutputCustomerInfo extends OutputUserInfo {

    @JsonProperty("number")
    private Long number;

    @JsonProperty("kindergarten")
    private String kindergarten;

    @JsonProperty("card_type")
    private Integer cardType;

    @JsonProperty("fee")
    private Integer fee;

    @JsonProperty("discount")
    private Integer discount;

    @JsonProperty("gift")
    private String gift;

    @JsonProperty("primary")
    private Integer primary;

    @JsonProperty("secondary")
    private Integer secondary;

    @JsonProperty("buy_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date buyDate;

    @JsonProperty("expire_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expireDate;

    @JsonProperty("primary_learn")
    private List<String> priLearn;

    @JsonProperty("comment")
    private String comment;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(String kindergarten) {
        this.kindergarten = kindergarten;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
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

    public Integer getPrimary() {
        return primary;
    }

    public void setPrimary(Integer primary) {
        this.primary = primary;
    }

    public Integer getSecondary() {
        return secondary;
    }

    public void setSecondary(Integer secondary) {
        this.secondary = secondary;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public List<String> getPriLearn() {
        return priLearn;
    }

    public void setPriLearn(List<String> priLearn) {
        this.priLearn = priLearn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OutputCustomerInfo(CustomerBean customer, UserBean user) {
        super(user);
        this.number = customer.getNo();
        this.kindergarten = customer.getKindergarten();
        this.primary = customer.getPrimary_class_hour();
        this.secondary = customer.getSecondary_class_hour();
        this.buyDate = customer.getBuy_dt();
        this.expireDate = customer.getExpire_dt();
        this.cardType = customer.getCard_type();
        this.discount = customer.getDiscount();
        this.fee = customer.getFee();
        this.gift = customer.getGift();
        this.comment = customer.getComment();
        this.priLearn = Arrays.asList(customer.getPrimary_learn().split(":"));
    }
}
