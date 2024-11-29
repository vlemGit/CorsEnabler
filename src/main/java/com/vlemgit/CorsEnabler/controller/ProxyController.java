package com.vlemgit.CorsEnabler.controller;


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
            logger.info("Getting data from {} " , url);
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Error : " + e.getMessage() + "with the api call : " + url);
        }
    }
}
