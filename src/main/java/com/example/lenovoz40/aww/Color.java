package com.example.lenovoz40.aww;

/**
 * Created by Lenovo Z40 on 4/26/2016.
 */
public class Color {
    private String name;
    private String color;

    public Color(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name;
    }
}
