package com.example.trackertreningow;

import java.io.Serializable;
import java.util.ArrayList;

// Serializable potrzebne do zapisu przy rotacji ekranu
public class Workout implements Serializable {

    private String date;        // data treningu
    private String name;        // nazwa ćwiczenia
    private String description; // opis ćwiczenia
    private String imagePath;   // ścieżka do zdjęcia

    private ArrayList<Serie> serieList = new ArrayList<>();

    public ArrayList<Serie> getSerieList() {
        return serieList;
    }

    public void addSerie(Serie s) {
        serieList.add(s);
    }
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