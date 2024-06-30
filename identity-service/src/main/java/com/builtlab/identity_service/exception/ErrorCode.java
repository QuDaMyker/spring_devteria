package com.builtlab.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXITED(1002, "User exited"),
    USERNAME_INVALID(1003, "Username invalid"),
    PASSWORD_INVALID(1004, "Password invalid"),
    USER_NOT_EXIST(1005, "User not existed"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
    INVALID_TOKEN(1007, "Invalid Token"),
    ACCESS_DENIED(1008, "Access Denied"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
