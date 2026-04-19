package com.example.trackertreningow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

// Główna aktywność aplikacji
public class MainActivity extends AppCompatActivity {

    ListView listView;                 // lista treningów
    Button btnAdd;                     // przycisk dodawania
    ArrayList<Workout> workouts;       // lista danych
    WorkoutAdapter adapter;            // adapter do ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pobranie widoków z XML
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        // sprawdzenie czy mamy zapisane dane (np. po rotacji)
        if (savedInstanceState != null) {
            workouts = (ArrayList<Workout>) savedInstanceState.getSerializable("workouts");
        } else {
            workouts = new ArrayList<>();
        }

        // tworzenie adaptera i podpięcie do listy
        adapter = new WorkoutAdapter(this, workouts);
        listView.setAdapter(adapter);

        // kliknięcie przycisku - przejście do drugiej aktywności
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWorkoutActivity.class);
            startActivityForResult(intent, 1);
        });

        // długie kliknięcie - usuwanie elementu
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            workouts.remove(position);      // usunięcie z listy
            adapter.notifyDataSetChanged(); // odświeżenie UI
            return true;
        });
    }

    // zapis danych przy rotacji ekranu
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("workouts", workouts);
    }

    // odbiór danych z AddWorkoutActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            // pobranie danych z intentu
            String date = data.getStringExtra("date");
            String name = data.getStringExtra("name");
            String desc = data.getStringExtra("desc");
            String img = data.getStringExtra("img");

            // dodanie nowego treningu do listy
            workouts.add(new Workout(date, name, desc, img));

            // odświeżenie listy
            adapter.notifyDataSetChanged();

            // komunikat dla użytkownika
            Toast.makeText(this, "Dodano trening", Toast.LENGTH_SHORT).show();
        }
    }
}