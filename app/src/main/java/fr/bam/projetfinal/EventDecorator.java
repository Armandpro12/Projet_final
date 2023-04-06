package fr.bam.projetfinal;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
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
        this.textColor = Color.WHITE; // Couleur de texte par défaut
        this.backgroundColor = backgroundColor;
    }

    public EventDecorator(CalendarDay date, int textColor, int backgroundColor) {
        this.date = date;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        // Renvoie vrai si la date fournie correspond à la date de ce décorateur
        return date.equals(calendarDay);
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        // Applique les styles personnalisés à la vue du jour fournie par le MaterialCalendarView
        GradientDrawable circleDrawable = new GradientDrawable();
        circleDrawable.setShape(GradientDrawable.OVAL);
        circleDrawable.setColor(Color.BLUE);
        dayViewFacade.setSelectionDrawable(circleDrawable);

        dayViewFacade.addSpan(new ForegroundColorSpan(textColor));
    }
}
