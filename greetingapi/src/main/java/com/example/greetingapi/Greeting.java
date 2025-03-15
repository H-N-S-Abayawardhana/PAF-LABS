package com.example.greetingapi;

import java.time.LocalDate;

public record Greeting(long id, String content, LocalDate date) {
    public Greeting(long id, String content) {
        this(id, content, null);
    }
}