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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.bam.projetfinal.CreatePatientActivity;
import fr.bam.projetfinal.DoctorDB;
import fr.bam.projetfinal.MainActivity;
import fr.bam.projetfinal.PatientInfoActivity;
import fr.bam.projetfinal.R;
import fr.bam.projetfinal.model.Patient;
import fr.bam.projetfinal.model.PatientAdapter;

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

    private ListView mListView;

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
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddPatientButton = view.findViewById(R.id.fragment_list_patient_addPatient_button);
        constraintLayout = view.findViewById(R.id.fragment_list_patient_constraintLayout);
        mListView = view.findViewById(R.id.fragment_list_patient_listView);
        displayPatient();
        mAddPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreatePatientActivity.class);
                startActivity(intent);


            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        displayPatient();
    }

    public void displayPatient() {
        DoctorDB db = new DoctorDB(ListPatientFragment.this.getContext());
        SharedPreferences stored_data = this.getActivity().getSharedPreferences(MainActivity.STORED_DATA, Context.MODE_PRIVATE);
        int doctorId = stored_data.getInt(MainActivity.IS_LOGIN, -1);
        ArrayList<Patient> patientsList = db.getAllPatientsOfDoctor(doctorId);

        System.out.printf("\n\n %s \n\n", patientsList.toString());

        PatientAdapter adapter = new PatientAdapter(ListPatientFragment.this.getContext(), patientsList);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), PatientInfoActivity.class);
            intent.putExtra("patientId", patientsList.get(position).getId());
            System.out.printf("\n\n %s %s \n\n", patientsList.get(position).getId() , patientsList.get(position).getFirstName());
            startActivity(intent);
        });





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_patient, container, false);
    }
}