package fr.bam.projetfinal.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.bam.projetfinal.DoctorDB;
import fr.bam.projetfinal.MainActivity;
import fr.bam.projetfinal.R;
import fr.bam.projetfinal.model.Date;
import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Patient;
import fr.bam.projetfinal.patient_view;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 * Use the {@link CalendrierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendrierFragment extends androidx.fragment.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendrierFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendrier.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendrierFragment newInstance(String param1, String param2) {

        CalendrierFragment calendrierFragment = new CalendrierFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        calendrierFragment.setArguments(args);
        return calendrierFragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DoctorDB doctorDB = new DoctorDB(CalendrierFragment.this.getContext());
        SharedPreferences stored_data = this.getActivity().getSharedPreferences(MainActivity.STORED_DATA, Context.MODE_PRIVATE);
        int id = stored_data.getInt(MainActivity.IS_LOGIN, -1);
        //afficherOrdonnance(doctorDB.getAllPatientDates(id));

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendrier, container, false);
    }

    public void afficherOrdonnance(ArrayList<Date> dates) {
        for (Date date : dates) {
            System.out.println("__________Date_________ :" + date.getDate());
        }
    }
}