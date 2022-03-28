package model;

import java.awt.image.TileObserver;
import java.util.ArrayList;
import java.util.List;

// Not part of user stories.
// Represents a collection of to-do lists.
public class ToDoListCollection {

    private List<ToDoList> collection;

    // EFFECTS: constructs an empty to-do list collection
    public ToDoListCollection() {
        collection = new ArrayList<>();
    }

    // REQUIRES: 0 <= index < size of collection
    // EFFECTS: returns to-do list at given index
    public ToDoList getToDoList(int index) {
        return collection.get(index);
    }

    // EFFECTS: returns the size of the collection
    public int size() {
        return collection.size();
    }

    // MODIFIES: this
    // EFFECTS: adds the to-do list to the end of the collection
    public void addToDoList(ToDoList toDoList) {
        collection.add(toDoList);
    }

    /* Has not been implemented yet. Not part of user stories.

    // EFFECTS: returns first to-do list with given name
    public void getToDoList(String name) {}

    // MODIFIES: this
    // EFFECTS: removes the to-do list with given name from the collection
    public void removeToDoList(String name) {}

    // EFFECTS: displays all items in the collection
    public String displayAll() {
        return "";
    }

     */
}
