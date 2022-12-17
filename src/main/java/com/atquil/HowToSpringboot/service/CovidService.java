package com.atquil.HowToSpringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class CovidService {


    @Value("${rapidAPI.covid.data.url}")
    private String url;

    @Value("${rapidAPI.covid.data.xRapidApiKey}")
    private String xRapidApiKey;
    @Value("${rapidAPI.covid.data.getxRapidApiHost}")
    private String getxRapidApiHost;

    @Autowired
    private RestTemplate restTemplate;

    public Object getAllCountryCovidData() {
            try {
                //Header value is set
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-RapidAPI-Key", xRapidApiKey);
                headers.set("X-RapidAPI-Host", getxRapidApiHost);

                // Make a GET call to the RapidAPI

                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class);

                log.info("Output form rapidAPI:{}",response.getBody());

                return response.getBody();
            }catch (Exception e){
                log.error("Something went wrong while getting value from RapidAPI",e);
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Exception while calling endpoint of RapidAPI for corona",
                        e
                );
            }
    }
}
