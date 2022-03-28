package persistence;

import model.Date;
import model.Task;
import model.ToDoList;
import model.ToDoListCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests adapted from JSON Demo project:
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoListCollection collection = new ToDoListCollection();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDoListCollection() {
        try {
            ToDoListCollection collection = new ToDoListCollection();
            JsonWriter writer = new JsonWriter();
            writer.setDestination("./data/testWriterEmptyToDoListCollection.json");
            writer.open();
            writer.write(collection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoListCollection.json");
            collection = reader.read();
            assertEquals("", collection.displayAll());
            assertEquals(0, collection.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDoListCollection() {
        try {
            ToDoListCollection collection = new ToDoListCollection();
            ToDoList list1 = new ToDoList("shopping");
            ToDoList list2 = new ToDoList("chores");
            list1.addTask(new Task("buy milk", false, new Date(2021, 3, 7)));
            list1.addTask(new Task("buy potatoes", false, null));
            list1.addTask(new Task("buy onions", true, null));
            collection.addToDoList(list1);
            collection.addToDoList(list2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoListCollection.json");
            writer.open();
            writer.write(collection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoListCollection.json");
            collection = reader.read();
            checkToDoListNameAndSize("shopping", 3, 0, collection);
            checkToDoListNameAndSize("chores", 0, 1, collection);

            ToDoList toDoList1 = collection.getToDoList(0);
            ToDoList toDoList2 = collection.getToDoList(1);

            checkTaskInToDoList(new Task("buy milk", false, new Date(2021, 3, 7)),
                    toDoList1);
            checkTaskInToDoList(new Task("buy potatoes", false, null),
                    toDoList1);
            checkTaskInToDoList(new Task("buy onions", true, null),
                    toDoList1);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}