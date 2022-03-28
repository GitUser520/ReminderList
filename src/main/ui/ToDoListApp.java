package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Console for the to-do list application.
public class ToDoListApp {
    private Scanner scanner;
    private ToDoListCollection collection;  // later change to ToDoListCollection for multiple to-do lists
    private ToDoList currentToDoList;       // currently viewed to-do list
    private boolean runStatus;              // status for main menu
    private boolean toDoListLoop;           // status for to-do list menu
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: Constructs a ToDoListApp to run the program
    public ToDoListApp() {
        runToDoList();
    }

    // MODIFIES: this
    // EFFECTS: runs the to-do list application
    private void runToDoList() {
        scanner = new Scanner(System.in);
        collection = new ToDoListCollection();
        currentToDoList = null;
        runStatus = true;
        toDoListLoop = false;
        jsonReader = new JsonReader();
        jsonWriter = new JsonWriter();

        showMainMenu();

        while (runStatus) {
            String input = scanner.nextLine();
            processMainCommandOne(input.toLowerCase());
        }

        scanner.close();
        System.out.println("Ended to-do list program.");
    }

    // EFFECTS: print a menu of options to the console
    private void showMainMenu() {
        System.out.println("Type one of the following commands:");
        System.out.println("display   \t- display all tasks in the to-do list collection");
        System.out.println("clear     \t- clear the to-do list collection");
        System.out.println("add       \t- add a to-do list to the to-do list collection");
        System.out.println("delete    \t- delete a to-do list from the to-do list collection");
        System.out.println("select     \t- select and enter a to-do list in the to-do list collection");
        System.out.println("save      \t- save the current collection to a file");
        System.out.println("load      \t- load a collection from a file");
        System.out.println("help      \t- print out the list of commands");
        System.out.println("quit      \t- exit the to-do list application");
    }

    // EFFECTS: process the user command from main menu (part 1)
    private void processMainCommandOne(String input) {
        switch (input) {
            case "display":
                displayCollection();
                break;
            case "clear":
                clearCollection();
                break;
            case "add":
                addToDoList();
                break;
            case "delete":
                deleteToDoList();
                break;
            case "select":
                modifyToDoList();
                break;
            default:
                processMainCommandTwo(input);
        }
    }

    // EFFECTS: process the user command from main menu (part 2)
    private void processMainCommandTwo(String input) {
        switch (input) {
            case "save":
                saveToDoList();
                break;
            case "load":
                loadToDoList();
                break;
            case "help":
                showMainMenu();
                break;
            case "quit":
                runStatus = false;
                break;
        }
    }

    // EFFECTS: displays all to-do lists in the collection
    private void displayCollection() {
        System.out.println("To-do lists:");
        System.out.println(collection.displayAll());
    }

    // MODIFIES: this
    // EFFECTS: clears all the to-do lists in the collection
    private void clearCollection() {
        collection.clearAll();
        System.out.println("Collection has been cleared.");
    }

    // MODIFIES: this
    // EFFECTS: adds a user generated to-do list to the collection
    private void addToDoList() {
        System.out.println("Type in the name of the new to-do list:");
        String name = scanner.nextLine();
        collection.addToDoList(new ToDoList(name));
        System.out.println(name + " has been added.");
    }

    // MODIFIES: this
    // EFFECTS: deletes the first to-do list with name specified by the user
    private void deleteToDoList() {
        System.out.println("Type in the name of the to-do list to delete:");
        String name = scanner.nextLine();
        collection.removeToDoList(name);
        System.out.println(name + "has been removed.");
    }

    // MODIFIES: this
    // EFFECTS: sets current to-do list and prompts user for commands to
    //          modify the current to-do list
    private void modifyToDoList() {
        currentToDoList = toDoListPrompt();
        toDoListLoop = true;
        showToDoListMenu();

        while (toDoListLoop) {
            String input = scanner.nextLine();
            processToDoListCommand(input.toLowerCase());
        }

        System.out.println("Returned to main menu \n");
    }

