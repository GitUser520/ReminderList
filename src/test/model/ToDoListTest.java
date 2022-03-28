package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList toDoList;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoList("Test List");
    }

    @Test
    public void testAddTask() {
        Task task1 = new Task("Do laundry");
        Task task2 = new Task("Go shopping");

        toDoList.addTask(task1);
        assertEquals(1, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));

        toDoList.addTask(task2);
        assertEquals(2, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task2));
        assertTrue(toDoList.getTask(1).equals(task1));
    }

    @Test
    public void testRemoveTaskByNameNoSameName() {
        Task task1 = new Task("Do laundry");
        Task task2 = new Task("Go shopping");
        Task task3 = new Task("Do homework");
        Task task4 = new Task("Go to sleep");

        toDoList.addTask(task4);
        toDoList.addTask(task3);
        toDoList.addTask(task2);
        toDoList.addTask(task1);

        assertEquals(4, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task2));
        assertTrue(toDoList.getTask(2).equals(task3));
        assertTrue(toDoList.getTask(3).equals(task4));

        assertTrue(toDoList.removeTask("Go shopping"));
        assertFalse(toDoList.removeTask("Go shopping"));

        assertEquals(3, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task3));
        assertTrue(toDoList.getTask(2).equals(task4));

        assertTrue(toDoList.removeTask("Do homework"));
        assertFalse(toDoList.removeTask("Do homework"));

        assertEquals(2, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task4));
    }

    @Test
    public void testRemoveTaskByNameSameName() {
        Task task1 = new Task("Do laundry");
        Task task2 = new Task("Go shopping");
        Task task3 = new Task("Do homework");

        toDoList.addTask(task2);
        toDoList.addTask(task3);
        toDoList.addTask(task2);
        toDoList.addTask(task1);

        assertEquals(4, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task2));
        assertTrue(toDoList.getTask(2).equals(task3));
        assertTrue(toDoList.getTask(3).equals(task2));

        assertTrue(toDoList.removeTask("Go shopping"));

        assertEquals(3, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task3));
        assertTrue(toDoList.getTask(2).equals(task2));

        assertTrue(toDoList.removeTask("Go shopping"));
        assertFalse(toDoList.removeTask("Go shopping"));

        assertEquals(2, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task3));
    }

    @Test
    public void testRemoveTaskByIndex() {
        Task task1 = new Task("Do laundry");
        Task task2 = new Task("Go shopping");
        Task task3 = new Task("Do homework");

        toDoList.addTask(task3);
        toDoList.addTask(task2);
        toDoList.addTask(task1);

        assertEquals(3, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task2));
        assertTrue(toDoList.getTask(2).equals(task3));

        toDoList.removeTask(2);

        assertEquals(2, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task2));

        toDoList.removeTask(0);

        assertEquals(1, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task2));
    }

    @Test
    public void testClear() {
        Task task1 = new Task("Do laundry");
        Task task2 = new Task("Go shopping");
        Task task3 = new Task("Do homework");

        toDoList.addTask(task3);
        toDoList.addTask(task2);
        toDoList.addTask(task1);

        assertEquals(3, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task2));
        assertTrue(toDoList.getTask(2).equals(task3));

        toDoList.clear();

        assertEquals(0, toDoList.size());
    }

    @Test
    public void testDisplayNone() {
        toDoList.clear();

        assertEquals(0, toDoList.size());

        assertEquals("", toDoList.display());
    }

    @Test
    public void testDisplayOne() {
        Task task = new Task("Go for a walk");

        toDoList.addTask(task);

        assertEquals(1, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task));

        assertEquals(task.toString() + "\n", toDoList.display());
    }

    @Test
    public void testDisplayMany() {
        Task task1 = new Task("Do laundry");
        Task task2 = new Task("Go shopping");
        Task task3 = new Task("Do homework");

        toDoList.addTask(task3);
        toDoList.addTask(task2);
        toDoList.addTask(task1);

        assertEquals(3, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));
        assertTrue(toDoList.getTask(1).equals(task2));
        assertTrue(toDoList.getTask(2).equals(task3));

        assertEquals(task1.toString() + "\n" + task2.toString() +
                "\n" + task3.toString() + "\n", toDoList.display());
    }

    /*
    Unimplemented tests (not part of user story):

    @Test
    public void testSortAlphabetical() {}

    @Test
    public void testSortByDueDate() {}

     */

}