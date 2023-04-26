package Bam_Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String getDateStr(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return formatter.format(now);
    }
}
