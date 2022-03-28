package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList toDoList;
    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoList("Test List");
        task1 = new Task("Do laundry");
        task2 = new Task("Go shopping");
        task3 = new Task("Do homework");
        task4 = new Task("Go to sleep");
    }

    @Test
    public void testGetTaskByNameNoTaskFound() {
        toDoList.addTask(task2);
        assertEquals(1, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task2));

        assertNull(toDoList.getTask(task4.getName()));
    }

    @Test
    public void testGetTaskByNameNoSameName() {
        toDoList.addTask(task2);
        toDoList.addTask(task4);
        toDoList.addTask(task3);

        assertEquals(3, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task3));
        assertTrue(toDoList.getTask(1).equals(task4));
        assertTrue(toDoList.getTask(2).equals(task2));

        assertTrue(task2.equals(toDoList.getTask(task2.getName())));
    }

    @Test
    public void testGetTaskByNameManySameName() {
        task1.editName("Task Alpha");
        task1.completeTask();

        task2.editName("Task Alpha");
        task2.setDueDate(new Date(2020, 11, 13));

        task3.editName("Task Alpha");

        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);

        assertEquals(3, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task3));
        assertTrue(toDoList.getTask(1).equals(task2));
        assertTrue(toDoList.getTask(2).equals(task1));

        assertFalse(task1.equals(toDoList.getTask("Task Alpha")));
        assertFalse(task2.equals(toDoList.getTask("Task Alpha")));
        assertTrue(task3.equals(toDoList.getTask("Task Alpha")));
    }

    @Test
    public void testAddTask() {
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
        toDoList.addTask(task1);

        assertEquals(1, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));

        assertEquals(task1.toString() + "\n", toDoList.display());
    }

    @Test
    public void testDisplayMany() {
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