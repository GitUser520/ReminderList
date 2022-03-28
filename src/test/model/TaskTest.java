package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("");
    }

    @Test
    public void testEditName() {
        task.setName("Laundry");
        assertEquals("Laundry", task.getName());
    }

    @Test
    public void testSetDueDate() {
        task.setDueDate(new Date(2021, 1, 20));
        assertTrue(task.getDueDate().equals(new Date(2021, 1, 20)));
    }

    @Test
    public void testCompleteTask() {
        task.completeTask();
        assertTrue(task.getCompletionStatus());
    }

    @Test
    public void testEqualsByName() {
        Task expectedTask = new Task ("Do laundry");

        task.setName("Finish laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setName("Do laundry");

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testEqualsByCompletionStatus() {
        Task expectedTask = new Task("Do laundry");

        task.setName("Do laundry");

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));

        task.setName("Some Random Task");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.completeTask();

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setName("Do laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        expectedTask.completeTask();

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testEqualsByDifferentDueDate() {
        Task expectedTask = new Task ("Do laundry");
        expectedTask.setDueDate(new Date(2020,2,3));

        task.setName("Not do laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setDueDate(new Date(1999, 1, 11));

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.completeTask();

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setName("Do laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        expectedTask.completeTask();

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setDueDate(new Date(2020,2,3));

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testEqualsBySameDueDate() {
        Task expectedTask = new Task ("Do laundry");
        expectedTask.setDueDate(new Date(2020,2,3));

        task.setName("Not do laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setDueDate(new Date(2020,2,3));

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.completeTask();

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setName("Do laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        expectedTask.completeTask();

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testToStringComplete() {
        task.setName("Do Laundry");
        task.completeTask();
        assertEquals("Do Laundry: complete.", task.displayTask());

        task.setDueDate(new Date(2021, 2, 13));
        assertEquals("Do Laundry: complete.", task.displayTask());
    }

    @Test
    public void testToStringNotCompleteWithDueDate() {
        Date date = new Date(2021, 2, 14);

        task.setName("Go shopping");
        task.setDueDate(date);

        assertEquals("Go shopping: incomplete. Due: " + date.toString(),
                task.displayTask());
    }

    @Test
    public void testToStringNotCompleteNoDueDate() {
        task.setName("Go shopping");
        assertEquals("Go shopping: incomplete.", task.displayTask());
    }

    @Test
    public void testToJsonNullDate() {
        task.setName("Task 1");
        assertEquals(null, task.getDueDate());
        JSONObject jsonTask = task.toJson();
        assertEquals(3, jsonTask.length());
        assertEquals("Task 1", jsonTask.get("task name"));
        assertFalse(jsonTask.getBoolean("completion status"));
        assertEquals(JSONObject.NULL, jsonTask.get("due date"));
    }

    @Test
    public void testToJsonHasDate() {
        Date date = new Date(2021, 4, 6);
        task.setName("Task 1");
        task.completeTask();
        task.setDueDate(date);
        assertTrue(task.getDueDate().equals(date));

        JSONObject jsonTask = task.toJson();
        assertEquals(3, jsonTask.length());
        assertEquals("Task 1", jsonTask.get("task name"));
        assertTrue(jsonTask.getBoolean("completion status"));

        JSONObject jsonDate = jsonTask.getJSONObject("due date");
        assertEquals(2021, jsonDate.get("year"));
        assertEquals(4, jsonDate.get("month"));
        assertEquals(6, jsonDate.get("day"));
    }
}
