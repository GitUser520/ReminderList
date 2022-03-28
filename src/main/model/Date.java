package model;

import exception.InvalidDayException;
import exception.InvalidMonthException;
import exception.InvalidValueException;
import org.json.JSONObject;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

// Represents a date with its year, month, and day
public class Date {

    private int year;
    private int month;
    private int day;

    // EFFECTS: constructs a date with given year, month and day.
    //          throws InvalidMonthException if month is invalid
    //          throws InvalidDayException
    public Date(int year, int month, int day)
            throws InvalidMonthException, InvalidDayException {
        if (!hasValidMonth(month)) {
            throw new InvalidMonthException();
        }
        if (!hasValidDay(year, month, day)) {
            throw new InvalidDayException();
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // EFFECTS: returns the year of this date
    public int getYear() {
        return year;
    }

    // EFFECTS: returns the month of this date
    public int getMonth() {
        return month;
    }

    // EFFECTS: returns the day of this date
    public int getDay() {
        return day;
    }

    // MODIFIES: this
    // EFFECTS: sets year as given year
    public void setYear(int year) {
        this.year = year;
    }

    // MODIFIES: this
    // EFFECTS: sets month as given month, or
    //          throws InvalidMonthException if month is invalid
    public void setMonth(int month) throws InvalidMonthException {
        if (!hasValidMonth(month)) {
            throw new InvalidMonthException();
        }
        this.month = month;
    }

    // EFFECTS: returns true if the month is valid, false otherwise
    private boolean hasValidMonth(int month) {
        return (1 <= month) && (month <= 12);
    }

    // MODIFIES: this
    // EFFECTS: sets day as given day
    public void setDay(int day) throws InvalidDayException {
        if (!hasValidDay(this.year, this.month, day)) {
            throw new InvalidDayException();
        }
        this.day = day;
    }

    // EFFECTS: returns true if the day is valid, false otherwise
    private boolean hasValidDay(int year, int month, int day) {
        int maxDayInMonth = maxDayInMonth(year, month);
        return (0 < day) && (day <= maxDayInMonth);
    }

    // MODIFIES: this
    // EFFECTS: sets year, month, day as given year, month, day
    public void setDate(int year, int month, int day)
            throws InvalidDayException, InvalidMonthException {
        if (!hasValidMonth(month)) {
            throw new InvalidMonthException();
        }
        if (!hasValidDay(year, month, day)) {
            throw new InvalidDayException();
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // REQUIRES: month is an int between 1 and 12
    // EFFECTS: returns the max valid day in the month in the year
    private int maxDayInMonth(int year, int month) {
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
                || (month == 8) || (month == 10) || (month == 12)) {
            return 31;
        } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            return 30;
        } else if ((month == 2) && (year % 4 == 0)) {
            return 29;
        }
        return 28;
    }

    // EFFECTS: returns true if the year, month, day of the two dates
    //          are equal
    public boolean equals(Date date) {
        return (year == date.getYear()) && (month == date.getMonth())
                && (day == date.getDay());
    }

    // EFFECTS: returns date in "YYYY/MM/DD" format
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    // EFFECTS: returns JSON object of date with year, month, day
    public JSONObject toJson() {
        JSONObject date = new JSONObject();
        date.put("year", year);
        date.put("month", month);
        date.put("day", day);
        return date;
    }
}
