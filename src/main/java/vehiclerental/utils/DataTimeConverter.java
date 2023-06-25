package vehiclerental.utils;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@UtilityClass
public class DataTimeConverter {
    public long getMillisFromDateTime(String datetimeString) {
        try {//w  w w  .j  a va 2 s  .  c  o  m
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(datetimeString));
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
