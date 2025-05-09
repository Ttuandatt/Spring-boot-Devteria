package com.learnspring.identity_service.exception;

public class AppException extends RuntimeException {
    // When there is any exception occurs, and we want to return a message, we just need to call this AppExcetion class with an ErrorCode which we predefined
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
