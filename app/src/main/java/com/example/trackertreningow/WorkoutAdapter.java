package com.example.trackertreningow;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    private Context context;
    private List<Workout> workouts;

    public WorkoutAdapter(Context context, List<Workout> workouts) {
        super(context, 0, workouts);
        this.context = context;
        this.workouts = workouts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item, parent, false);
        }

        Workout w = workouts.get(position);

        TextView name = convertView.findViewById(R.id.name);
        TextView date = convertView.findViewById(R.id.date);
        TextView desc = convertView.findViewById(R.id.description);
        ImageView img = convertView.findViewById(R.id.image);

        name.setText(w.getName());
        date.setText(w.getDate());
        desc.setText(w.getDescription());

        if (w.getImagePath() != null) {
            img.setImageURI(Uri.parse(w.getImagePath()));
        }

        return convertView;
    }
}