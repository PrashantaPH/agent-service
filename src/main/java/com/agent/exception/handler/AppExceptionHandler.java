package com.agent.exception.handler;

import com.agent.exception.AgentAlreadyExistsException;
import com.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.common.utils.CommonUtil.errorObject;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(AgentAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserAlreadyExists(AgentAlreadyExistsException exception) {
        ApiResponse<Object> response = errorObject(exception.getErrorCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
