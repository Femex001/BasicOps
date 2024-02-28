package com.project.cbstask.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.project.cbstask.model.response.APIError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({
            NullPointerException.class,
            IllegalArgumentException.class,
            MismatchedInputException.class,
            Exception.class,
            RuntimeException.class
    })
    public ResponseEntity<APIError> handleException(Throwable throwable){
        return ResponseEntity
                .internalServerError()
                .body(new APIError(throwable.getMessage()));
    }
}
