package model;

public class Date {

    private int year;
    private int month;
    private int day;

    // REQUIRES: month is between 1 and 12, day is between 1 and 31
    //           date must be a valid date
    // EFFECTS: constructs a date with given year, month and day.
    public Date(int year, int month, int day) {
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

    // REQUIRES: year is non-negative
    // MODIFIES: this
    // EFFECTS: sets year as given year
    public void setYear(int year) {
        this.year = year;
    }

    // REQUIRES: 0 <= month <= 12
    // MODIFIES: this
    // EFFECTS: sets month as given month
    public void setMonth(int month) {
        this.month = month;
    }

    // REQUIRES: 0 <= day <= 31
    // MODIFIES: this
    // EFFECTS: sets day as given day
    public void setDay(int day) {
        this.day = day;
    }

    // MODIFIES: this
    // EFFECTS: sets year, month, day as given year, month, day
    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
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

}
