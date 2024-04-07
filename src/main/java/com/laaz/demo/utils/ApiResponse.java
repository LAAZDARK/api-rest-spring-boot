package com.laaz.demo.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {
    private String message;
    private int status;
    public T result;

    public ApiResponse(T result, String message, int status) {
        this.result = result;
        this.message = message;
        this.status = status;
    }

    public ApiResponse(T result, String message) {
        this.result = result;
        this.message = message;
        this.status = HttpStatus.OK.value();
    }

    public ApiResponse(T result) {
        this.result = result;
        this.message = "Success";
        this.status = HttpStatus.OK.value();
    }

    public ApiResponse() {
        this.message = "Success";
        this.status = HttpStatus.OK.value();
    }
}
