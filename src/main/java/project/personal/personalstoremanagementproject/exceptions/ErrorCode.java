package project.personal.personalstoremanagementproject.exceptions;


import lombok.Getter;

@Getter
public enum ErrorCode {

    SUCCESS("I00001", "Success"),
    VALIDATION_ERROR("E00000", "Validation error"),
    FAILED("E00001", "Failed"),
    NOT_FOUND("E00002", "Not found"),
;
    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
