package com.example.trackertreningow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

// Główna aktywność aplikacji - wyświetla listę treningów
public class MainActivity extends AppCompatActivity {

    ListView listView;                 // lista treningów
    Button btnAdd;                     // przycisk dodawania
    ArrayList<Workout> workouts;       // lista danych (treningów)
    WorkoutAdapter adapter;            // adapter łączący dane z widokiem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pobranie widoków z XML
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        // 🔥 WCZYTYWANIE DANYCH Z PAMIĘCI (SharedPreferences + Gson)
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("workouts", null);

        // typ potrzebny do konwersji JSON -> ArrayList
        Type type = new TypeToken<ArrayList<Workout>>() {}.getType();

        if (json != null) {
            // jeśli dane istnieją - wczytaj je
            workouts = gson.fromJson(json, type);
        } else {
            // jeśli nie ma danych - stwórz pustą listę
            workouts = new ArrayList<>();
        }

        // podłączenie adaptera do ListView
        adapter = new WorkoutAdapter(this, workouts);
        listView.setAdapter(adapter);

        // kliknięcie przycisku - przejście do dodawania treningu
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWorkoutActivity.class);
            startActivityForResult(intent, 1);
        });

        // długie kliknięcie - usuwanie treningu
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            workouts.remove(position);      // usunięcie z listy
            adapter.notifyDataSetChanged(); // odświeżenie UI
            saveData();                    // zapis zmian
            return true;
        });
    }

    // 🔥 odbiór danych z AddWorkoutActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            // pobranie danych z drugiej aktywności
            String date = data.getStringExtra("date");
            String name = data.getStringExtra("name");
            String desc = data.getStringExtra("desc");
            String img = data.getStringExtra("img");

            // dodanie nowego treningu do listy
            workouts.add(new Workout(date, name, desc, img));

            // odświeżenie listy
            adapter.notifyDataSetChanged();

            // zapis do pamięci
            saveData();

            // komunikat dla użytkownika
            Toast.makeText(this, "Dodano trening", Toast.LENGTH_SHORT).show();
        }
    }

    // 🔥 metoda zapisująca dane do pamięci telefonu
    private void saveData() {
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // konwersja listy do JSON
        Gson gson = new Gson();
        String json = gson.toJson(workouts);

        // zapis do pamięci
        editor.putString("workouts", json);
        editor.apply();
    }
}