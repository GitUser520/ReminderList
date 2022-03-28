package ui;

import model.Date;
import model.Task;
import model.ToDoList;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Not part of project implementation.
// Test console to test out console outputs.
public class TestUseMain extends Frame implements ActionListener {
    private JTextField textField;
    private JLabel label;
    private JButton button;
    private List<String> list;
    private JComboBox<String> comboBox;

    public TestUseMain() {
        JFrame frame = new JFrame("Example");
        textField = new JTextField();
        textField.setBounds(50, 50, 150, 30);
        textField.setText("Hello world!!!!!!");
        textField.addActionListener(this);
        JList jlist = new JList(new AbstractListModel() {
            @Override
            public int getSize() {
                return list.size();
            }

            @Override
            public Object getElementAt(int index) {
                return list.get(index);
            }
        });
        frame.add(textField);
        frame.add(button);
        frame.add(label);
        frame.add(comboBox);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestUseMain();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textField) {
            textField.setText("Overwrote text");
            label.setText("Overwrote text");
        } else if (e.getSource() == button) {
            textField.setText("Reset text");
            label.setText("Label :D");
        }
    }

    // Code segments which I'd love to save :D
    /*
        // Wow, so this is how it's done. :DDDDDD
        comboBox = new JComboBox<>(new ComboBoxModel<String>() {
            private List<ListDataListener> observers = new ArrayList<>();
            private int index = 0;
            @Override
            public void setSelectedItem(Object anItem) {
                index = list.indexOf(anItem);
            }
            @Override
            public Object getSelectedItem() {
                return list.get(index);
            }
            @Override
            public int getSize() {
                return list.size();
            }
            @Override
            public String getElementAt(int index) {
                return list.get(index);
            }
            @Override
            public void addListDataListener(ListDataListener l) {
                observers.add(l);
            }
            @Override
            public void removeListDataListener(ListDataListener l) {
                observers.remove(l);
            }
        });
        comboBox.setBounds(200, 200, 100, 30);*/
}


