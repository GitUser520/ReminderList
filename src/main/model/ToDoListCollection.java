package model;

import java.awt.image.TileObserver;
import java.util.ArrayList;
import java.util.List;

public class ToDoListCollection {

    private List<ToDoList> collection;

    // EFFECTS: constructs an empty to-do list collection
    public ToDoListCollection() {
        collection = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the to-do list to the collection
    public void addToDoList(ToDoList toDoList) {

    }

    // MODIFIES: this
    // EFFECTS: removes the to-do list with given name from the collection
    public void removeToDoList(String name) {}



}
