package com.walle.cplatform.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walle.cplatform.utils.Constants;

public class RestResult {
    @JsonProperty("code")
    private String rspCode;
    @JsonProperty("msg")
    private String rspMsg;
    @JsonProperty("data")
    private Object data;

    public String getRspCode() {
        return rspCode;
    }

    public RestResult setRspCode(String rspCode) {
        this.rspCode = rspCode;
        return this;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public RestResult setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RestResult setData(Object data) {
        this.data = data;
        return this;
    }

    public static RestResult success() {
        return new RestResult().setRspCode(Constants.SUCCESS_FLAG).setRspMsg(Constants.SUCCESS_MSG);
    }

    public static RestResult success(Object o) {
        return RestResult.success().setData(o);
    }

    public static RestResult failure() {
        return new RestResult().setRspCode(Constants.SYS_FAIL_FLAG).setRspMsg(Constants.SYS_FAIL_MSG);
    }

    public static RestResult generate(String rspCode) {
        return new RestResult().setRspCode(rspCode).setRspMsg(Constants.getRspMessage(rspCode));
    }
}
