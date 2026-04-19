package com.example.trackertreningow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddWorkoutActivity extends AppCompatActivity {

    EditText etDate, etName, etDesc;
    Button btnSave, btnImage;
    String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etDate = findViewById(R.id.etDate);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);

        btnImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 2);
        });

        btnSave.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("date", etDate.getText().toString());
            result.putExtra("name", etName.getText().toString());
            result.putExtra("desc", etDesc.getText().toString());
            result.putExtra("img", imagePath);

            setResult(RESULT_OK, result);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imagePath = uri.toString();
        }
    }
}