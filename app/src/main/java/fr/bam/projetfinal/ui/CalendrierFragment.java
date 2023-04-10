package fr.bam.projetfinal.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

import fr.bam.projetfinal.DoctorDB;
import fr.bam.projetfinal.MainActivity;
import fr.bam.projetfinal.R;
import fr.bam.projetfinal.model.Date;
import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Medication;
import fr.bam.projetfinal.model.Ordonnance;
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

    private ArrayList<Date> localDates;
    private LinearLayout linearLayout;
    MaterialCalendarView materialCalendarView;

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

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DoctorDB doctorDB = new DoctorDB(CalendrierFragment.this.getContext());
        materialCalendarView = view.findViewById(R.id.calendarView);
        linearLayout = view.findViewById(R.id.SVlinearlayout);
        localDates = new ArrayList<>();
        SharedPreferences stored_data = this.getActivity().getSharedPreferences(MainActivity.STORED_DATA, Context.MODE_PRIVATE);
        int id = stored_data.getInt(MainActivity.IS_LOGIN, -1);
        System.out.println("__________id_________ :" + id);
        getPatientDates(id);
        afficherOrdonnance(localDates);

        //calendar click listenenr
        materialCalendarView.setOnDateChangedListener((widget, date, selected) -> {
            System.out.println("__________date_________ :" + date);
            System.out.println("__________selected_________ :" + selected);
            //date format : YYYY-MM-DD
            String year = String.valueOf(date.getYear());
            String month = String.valueOf(date.getMonth());
            String day = String.valueOf(date.getDay());
            if (date.getMonth()<10){
                month = "0"+month;
            }
            if (date.getDay()<10){
                day = "0"+day;
            }
            //parse to sql date
            String dateStr = year + "-" + month + "-" + day;
            System.out.println("__________dateStr_________ :" + dateStr);
            if (selected) {
                ArrayList<Date> dates = doctorDB.getAllDateDates(dateStr);
                //sout list size
                System.out.println("__________dates_________ :" + dates.size());
                for (Date date1 : dates) {
                    Ordonnance ordonnance = doctorDB.getOrdonnance(date1.getOrdonnanceID());
                    affichageOrdonnance(date1, ordonnance);
                }
               // ArrayList<Date> = doctorDB.getAllDateDates(date)
                //TODO : afficher les ordonnances de la date
            }
        });
        doctorDB.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendrier, container, false);
    }

    public void getPatientDates(int patientID) {
        DoctorDB db = new DoctorDB(this.getContext());
        ArrayList<Ordonnance> ordonnances = db.getAllPatientOrdonnances(patientID);
        for (Ordonnance ordonnance : ordonnances) {
            ArrayList<Date> dates = db.getAllOrdonnanceDates(ordonnance.getId());
            localDates.addAll(dates);

        }

    }

    public void afficherOrdonnance(ArrayList<Date> dates) {
        //date format : YYYY-MM-DD HH:MM:SS
        CalendarDay calendarDay = null;
        for (Date date : dates) {
            //catch interger.parseint
            try {
                calendarDay = CalendarDay.from(date.getYear(), date.getMonth(), date.getDay());
            } catch (NumberFormatException e) {
                System.out.println("error parsing date");
            }
            //

        }

        materialCalendarView.addDecorator(new EventDecorator(calendarDay, Color.RED));
    }

    private void affichageOrdonnance(Date date, Ordonnance ordonnance) {
        DoctorDB doctorDB = new DoctorDB(CalendrierFragment.this.getContext());
        Medication medication = doctorDB.getMedication(ordonnance.getMedicationID());
        // add information LinearLayout to existing ScrollView
        LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        // add color indicator to left of layout
        View colorIndicator = new View(this.getContext());
        colorIndicator.setLayoutParams(new LinearLayout.LayoutParams(20, LinearLayout.LayoutParams.MATCH_PARENT));
        if (date.isTaken()) {
            colorIndicator.setBackgroundColor(Color.GREEN);
        } else {
            colorIndicator.setBackgroundColor(Color.RED);
        }
        linearLayout.addView(colorIndicator);

        // add image to right of color indicator
        ImageView imageView = new ImageView(this.getContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        //default image [0]
        byte[] image = new byte[1];
        //byte[] image = medication.getPhoto();
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        linearLayout.addView(imageView);

        // add title, date, and description to layout
        LinearLayout textLayout = new LinearLayout(this.getContext());
        textLayout.setOrientation(LinearLayout.VERTICAL);
        textLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView titleView = new TextView(this.getContext());
        titleView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        titleView.setText(medication.getName());
        textLayout.addView(titleView);

        TextView dateView = new TextView(this.getContext());
        dateView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        dateView.setText(date.getDate());
        textLayout.addView(dateView);

        TextView descriptionView = new TextView(this.getContext());
        descriptionView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        descriptionView.setText(ordonnance.getDescription());
        textLayout.addView(descriptionView);

        linearLayout.addView(textLayout);

        // add layout to existing ScrollView
        linearLayout.addView(linearLayout);

    }

}