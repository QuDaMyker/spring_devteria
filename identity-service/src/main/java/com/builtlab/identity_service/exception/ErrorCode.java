package com.builtlab.identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXITED(1002, "User exited", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password invalid", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You do not have permission to access", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(1007, "Invalid Token", HttpStatus.FORBIDDEN),
    ACCESS_DENIED(1008, "Access Denied", HttpStatus.FORBIDDEN),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
