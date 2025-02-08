// view/FoodQualityGUI.java
package view;

import controller.FoodController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodQualityGUI extends JFrame {
    private JTextField idField;
    private JTextArea outputArea;

    //หน้า GUI
    public FoodQualityGUI() {
        setTitle("Food Quality Checker");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("รหัสอาหาร (6 หลัก):");
        label.setFont(thaiFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(label, gbc);

        idField = new JTextField(15);
        idField.setFont(thaiFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(idField, gbc);

        JButton checkButton = new JButton("ตรวจสอบคุณภาพอาหาร");
        checkButton.setFont(thaiFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(checkButton, gbc);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea(5, 30);
        outputArea.setFont(thaiFont);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodId = idField.getText().trim();
                String validation = FoodController.validateFoodId(foodId);
                
                if (!validation.equals("OK")) {
                    outputArea.setText(validation);
                    return;
                }
                
                String result = FoodController.getFoodStatus(foodId);
                outputArea.setText(result);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FoodQualityGUI().setVisible(true));
    }
}
