package com.walle.cplatform.customer.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.walle.cplatform.user.pojos.InputUserCreate;

import java.sql.Date;
import java.util.List;

public class InputCustomerCreate extends InputUserCreate {

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
}
