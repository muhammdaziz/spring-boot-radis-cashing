package com.example.springcaching.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {

    private boolean success;

    private String message;
    private T data;

    private List<ErrorData> errors;

    private ApiResult(String message, T data){
        this.success = true;
        this.message = message;
        this.data = data;
    }

    private ApiResult(T data){
        this.success = true;
        this.data = data;
    }

    private ApiResult(){
        this.success = true;
    }

    private ApiResult(List<ErrorData> errors) {
        this.errors = errors;
    }


    public static <T> ApiResult <T> successResponse(){
        return new ApiResult<>();
    }

    public static <T> ApiResult <T> successResponse(T data){
        return new ApiResult<>(data);
    }

    public static ApiResult<List<ErrorData>> failResponse(List<ErrorData> errors){
        return new ApiResult<>(errors);
    }

    public static ApiResult<List<ErrorData>> failResponse(String message, List<ErrorData> errors){
        return new ApiResult<>(message, errors);
    }

    public static ApiResult<List<ErrorData>> failResponse(String message, int code) {
        List<ErrorData> errorDataList = List.of(new ErrorData(message, code));

        return failResponse(errorDataList);
    }
}
