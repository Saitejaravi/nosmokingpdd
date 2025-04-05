package com.example.nosmoking;

public class Patient {
    private String name;
    private String age;
    private String condition;  // 🔹 Ensure this field exists
    private int imageResId;

    public Patient(String name, String age, String condition, int imageResId) {
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCondition() {  // 🔹 Add this missing method
        return condition;
    }

    public int getImageResId() {
        return imageResId;
    }
}
