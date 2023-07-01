package com.atquil.HowToSpringboot.controller;

import com.atquil.HowToSpringboot.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @GetMapping("/home")
    public ResponseEntity<?> getApplicationName(){
        return ResponseEntity.ok(monitoringService.getApplicationName());
    }
}
