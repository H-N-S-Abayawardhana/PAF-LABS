package com.example.getgreetings;

import java.time.LocalDate;

public record Greeting(long id, String content, LocalDate date) {
}