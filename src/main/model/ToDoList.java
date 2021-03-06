package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a to-do list that has a name and a list of tasks.
public class ToDoList {

    private String name;
    private List<Task> toDoList;

    // EFFECTS: constructs an empty to-do list with given name
    public ToDoList(String name) {
        this.name = name;
        toDoList = new ArrayList<>();
    }

    // EFFECTS: gets the first task with the given task name
    //          returns null if not found
    public Task getTask(String taskName) {
        for (Task task: toDoList) {
            if (task.getName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    // REQUIRES: 0 <= index < size of to-do list
    // EFFECTS: gets the task at the given index
    public Task getTask(int index) {
        return toDoList.get(index);
    }

    // EFFECTS: returns the name of the to-do list
    public String getName() {
        return name;
    }

    // EFFECTS: returns the task at the front of the to-do list
    //          or null if to-do list is empty
    public Task getFirst() {
        if (toDoList.size() == 0) {
            return null;
        }
        return toDoList.get(0);
    }

    // EFFECTS: returns all tasks in the to-do list
    public List<Task> getAllTasks() {
        return toDoList;
    }

    // EFFECTS: returns the size of the to-do list
    public int size() {
        return toDoList.size();
    }

    // EFFECTS: returns true if to-do list contains task, false otherwise
    public boolean contains(Task task) {
        for (Task other : toDoList) {
            if (other.equals(task)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: sets the name of the to-do list to the given name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds the task to the beginning of to-do list
    public void addTask(Task task) {
        toDoList.add(0, task);
    }

    // MODIFIES: this
    // EFFECTS: removes the first task with the given name from the to-do list
    //          returns true if found; false otherwise
    public boolean removeTask(String name) {
        for (Task task: toDoList) {
            if (task.getName().equals(name)) {
                toDoList.remove(task);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes the task at the given index
    public void removeTask(int index) {
        toDoList.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: clears all tasks from this to-do list
    public void clear() {
        toDoList.clear();
    }

    // EFFECTS: displays to-do list and all tasks in the list in current order
    public String display() {
        String result = name + "\n";
        for (Task task: toDoList) {
            result = result + "\t" + task.displayTask() + "\n";
        }
        return result;
    }

    // EFFECTS: returns the name of the to-do list
    public String toString() {
        return name;
    }

    // EFFECTS: returns a JSON object with to-do list name and tasks
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (Task task : toDoList) {
            array.put(task.toJson());
        }
        object.put("name", name);
        object.put("tasks", array);
        return object;
    }


    /* Not yet implemented.

    // MODIFIES: this
    // EFFECTS: sorts the tasks in the to-do list by alphabetical order
    public void sortAlphabetical() {}

    // MODIFIES: this
    // EFFECTS: sorts the tasks in the to-do list by earliest to latest
    //          due dates; tasks without due dates are put last in to-do list
    public void sortByDueDate() {}

     */
}
