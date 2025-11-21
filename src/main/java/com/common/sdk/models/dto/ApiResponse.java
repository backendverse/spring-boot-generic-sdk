package com.common.sdk.models.dto;

import com.common.sdk.models.interfaces.GenericAPIResponse;
import com.common.sdk.models.interfaces.ResponseCode;
import lombok.Getter;

import static ch.qos.logback.core.CoreConstants.EMPTY_STRING;

@Getter
public class ApiResponse<R> implements GenericAPIResponse<R> {

    private boolean success;
    private R data;
    private Integer code;
    private String errorMessage;
    private String requestId;

    private ApiResponse() {
    }

    public static <T> ApiResponse<T> success(T data, ResponseCode responseCode, String requestId) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.code = responseCode.getCode();
        response.requestId = requestId;
        return response;
    }

    public static <T> ApiResponse<T> success(ResponseCode responseCode, String requestId) {
        return success(null, responseCode, requestId);
    }

    public static <T> ApiResponse<T> failure(int code, String message, String requestId) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.code = code;
        response.errorMessage = message;
        response.requestId = requestId;
        return response;
    }

    public static <T> ApiResponse<T> failure(ResponseCode responseCode, String requestId) {
        return failure(responseCode.getCode(), EMPTY_STRING, requestId);

    }


}
