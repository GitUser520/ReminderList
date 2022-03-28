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
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new Date(2021, 1, 20);
            date2 = new Date(2021, 1, 20);
        } catch (Exception e) {
            fail();
        }
        task.setDueDate(date1);
        assertTrue(task.getDueDate().equals(date2));
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
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = new Date(2020,2,3);
            date2 = new Date(1999, 1, 11);
        } catch (Exception e) {
            fail();
        }

        Task expectedTask = new Task ("Do laundry");
        expectedTask.setDueDate(date1);

        task.setName("Not do laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setDueDate(date2);

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

        task.setDueDate(date1);

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testEqualsBySameDueDate() {
        Date date = null;
        try {
            date = new Date(2020,2,3);
        } catch (Exception e) {
            fail();
        }

        Task expectedTask = new Task ("Do laundry");
        expectedTask.setDueDate(date);

        task.setName("Not do laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setDueDate(date);

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
    public void testTDisplayDateComplete() {
        task.setName("Do Laundry");
        task.completeTask();
        assertEquals("Do Laundry\n\nStatus: complete\nDue date: ", task.displayTask());

        Date date = null;
        try {
            date = new Date(2021, 2, 13);
        } catch (Exception e) {
            fail();
        }

        task.setDueDate(date);
        assertEquals("Do Laundry\n\nStatus: complete\nDue date: 2021/2/13", task.displayTask());
    }

    @Test
    public void testDisplayDateNotCompleteWithDueDate() {
        Date date = null;
        try {
            date = new Date(2021, 2, 14);
        } catch (Exception e) {
            fail();
        }

        task.setName("Go shopping");
        task.setDueDate(date);

        assertEquals("Go shopping\n\nStatus: incomplete\nDue date: " + date.toString(),
                task.displayTask());
    }

    @Test
    public void testDisplayDateNotCompleteNoDueDate() {
        task.setName("Go shopping");
        assertEquals("Go shopping\n\nStatus: incomplete\nDue date: ", task.displayTask());
    }

    @Test
    public void testToString() {
        task.setName("Hello");
        assertEquals("Hello", task.toString());
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
        Date date = null;
        try {
            date = new Date(2021, 4, 6);
        } catch (Exception e) {
            fail();
        }
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
