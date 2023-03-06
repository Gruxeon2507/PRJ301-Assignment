import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WholeWeekFromDate {
    public static List<java.sql.Date> getWholeWeekFromDate(java.sql.Date date) {
        // Find the Monday of the week
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        if (monday.after(date)) {
            monday.add(Calendar.WEEK_OF_YEAR, -1);
        }

        // Find the dates for the rest of the week
        List<java.sql.Date> weekDates = new ArrayList<>();
        Calendar day = (Calendar) monday.clone();
        for (int i = 0; i < 7; i++) {
            weekDates.add(new java.sql.Date(day.getTimeInMillis()));
            day.add(Calendar.DAY_OF_WEEK, 1);
        }
        return weekDates;
    }
    
}