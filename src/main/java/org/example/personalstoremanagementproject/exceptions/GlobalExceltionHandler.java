package org.example.personalstoremanagementproject.exceptions;

import org.example.personalstoremanagementproject.dtos.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceltionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setCode(400);
        response.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception e) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setCode(400);
        response.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setCode(400);
        response.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setCode(400);
        response.setMessage(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
