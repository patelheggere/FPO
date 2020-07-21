package com.ktdcl.fpo.model;

public class ResponseModel {
    private boolean status;
    private String msg ;

    public ResponseModel() {

    }

    public ResponseModel(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
