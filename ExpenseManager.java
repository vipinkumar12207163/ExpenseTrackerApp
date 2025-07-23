package tracker;

import java.io.*;
import java.util.*;

public class ExpenseManager {
    private static final String FILE_NAME = "expenses.txt";

    public static void saveExpense(Expense expense) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(expense.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving expense: " + e.getMessage());
        }
    }

    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return expenses;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 4) {
                    Expense e = new Expense(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                    expenses.add(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
}
