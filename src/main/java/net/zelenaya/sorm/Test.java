package net.zelenaya.sorm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        Date date = new Date(1429709370000L);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(sdf.format(date));
        System.out.println(date);

        Calendar calendar = new GregorianCalendar();
        calendar.set(2049, 11, 31, 23, 59, 59);
        Date date1 = new Date(calendar.getTimeInMillis());
        System.out.println(date1.getTime());
    }
}
