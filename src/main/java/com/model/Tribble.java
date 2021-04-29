package com.model;

public class Tribble {

    private int tribbleId;
    private int age;
    private String color;
    private int labId;

    public Tribble() {
    }

    public Tribble(int tribbleId, int age, String color, int labId) {
        this.tribbleId = tribbleId;
        this.age = age;
        this.color = color;
        this.labId = labId;
    }

    public int getTribbleId() {
        return tribbleId;
    }

    public void setTribbleId(int tribbleId) {
        this.tribbleId = tribbleId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }
}
