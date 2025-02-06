package test.aws.polly.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/customPolly")
public class Controller {
    @PostMapping(value = "syntethize")
    public void syntethize(){

    }
}
