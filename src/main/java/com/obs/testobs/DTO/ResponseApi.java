package com.obs.testobs.DTO;

import lombok.Data;

@Data
public class ResponseApi<T> {

    private String message;
    private int code;
    private T data;

    public ResponseApi(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseApi(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ResponseApi(T data) {
        this.message = "Success";
        this.code = 00;
        this.data = data;
    }

    public ResponseApi(String message) {
        this.message = message;
        this.code = 99;
    }

    public ResponseApi(String message, T data) {
        this.message = message;
        this.code = 99;
        this.data = data;
    }
}
