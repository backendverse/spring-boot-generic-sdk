package com.common.sdk.utils;

import org.slf4j.MDC;

import static com.common.sdk.constants.ApplicationConstant.CORRELATION_ID;

public final class CommonUtils {

    private CommonUtils() {
    }

    public static String getCurrentRequestId() {
        return MDC.get(CORRELATION_ID);
    }
}
