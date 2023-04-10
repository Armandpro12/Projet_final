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

public class PatientAdapter extends ArrayAdapter<Patient> {

    public PatientAdapter(Context context, ArrayList<Patient> patients) {
        super(context, 0, patients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Patient patient = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.patientlistcell, parent, false);
        }

        TextView mFirstName = convertView.findViewById(R.id.patient_list_cell_FirstName_textView);
        TextView mLastName = convertView.findViewById(R.id.patient_list_cell_name_textView);

        mFirstName.setText(patient.getFirstName());
        mLastName.setText(patient.getLastName());

        return convertView;
    }
}
