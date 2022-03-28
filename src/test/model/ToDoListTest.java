package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Task;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList toDoList;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoList("Test List");
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Do laundry");

        toDoList.addTask(task);
        assertEquals(1, toDoList.size());
        assertTrue(toDoList.getTask("Do laundry").equals(task));
    }

    @Test
    public void testRemoveTaskByName() {}

    @Test
    public void testRemoveTaskByIndex() {}

    @Test
    public void testClear() {}

    @Test
    public void testDisplayNone() {}

    @Test
    public void testDisplayOne() {}

    @Test
    public void testDisplayMany() {}

    /*
    Unimplemented tests (not part of user story):

    @Test
    public void testSortAlphabetical() {}

    @Test
    public void testSortByDueDate() {}

     */

}