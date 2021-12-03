package utils;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateIn
{
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy/MM/dd|HH:mm:ss").format(new Date());
    }
}
