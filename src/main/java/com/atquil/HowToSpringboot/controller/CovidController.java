package com.atquil.HowToSpringboot.controller;

import com.atquil.HowToSpringboot.service.CovidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid")
@RequiredArgsConstructor
public class CovidController {

    private final CovidService covidService;
    @GetMapping("/get-all-country-covid-data")
    public ResponseEntity<?> callRapidEndpointToGetCovidData(){
        return ResponseEntity.ok(covidService.getAllCountryCovidData());

    }
}
