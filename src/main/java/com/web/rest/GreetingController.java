package com.web.rest;

import java.util.concurrent.atomic.AtomicLong;

import com.back.Entry;
import com.back.SQLData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

        // greeting?name=param
    @GetMapping("/Query")
    public Entry Query(@RequestParam(value = "name", defaultValue = "World") String name){
        SQLData init = new SQLData();
        init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
        var test = init.readEntry(name);
        System.out.println(test);
        return test;
 
    }
}
