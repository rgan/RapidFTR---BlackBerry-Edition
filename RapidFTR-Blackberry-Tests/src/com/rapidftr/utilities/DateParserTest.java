package com.rapidftr.utilities;

import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class DateParserTest {

    @Test
    public void shouldParseDateInEitherFormat() {
       assertNotNull(new DateParser().parse("01/02/2011 21:58"));
       assertNotNull(new DateParser().parse("Wed Feb 01 21:38:57 GMT 2011"));
    }

    @Test
    public void shouldParseDateInRailsAppFormat() {
        Calendar date = new DateParser().parseFirstFormat("01/02/2011 21:58");
        assertEquals(1, date.get(Calendar.DATE));
        assertEquals(Calendar.FEBRUARY, date.get(Calendar.MONTH));
        assertEquals(2011, date.get(Calendar.YEAR));
        assertEquals(21, date.get(Calendar.HOUR_OF_DAY));
        assertEquals(58, date.get(Calendar.MINUTE));
    }

    @Test
    public void shouldReturnNullIfInvalidFormat() {
        assertNull(new DateParser().parse(""));
        assertNull(new DateParser().parse("abcdef"));
        // in early versions of the SDK Calendar does not throw
        // exceptions when invalid values are set for fields.
        //assertNull(new DateParser().parse("01/99/2011 21:58"));
    }

    @Test
    public void shouldParseDateInBBFormat() {
        Calendar date = new DateParser().parseSecondFormat("Wed Feb 01 21:38:57 GMT 2011");
        assertEquals(1, date.get(Calendar.DATE));
        assertEquals(Calendar.FEBRUARY, date.get(Calendar.MONTH));
        assertEquals(2011, date.get(Calendar.YEAR));
        assertEquals(21, date.get(Calendar.HOUR_OF_DAY));
        assertEquals(38, date.get(Calendar.MINUTE));
    }
}
