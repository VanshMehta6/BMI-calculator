package com.bmi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class bmirecord {
    private final String id;
    private final String personName;
    private final double bmi;
    private final String category;
    private final LocalDateTime timestamp;
    
    public bmirecord(String personName, double bmi, String category) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.personName = personName;
        this.bmi = bmi;
        this.category = category;
        this.timestamp = LocalDateTime.now();
    }
    
    public String getId() { return id; }
    public String getpersonName() { return personName; }
    public double getBmi() { return bmi; }
    public String getCategory() { return category; }
    public LocalDateTime getTimestamp() { return timestamp; }
    
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return timestamp.format(formatter);
    }
    
    public boolean isHealthy() {
        return bmi >= 18.5 && bmi <= 24.9;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - BMI: %.2f (%s)", 
                            getFormattedDate(), personName, bmi, category);
    }
}