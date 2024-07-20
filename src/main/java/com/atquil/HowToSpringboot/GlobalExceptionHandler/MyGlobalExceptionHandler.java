package com.atquil.HowToSpringboot.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author atquil
 */

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    //Now whenever the NPE is being thrown it will be handled here
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<?,?> myNullPointerException(NullPointerException exception){
        Map<String, String> exceptionMap  = new HashMap<>();
        exceptionMap.put("message",exception.getMessage());
        return exceptionMap;
    }
}
