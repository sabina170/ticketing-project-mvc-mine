package com.cydeo.enums;

public enum Gender {

    MALE("Male"), FEMALE("Female");
    private String gender;

    Gender(String gender) {
        this.gender=gender;
    }

    public String getGender() {
        return gender;
    }
}
