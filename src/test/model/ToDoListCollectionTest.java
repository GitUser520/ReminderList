package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListCollectionTest {
    public ToDoListCollection collection;
    ToDoList list1;
    ToDoList list2;

    @BeforeEach
    public void setUp() {
        collection = new ToDoListCollection();
        list1 = new ToDoList("List 1");
        list2 = new ToDoList("List 2");
        list1.addTask(new Task("buy groceries"));
        list1.addTask(new Task("clean bedroom", false, new Date(2023, 2, 1)));
        list1.addTask(new Task("completed task", true, null));
        list2.addTask(new Task("task"));
    }

    @Test
    public void testGetToDoListByIndex() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        assertEquals(list1, collection.getToDoList(0));
        assertEquals(list2, collection.getToDoList(1));
    }

    @Test
    public void testGetToDoListByNameListInCollection() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        assertEquals(list1, collection.getToDoList("List 1"));
    }

    @Test
    public void testGetToDoListByNameListNotInCollection() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        assertEquals(null, collection.getToDoList("Invalid list"));
    }

    @Test
    public void testGetToDoLists() {
        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(list1);
        toDoLists.add(list2);
        collection.addToDoList(list1);
        collection.addToDoList(list2);

        assertEquals(toDoLists, collection.getToDoLists());
    }

    @Test
    public void testAddToDoList() {
        assertEquals(0, collection.size());

        collection.addToDoList(new ToDoList("List 1"));
        collection.addToDoList(new ToDoList("List 2"));

        assertEquals(2, collection.size());
        assertEquals("List 1", collection.getToDoList(0).getName());
        assertEquals("List 2", collection.getToDoList(1).getName());
    }

    @Test
    public void testRemoveToDoListByNameDoesNotContainList() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        assertEquals(2, collection.size());

        collection.removeToDoList("invalid list");
        assertEquals(2, collection.size());
        assertEquals(list1, collection.getToDoList("List 1"));
        assertEquals(list2, collection.getToDoList("List 2"));
    }

    @Test
    public void testRemoveToDoListByNameDoesContainList() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        assertEquals(2, collection.size());

        collection.removeToDoList("List 1");
        assertEquals(1, collection.size());
        assertEquals(null, collection.getToDoList("List 1"));
        assertEquals(list2, collection.getToDoList("List 2"));
    }

    @Test
    public void testRemoveToDoListByNameDoesContainListDuplicates() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        collection.addToDoList(list1);
        assertEquals(3, collection.size());

        collection.removeToDoList("List 1");
        assertEquals(2, collection.size());
        assertEquals(list2, collection.getToDoList(0));
        assertEquals(list1, collection.getToDoList(1));
    }

    @Test
    public void testRemoveToDoListByIndex() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        assertEquals(2, collection.size());

        collection.removeToDoList(1);
        assertEquals(1, collection.size());
        assertEquals(list1, collection.getToDoList("List 1"));
        assertEquals(null, collection.getToDoList("List 2"));
    }

    @Test
    public void testClearAll() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        assertEquals(2, collection.size());

        collection.clearAll();
        assertEquals(0, collection.size());
    }

    @Test
    public void testDisplayAllEmptyCollection() {
        assertEquals("", collection.displayAll());
    }

    @Test
    public void testDisplayAllSingle() {
        collection.addToDoList(list1);
        assertEquals(list1.display() + "\n", collection.displayAll());
    }

    @Test
    public void testDisplayAllMany() {
        ToDoList list3 = new ToDoList("Empty List");
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        collection.addToDoList(list3);
        assertEquals(list1.display() + "\n" + list2.display() + "\n" +
                list3.display() + "\n", collection.displayAll());
    }

    @Test
    public void testToJsonEmpty() {
        assertEquals(0, collection.size());
        JSONObject jsonCollection = collection.toJson();

        assertEquals(0, jsonCollection.getJSONArray("collection").length());
    }

    @Test
    public void testToJsonSingle() {
        collection.addToDoList(list1);
        assertEquals(1, collection.size());
        JSONObject jsonCollection = collection.toJson();

        assertEquals(1, jsonCollection.getJSONArray("collection").length());
    }

    @Test
    public void testToJsonMany() {
        collection.addToDoList(list1);
        collection.addToDoList(list2);
        collection.addToDoList(new ToDoList("List 3"));
        assertEquals(3, collection.size());
        JSONObject jsonCollection = collection.toJson();

        assertEquals(3, jsonCollection.getJSONArray("collection").length());
    }
}
