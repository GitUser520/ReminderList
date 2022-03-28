package model;

import org.json.JSONArray;
import org.json.JSONObject;

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

    // EFFECTS: returns first to-do list with given name, or null if not found
    public ToDoList getToDoList(String name) {
        ToDoList toDoList = null;
        for (ToDoList list : collection) {
            if (list.getName().equals(name)) {
                toDoList = list;
            }
        }
        return toDoList;
    }

    // EFFECTS: returns the list of to-do lists in collection
    public List<ToDoList> getToDoLists() {
        return collection;
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

    // MODIFIES: this
    // EFFECTS: removes the first to-do list with given name from the collection
    public void removeToDoList(String name) {
        for (ToDoList list : collection) {
            if (list.getName().equals(name)) {
                collection.remove(list);
                break;
            }
        }
    }

    // REQUIRES: index must be valid
    // MODIFIES: this
    // EFFECTS: removes the to-do list at the given index
    public void removeToDoList(int index) {
        collection.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: removes all to-do lists from the collection
    public void clearAll() {
        collection.clear();
    }

    // EFFECTS: return string of all items in the collection
    public String displayAll() {
        String result = "";
        for (ToDoList list : collection) {
            result = result + list.display() + "\n";
        }
        return result;
    }

    // EFFECTS: returns a JSON object of to-do lists
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (ToDoList list : collection) {
            array.put(list.toJson());
        }
        object.put("collection", array);
        return object;
    }
}
