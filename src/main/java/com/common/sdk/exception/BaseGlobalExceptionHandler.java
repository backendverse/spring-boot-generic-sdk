package com.common.sdk.exception;

import com.common.sdk.services.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseGlobalExceptionHandler {

    private final ResponseHandler responseHandler;


}
