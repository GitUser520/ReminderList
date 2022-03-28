package ui;

import exception.NotInRangeException;
import model.Date;
import model.Task;
import model.ToDoList;
import model.ToDoListCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Graphical user interface for a to-do list.
public class ToDoListGUI implements ActionListener, ListSelectionListener {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int TEXT_HEIGHT = 20;

    private ToDoListCollection collection;  // later change to ToDoListCollection for multiple to-do lists
    private ToDoList currentToDoList;       // currently viewed to-do list
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private JFrame frame;
    // Task manager fields
    private JLabel taskAdderLabel;
    private JTextField taskAdder;
    private JTextArea taskDisplay;
    private JButton removeTaskButton;
    private JButton editNameButton;
    private JButton editDateButton;
    private JButton markAsCompleteButton;
    private ImageIcon completeIcon;
    // List fields
    private JList<Task> currentJList;
    private JList<ToDoList> collectionJList;
    private JScrollPane currentListScroller;
    private JScrollPane collectionScroller;
    // Save/load fields
    private JButton saveButton;
    private JFileChooser saveFileChooser;
    private JButton loadButton;
    private JFileChooser loadFileChooser;
    // To-Do list management fields
    private JButton addToDoListButton;
    private JButton deleteToDoListButton;
    private JButton renameToDoListButton;


    // EFFECTS: constructor for to-do list GUI
    public ToDoListGUI() {
        initializeToDoList();
        initializeGUI();
    }

    // MODIFIES: this
    // EFFECTS: initializes the to-do list objects
    private void initializeToDoList() {
        collection = new ToDoListCollection();
        currentToDoList = new ToDoList("untitled");
        jsonReader = new JsonReader();
        jsonWriter = new JsonWriter();

        collection.addToDoList(currentToDoList);
    }

    // MODIFIES: this
    // EFFECTS: initializes the GUI
    private void initializeGUI() {
        frame = new JFrame("To-Do List App");
        taskAdderLabel = new JLabel("Add a task here:");
        taskAdder = new JTextField();
        taskDisplay = new JTextArea();
        removeTaskButton = new JButton("Remove");
        editNameButton = new JButton("Name");
        editDateButton = new JButton("Date");
        markAsCompleteButton = new JButton("Complete");
        completeIcon = new ImageIcon("./data./tobs.jpg");

        initializeGUI2();
    }

    // MODIFIES: this
    // EFFECTS: continues initializing the GUI
    private void initializeGUI2() {
        currentJList = new JList<>(new AbstractListModel<Task>() {
            // Anonymous class corresponding to the JList.
            // Reduces coupling between the To-Do List object and the AbstractListModel
            @Override
            public int getSize() {
                return currentToDoList.size();
            }

            @Override
            public Task getElementAt(int index) {
                return currentToDoList.getTask(index);
            }
        });
        collectionJList = new JList<>(new AbstractListModel<ToDoList>() {
            @Override
            public int getSize() {
                return collection.size();
            }

            @Override
            public ToDoList getElementAt(int index) {
                return collection.getToDoList(index);
            }
        });
        currentListScroller = new JScrollPane(currentJList);
        collectionScroller = new JScrollPane(collectionJList);

        initializeGUI3();
    }

    // MODIFIES: this
    // EFFECTS: continues initializing the GUI
    private void initializeGUI3() {
        saveButton = new JButton("Save");
        saveFileChooser = new JFileChooser();
        loadButton = new JButton("Load");
        loadFileChooser = new JFileChooser();

        addToDoListButton = new JButton("Add");
        deleteToDoListButton = new JButton("Delete");
        renameToDoListButton = new JButton("Rename");

        setUpGUI();
    }

