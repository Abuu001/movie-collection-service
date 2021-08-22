package io.lugonzo.springBootConfig.controller;

import io.lugonzo.springBootConfig.config.DbSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingController {

    @Value("${my.greeting}")
    private String greetingMessage;

    @Value("${app.description : defaultValue}")
    private String description;

    @Value("${my.list.values}")
    private List<String> listValues;

    @Autowired
    private DbSettings dbSettings;

    @GetMapping("/greeting")
    public String greetings(){
        return  greetingMessage + description + listValues +" " + dbSettings.getPort() + dbSettings.getConnection();
    }

}
