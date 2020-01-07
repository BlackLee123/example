package com.netease.example.models.ordercenter;

public class OrderCenterResponse {
    private boolean success;

    private OrderCenter data;

    private String message;

    private int errcode;

    public OrderCenterResponse(){}

    public OrderCenterResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OrderCenter getData() {
        return data;
    }

    public void setData(OrderCenter data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
