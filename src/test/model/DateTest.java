package model;

import exception.InvalidDayException;
import exception.InvalidMonthException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.INVALID_ACTIVITY;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    private Date date;

    @BeforeEach
    public void setUp() {
        try {
            date = new Date(0, 1, 1);
        } catch (Exception e) {
            fail("This should not occur.");
        }
    }

    @Test
    public void testConstructorNoMonth() {
        try {
            Date date = new Date(2020, 13, 1);
            fail();
        } catch (InvalidMonthException e) {

        } catch (InvalidDayException e) {
            fail();
        }
    }

    @Test
    public void testConstructorNoDay() {
        try {
            Date date = new Date(2020, 2, 43);
            fail();
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {

        }
    }

    @Test
    public void testSetYear() {
        date.setYear(2021);
        assertEquals(2021, date.getYear());
    }

    @Test
    public void testSetValidMonth() {
        try {
            date.setMonth(10);
        } catch (InvalidMonthException e) {
            fail();
        }
        assertEquals(10, date.getMonth());
    }

    @Test
    public void testSetMonthTooLarge() {
        try {
            date.setMonth(13);
            fail();
        } catch (InvalidMonthException e) {

        }
        assertEquals(1, date.getMonth());
    }

    @Test
    public void testSetMonthTooSmall() {
        try {
           date.setMonth(0);
           fail();
        } catch (InvalidMonthException e) {

        }
        assertEquals(1, date.getMonth());
    }

    @Test
    public void testSetValidDay() {
        try {
            date.setDay(26);
        } catch (InvalidDayException e) {
            fail();
        }
        assertEquals(26, date.getDay());
    }

    @Test
    public void testSetDayTooLarge() {
        try {
            date.setDay(40);
            fail();
        } catch (InvalidDayException e) {

        }
        assertEquals(1, date.getDay());
    }

    @Test
    public void testSetDayTooSmall() {
        try {
            date.setDay(0);
            fail();
        } catch (InvalidDayException e) {

        }
        assertEquals(1, date.getDay());
    }

    @Test
    public void testSetValidDate() {
        try {
            date.setDate(2008, 3, 12);
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {
            fail();
        }
        assertEquals(2008, date.getYear());
        assertEquals(3, date.getMonth());
        assertEquals(12, date.getDay());
    }

    @Test
    public void testSetValidDate28() {
        try {
            date.setDate(2007, 2, 28);
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {
            fail();
        }
        assertEquals(2007, date.getYear());
        assertEquals(2, date.getMonth());
        assertEquals(28, date.getDay());
    }

    @Test
    public void testSetValidDate29() {
        try {
            date.setDate(2008, 2, 29);
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {
            fail();
        }
        assertEquals(2008, date.getYear());
        assertEquals(2, date.getMonth());
        assertEquals(29, date.getDay());
    }

    @Test
    public void testSetValidDate30() {
        try {
            date.setDate(2008, 11, 30);
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {
            fail();
        }
        assertEquals(2008, date.getYear());
        assertEquals(11, date.getMonth());
        assertEquals(30, date.getDay());
    }

    @Test
    public void testSetValidDate31() {
        try {
            date.setDate(2008, 10, 31);
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {
            fail();
        }
        assertEquals(2008, date.getYear());
        assertEquals(10, date.getMonth());
        assertEquals(31, date.getDay());
    }

    @Test
    public void testSetInvalidDateByMonth() {
        try {
            date.setDate(2008, 13, 12);
            fail();
        } catch (InvalidMonthException e) {

        } catch (InvalidDayException e) {
            fail();
        }
        assertEquals(0, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }

    @Test
    public void testSetInvalidDateByDay() {
        try {
            date.setDate(2008, 4, 0);
            fail();
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {

        }
        assertEquals(0, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }

    @Test
    public void testSetInvalidDateByDay29() {
        try {
            date.setDate(2007, 2, 29);
            fail();
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {

        }
        assertEquals(0, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }

    @Test
    public void testSetInvalidDateByDay30() {
        try {
            date.setDate(2008, 2, 30);
            fail();
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {

        }
        assertEquals(0, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }

    @Test
    public void testSetInvalidDateByDay31() {
        try {
            date.setDate(2008, 9, 31);
            fail();
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {

        }
        assertEquals(0, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }

    @Test
    public void testSetInvalidDateByDay32() {
        try {
            date.setDate(2008, 8, 32);
            fail();
        } catch (InvalidMonthException e) {
            fail();
        } catch (InvalidDayException e) {

        }
        assertEquals(0, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }

    @Test
    public void testEquals() {
        Date expectedDate = null;
        try {
            expectedDate = new Date(2020, 12, 31);
        } catch (Exception e) {
            fail();
        }

        assertFalse(date.equals(expectedDate));
        assertFalse(expectedDate.equals(date));

        date.setYear(2020);

        assertFalse(date.equals(expectedDate));
        assertFalse(expectedDate.equals(date));

        try {
            date.setMonth(12);
        } catch (Exception e) {
            fail();
        }

        assertFalse(date.equals(expectedDate));
        assertFalse(expectedDate.equals(date));

        try {
            date.setDay(31);
        } catch (Exception e) {
            fail();
        }

        assertTrue(date.equals(expectedDate));
        assertTrue(expectedDate.equals(date));
    }

    @Test
    public void testToStringFourDigitYear() {
        try {
            date.setDate(1999, 5, 12);
        } catch (Exception e) {
            fail();
        }
        assertEquals("1999/5/12", date.toString());
    }

    @Test
    public void testToStringNonFourDigitYear() {
        try {
            date.setDate(20, 6, 4);
        } catch (Exception e) {
            fail();
        }
        assertEquals("20/6/4", date.toString());
    }

    @Test
    public void testToJson() {
        try {
            date.setDate(2111, 7, 16);
        } catch (Exception e) {
            fail();
        }
        JSONObject jsonDate = date.toJson();
        assertEquals(3, jsonDate.length());
        assertEquals(2111, jsonDate.get("year"));
        assertEquals(7, jsonDate.get("month"));
        assertEquals(16, jsonDate.get("day"));
    }
}
