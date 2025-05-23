package com.learnspring.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception!"),
    INVALID_KEY(8888, "Invalid message key!"),
    USER_EXISTED(1001, "Username existed!"),
    INVALID_USERNAME(1002, "Username must be at least 3 characters!"),
    INVALID_PASSWORD(1003, "Password must be at least 3 characters!"),
    UPDATE_FAILED(1004, "Update failed!"),
    RESOURCE_NOT_FOUND(1005, "Resource not found!"),
    USER_NOT_FOUND(1006, "User not found!"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
