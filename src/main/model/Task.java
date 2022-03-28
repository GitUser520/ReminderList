package model;

public class Task {

    private String name;
    private Boolean completionStatus;

    private Date dueDate;

    // EFFECTS: constructs a task with given name and incomplete status.
    public Task(String name) {
        this.name = name;
        this.completionStatus = false;
        dueDate = null;
    }

    // MODIFIES: this
    // EFFECTS: sets a due date with given date
    public void setDueDate(Date date) {
        dueDate = date;
    }

    // MODIFIES: this
    // EFFECTS: marks this task as complete
    public void complete() {
        completionStatus = true;
    }

    // EFFECTS: if task is incomplete, displays the task as incomplete, with
    //                     due date and reminder
    //          if complete, display task as complete
    public String toString() {
        if (completionStatus) {
            return name + ": incomplete. Due: " + dueDate.toString()
                    + " Remind: ";
        }
        return name + ": complete.";
    }

}
