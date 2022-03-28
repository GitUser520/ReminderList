package ui;

import model.Date;
import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Not part of project implementation.
// Test console to test out console outputs.
public class TestUseMain extends Frame implements ActionListener {
    private JTextField textField;
    private JLabel label;
    private JButton button;

    public TestUseMain() {
        JFrame frame = new JFrame("Example");
        textField = new JTextField();
        textField.setBounds(50, 50, 150, 20);
        textField.setText("Welcome to JavaPoint");
        textField.addActionListener(this);
        button = new JButton("Click here");
        button.setBounds(50, 100, 95, 30);
        button.addActionListener(this);
        label = new JLabel("Hi!");
        label.setBounds(80, 200, 50, 30);


        frame.add(textField);
        frame.add(button);
        frame.add(label);
        frame.setSize(400,400);
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
}


