package com.project.kien.identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    INVALID_YEAR(1007,"invalid year",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1005,"invalid message key",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002,"User existed",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1009,"User not existed",HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(1003,"User not found",HttpStatus.BAD_REQUEST),
    UNCATEGORIZED(9999,"uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_VALID(1004,"Username must be at least 4 character",HttpStatus.BAD_REQUEST),
    PASSWORD_VALID(1006,"Password must be at least 4 character",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1001,"unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHOZIED(1006,"you don't have permission",HttpStatus.FORBIDDEN)
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;


    ErrorCode(int code, String message,HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode =statusCode;

    }
}
