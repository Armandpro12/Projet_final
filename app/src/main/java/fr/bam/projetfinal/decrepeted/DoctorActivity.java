package fr.bam.projetfinal.decrepeted;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.bam.projetfinal.MainActivity;
import fr.bam.projetfinal.R;

public class DoctorActivity extends AppCompatActivity {

    public Button mdisconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_old);
        mdisconnect = findViewById(R.id.Disconnect_button);

        mdisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            disconnect();

            }
        });





    }


    public  void disconnect(){
        SharedPreferences stored_data = getSharedPreferences(MainActivity.STORED_DATA, MODE_PRIVATE);
        stored_data.edit().putInt(MainActivity.IS_LOGIN , -1).apply();
        stored_data.edit().putInt(MainActivity.IS_DOCTOR , -1).apply();

        finish();


    }
}