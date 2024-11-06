package project.personal.personalstoremanagementproject.exceptions;

public class AppException extends RuntimeException{
    private ErrorCode codeError;

    public AppException(ErrorCode codeError) {
        super(codeError.getMessage());
        this.codeError = codeError;
    }

    public ErrorCode getCodeError() {
        return codeError;
    }

    public void setCodeError(ErrorCode codeError) {
        this.codeError = codeError;
    }
}
