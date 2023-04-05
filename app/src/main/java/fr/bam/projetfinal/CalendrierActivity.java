package fr.bam.projetfinal;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendrierActivity extends AppCompatActivity {
    private CalendarView calendarView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        calendarView = findViewById(R.id.calendarView);

        Calendar calendarT = Calendar.getInstance();
        calendarT.set(2023, 3, 4); // mois de 0 à 11, donc avril est 3

        EventDay eventDay1 = new EventDay(calendarT, R.drawable.green_circle);
        EventDay eventDay2 = new EventDay(calendarT, R.drawable.blue_circle);

        List<EventDay> events = new ArrayList<>();
        events.add(eventDay1);
        events.add(eventDay2);
        calendarView.setEvents(events);
        calendarView.setEvents(events);

    }
}