    // MODIFIES: this
    // EFFECTS: sets up the GUI
    private void setUpGUI() {
        addComponentsToFrame();
        setUpComponents();

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: adds the ui components to the frame
    private void addComponentsToFrame() {
        frame.add(taskAdderLabel);
        frame.add(taskAdder);
        frame.add(taskDisplay);
        frame.add(removeTaskButton);
        frame.add(editNameButton);
        frame.add(editDateButton);
        frame.add(markAsCompleteButton);
        frame.add(currentListScroller);
        frame.add(collectionScroller);
        frame.add(saveButton);
        frame.add(saveFileChooser);
        frame.add(loadButton);
        frame.add(loadFileChooser);
        frame.add(addToDoListButton);
        frame.add(deleteToDoListButton);
        frame.add(renameToDoListButton);
    }

    // MODIFIES: this
    // EFFECTS: sets up the GUI components
    private void setUpComponents() {
        setUpTaskAdder();
        setUpJListComponents();
        setUpSaveLoadComponents();
        setUpListEditor();
    }

    // MODIFIES: this
    // EFFECTS: sets up the list editor components
    private void setUpListEditor() {
        addToDoListButton.setBounds(50, 250, 100, TEXT_HEIGHT);
        addToDoListButton.setActionCommand("add to-do list");
        addToDoListButton.addActionListener(this);

        deleteToDoListButton.setBounds(50, 300, 100, TEXT_HEIGHT);
        deleteToDoListButton.setActionCommand(("delete to-do list"));
        deleteToDoListButton.addActionListener(this);

        renameToDoListButton.setBounds(50, 350, 100, TEXT_HEIGHT);
        renameToDoListButton.setActionCommand(("rename to-do list"));
        renameToDoListButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets up the save/load components
    private void setUpSaveLoadComponents() {
        saveButton.setBounds(50, 150, 100, TEXT_HEIGHT);
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        loadButton.setBounds(50, 200, 100, TEXT_HEIGHT);
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        saveFileChooser.addActionListener(this);
        loadFileChooser.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets up the JList components
    private void setUpJListComponents() {
        currentJList.setLayoutOrientation(JList.VERTICAL);
        currentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        currentJList.addListSelectionListener(this);

        collectionJList.setLayoutOrientation(JList.VERTICAL);
        collectionJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        collectionJList.addListSelectionListener(this);

        currentListScroller.setBounds(200, 100, 400, 400);

        collectionScroller.setBounds(50, 50, 100, 3 * TEXT_HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: sets up the task adder components
    private void setUpTaskAdder() {
        taskAdderLabel.setBounds(300, 20, 200, TEXT_HEIGHT);

        taskAdder.setBounds(300, 20 + TEXT_HEIGHT, 200, TEXT_HEIGHT);
        taskAdder.setActionCommand("add task");
        taskAdder.addActionListener(this);

        taskDisplay.setBounds(625, 300, 150, 8 * TEXT_HEIGHT);
        taskDisplay.setEditable(false);

        removeTaskButton.setBounds(650, 100, 100, TEXT_HEIGHT);
        removeTaskButton.setActionCommand("remove task");
        removeTaskButton.addActionListener(this);

        editNameButton.setBounds(650, 150, 100, TEXT_HEIGHT);
        editNameButton.setActionCommand("edit task name");
        editNameButton.addActionListener(this);

        editDateButton.setBounds(650, 200, 100, TEXT_HEIGHT);
        editDateButton.setActionCommand("edit task date");
        editDateButton.addActionListener(this);

        markAsCompleteButton.setBounds(650, 250, 100, TEXT_HEIGHT);
        markAsCompleteButton.setActionCommand("mark as complete");
        markAsCompleteButton.addActionListener(this);
    }

    // EFFECTS: tracks and processes action events
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add task":
                addTask();
                break;
            case "remove task":
                removeTask();
                break;
            case "edit task name":
                editTaskName();
                break;
            case "edit task date":
                editTaskDate();
                break;
            case "mark as complete":
                markTaskAsComplete();
                break;
            default:
                actionPerformed2(e);
        }
    }

    // EFFECTS: continues tracking and processing action events
    private void actionPerformed2(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                saveCollection();
                break;
            case "load":
                loadCollection();
                break;
            case "add to-do list":
                addToDoList();
                break;
            case "delete to-do list":
                deleteToDoList();
                break;
            case "rename to-do list":
                renameToDoList();
                break;
            case JFileChooser.APPROVE_SELECTION:
                break;
            case JFileChooser.CANCEL_SELECTION:
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Action Error.");
        }
    }

    // MODIFIES: this
    // EFFECTS: marks currently selected task as complete
    private void markTaskAsComplete() {
        int selectedIndex = currentJList.getSelectedIndex();
        if (selectedIndex != -1) {
            currentToDoList.getTask(selectedIndex).completeTask();
            JOptionPane.showMessageDialog(frame, null, "Hooray!",
                    JOptionPane.INFORMATION_MESSAGE, completeIcon);
        }
        displayTask();
    }

    // MODIFIES: this
    // EFFECTS: asks a user for input to create a date, shows pop-up if fails
    private void editTaskDate() {
        int selectedIndex = currentJList.getSelectedIndex();
        if (selectedIndex != -1) {
            String year = JOptionPane.showInputDialog(frame, "Please enter new year for task");
            String month = JOptionPane.showInputDialog(frame, "Please enter new month for task");
            String day = JOptionPane.showInputDialog(frame, "Please enter new day for task");
            try {
                int yearInt = Integer.parseInt(year);
                int monthInt = Integer.parseInt(month);
                checkIntegerInRange(monthInt, 1, 12);
                int dayInt = Integer.parseInt(day);
                checkIntegerInRange(monthInt, 1, maxDayInMonth(yearInt, monthInt));
                currentToDoList.getTask(selectedIndex).setDueDate(new Date(yearInt, monthInt, dayInt));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Failed to create date.");
            }
        }
        displayTask();
    }

    // EFFECTS: throws NotInRangeException if value is not within [min,max]
    private void checkIntegerInRange(int value, int min, int max) throws NotInRangeException {
        if ((value < min) || (value > max)) {
            throw new NotInRangeException();
        }
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

    // MODIFIES: this
    // EFFECTS: edits the name of the currently selected task
    private void editTaskName() {
        int selectedIndex = currentJList.getSelectedIndex();
        if (selectedIndex != -1) {
            String input = JOptionPane.showInputDialog(frame, "Please enter new name for task");
            if (input != null) {
                currentToDoList.getTask(selectedIndex).setName(input);
            }
        }
        displayTask();
    }

    // MODIFIES: this
    // EFFECTS: removes the currently selected task
    private void removeTask() {
        int selectedIndex = currentJList.getSelectedIndex();
        if (selectedIndex != -1) {
            currentToDoList.removeTask(selectedIndex);
        }
        currentJList.updateUI();
    }

    // MODIFIES: this
    // EFFECTS: renames the current to-do list
    private void renameToDoList() {
        String input = JOptionPane.showInputDialog(frame,
                "Please input the new name of this to-do list");
        if (input != null) {
            currentToDoList.setName(input);
        }
        collectionJList.updateUI();
    }

    // MODIFIES: this
    // EFFECTS: creates pop-up and adds an empty to-do list with given name to list
    private void addToDoList() {
        String input = JOptionPane.showInputDialog(frame,
                "Please input the name of the new to-do list");
        if (input != null) {
            collection.addToDoList(new ToDoList(input));
        }
        collectionJList.updateUI();
    }

    // MODIFIES: this
    // EFFECTS: creates pop-up and deletes the current to-do list if user clicks yes
    private void deleteToDoList() {
        int selectValue = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to delete \"" + currentToDoList.getName() + "\" ?");
        if (selectValue == JOptionPane.YES_OPTION) {
            collection.removeToDoList(currentToDoList);
            collectionJList.updateUI();
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the collection to specified location
    private void saveCollection() {
        int selectValue = saveFileChooser.showSaveDialog(frame);
        if (selectValue == JFileChooser.APPROVE_OPTION) {
            String destination = saveFileChooser.getSelectedFile().getPath() + ".json";
            jsonWriter.setDestination(destination);
            try {
                jsonWriter.open();
                jsonWriter.write(collection);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "Error when saving file.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the collection from specified file
    private void loadCollection() {
        int selectValue = loadFileChooser.showOpenDialog(frame);
        if (selectValue == JFileChooser.APPROVE_OPTION) {
            File file = loadFileChooser.getSelectedFile();
            jsonReader.setSource(file.getPath());
            try {
                collection = jsonReader.read();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error when reading file.");
            }
        }
        collectionJList.updateUI();
        currentJList.updateUI();
    }

    // MODIFIES: this
    // EFFECTS: adds a task to the to-do list and updates the GUI
    private void addTask() {
        Task newTask = new Task(taskAdder.getText());
        currentToDoList.addTask(newTask);

        taskAdder.setText("");
        currentJList.updateUI();
    }

    // EFFECTS: processes selection events in the lists
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (e.getSource() == currentJList) {
                displayTask();
            } else if (e.getSource() == collectionJList) {
                changeToDoList();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the status of the currently selected task
    private void displayTask() {
        int selectedIndex = currentJList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskDisplay.setText(currentToDoList.getTask(selectedIndex).displayTask());
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the to-do list to the selected to-do list
    private void changeToDoList() {
        int selectedIndex = collectionJList.getSelectedIndex();
        if (selectedIndex != -1) {
            currentToDoList = collection.getToDoList(selectedIndex);
        }
        currentJList.updateUI();
    }
}
