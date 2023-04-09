package fr.bam.projetfinal;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

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
        hideSystemUI();
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

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }

}