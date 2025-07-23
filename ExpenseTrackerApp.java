package tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import tracker.Expense;
import tracker.ExpenseManager;

public class ExpenseTrackerApp extends JFrame {
    private JTextField dateField, amountField, descField;
    private JComboBox<String> categoryBox;
    private JTextArea outputArea;

    public ExpenseTrackerApp() {
        setTitle("Personal Expense Tracker");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        dateField = new JTextField(10);
        categoryBox = new JComboBox<>(new String[]{"Food", "Travel", "Bills", "Shopping", "Others"});
        descField = new JTextField(10);
        amountField = new JTextField(10);
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);

        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View All");

        add(new JLabel("Date (yyyy-mm-dd):"));
        add(dateField);
        add(new JLabel("Category:"));
        add(categoryBox);
        add(new JLabel("Description:"));
        add(descField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(addBtn);
        add(viewBtn);
        add(new JScrollPane(outputArea));

        addBtn.addActionListener(e -> addExpense());
        viewBtn.addActionListener(e -> viewExpenses());
    }

    private void addExpense() {
        String date = dateField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();
        String desc = descField.getText().trim();
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount.");
            return;
        }

        Expense exp = new Expense(date, category, desc, amount);
        ExpenseManager.saveExpense(exp);
        JOptionPane.showMessageDialog(this, "Expense added successfully!");
        clearFields();
    }

    private void viewExpenses() {
        List<Expense> list = ExpenseManager.loadExpenses();
        outputArea.setText("");
        for (Expense e : list) {
            outputArea.append(e.toString() + "\n");
        }
    }

    private void clearFields() {
        dateField.setText("");
        descField.setText("");
        amountField.setText("");
        categoryBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTrackerApp().setVisible(true));
    }
}
