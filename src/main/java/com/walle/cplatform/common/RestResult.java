package com.walle.cplatform.common;

import com.walle.cplatform.utils.Constants;

public class RestResult {
    private String rspCode;
    private String rspMsg;
    private String data;

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public RestResult() {
        this.rspCode = Constants.SUCCESS_FLAG;
        this.rspMsg = Constants.SUCCESS_MSG;
    }

    public RestResult(String rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }
}
