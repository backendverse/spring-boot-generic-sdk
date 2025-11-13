package com.common.sdk.models.dto;

import com.common.sdk.models.interfaces.GenericAPIResponse;
import lombok.Getter;

@Getter
public class ApiResponse<R> implements GenericAPIResponse<R> {

    private boolean success;
    private R data;
    private Integer code;
    private String errorMessage;
    private String requestId;

    private ApiResponse() {}

    //
}
