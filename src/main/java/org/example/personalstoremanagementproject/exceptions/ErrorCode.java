package org.example.personalstoremanagementproject.exceptions;


public enum ErrorCode {
    USER_EXIST(1001, "User already exist"),
    USER_NOT_FOUND(1002, "User not found"),
    USER_NOT_ACTIVE(1003, "User not active"),
    USER_NOT_AUTHORIZED(1004, "User not authorized"),
    USER_NOT_LOGGED_IN(1005, "User not logged in"),
    USER_NOT_VERIFIED(1006, "User not verified"),
    USER_NOT_DELETED(1007, "User not deleted"),
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

    public String getMessage() {
        return message;
    }
}
