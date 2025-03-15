package com.example.getgreetings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GetgreetingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetgreetingsApplication.class, args);
    }

    // Method to create a RestTemplate bean
    @Bean
    public RestTemplate getHttpClient() {
        return new RestTemplateBuilder().build();
    }

    // Method to get a greeting from the API
    public Greeting getGreeting() {
        RestTemplate restTemplate = getHttpClient();
        try {
            return restTemplate.getForObject("http://localhost:8080/greeting", Greeting.class);
        } catch (Exception e) {
            System.err.println("Error getting default greeting: " + e.getMessage());
            throw e;
        }
    }

    // Method to get a greeting with a name from the API
    public Greeting getGreetingByName(String name) {
        RestTemplate restTemplate = getHttpClient();
        try {
            return restTemplate.getForObject(
                "http://localhost:8080/greeting/name?name=" + name, 
                Greeting.class);
        } catch (Exception e) {
            System.err.println("Error getting named greeting: " + e.getMessage());
            throw e;
        }
    }

    // Method to get a greeting with date from the API
    public Greeting getGreetingWithDate(String name) {
        RestTemplate restTemplate = getHttpClient();
        try {
            return restTemplate.getForObject(
                "http://localhost:8080/greeting/withdate?name=" + name, 
                Greeting.class);
        } catch (Exception e) {
            System.err.println("Error getting dated greeting: " + e.getMessage());
            throw e;
        }
    }

    // Method to make all API calls and print the results
    public void makeCalls() {
        try {
            // Get default greeting
            Greeting greeting = getGreeting();
            System.out.println("Default greeting received: " + greeting.content() + " (ID: " + greeting.id() + ")");
            
            // Get greeting with name
            Greeting namedGreeting = getGreetingByName("Student");
            System.out.println("Named greeting received: " + namedGreeting.content() + " (ID: " + namedGreeting.id() + ")");

            // Try getting greeting with date if the endpoint exists
            try {
                Greeting datedGreeting = getGreetingWithDate("Student");
                System.out.println("Dated greeting received: " + datedGreeting.content() + 
                                   " (ID: " + datedGreeting.id() + 
                                   (datedGreeting.date() != null ? ", Date: " + datedGreeting.date() : "") + ")");
            } catch (Exception e) {
                System.out.println("Could not get dated greeting. Have you implemented the withdate endpoint? Error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error in makeCalls: " + e.getMessage());
        }
    }

    // CommandLineRunner bean to execute the makeCalls method at startup
    @Bean
    public CommandLineRunner run() {
        return args -> {
            try {
                makeCalls();
            } catch (Exception e) {
                System.err.println("Error in CommandLineRunner: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}