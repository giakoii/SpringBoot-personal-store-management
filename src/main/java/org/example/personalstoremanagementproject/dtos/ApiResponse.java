package org.example.personalstoremanagementproject.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse <T> {
    private Boolean success;
    private int status;
    private int code;
    private String message;
    private T result;
}
