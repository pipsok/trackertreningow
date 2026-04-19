package com.example.trackertreningow;

import java.io.Serializable;

// Serializable potrzebne do zapisu przy rotacji ekranu
public class Workout implements Serializable {

    private String date;        // data treningu
    private String name;        // nazwa ćwiczenia
    private String description; // opis ćwiczenia
    private String imagePath;   // ścieżka do zdjęcia

    // Konstruktor - tworzy nowy obiekt treningu
    public Workout(String date, String name, String description, String imagePath) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Gettery - pobieranie danych
    public String getDate() { return date; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }
}