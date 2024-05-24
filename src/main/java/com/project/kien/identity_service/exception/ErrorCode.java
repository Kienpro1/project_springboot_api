package com.project.kien.identity_service.exception;

public enum ErrorCode {
    INVALID_YEAR(1007,"invalid year"),
    INVALID_KEY(1005,"invalid message key"),
    USER_EXISTED(1002,"User existed"),
    USER_NOT_FOUND(1003,"User not found"),
    UNCATEGORIZED(9999,"uncategorized error"),
    USERNAME_VALID(1004,"Username must be at least 4 character"),
    PASSWORD_VALID(1006,"Password must be at least 4 character"),

    ;
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
