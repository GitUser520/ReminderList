package persistence;

import model.Date;
import model.Task;
import model.ToDoList;
import model.ToDoListCollection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Class referenced and modified from JSON Demo.
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads ToDoListCollection from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader with empty source
    public JsonReader() {
        source = "";
    }

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // MODIFIES: this
    // EFFECTS: sets the source as the given source
    public void setSource(String source) {
        this.source = source;
    }

    // EFFECTS: reads ToDoListCollection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoListCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoListCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ToDoListCollection from JSON object and returns it
    private ToDoListCollection parseToDoListCollection(JSONObject jsonObject) {
        ToDoListCollection collection = new ToDoListCollection();
        addToDoLists(collection, jsonObject);
        return collection;
    }

    // MODIFIES: collection
    // EFFECTS: parses to-do lists from JSON object and adds them to collection
    private void addToDoLists(ToDoListCollection collection, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("collection");
        for (Object json : jsonArray) {
            JSONObject nextToDoList = (JSONObject) json;
            addToDoList(collection, nextToDoList);
        }
    }

    // MODIFIES: collection
    // EFFECTS: parses to-do list from JSON object and adds it to collection
    private void addToDoList(ToDoListCollection collection, JSONObject jsonToDoList) {
        String name = jsonToDoList.getString("name");
        ToDoList toDoList = new ToDoList(name);
        JSONArray tasks = jsonToDoList.getJSONArray("tasks");
        for (Object task : tasks) {
            JSONObject nextTask = (JSONObject) task;
            addTask(toDoList, nextTask);
        }
        collection.addToDoList(toDoList);
    }

    // MODIFIES: toDoList
    // EFFECTS: parses task from JSON object and adds it to to-do list
    private void addTask(ToDoList toDoList, JSONObject jsonTask) {
        String name = jsonTask.getString("task name");
        Boolean completionStatus = jsonTask.getBoolean("completion status");
        Date date = null;
        if (!jsonTask.isNull("due date")) {
            JSONObject jsonDate = jsonTask.getJSONObject("due date");
            date = getDate(jsonDate);
        }
        toDoList.addTask(new Task(name, completionStatus, date));
    }

    // EFFECTS: parses JSON object and returns a date
    private Date getDate(JSONObject jsonDate) {
        try {
            int year = jsonDate.getInt("year");
            int month = jsonDate.getInt("month");
            int day = jsonDate.getInt("day");
            return new Date(year, month, day);
        } catch (Exception e) {
            return null;
        }
    }
}
