package com.example.trackertreningow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

// Aktywność do dodawania nowego treningu
public class AddWorkoutActivity extends AppCompatActivity {

    EditText etDate, etName, etDesc; // pola tekstowe
    Button btnSave, btnImage;        // przyciski
    String imagePath = "";           // ścieżka do zdjęcia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // podpięcie elementów z XML
        etDate = findViewById(R.id.etDate);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);

        // wybór zdjęcia z galerii
        btnImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 2);
        });

        // zapis treningu
        btnSave.setOnClickListener(v -> {

            // tworzenie intentu zwrotnego
            Intent result = new Intent();

            // przekazanie danych
            result.putExtra("date", etDate.getText().toString());
            result.putExtra("name", etName.getText().toString());
            result.putExtra("desc", etDesc.getText().toString());
            result.putExtra("img", imagePath);

            // ustawienie wyniku i zamknięcie aktywności
            setResult(RESULT_OK, result);
            finish();
        });
    }

    // odbiór zdjęcia z galerii
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();          // pobranie URI zdjęcia
            imagePath = uri.toString();        // zapis jako string
        }
    }
}