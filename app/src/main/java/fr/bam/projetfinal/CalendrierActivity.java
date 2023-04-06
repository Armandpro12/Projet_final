package fr.bam.projetfinal;


import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.DayOfWeek;

import java.util.List;

import fr.bam.projetfinal.model.Date;
import fr.bam.projetfinal.model.Dosage;

public class CalendrierActivity extends AppCompatActivity {
    DoctorDB doctorDB;
    private MaterialCalendarView materialCalendarView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        //calendarView = findViewById(R.id.calendarView);

        materialCalendarView = findViewById(R.id.calendarView);
        Bundle extras = getIntent().getExtras();
        doctorDB = new DoctorDB(CalendrierActivity.this);
//        int id = extras.getInt("Idpatient");
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;

        materialCalendarView.state().edit().setFirstDayOfWeek(firstDayOfWeek).commit();
        materialCalendarView.state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit();
        materialCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        materialCalendarView.setTopbarVisible(false);


        //loadPatientMedicationDates(id);
        CalendarDay calendarDay = CalendarDay.from(2023, 4, 14);
       // CalendarDay calendarDay1 = CalendarDay.from(2017, 11, 16);

        //materialCalendarView.setSelectedDate(calendarDay1);

        materialCalendarView.addDecorator(new EventDecorator(calendarDay, Color.RED));


    }

    void loadPatientMedicationDates(int idPatient) {
        System.out.println("_________PATIENT__________\n" + idPatient);
        List<Dosage> dosages = doctorDB.getAllDosages(idPatient);
        System.out.println("_________DOSAGES__________\n" + dosages.size());
        List<Date> dates ;
        for (Dosage dosage : dosages) {
            System.out.println("_________DOSAGE__________\n" + dosage.getMedication().getName());
            dates = doctorDB.getAllDates(dosage.getId());
            for (Date date : dates) {
                System.out.println("_________DATE__________\n" + date.getDate());
                CalendarDay calendarDay = CalendarDay.from(Integer.parseInt(date.getDate().substring(0, 4)), Integer.parseInt(date.getDate().substring(5, 7)), Integer.parseInt(date.getDate().substring(8, 10)));
                materialCalendarView.addDecorator(new EventDecorator(calendarDay, Color.RED));
            }
        }
    }
}
