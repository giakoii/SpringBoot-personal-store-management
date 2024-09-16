package org.example.personalstoremanagementproject.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T> {
    private Boolean success;
    private int code;
    private String message;
    private T result;
}
