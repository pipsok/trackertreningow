package com.example.trackertreningow;

import java.io.Serializable;

// pojedyncza seria
public class Serie implements Serializable {
    private int kg;
    private int reps;

    public Serie(int kg, int reps) {
        this.kg = kg;
        this.reps = reps;
    }

    public int getKg() { return kg; }
    public int getReps() { return reps; }
}