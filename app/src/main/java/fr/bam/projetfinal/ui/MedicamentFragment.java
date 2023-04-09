package fr.bam.projetfinal.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.bam.projetfinal.CreateMedicationActivity;
import fr.bam.projetfinal.CreatePatientActivity;
import fr.bam.projetfinal.DoctorDB;
import fr.bam.projetfinal.MainActivity;
import fr.bam.projetfinal.R;
import fr.bam.projetfinal.model.Medication;
import fr.bam.projetfinal.model.Patient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicamentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicamentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FloatingActionButton createMedicamentButton;

    public MedicamentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicamentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicamentFragment newInstance(String param1, String param2) {
        MedicamentFragment fragment = new MedicamentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        createMedicamentButton = view.findViewById(R.id.fragment_list_Medication_addPatient_button);
        ConstraintLayout cl = view.findViewById(R.id.fragment_list_Medication_constraintLayout);
        displayPatient(cl);

        createMedicamentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateMedicationActivity.class);
                startActivity(intent);


            }
        });

    }

    public void displayPatient(ConstraintLayout cl) {
        DoctorDB db = new DoctorDB(MedicamentFragment.this.getContext());

        ArrayList<Medication> MedicationsList = db.getAllMedications();

        for (int i = 0; i < MedicationsList.size(); i++) {

            TextView textView = new TextView(this.getContext());
            textView.setText(MedicationsList.get(i).getName());
            textView.setId(MedicationsList.get(i).getId());
            textView.setPadding(10, 60 * i, 0, 0);
            textView.setTextSize(20);
            cl.addView(textView);


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                    Intent myI =   new Intent(DisplayProfile.this , ProfilActivity.class);
                    myI.putExtra("id",textView.getId());
                    startActivity(myI);
                    */

                }
            });
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicament, container, false);
    }



}