package persistence;

import model.Task;
import model.ToDoList;
import model.ToDoListCollection;

import static org.junit.jupiter.api.Assertions.*;

// Tests adapted from JSON Demo project:
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {

    protected void checkToDoListNameAndSize(String name, int size, int index, ToDoListCollection collection) {
        assertEquals(name, collection.getToDoList(index).getName());
        assertEquals(size, collection.getToDoList(index).size());
    }

    protected void checkTaskInToDoList(Task task, ToDoList toDoList) {
        assertTrue(toDoList.contains(task));
    }
}
