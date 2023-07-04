package com.snob.tech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchMethodException(NoSuchMethodException ex, Model model) {
        model.addAttribute("errorMessage", "Oops! The requested endpoint does not exist.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Oops! Something went wrong.");
        return "error";
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable ex, Model model) {
        model.addAttribute("errorMessage", "Oops! Something went wrong.");
        return "error";
    }
}
