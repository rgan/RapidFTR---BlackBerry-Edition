package com.rapidftr.utilities;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DateComparatorTest {

    @Test
    public void shouldReturnOneWhenFirstDateIsGreaterThanSecond() {
        assertEquals(1, new DateComparator().compare("01/02/2011 21:58", "01/01/2011 21:58"));
    }

    @Test
    public void shouldReturnNegativeOneWhenFirstDateIsLessThanSecond() {
        assertEquals(-1, new DateComparator().compare("01/01/2011 21:58", "01/04/2011 21:58"));
    }

    @Test
    public void shouldReturnZeroOneWhenTheyAreEqual() {
        assertEquals(0, new DateComparator().compare("01/01/2011 21:58", "01/01/2011 21:58"));
    }

    @Test
    public void shouldReturnNegativeOneWhenFirstDateIsInvalid() {
        assertEquals(-1, new DateComparator().compare("afadfadf", "01/01/2011 21:58"));
    }

    @Test
    public void shouldReturnOneWhenSecondDateIsInvalid() {
        assertEquals(1, new DateComparator().compare("01/01/2011 21:58", "afadfadf"));
    }

    @Test
    public void shouldReturnZeroWhenBothAreInvalid() {
        assertEquals(0, new DateComparator().compare("afdas", "afadfadf"));
    }
}
