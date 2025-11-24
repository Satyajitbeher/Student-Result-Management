package ui;
import model.Student;
import manager.ResultManager;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
//import java.awt.event.*;

public class MainUI extends JFrame {
    private ResultManager rm = new ResultManager();
    private JTextField rollField, nameField, marksField;
    private JTable table;
    private DefaultTableModel model;

    public MainUI() {
        setTitle("Student Result Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Student"));

        inputPanel.add(new JLabel("Roll No:"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addBtn = new JButton("Add Student");
        inputPanel.add(addBtn);

        JButton searchBtn = new JButton("Search");
        inputPanel.add(searchBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new Object[]{"Roll", "Name", "Marks"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();

        // Button actions
        addBtn.addActionListener(e -> addStudent());
        searchBtn.addActionListener(e -> searchStudent());

        setVisible(true);
    }

    private void addStudent() {
        String roll = rollField.getText().trim();
        String name = nameField.getText().trim();
        String marksStr = marksField.getText().trim();

        if (roll.isEmpty() || name.isEmpty() || marksStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        int marks;
        try {
            marks = Integer.parseInt(marksStr);
            if (marks < 0 || marks > 100) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Marks must be 0-100");
            return;
        }

        Student s = new Student(roll, name, marks);
        rm.addStudent(s);
        JOptionPane.showMessageDialog(this, "Student added!");
        rollField.setText("");
        nameField.setText("");
        marksField.setText("");
        refreshTable();
    }

    private void searchStudent() {
        String roll = rollField.getText().trim();
        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Roll to search");
            return;
        }

        Student s = rm.search(roll);
        if (s != null) {
            JOptionPane.showMessageDialog(this, "Found: " + s.getName() + " | Marks: " + s.getMarks());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Student s : rm.getAllStudents()) {
            model.addRow(new Object[]{s.getRoll(), s.getName(), s.getMarks()});
        }
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
