# To-Do List

## Project Proposal

The proposed project is a personalized to-do list that allows the user to make tasks, keep track of tasks,
and remove tasks when they have completed the task. It will **not** make external calls, so users can limit 
all information to their own computers.

Users will be:
- public

I am interested in this project because I think that a local to-do list software with only local storage is
a step forward from externally hosted storage systems, and the privacy issues surrounding them. With local
storage, personal information is in the hands of the user.

## User Stories

- As a user, I want to be able to see all tasks in my to-do list.
- As a user, I want to be able to add a task to my to-do list.
- As a user, I want to be able to delete tasks from my to-do list.
- As a user, I want to be able to mark a task as complete.
- As a user, I want to be able to modify tasks in my to-do list.
- As a user, I want to be able to clear all tasks from my to-do list.
- As a user, I want to be able to manage my to-do lists.

### Data persistence

- As a user, I want to be able to save the current to-do lists as a file.
- As a user, I want to be able to load previously-saved to-do lists from a file.

## Phase 4: Task 2

I have made my Date class robust. It now only accepts valid dates (i.e. month and day are valid)
rather than any month or day. If a call tries to construct a date with an invalid month or day, 
it will throw an exception. Additionally, trying to set the date as an invalid day will also
throw exceptions.

All of the methods in the Date class have a robust design. The specific methods that throw
exceptions are:

- Date(int year, int month, int day)
- setMonth(int month)
- setDay(int day)
- setDate(int year, int month, int day)

## Phase 4: Task 3

Reflections:

- If I had more time to work on this project, I would have refactored the ToDoListGUI class, by separating the 
GUI into different sections. Currently, it only has one class that holds all of the items. By separating it 
into different sections that extend different frames, I believe that I can better segment the different panels
of the GUI, and increase the cohesion of the class. 
- Additionally, I might remove some buttons and add pop-up menus instead. I think that would be more intuitive,
especially for the renaming feature of the project.
- I have also considered swapping the collection list for a combo box instead. That may be more suitable, since
you can only select a single to-do list in the collection.
- Finally, I believe that I should implement by Task class' equal method with an @Override of the default equals
method instead of overloading the method as it is currently done.


 