package com.example.demo;

public class Response<T> {
    private T data;
    private boolean success;
    private String errorMsg;

    public static <K> Response<K> newSuccess(K data) {
        Response<K> response = new Response<K>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static Response<Void> newFailure(String errorMsg) {
        Response<Void> response = new Response<>();
        response.setErrorMsg(errorMsg);
        response.setSuccess(false);
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
