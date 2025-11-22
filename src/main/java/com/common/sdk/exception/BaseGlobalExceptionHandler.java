package com.common.sdk.exception;

import com.common.sdk.models.enums.APIResponseCode;
import com.common.sdk.models.interfaces.GenericAPIResponse;
import com.common.sdk.services.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseGlobalExceptionHandler {

    private final ResponseHandler responseHandler;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public GenericAPIResponse<Void> handleBaseException(BaseException ex) {
        log.error("BaseExceptionL code= {}, message = {}, error = ",
                ex.getStatusCode(), ex.getMessage(), ex);
        return responseHandler.failure(ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericAPIResponse<Void> handleValidation(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors()
                .stream().map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("Validation error: {} - {}", ex.getClass().getSimpleName(), errorMsg);
        return responseHandler.failure(APIResponseCode.VALIDATION_ERROR.getCode(), errorMsg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericAPIResponse<Void> handleMissingParam(MissingServletRequestParameterException ex) {
        log.warn("Missing request parameter: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.MISSING_REQUEST_PARAM.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericAPIResponse<Void> handleMissingPathVar(MissingPathVariableException ex) {
        log.warn("Missing path variable: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.MISSING_PATH_VARIABLE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericAPIResponse<Void> handleMissingHeader(MissingRequestHeaderException ex) {
        log.warn("Missing request header: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.MISSING_REQUEST_HEADER.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericAPIResponse<Void> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.warn("Argument type mismatch: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.ARGUMENT_TYPE_MISMATCH.getCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericAPIResponse<Void> handleUnreadable(HttpMessageNotReadableException ex) {
        log.warn("Message not readable: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.HTTP_MESSAGE_NOT_READABLE.getCode(), "Invalid request payload or format");
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericAPIResponse<Void> handleConversion(ConversionFailedException ex) {
        log.warn("Conversion failed: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.CONVERSION_FAILED.getCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public GenericAPIResponse<Void> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        log.warn("Method not allowed: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public GenericAPIResponse<Void> handleUnsupportedMedia(HttpMediaTypeNotSupportedException ex) {
        log.warn("Unsupported media type: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.UNSUPPORTED_MEDIA_TYPE);
    }

    // --- Java Runtime exceptions ---
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericAPIResponse<Void> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Illegal argument: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.ILLEGAL_ARGUMENT.getCode(), ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericAPIResponse<Void> handleIllegalState(IllegalStateException ex) {
        log.error("Illegal state: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.ILLEGAL_STATE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericAPIResponse<Void> handleNullPointer(NullPointerException ex) {
        log.error("Null pointer: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.NULL_POINTER.getCode(),
                "Unexpected null value encountered");
    }

    // --- Fallback handler for uncaught exceptions ---
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericAPIResponse<Void> handleGeneric(Exception ex) {
        log.error("Unhandled exception: {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return responseHandler.failure(APIResponseCode.INTERNAL_ERROR.getCode(),
                "Sorry something went wrong on server side, please try again after sometime.");
    }

}
