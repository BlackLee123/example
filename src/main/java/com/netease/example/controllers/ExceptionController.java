package com.netease.example.controllers;

import com.netease.example.utils.ApiResponse;
import io.sentry.Sentry;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ApiResponse errorHandler(Exception ex) {
        Sentry.captureException(ex);
        return new ApiResponse(false, null, ex.getMessage());
    }
}

