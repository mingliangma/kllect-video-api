package com.kllect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by ejz492 on 10/7/16.
 */
public class DateUtil {
    private static final String[] formats = {
            "yyyy-MM-dd'T'HH:mm:ss'Z'",   "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss",      "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss",
            "MM/dd/yyyy HH:mm:ss",        "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'",
            "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
            "MM/dd/yyyy'T'HH:mm:ssZ",     "MM/dd/yyyy'T'HH:mm:ss",
            "yyyy:MM:dd HH:mm:ss",        "yyyyMMdd",
            "EEE MMM dd HH:mm:ss z yyyy", "yyyy-MM-dd"};

    //Fri Oct 07 12:47:42 EDT 2016

    public static void main(String[] args){
        System.out.println(parseStringToDate("2016-09-26T12:07:06.000Z"));
        LocalDateTime past = LocalDateTime.now().minusDays(60);
        Date out = convertLocalDateTimeToDate(past);
        System.out.println(out);
    }

    public static Date getPastDateMinusDays(int days){
        return convertLocalDateTimeToDate(LocalDateTime.now().minusDays(days));
    }

    public static Date parseStringToDate(String dateStr) {

        for(String format: formats) {

            SimpleDateFormat formatter = new SimpleDateFormat(format);
            try
            {
                // (2) give the formatter a String that matches the SimpleDateFormat pattern
                Date date = formatter.parse(dateStr);

                // (3) prints out "Tue Sep 22 00:00:00 EDT 2009"
//                System.out.println(date);
                return date;
            }
            catch (ParseException e)
            {
                // execution will come here if the String that is given
                // does not match the expected format.
//                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime ldt){
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
