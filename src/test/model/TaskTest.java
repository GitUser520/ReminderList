package model;

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
        task.editName("Laundry");
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
    public void testToStringNotCompleteNotNull() {
        Date date = new Date(2021, 2, 14);

        task.editName("Go shopping");
        task.setDueDate(date);
        assertEquals("Go shopping: incomplete. Due: " + date.toString(),
                task.toString());
    }

    @Test
    public void testToStringNotCompleteAndNull() {
        task.editName("Go shopping");
        assertEquals("Go shopping: incomplete.", task.toString());
    }

    @Test
    public void testEqualsByName() {
        Task expectedTask = new Task ("Do laundry");

        task.editName("Finish laundry");

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.editName("Do laundry");

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testEqualsByCompletionStatus() {
        Task expectedTask = new Task("Do laundry");

        task.editName("Do laundry");

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));

        task.completeTask();

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        expectedTask.completeTask();

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testEqualsByDueDate() {
        Task expectedTask = new Task ("Do laundry");

        task.editName("Do laundry");

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));

        expectedTask.setDueDate(new Date(2020,2,3));

        assertFalse(task.equals(expectedTask));
        assertFalse(expectedTask.equals(task));

        task.setDueDate(new Date(2020,2,3));

        assertTrue(task.equals(expectedTask));
        assertTrue(expectedTask.equals(task));
    }

    @Test
    public void testToStringComplete() {
        task.editName("Do Laundry");
        task.completeTask();
        assertEquals("Do Laundry: complete.", task.toString());
    }

}
