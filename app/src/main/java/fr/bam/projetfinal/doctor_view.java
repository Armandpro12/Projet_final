package fr.bam.projetfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import fr.bam.projetfinal.databinding.ActivityDoctorViewBinding;
import fr.bam.projetfinal.ui.ListPatientFragment;
import fr.bam.projetfinal.ui.MedicamentFragment;
import fr.bam.projetfinal.ui.ParametreFragment;

public class doctor_view extends AppCompatActivity {

    private ActivityDoctorViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ListPatientFragment());


        // create a switch case to navigate between the different fragments using setOnItemSelectedListener()

        binding.navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.navigation_patient:
                    replaceFragment(new ListPatientFragment());
                    break;
                case R.id.navigation_Medicament:
                    replaceFragment(new MedicamentFragment());
                    break;
                case R.id.navigation_parameter:
                    replaceFragment(new ParametreFragment());
                    break;
            }
            return true;
        });




    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}