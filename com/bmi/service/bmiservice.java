package com.bmi.service;

import com.bmi.model.person;
import com.bmi.model.bmirecord;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class bmiservice {
    private final List<person> users;
    private final List<bmirecord> history;
    
    public bmiservice() {
        this.users = new ArrayList<>();
        this.history = new ArrayList<>();
    }
    
    // User management
    public void addperson(person person) {
        if (person != null) {
            users.add(person);
        }
    }
    
    public person getperson(int index) {
        if (index >= 0 && index < users.size()) {
            return users.get(index);
        }
        return null;
    }
    
    public List<person> getAllpersons() {
        return new ArrayList<>(users);
    }
    
    public int getpersonCount() {
        return users.size();
    }
    
    public boolean hasUsers() {
        return !users.isEmpty();
    }
    
    // BMI calculation and storage
    public bmirecord calculateAndStoreBMI(person person) {
        double bmi = person.calculateBMI();
        String category = person.getBMICategory();
        bmirecord record = new bmirecord(person.getName(), bmi, category);
        history.add(record);
        return record;
    }
    
    // History management
    public List<bmirecord> getAllHistory() {
        return new ArrayList<>(history);
    }
    
    public List<bmirecord> getHistoryForperson(String personName) {
        return history.stream()
                .filter(record -> record.getpersonName().equals(personName))
                .collect(Collectors.toList());
    }
    
    public boolean hasHistory() {
        return !history.isEmpty();
    }
    
    public void clearHistory() {
        history.clear();
    }
    
    // Statistics
    public double getAverageBMI() {
        if (history.isEmpty()) return 0;
        double sum = history.stream().mapToDouble(bmirecord::getBmi).sum();
        return sum / history.size();
    }
    
    public bmirecord getLatestRecord() {
        if (history.isEmpty()) return null;
        return history.get(history.size() - 1);
    }
}