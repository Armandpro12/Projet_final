package fr.bam.projetfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import fr.bam.projetfinal.databinding.ActivityDoctorViewBinding;
import fr.bam.projetfinal.ui.CalendrierFragment;
import fr.bam.projetfinal.ui.MedicamentPatient;
import fr.bam.projetfinal.ui.ParametrePatient;

public class patient_view extends AppCompatActivity {

    private ActivityDoctorViewBinding binding;
    /*
    * docteur : D; a
    * Patient : P; b
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new CalendrierFragment());


        // create a switch case to navigate between the different fragments using setOnItemSelectedListener()

        binding.navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.navigation_patient:
                    replaceFragment(new CalendrierFragment());
                    break;
                case R.id.navigation_Medicament:
                    replaceFragment(new MedicamentPatient());
                    break;
                case R.id.navigation_parameter:
                    replaceFragment(new ParametrePatient());
                    break;
            }
            return true;
        });




    }

    private void replaceFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}