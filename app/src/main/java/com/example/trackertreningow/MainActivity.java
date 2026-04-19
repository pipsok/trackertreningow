package com.example.trackertreningow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    ArrayList<Workout> workouts;
    WorkoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        workouts = new ArrayList<>();
        adapter = new WorkoutAdapter(this, workouts);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWorkoutActivity.class);
            startActivityForResult(intent, 1);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            workouts.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String date = data.getStringExtra("date");
            String name = data.getStringExtra("name");
            String desc = data.getStringExtra("desc");
            String img = data.getStringExtra("img");

            workouts.add(new Workout(date, name, desc, img));
            adapter.notifyDataSetChanged();
        }
    }
}