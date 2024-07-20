package com.atquil.HowToSpringboot.service;

import org.springframework.stereotype.Service;

/**
 * @author atquil
 */

@Service
public class MyExceptionService {

    public String myExceptionService(String type) throws NullPointerException{
        if(type.equals("NPE")){
            throw new NullPointerException("ExceptionType NPE");
        }
        else
            return "Success";
    }
}
