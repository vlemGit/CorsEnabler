package com.vlemgit.CorsEnabler.controller;


import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/proxy")
@CrossOrigin(origins = "*")
public class ProxyController {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProxyController.class);


    public ProxyController(){
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchDataFromApi(@RequestParam String url){
        try{
            UrlValidator validator = new UrlValidator();
            if(validator.isValid(url)) {
                logger.info("Getting data from {} ", url);
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            } else return ResponseEntity.badRequest().body("Invalid URL");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Error : " + e.getMessage() + "with the api call : " + url);
        }
    }
}