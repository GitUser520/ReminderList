package model;

import org.json.JSONObject;

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

    // EFFECTS: constructs a task with given name, completion status, and due date
    public Task(String name, Boolean completionStatus, Date dueDate) {
        this.name = name;
        this.completionStatus = completionStatus;
        this.dueDate = dueDate;
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
    // EFFECTS: sets the task name to given name
    public void setName(String name) {
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
    @Override
    public String toString() {
        if ((!completionStatus) && dueDate != null) {
            return name + ": incomplete. Due: " + dueDate.toString();
        } else if (!completionStatus) {
            return name + ": incomplete.";
        }
        return name + ": complete.";
    }

    // EFFECTS: returns a JSON object with name, completion status, and date
    public JSONObject toJson() {
        JSONObject task = new JSONObject();
        task.put("task name", name);
        task.put("completion status", completionStatus);
        if (dueDate != null) {
            task.put("due date", dueDate.toJson());
        } else {
            task.put("due date", JSONObject.NULL);
        }
        return task;
    }
}
