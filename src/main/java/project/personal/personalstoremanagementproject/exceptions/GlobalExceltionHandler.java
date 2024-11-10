package project.personal.personalstoremanagementproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import project.personal.personalstoremanagementproject.v1.ConcreteApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceltionHandler {
    /**
     * Handle Illegal Argument Exception
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ConcreteApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<DetailError> detailErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(this::mapFieldErrorToDetailError)
                .collect(Collectors.toList());

        ConcreteApiResponse<?> response = new ConcreteApiResponse<>();
        response.setSuccess(false);
        response.setMessageId(ErrorCode.FAILED);
        response.setMessage("Validation failed");
        response.setDetailErrorList(detailErrors);
        response.setResponse(null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private DetailError mapFieldErrorToDetailError(FieldError error) {
        String errorCode = getErrorCode(error);
        String errorMessage = error.getField() + " " + getErrorMessage(error);

        return new DetailError(error.getField(), errorCode, errorMessage);
    }

    private String getErrorCode(FieldError error) {
        // Check the error code and return the corresponding error code
        if (error.getCode().equals("NotBlank")) {
            return "REQUIRED_FIELD";
        } else if (error.getCode().equals("Size")) {
            return "INVALID_SIZE";
        } else if (error.getCode().equals("Pattern")) {
            return "INVALID_FORMAT";
        } else if (error.getCode().equals("Email")) {
            return "INVALID_EMAIL";
        } else if (error.getCode().equals("Min")) {
            return "MINIMUM_VALUE";
        } else if (error.getCode().equals("Max")) {
            return "MAXIMUM_VALUE";
        } else if (error.getCode().equals("NotNull")) {
            return "NOT_NULL";
        } else if (error.getCode().equals("Length")) {
            return "INVALID_LENGTH";
        } else if (error.getCode().equals("Range")) {
            return "INVALID_RANGE";
        }
        return "UNKNOWN_ERROR";
    }

    private String getErrorMessage(FieldError error) {
        // Xác định thông báo lỗi dựa trên loại ràng buộc
        if (error.getCode().equals("NotBlank")) {
            return "is required";
        } else if (error.getCode().equals("Size")) {
            return "must be within the required size";
        } else if (error.getCode().equals("Pattern")) {
            return "is invalid";
        } else if (error.getCode().equals("Email")) {
            return "is invalid";
        } else if (error.getCode().equals("Min")) {
            return "must be greater than or equal to " + error.getArguments()[1];
        } else if (error.getCode().equals("Max")) {
            return "must be less than or equal to " + error.getArguments()[1];
        } else if (error.getCode().equals("NotNull")) {
            return "must not be null";
        } else if (error.getCode().equals("Length")) {
            return "must be " + error.getArguments()[1] + " characters";
        } else if (error.getCode().equals("Range")) {
            return "must be between " + error.getArguments()[1] + " and " + error.getArguments()[2];

        }
        return "is invalid";
    }

}
