package com.atquil.HowToSpringboot.controller;

import com.atquil.HowToSpringboot.service.MyExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author atquil
 */

@RestController
@RequestMapping("/api/exception")
@RequiredArgsConstructor
public class MyExceptionController {

    private MyExceptionService myExceptionService;
    @GetMapping("/globalException/{type}")
    public ResponseEntity<?> myGlobalException(@RequestParam("type") String type) throws NullPointerException{
        return ResponseEntity.ok(myExceptionService.myExceptionService(type));
    }
}
