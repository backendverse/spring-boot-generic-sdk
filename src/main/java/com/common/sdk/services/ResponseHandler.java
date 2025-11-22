package com.common.sdk.services;

import com.common.sdk.models.dto.ApiResponse;
import com.common.sdk.models.enums.APIResponseCode;
import com.common.sdk.models.interfaces.GenericAPIResponse;
import com.common.sdk.models.interfaces.ResponseCode;
import org.springframework.stereotype.Component;

import static com.common.sdk.utils.CommonUtils.getCurrentRequestId;

@Component
public class ResponseHandler {

    public <T> GenericAPIResponse<T> ok(T data) {
        return ApiResponse.success(data, APIResponseCode.SUCCESS, getCurrentRequestId());
    }

    public <T> GenericAPIResponse<T> ok() {
        return ApiResponse.success(APIResponseCode.SUCCESS, getCurrentRequestId());
    }

    public <T> GenericAPIResponse<T> ok(T data, ResponseCode responseCode) {
        return ApiResponse.success(data, responseCode, getCurrentRequestId());
    }

    public GenericAPIResponse<Void> ok(ResponseCode responseCode) {
        return ApiResponse.success(responseCode, getCurrentRequestId());
    }

    public GenericAPIResponse<Void> failure(ResponseCode responseCode) {
        return ApiResponse.failure(responseCode, getCurrentRequestId());
    }

    public GenericAPIResponse<Void> failure(int code, String message) {
        return ApiResponse.failure(code, message, getCurrentRequestId());
    }

    public GenericAPIResponse<Void> failure(ResponseCode responseCode, String message) {
        return ApiResponse.failure(responseCode.getCode(), message, getCurrentRequestId());
    }

}
