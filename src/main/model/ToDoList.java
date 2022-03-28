package model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    private String name;
    private List<Task> toDoList;

    // EFFECTS: constructs an empty to-do list with given name
    public ToDoList(String name) {
        this.name = name;
        toDoList = new ArrayList<>();
    }

    // EFFECTS: gets the first task with the given task name
    public Task getTask(String taskName) {
        return null;
    }

    // REQUIRES: 0 <= index < size of to-do list
    // EFFECTS: gets the task at the given index
    public Task getTask(int index) {
        return null;
    }

    // EFFECTS: returns the size of the to-do list
    public int size() {
        return toDoList.size();
    }

    // MODIFIES: this
    // EFFECTS: adds the task to the to-do list
    public void addTask(Task task) {}

    // MODIFIES: this
    // EFFECTS: removes the first task with the given name from the to-do list
    public void removeTask(String name) {}

    // REQUIRES: 0 <= index < size of to-do list
    // MODIFIES: this
    // EFFECTS: removes the task at the given index
    public void removeTask(int index) {}

    // MODIFIES: this
    // EFFECTS: clears all tasks from this to-do list
    public void clear() {}

    // EFFECTS: displays all of the tasks in the to-do list in current order
    public String display() {
        return "";
    }

    // MODIFIES: this
    // EFFECTS: sorts the tasks in the to-do list by alphabetical order
    public void sortAlphabetical() {}

    // MODIFIES: this
    // EFFECTS: sorts the tasks in the to-do list by earliest to latest
    //          due dates; tasks without due dates are put last in to-do list
    public void sortByDueDate() {}

}
