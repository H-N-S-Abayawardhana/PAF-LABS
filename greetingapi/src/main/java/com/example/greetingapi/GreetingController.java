package com.example.greetingapi;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting() {
        return new Greeting(counter.incrementAndGet(), String.format(template, "World"));
    }

    @GetMapping("/greeting/name")
    public Greeting greetingWithName(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/greeting/withdate")
    public Greeting greetingWithDate(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(
        counter.incrementAndGet(), 
        String.format(template, name),
        LocalDate.now()
    );
}




    
}