package com.example.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    // Basic greeting endpoint
    @GetMapping("/greet")
    public String greet() {
        return "Welcome to Spring Boot!";
    }
    
    // Endpoint with path variable and optional query parameter
    @GetMapping("/greet/{name}")
    public String greetWithNameAndMessage(
            @PathVariable String name,
            @RequestParam(required = false) String message) {
        
        if (message != null && !message.isEmpty()) {
            return "Hello " + name + "! " + message;
        } else {
            return "Hello " + name + "! Welcome to Spring Boot!";
        }
    }
}