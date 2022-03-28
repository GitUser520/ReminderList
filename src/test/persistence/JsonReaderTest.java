package persistence;

import model.Date;
import model.Task;
import model.ToDoList;
import model.ToDoListCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Tests adapted from JSON Demo project:
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader();
        reader.setSource("./data/noSuchFile.json");
        try {
            ToDoListCollection collection = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyToDoListCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoListCollection.json");
        try {
            ToDoListCollection collection = reader.read();
            assertEquals("", collection.displayAll());
            assertEquals(0, collection.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralToDoListCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoListCollection.json");
        try {
            ToDoListCollection collection = reader.read();
            assertEquals(3, collection.size());

            checkToDoListNameAndSize("personal", 2, 0, collection);
            Task task1 = new Task("buy groceries", false, new Date(2020, 11, 2));
            Task task2 = new Task("wash dishes", true, null);
            ToDoList toDoList1 = collection.getToDoList(0);
            checkTaskInToDoList(task1, toDoList1);
            checkTaskInToDoList(task2, toDoList1);

            checkToDoListNameAndSize("work", 0, 1, collection);

            checkToDoListNameAndSize("other", 0, 2, collection);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}