package com.example.trackertreningow;

public class Workout {
    private String date;
    private String name;
    private String description;
    private String imagePath;

    public Workout(String date, String name, String description, String imagePath) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getDate() { return date; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }
}