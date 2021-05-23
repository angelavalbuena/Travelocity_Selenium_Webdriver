package com.travelocity.bootcamp.pages;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class Utils {

    /***
     * Methods to convert flight type time to 24 hours format
     * @param hour
     * @return
     */
    public String convertTypeTime(String hour){
        String newTime = "";
        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mma");
            Date date = parseFormat.parse(hour);
            newTime = displayFormat.format((date));
        }catch (Exception Ignored){
        }
        return newTime;
    }

    /***
     * Method to get total of minutes from hh:mm time format
     * @param time
     * @return
     */
    public int getMinutes(String time){
        LocalTime localTimeA = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        int hour = localTimeA.getHour();
        int minute = localTimeA.getMinute();
        return (hour*60)+minute;
    }

    /***
     * Method to extract flight hours from flight options.
     * @param time
     * @return
     */
    public String[]  extractHours(String time){
        return Arrays.stream(time.split("-")).map(String::trim).toArray(String[]::new);
    }
}
