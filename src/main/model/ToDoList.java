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

    // MODIFIES: this
    // EFFECTS: adds the task to the to-do list
    public void addTask(Task task) {}

    // MODIFIES: this
    // EFFECTS: removes the task with the given name from the to-do list
    public void removeTask(String name) {}

    // MODIFIES: this
    // EFFECTS: clears all tasks from this to-do list
    public void clear() {}
}
