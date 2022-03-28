package model;

// Represents a task with its name, completion status, and an optional due date.
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

    // EFFECTS: returns the name of this task
    public String getName() {
        return name;
    }

    // EFFECTS: returns the completion status of this task
    public Boolean getCompletionStatus() {
        return completionStatus;
    }

    // EFFECTS: returns the due date of the task
    public Date getDueDate() {
        return dueDate;
    }

    // MODIFIES: this
    // EFFECTS: changes the task name to given name
    public void editName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets a due date with given date
    public void setDueDate(Date date) {
        dueDate = date;
    }

    // MODIFIES: this
    // EFFECTS: marks this task as complete
    public void completeTask() {
        completionStatus = true;
    }

    // EFFECTS: returns true if name, completion status, and due date are equal
    public boolean equals(Task task) {
        if ((dueDate != null) && (task.getDueDate() != null)) {
            return (name.equals(task.getName())) && (completionStatus == task.getCompletionStatus())
                    && (dueDate.equals(task.getDueDate()));
        }
        return (name.equals(task.getName())) && (completionStatus == task.getCompletionStatus())
                && dueDate == task.getDueDate();
    }

    // EFFECTS: if task is incomplete, displays the task as incomplete, with
    //                     due date (if applicable)
    //          if complete, display task as complete
    public String toString() {
        if ((!completionStatus) && dueDate != null) {
            return name + ": incomplete. Due: " + dueDate.toString();
        } else if ((!completionStatus) && dueDate == null) {
            return name + ": incomplete.";
        }
        return name + ": complete.";
    }
}
