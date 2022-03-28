package ui;

import model.*;

import java.util.Scanner;

// Console for the to-do list application.
public class ToDoListApp {
    private Scanner scanner;
    private ToDoList toDoList;  // later change to ToDoListCollection for multiple to-do lists
    private boolean runStatus;

    // EFFECTS: Constructs a ToDoListApp to run the program
    public ToDoListApp() {
        runToDoList();
    }

    // MODIFIES: this
    // EFFECTS: runs the to-do list application
    private void runToDoList() {
        scanner = new Scanner(System.in);
        toDoList = new ToDoList("To-Do List");
        runStatus = true;

        showMainMenu();

        while (runStatus) {
            String input = scanner.nextLine();
            processCommand(input.toLowerCase());
        }

        scanner.close();
        System.out.println("Ended to-do list program.");
    }

    // EFFECTS: print a menu of options to the console
    private void showMainMenu() {
        System.out.println("Type one of the following commands:");
        System.out.println("display   \t- display all tasks in the to-do list");
        System.out.println("clear     \t- clear the to-do list");
        System.out.println("add       \t- add a task to the to-do list");
        System.out.println("delete    \t- delete a task from the to-do list");
        System.out.println("edit      \t- edit a task in the to-do list");
        System.out.println("help      \t- print out the list of commands");
        System.out.println("quit      \t- exit the to-do list application");
    }

    // EFFECTS: process the user command
    private void processCommand(String input) {
        if (input.equals("display")) {
            displayAll();
        } else if (input.equals("clear")) {
            clearToDoList();
        } else if (input.equals("add")) {
            addTask();
        } else if (input.equals("delete")) {
            deleteTask();
        } else if (input.equals("edit")) {
            editTask();
        } else if (input.equals("help")) {
            showMainMenu();
        } else if (input.equals("quit")) {
            runStatus = false;
        }
    }

    // EFFECTS: display all tasks in the to-do list
    private void displayAll() {
        System.out.println("List of tasks in to-do list:");
        System.out.println(toDoList.display());
    }

    // MODIFIES: this
    // EFFECTS: adds the specified task to the to-do list
    private void addTask() {
        System.out.println("Please specify the name of the task to add:");
        String taskName = scanner.nextLine();

        toDoList.addTask(new Task(taskName));
        System.out.println("The task has been added.");
    }

    // MODIFIES: this
    // EFFECTS: deletes the chosen task
    private void deleteTask() {
        System.out.println("Please specify the name of the task to delete:");
        String taskName = returnValidTask();

        toDoList.removeTask(taskName);
        System.out.println("The task has been removed.");
    }

    // MODIFIES: this
    // EFFECTS: edit the selected task
    private void editTask() {
        boolean stop = false;

        System.out.println("Please specify the name of the task to edit:");
        String taskName = returnValidTask();

        showEditMenu();
        String command = scanner.nextLine();
        command = command.toLowerCase();

        while (!stop) {
            if (command.equals("name")) {
                changeTaskName(taskName);
                stop = true;
            } else if (command.equals("complete")) {
                markTaskComplete(taskName);
                stop = true;
            } else if (command.equals("due date")) {
                changeTaskDueDate(taskName);
                stop = true;
            }
        }
    }

    // EFFECTS: display menu of commands for editing tasks
    private void showEditMenu() {
        System.out.println("Please specify how to modify this task:");
        System.out.println("name     \t- modify the name of the task");
        System.out.println("complete \t- mark the task as complete");
        System.out.println("due date \t- modify the due date of the task");
    }

    // MODIFIES: this
    // EFFECTS: change the name of the task
    private void changeTaskName(String taskName) {
        System.out.println("Please specify the task's new name:");
        String nameTask = scanner.nextLine();
        toDoList.getTask(taskName).setName(nameTask);
        System.out.println("The task's new name is: " + nameTask);
    }

    // MODIFIES: this
    // EFFECTS: marks the chosen task as complete
    private void markTaskComplete(String taskName) {
        toDoList.getTask(taskName).completeTask();
        System.out.println(taskName + " has been marked as complete.");
    }

    // MODIFIES: this
    // EFFECTS: change the due date of the task
    private void changeTaskDueDate(String taskName) {
        System.out.println("Please specify the task's new due date:");
        Date date = datePrompt();
        toDoList.getTask(taskName).setDueDate(date);
        System.out.println("The new date has been set to " + date.toString());
    }

    // EFFECTS: returns the date given from user input
    private Date datePrompt() {
        int year;
        int month;
        int day;

        System.out.println("Please input a year:");
        year = parseIntFromString();
        System.out.println("Please input a month (from 1 to 12):");
        month = parseIntFromString(1,12);
        System.out.println("Please input a valid day in the month:");
        day = parseIntFromString(1,maxDayInMonth(year,month));

        return new Date(year, month, day);
    }

    // REQUIRES: month is an int between 1 and 12
    // EFFECTS: returns the max valid day in the month in the year
    private int maxDayInMonth(int year, int month) {
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
                || (month == 8) || (month == 10) || (month == 12)) {
            return 31;
        } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            return 30;
        } else if ((month == 2) && (year % 4 == 0)) {
            return 29;
        }
        return 28;
    }

    // EFFECTS: returns user inputted integer between min and max
    private int parseIntFromString(int min, int max) {
        boolean stop = false;
        String input = scanner.nextLine();
        int num = Integer.MIN_VALUE;

        while (!stop) {
            try {
                if ((min <= Integer.parseInt(input)) && (Integer.parseInt(input) <= max)) {
                    num = Integer.parseInt(input);
                    stop = true;

                } else {
                    System.out.println("Please input an integer between " + min + " and " + max);
                    input = scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Not a valid integer.");
                input = scanner.nextLine();
            }
        }
        return num;
    }

    // EFFECTS: returns a user-inputted integer
    private int parseIntFromString() {
        boolean stop = false;
        String input = scanner.nextLine();
        int num = Integer.MIN_VALUE;

        while (!stop) {
            try {
                num = Integer.parseInt(input);
                stop = true;
            } catch (Exception e) {
                System.out.println("Not a valid integer.");
                input = scanner.nextLine();
            }
        }
        return num;
    }

    // MODIFIES: this
    // EFFECTS: clears the to-do list
    private void clearToDoList() {
        toDoList.clear();
    }

    // EFFECTS: returns a valid task name from user input
    private String returnValidTask() {
        String taskName = scanner.nextLine();
        while (toDoList.getTask(taskName) == null) {
            System.out.println("The task name you have specified is not valid. Please try again:");
            taskName = scanner.nextLine();
        }
        return taskName;
    }

}
