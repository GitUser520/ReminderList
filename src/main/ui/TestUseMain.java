package ui;

import model.Date;
import model.Task;
import model.ToDoList;

// Not part of project implementation.
// Test console to test out console outputs.
public class TestUseMain {

    public static void main(String[] args) {
        Date date = new Date(2020, 12, 31);
        Task task = new Task("Task 1");
        task.setDueDate(date);
        ToDoList toDoList = new ToDoList("List 1");

        toDoList.addTask(task);
        System.out.println(toDoList.display());

        toDoList.getTask("Task 1").completeTask();
        System.out.println(toDoList.display());
    }
}


