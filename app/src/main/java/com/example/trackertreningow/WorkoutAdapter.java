package com.example.trackertreningow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.*;
import android.widget.*;

import java.util.List;

// Adapter odpowiada za wyświetlanie elementów w ListView
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

        // pobranie aktualnego treningu
        Workout w = getItem(position);

        // podpięcie elementów z layoutu
        TextView name = convertView.findViewById(R.id.name);
        TextView date = convertView.findViewById(R.id.date);
        TextView desc = convertView.findViewById(R.id.description);
        ImageView img = convertView.findViewById(R.id.image);
        TextView serieList = convertView.findViewById(R.id.serieList);

        Button btnSearch = convertView.findViewById(R.id.btnSearch);
        Button btnAddSerie = convertView.findViewById(R.id.btnAddSerie);

        // ustawienie danych w widoku
        name.setText(w.getName());
        date.setText(w.getDate());
        desc.setText(w.getDescription());
        // budowanie tekstu z serii
        StringBuilder sb = new StringBuilder();

        if (w.getSerieList() != null && !w.getSerieList().isEmpty()) {
            sb.append("Serie:\n");

            for (Serie s : w.getSerieList()) {
                sb.append(s.getKg())
                        .append(" kg x ")
                        .append(s.getReps())
                        .append("\n");
            }

            serieList.setText(sb.toString());
        } else {
            serieList.setText("Brak serii");
        }

        // ustawienie zdjęcia (jeśli istnieje)
        if (w.getImagePath() != null && !w.getImagePath().isEmpty()) {
            img.setImageURI(Uri.parse(w.getImagePath()));
        } else {
            img.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        // 🔥 PRZYCISK GOOGLE SEARCH
        btnSearch.setOnClickListener(v -> {

            // tworzenie zapytania do wyszukiwarki
            String query = "cwiczenie na " + w.getName();

            // budowa URL
            String url = "https://www.google.com/search?q=" + query.replace(" ", "%20");

            // intent otwierający przeglądarkę
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            getContext().startActivity(intent);
        });

        // 🔥 PRZYCISK DODAWANIA SERII
        btnAddSerie.setOnClickListener(v -> {

            // tworzenie prostego layoutu w popupie
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            // pole na kilogramy
            EditText etKg = new EditText(getContext());
            etKg.setHint("Kg");

            // pole na powtórzenia
            EditText etReps = new EditText(getContext());
            etReps.setHint("Powtórzenia");

            layout.addView(etKg);
            layout.addView(etReps);

            // dialog (popup)
            new AlertDialog.Builder(getContext())
                    .setTitle("Dodaj serię")
                    .setView(layout)

                    // kliknięcie OK
                    .setPositiveButton("OK", (dialog, which) -> {
                        try {
                            // pobranie danych z pól
                            int kg = Integer.parseInt(etKg.getText().toString());
                            int reps = Integer.parseInt(etReps.getText().toString());

                            // dodanie serii do treningu
                            w.addSerie(new Serie(kg, reps));
                            notifyDataSetChanged();

                            Toast.makeText(getContext(), "Dodano serię", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            // błąd gdy pola są puste lub niepoprawne
                            Toast.makeText(getContext(), "Błąd danych", Toast.LENGTH_SHORT).show();
                        }
                    })

                    // anulowanie
                    .setNegativeButton("Anuluj", null)
                    .show();
        });

        return convertView;
    }
}