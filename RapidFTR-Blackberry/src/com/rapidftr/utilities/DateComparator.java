package com.rapidftr.utilities;

import com.rapidftr.utilities.DateParser;
import java.util.Calendar;

public class DateComparator {
   private static final DateParser DATE_PARSER = new DateParser();
   
   // negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   public int compare(String date1, String date2) {
       Calendar calendar1 = DATE_PARSER.parse(date1);
       Calendar calendar2 = DATE_PARSER.parse(date2);
       if (calendar1 == null && calendar2 == null) {
           return 0;
       }
       if (calendar1 == null) {
           return -1;
       }
       if (calendar2 == null) {
           return 1;
       }
       return calendar1.after(calendar2) ? 1 : (calendar1.before(calendar2) ? -1 : 0);
   }
}
