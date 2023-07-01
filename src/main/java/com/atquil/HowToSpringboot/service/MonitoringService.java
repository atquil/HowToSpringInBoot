package com.atquil.HowToSpringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Service
@Slf4j
public class MonitoringService {

    @Value("${spring.application.name}")
    private String applicationName;

    public String getApplicationName(){
       try{
           log.info("Application Name:{}",applicationName);
           log.error("Increase Error Count");
           return applicationName;
       } catch (Exception e){
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong",e);
       }
    }
}
