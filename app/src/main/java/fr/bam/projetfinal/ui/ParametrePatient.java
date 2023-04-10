package fr.bam.projetfinal.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.bam.projetfinal.MainActivity;
import fr.bam.projetfinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParametrePatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParametrePatient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Button mdisconnect;

    public ParametrePatient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParametrePatient.
     */
    // TODO: Rename and change types and number of parameters
    public static ParametrePatient newInstance(String param1, String param2) {
        ParametrePatient fragment = new ParametrePatient();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mdisconnect = view.findViewById(R.id.fragment_parametre_deconexion_button);
        mdisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnect();

            }
        });
    }
    public  void disconnect(){
        SharedPreferences stored_data = this.getActivity().getSharedPreferences(MainActivity.STORED_DATA, Context.MODE_PRIVATE);

        stored_data.edit().putInt(MainActivity.IS_LOGIN , -1).apply();
        stored_data.edit().putInt(MainActivity.IS_DOCTOR , -1).apply();

        getActivity().finish();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parametre_patient, container, false);
    }
}