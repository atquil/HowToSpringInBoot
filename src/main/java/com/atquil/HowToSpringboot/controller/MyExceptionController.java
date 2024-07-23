package com.atquil.HowToSpringboot.controller;

import com.atquil.HowToSpringboot.service.MyExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author atquil
 */

@RestController
@RequestMapping("/api/exception")
@RequiredArgsConstructor
public class MyExceptionController {

    private MyExceptionService myExceptionService;
    @GetMapping("/globalException/{type}")
    public ResponseEntity<?> myGlobalException(@PathVariable("type") String type) throws NullPointerException{
        return ResponseEntity.ok(myExceptionService.myExceptionService(type));
    }
}
