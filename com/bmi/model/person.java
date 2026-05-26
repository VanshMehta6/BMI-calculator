package com.bmi.model;

import java.time.LocalDateTime;

public class person {
    private String name;
    private int age;
    private double height;
    private double weight;
    private LocalDateTime createdAt;
    
    public person(String name, int age, double height, double weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    // Setters with validation
    public void setWeight(double weight) {
        if (weight > 0 && weight < 500) {
            this.weight = weight;
        }
    }
    
    public void setHeight(double height) {
        if (height > 0 && height < 3) {
            this.height = height;
        }
    }
    
    // BMI Calculation
    public double calculateBMI() {
        if (height <= 0) return 0;
        return weight / (height * height);
    }
    
    // BMI Category
    public String getBMICategory() {
        double bmi = calculateBMI();
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal weight";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }
    
    // Health advice
    public String getHealthAdvice() {
        double bmi = calculateBMI();
        if (bmi < 18.5) {
            return "Eat more protein-rich foods and healthy fats. Consult a nutritionist.";
        } else if (bmi < 25) {
            return "Great! Maintain with balanced diet and regular exercise.";
        } else if (bmi < 30) {
            return "Reduce carbohydrates and sugar. Walk 30 minutes daily.";
        } else {
            return "Please consult a doctor for personalized weight management plan.";
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s | Age: %d | %.2fm | %.1fkg", name, age, height, weight);
    }
}