package ui;

import model.Date;
import model.Task;
import model.ToDoList;

// Not part of project implementation.
// Test console to test out console outputs.
public class TestUseMain {

    public static void main(String[] args) {
        Date date = new Date(2020, 12, 31);
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 1");
        task1.setDueDate(date);
        task2.setDueDate(date);
        ToDoList toDoList = new ToDoList("List 1");

        toDoList.addTask(task1);
        System.out.println(toDoList.display());

        System.out.println(task1);
        System.out.println(task2);

        System.out.println("Is task2 in the to-do list? " + toDoList.contains(task2));

        toDoList.getTask("Task 1").completeTask();
        System.out.println(toDoList.display());
    }
}


