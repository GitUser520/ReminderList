package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    private Date date;

    @BeforeEach
    public void setUp() {
        date = new Date(0,0,0);
    }

    @Test
    public void testSetYear() {
        date.setYear(2021);
        assertEquals(2021, date.getYear());
    }

    @Test
    public void testSetMonth() {
        date.setMonth(10);
        assertEquals(10, date.getMonth());
    }

    @Test
    public void testSetDay() {
        date.setDay(26);
        assertEquals(26, date.getDay());
    }

    @Test
    public void testSetDate() {
        date.setDate(2008, 3, 12);
        assertEquals(2008, date.getYear());
        assertEquals(3, date.getMonth());
        assertEquals(12, date.getDay());
    }

    @Test
    public void testEquals() {
        Date expectedDate = new Date(2020, 12, 31);

        assertFalse(date.equals(expectedDate));
        assertFalse(expectedDate.equals(date));

        date.setYear(2020);

        assertFalse(date.equals(expectedDate));
        assertFalse(expectedDate.equals(date));

        date.setMonth(12);

        assertFalse(date.equals(expectedDate));
        assertFalse(expectedDate.equals(date));

        date.setDay(31);

        assertTrue(date.equals(expectedDate));
        assertTrue(expectedDate.equals(date));
    }

    @Test
    public void testToStringFourDigitYear() {
        date.setDate(1999, 4, 12);
        assertEquals("1999/4/12", date.toString());
    }

    @Test
    public void testToStringNonFourDigitYear() {
        date.setDate(20, 11, 4);
        assertEquals("20/11/4", date.toString());
    }
}
