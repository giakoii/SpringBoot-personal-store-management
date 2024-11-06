package project.personal.personalstoremanagementproject.exceptions;


import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_EXIST("1001", "User already exist"),
    USER_NOT_FOUND("1002", "User not found"),
    USER_NOT_ACTIVE("1003", "User not active"),
    USER_NOT_AUTHORIZED("1004", "User not authorized"),
    USER_NOT_LOGGED_IN("1005", "User not logged in"),
    USER_NOT_VERIFIED("1006", "User not verified"),
    USER_NOT_DELETED("1007", "User not deleted"),
    SUCCESS("I00001", "Success"),
;
    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
