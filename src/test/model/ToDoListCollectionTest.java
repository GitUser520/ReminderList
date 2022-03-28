package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListCollectionTest {
    public ToDoListCollection collection;

    @BeforeEach
    public void setUp() {
        collection = new ToDoListCollection();
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

    /*
    public void testGetTaskByName() {}

    public void removeToDoListByName() {}

    public void testDisplay() {}

     */
}
