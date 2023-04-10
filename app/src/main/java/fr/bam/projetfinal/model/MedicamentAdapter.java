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

import fr.bam.projetfinal.R;

public class MedicamentAdapter extends ArrayAdapter<Medication> {


    public MedicamentAdapter(Context context, ArrayList<Medication> medications) {
        super(context, 0, medications);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Medication m = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.medicament_cell, parent, false);
        }

        TextView mFirstName = convertView.findViewById(R.id.medicament_list_cell_name_textView);


        mFirstName.setText(m.getName());


        return convertView;
    }

}