    // EFFECTS: returns the to-do list specified by the user
    private ToDoList toDoListPrompt() {
        ToDoList currentList = null;
        System.out.println("Please type the name of the to-do list to enter:");
        while (currentList == null) {
            String name = scanner.nextLine();
            currentList = collection.getToDoList(name);
        }
        return currentList;
    }

    // MODIFIES: this
    // EFFECTS: saves the to-do list to file specified by user
    private void saveToDoList() {
        System.out.println("Please type in the name of the file:");
        String destination = "./data/" + scanner.nextLine() + ".json";
        jsonWriter.setDestination(destination);

        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();
            System.out.println("Save successful.");
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to specified file: " + destination);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the to-do list from file specified by user
    private void loadToDoList() {
        System.out.println("Please type in the name of the file:");
        String destination = "./data/" + scanner.nextLine() + ".json";
        jsonReader.setSource(destination);

        try {
            collection = jsonReader.read();
            System.out.println("Load successful.");
        } catch (IOException e) {
            System.out.println("Error reading from specified file: " + destination);
        }
    }

    // EFFECTS: prints a menu of options for a to-do list
    private void showToDoListMenu() {
        System.out.println("Type one of the following commands:");
        System.out.println("display   \t- display all tasks in the to-do list");
        System.out.println("clear     \t- clear the to-do list");
        System.out.println("add       \t- add a task to the to-do list");
        System.out.println("delete    \t- delete a task from the to-do list");
        System.out.println("edit      \t- edit a task in the to-do list");
        System.out.println("help      \t- print out the list of commands");
        System.out.println("back      \t- return to main menu");
    }

    // EFFECTS: process the user command for to-do list
    private void processToDoListCommand(String input) {
        switch (input) {
            case "display":
                displayAll();
                break;
            case "clear":
                clearToDoList();
                break;
            case "add":
                addTask();
                break;
            case "delete":
                deleteTask();
                break;
            case "edit":
                editTask();
                break;
            case "help":
                showToDoListMenu();
                break;
            case "back":
                toDoListLoop = false;
                break;
        }
    }

    // EFFECTS: display all tasks in the to-do list
    private void displayAll() {
        System.out.println("List of tasks in to-do list:");
        System.out.println(currentToDoList.display());
    }

    // MODIFIES: this
    // EFFECTS: adds the specified task to the to-do list
    private void addTask() {
        System.out.println("Please specify the name of the task to add:");
        String taskName = scanner.nextLine();

        currentToDoList.addTask(new Task(taskName));
        System.out.println("The task has been added.");
    }

    // MODIFIES: this
    // EFFECTS: deletes the chosen task
    private void deleteTask() {
        System.out.println("Please specify the name of the task to delete:");
        String taskName = returnValidTask();

        currentToDoList.removeTask(taskName);
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
        currentToDoList.getTask(taskName).setName(nameTask);
        System.out.println("The task's new name is: " + nameTask);
    }

    // MODIFIES: this
    // EFFECTS: marks the chosen task as complete
    private void markTaskComplete(String taskName) {
        currentToDoList.getTask(taskName).completeTask();
        System.out.println(taskName + " has been marked as complete.");
    }

    // MODIFIES: this
    // EFFECTS: change the due date of the task
    private void changeTaskDueDate(String taskName) {
        System.out.println("Please specify the task's new due date:");
        Date date = datePrompt();
        currentToDoList.getTask(taskName).setDueDate(date);
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

        Date date = null;
        try {
            date = new Date(year, month, day);
        } catch (Exception e) {
            e.getStackTrace();
        }

        return date;
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
        currentToDoList.clear();
    }

    // EFFECTS: returns a valid task name from user input
    private String returnValidTask() {
        String taskName = scanner.nextLine();
        while (currentToDoList.getTask(taskName) == null) {
            System.out.println("The task name you have specified is not valid. Please try again:");
            taskName = scanner.nextLine();
        }
        return taskName;
    }

}
