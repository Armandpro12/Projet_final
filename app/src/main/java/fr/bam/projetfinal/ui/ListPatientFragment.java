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
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.bam.projetfinal.CreatePatientActivity;
import fr.bam.projetfinal.DoctorDB;
import fr.bam.projetfinal.MainActivity;
import fr.bam.projetfinal.R;
import fr.bam.projetfinal.model.Patient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPatientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FloatingActionButton mAddPatientButton;

    private ConstraintLayout constraintLayout;

    public ListPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPatientFragment newInstance(String param1, String param2) {
        ListPatientFragment fragment = new ListPatientFragment();
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
        // You can call the findViewById() method here


        mAddPatientButton = view.findViewById(R.id.fragment_list_patient_addPatient_button);
        constraintLayout = view.findViewById(R.id.fragment_list_patient_constraintLayout);
        displayPatient(constraintLayout);
        mAddPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreatePatientActivity.class);
                startActivity(intent);


            }
        });

    }

    public void displayPatient(ConstraintLayout cl) {
        DoctorDB db = new DoctorDB(ListPatientFragment.this.getContext());
        SharedPreferences stored_data = this.getActivity().getSharedPreferences(MainActivity.STORED_DATA, Context.MODE_PRIVATE);
        int doctorId = stored_data.getInt(MainActivity.STORED_DATA, -1);
        ArrayList<Patient> patientsList = db.getAlldoctorsPatients(doctorId);

        for (int i = 0; i < patientsList.size(); i++) {

            TextView textView = new TextView(this.getContext());
            textView.setText(patientsList.get(i).getFirstName() + " " + patientsList.get(i).getLastName());
            textView.setId(patientsList.get(i).getId());
            textView.setPadding(10 , 60*i , 0 , 0 );
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_patient, container, false);
    }
}