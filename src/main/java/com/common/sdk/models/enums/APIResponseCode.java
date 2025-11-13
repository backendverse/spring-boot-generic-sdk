package com.common.sdk.models.enums;

import com.common.sdk.models.interfaces.ResponseCode;
import lombok.Getter;

@Getter
public enum APIResponseCode implements ResponseCode {

    SUCCESS(200, "Success"),
    INTERNAL_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    APIResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
