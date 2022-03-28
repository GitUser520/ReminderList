package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    public void testGetName() {
        assertEquals("Test List", toDoList.getName());
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
        Date date = null;
        try {
            date = new Date(2020, 11, 13);
        } catch (Exception e) {
            fail();
        }
        
        task1.setName("Task Alpha");
        task1.completeTask();

        task2.setName("Task Alpha");
        task2.setDueDate(date);

        task3.setName("Task Alpha");

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
    public void testGetFirstEmpty() {
        assertEquals(null, toDoList.getFirst());
    }

    @Test
    public void testGetFirstNotEmpty() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        assertEquals(task2, toDoList.getFirst());
    }

    @Test
    public void testGetAllTasksEmpty() {
        assertEquals(0, toDoList.getAllTasks().size());
    }

    @Test
    public void testGetAllTasks() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);

        List<Task> tasks = toDoList.getAllTasks();
        assertEquals(3, tasks.size());
        assertEquals(task3, tasks.get(0));
        assertEquals(task2, tasks.get(1));
        assertEquals(task1, tasks.get(2));
    }

    @Test
    public void testContainsDoesNotContainTask() {
        toDoList.addTask(task1);
        assertEquals(1, toDoList.size());
        assertFalse(toDoList.contains(task3));
    }

    @Test
    public void testContainsContainsTask() {
        toDoList.addTask(task2);
        assertEquals(1, toDoList.size());
        assertTrue(toDoList.contains(task2));
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

        assertEquals(toDoList.getName() + "\n", toDoList.display());
    }

    @Test
    public void testDisplayOne() {
        toDoList.addTask(task1);

        assertEquals(1, toDoList.size());
        assertTrue(toDoList.getTask(0).equals(task1));

        assertEquals(toDoList.getName() + "\n\t" + task1.displayTask() + "\n", toDoList.display());
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

        assertEquals(toDoList.getName() + "\n\t" + task1.displayTask()
                + "\n\t" + task2.displayTask() + "\n\t" + task3.displayTask() + "\n", toDoList.display());
    }

    @Test
    public void testToString() {
        toDoList.setName("List of to-dos");
        assertEquals("List of to-dos", toDoList.toString());
    }

    @Test
    public void testToJsonEmpty() {
        assertEquals(0, toDoList.size());
        JSONObject jsonList = toDoList.toJson();

        assertEquals(toDoList.getName(), jsonList.getString("name"));
        assertEquals(0, jsonList.getJSONArray("tasks").length());
    }

    @Test
    public void testToJsonSingle() {
        toDoList.addTask(task1);
        assertEquals(1, toDoList.size());

        JSONObject jsonList = toDoList.toJson();
        assertEquals(toDoList.getName(), jsonList.getString("name"));
        assertEquals(1, jsonList.getJSONArray("tasks").length());
    }

    @Test
    public void testToJsonMany() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertEquals(3, toDoList.size());

        JSONObject jsonList = toDoList.toJson();
        assertEquals(toDoList.getName(), jsonList.getString("name"));
        assertEquals(3, jsonList.getJSONArray("tasks").length());
    }

    /*
    Unimplemented tests (not part of user story):

    @Test
    public void testSortAlphabetical() {}

    @Test
    public void testSortByDueDate() {}

     */

}