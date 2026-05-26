package com.bmi;
import com.bmi.model.person;
import com.bmi.model.bmirecord;
import com.bmi.service.bmiservice;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class mains extends JFrame {
    private final bmiservice service;
    
    // GUI components
    private JTextField nameField, ageField, heightField, weightField;
    private JTextArea resultArea;
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private JButton calculateBtn, clearBtn;
    
    public mains() {
        service = new bmiservice();
        initUI();
    }
    
    private void initUI() {
        setTitle("BMI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Input Panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Result Panel
        JPanel resultPanel = createResultPanel();
        add(resultPanel, BorderLayout.CENTER);
        
        // History Panel
        JPanel historyPanel = createHistoryPanel();
        add(historyPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Labels and fields
        String[] labels = {"Name:", "Age:", "Height (m):", "Weight (kg):"};
        JTextField[] fields = new JTextField[4];
        nameField = new JTextField(15);
        ageField = new JTextField(15);
        heightField = new JTextField(15);
        weightField = new JTextField(15);
        fields[0] = nameField;
        fields[1] = ageField;
        fields[2] = heightField;
        fields[3] = weightField;
        
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        calculateBtn = new JButton("Calculate BMI");
        clearBtn = new JButton("Clear");
        calculateBtn.addActionListener(this::calculateBMI);
        clearBtn.addActionListener(e -> clearFields());
        buttonPanel.add(calculateBtn);
        buttonPanel.add(clearBtn);
        
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(0, 150));
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBackground(new Color(240, 248, 255));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(0, 250));
        
        JLabel historyLabel = new JLabel("BMI History");
        historyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(historyLabel, BorderLayout.NORTH);
        
        String[] columns = {"Date", "Name", "BMI", "Category"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        historyTable = new JTable(tableModel);
        historyTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void calculateBMI(ActionEvent e) {
        // Validate input
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            showError("Please enter a name.");
            return;
        }
        
        int age;
        double height, weight;
        try {
            age = Integer.parseInt(ageField.getText().trim());
            if (age < 1 || age > 120) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            showError("Age must be a number between 1 and 120.");
            return;
        }
        
        try {
            height = Double.parseDouble(heightField.getText().trim());
            if (height < 0.5 || height > 2.5) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            showError("Height must be a number between 0.5 and 2.5 meters.");
            return;
        }
        
        try {
            weight = Double.parseDouble(weightField.getText().trim());
            if (weight < 10 || weight > 300) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            showError("Weight must be a number between 10 and 300 kg.");
            return;
        }
        
        // Create person and calculate
        person person = new person(name, age, height, weight);
        service.addperson(person);
        bmirecord record = service.calculateAndStoreBMI(person);
        
        // Show result
        resultArea.setText(String.format(
            "person: %s (Age: %d)\n" +
            "Height: %.2f m | Weight: %.1f kg\n" +
            "BMI: %.2f\n" +
            "Category: %s\n" +
            "Advice: %s\n" +
            "Record ID: %s",
            person.getName(), person.getAge(),
            person.getHeight(), person.getWeight(),
            record.getBmi(), record.getCategory(),
            person.getHealthAdvice(), record.getId()
        ));
        
        // Add to history table
        tableModel.addRow(new Object[]{
            record.getFormattedDate(),
            record.getpersonName(),
            String.format("%.2f", record.getBmi()),
            record.getCategory()
        });
        
        // Clear input fields (keep name for convenience)
        ageField.setText("");
        heightField.setText("");
        weightField.setText("");
        nameField.requestFocus();
    }
    
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        heightField.setText("");
        weightField.setText("");
        resultArea.setText("");
        nameField.requestFocus();
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(mains::new);
    }
}