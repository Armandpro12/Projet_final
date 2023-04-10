package fr.bam.projetfinal.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fr.bam.projetfinal.DoctorDB;
import fr.bam.projetfinal.R;

public class OrdonnanceAdapter extends ArrayAdapter<Ordonnance> {

    public OrdonnanceAdapter(Context context, ArrayList<Ordonnance> ordonnances) {
        super(context, 0, ordonnances);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Ordonnance ordonnance = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ordonnance_cell, parent, false);
        }

        TextView mFirstName = convertView.findViewById(R.id.ordonnance_cell_name_textView);
        TextView description = convertView.findViewById(R.id.ordonnance_cell_date_textView);
        DoctorDB db = new DoctorDB(getContext());
        mFirstName.setText(db.getMedication(ordonnance.getMedicationID()).getName());
        description.setText(ordonnance.getDescription());
        description.setTextSize(10);

        return convertView;
    }
}
