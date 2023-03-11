package util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Day;

public class MondayAndSundayOfWeek {
    public static java.sql.Date getMonday(String date) {
        // Parse the input date into a Calendar object
        Date inputDate = Date.valueOf(date); // Replace with your desired date
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);
        // Find the Monday of the week
        Calendar monday = (Calendar) cal.clone();
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        if (monday.after(cal)) {
            monday.add(Calendar.WEEK_OF_YEAR, -1);
        }
        monday.set(Calendar.HOUR_OF_DAY, 0);
        monday.set(Calendar.MINUTE, 0);
        monday.set(Calendar.SECOND, 0);
        monday.set(Calendar.MILLISECOND, 0);
        return new Date(monday.getTimeInMillis());
        // Find the Sunday of the week
    }
    public static java.sql.Date getSunday(String date) {       
        Date inputDate = Date.valueOf(date); // Replace with your desired date
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);
        Calendar sunday = (Calendar) cal.clone();
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        if (sunday.before(cal)) {
            sunday.add(Calendar.WEEK_OF_YEAR, 1);
        }
        sunday.set(Calendar.HOUR_OF_DAY, 0);
        sunday.set(Calendar.MINUTE, 0);
        sunday.set(Calendar.SECOND, 0);
        sunday.set(Calendar.MILLISECOND, 0);

        // Print the results
       return new Date(sunday.getTimeInMillis());
    }
    public static ArrayList<Day> getWholeWeekFromDate(java.sql.Date date) {
        // Find the Monday of the week
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        if (monday.after(date)) {
            monday.add(Calendar.WEEK_OF_YEAR, -1);
        }

        // Find the dates for the rest of the week
        ArrayList<Day> weekDates = new ArrayList<>();
        Calendar day = (Calendar) monday.clone();
        for (int i = 0; i < 7; i++) {
            Day d = new Day();
            d.setDate(new java.sql.Date(day.getTimeInMillis()));
            d.setDateCount(i);
            day.add(Calendar.DAY_OF_WEEK, 1);
            weekDates.add(d);    
        }
        return weekDates;
    }
}
