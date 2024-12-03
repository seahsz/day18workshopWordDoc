package vttp.ssf.day18workshopWordDoc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperFunction {

    public static Date strToDate(String dateString) {

        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MM/dd/yyyy");

        try {
            Date date = formatter.parse(dateString);            
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public static long dateTOEpochMilliseconds(Date date) {

        return date.getTime();
    }

    public static Date epochMillisecondsToDate(long epoch) {

        return new Date(epoch);
    }

    public static String dateToString(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MM/dd/yyyy");
        return formatter.format(date);
    }
    
}
