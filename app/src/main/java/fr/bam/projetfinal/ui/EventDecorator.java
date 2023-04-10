package fr.bam.projetfinal.ui;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class EventDecorator implements DayViewDecorator {

    private final CalendarDay date;
    private final int textColor;
    private final int backgroundColor;

    public EventDecorator(CalendarDay date, int backgroundColor) {
        this.date = date;
        this.textColor = Color.WHITE;
        this.backgroundColor = backgroundColor;
    }

    public EventDecorator(CalendarDay date, int textColor, int backgroundColor) {
        this.date = date;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return date.equals(calendarDay);
    }

    @Override
    public void decorate(DayViewFacade view) {
        GradientDrawable circleDrawable = new GradientDrawable();
        circleDrawable.setShape(GradientDrawable.OVAL);
        circleDrawable.setColor(backgroundColor);
        view.setSelectionDrawable(circleDrawable);

        view.addSpan(new ForegroundColorSpan(textColor));
    }
}
