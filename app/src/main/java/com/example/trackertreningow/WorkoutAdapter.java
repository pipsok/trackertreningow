package com.example.trackertreningow;

import android.content.Context;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.util.List;
import android.content.Intent;
import android.net.Uri;

// Adapter łączy dane (Workout) z widokiem ListView
public class WorkoutAdapter extends ArrayAdapter<Workout> {

    public WorkoutAdapter(Context context, List<Workout> workouts) {
        super(context, 0, workouts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // jeśli widok nie istnieje - tworzymy nowy
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        // pobranie aktualnego elementu
        Workout w = getItem(position);

        // podpięcie widoków
        TextView name = convertView.findViewById(R.id.name);
        TextView date = convertView.findViewById(R.id.date);
        TextView desc = convertView.findViewById(R.id.description);
        ImageView img = convertView.findViewById(R.id.image);
        Button btnSearch = convertView.findViewById(R.id.btnSearch);

        // ustawienie danych
        name.setText(w.getName());
        date.setText(w.getDate());
        desc.setText(w.getDescription());

        // ustawienie zdjęcia jeśli istnieje
        if (w.getImagePath() != null && !w.getImagePath().isEmpty()) {
            img.setImageURI(Uri.parse(w.getImagePath()));
        } else {
            img.setImageResource(android.R.drawable.ic_menu_gallery);
        }
        //funkcja na wyszukiwanie w google
        btnSearch.setOnClickListener(v -> {
            String query = "cwiczenie na " + w.getName();

            // zamiana spacji na %20 (URL encoding uproszczony)
            String url = "https://www.google.com/search?q=" + query.replace(" ", "%20");

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            getContext().startActivity(intent);
        });
        return convertView;
    }
}