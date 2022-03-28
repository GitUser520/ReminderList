# Domain Analysis

## Models

We require at least three models: Task, ToDoList, and ToDoListCollection.

Task:
- abstraction for a task in the to-do list
- should have a name and completion status
- should have options reminder, due date
(*reminder feature looks too complicated for now, won't implement yet*)
- should be able to be modified
- should be able to mark the task as complete

ToDoList:
- abstraction for a list of tasks
- should have list that keeps track of tasks
- should have a name for the to-do list
- should have option to add a task to the to-do list
- should have option to select a task in the to-do list
- should be able to modify or remove the selected task 
- should be able to clear all tasks from the to-do list

ToDoListCollection:
- abstraction for a list of to-do lists
- should have a collection that keeps track of to-do lists
- should be able to add to-do lists to the collection
- should be able to remove to-do lists from the collection
- should allow us to access a to-do list that we want to view or modify


Additional models:

Date:
- abstraction for a date (year, month, day)
- returns a date on toString() in String format (ex: "Jan 2, 2021")

Reminder:
- abstraction for a reminder
- keeps track of reminder status