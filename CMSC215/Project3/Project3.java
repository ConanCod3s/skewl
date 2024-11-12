package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

public class Project3 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Trip Cost Estimator");
        JPanel panel = new JPanel(new GridBagLayout());
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input Fields and Labels
        JTextField distanceField = new JTextField(10);
        JComboBox<String> distanceUnit = new JComboBox<>(new String[]{"Miles", "Kilometers"});

        JTextField gasCostField = new JTextField(10);
        JComboBox<String> gasCostUnit = new JComboBox<>(new String[]{"Dollars/Gallon", "Dollars/Liter"});

        JTextField mileageField = new JTextField(10);
        JComboBox<String> mileageUnit = new JComboBox<>(new String[]{"Miles/Gallon", "Kilometers/Liter"});

        JTextField hotelCostField = new JTextField(10);
        JTextField foodCostField = new JTextField(10);
        JTextField attractionCostField = new JTextField(10);
        JTextField daysField = new JTextField(10);

        JTextField resultField = new JTextField(10);
        resultField.setEditable(false);

        JButton calculateButton = new JButton("Calculate");

        // Distance
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Distance:"), gbc);
        gbc.gridx = 1;
        panel.add(distanceField, gbc);
        gbc.gridx = 2;
        panel.add(distanceUnit, gbc);

        // Gasoline Cost
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Gasoline Cost:"), gbc);
        gbc.gridx = 1;
        panel.add(gasCostField, gbc);
        gbc.gridx = 2;
        panel.add(gasCostUnit, gbc);

        // Gas Mileage
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Gas Mileage:"), gbc);
        gbc.gridx = 1;
        panel.add(mileageField, gbc);
        gbc.gridx = 2;
        panel.add(mileageUnit, gbc);

        // Number of Days
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Number Of Days:"), gbc);
        gbc.gridx = 1;
        panel.add(daysField, gbc);

        // Hotel Cost
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Hotel Cost:"), gbc);
        gbc.gridx = 1;
        panel.add(hotelCostField, gbc);

        // Food Cost
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Food Cost:"), gbc);
        gbc.gridx = 1;
        panel.add(foodCostField, gbc);

        // Attraction Cost
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Attractions:"), gbc);
        gbc.gridx = 1;
        panel.add(attractionCostField, gbc);

        // Total Trip Cost
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Total Trip Cost:"), gbc);
        gbc.gridx = 1;
        panel.add(resultField, gbc);

        // Calculate Button
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(calculateButton, gbc);

        // Update ComboBox options based on distance unit selection
        distanceUnit.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedUnit = (String) distanceUnit.getSelectedItem();
                    if (selectedUnit.equals("Miles")) {
                        gasCostUnit.setModel(new DefaultComboBoxModel<>(new String[]{"Dollars/Gallon"}));
                        mileageUnit.setModel(new DefaultComboBoxModel<>(new String[]{"Miles/Gallon"}));
                    } else if (selectedUnit.equals("Kilometers")) {
                        gasCostUnit.setModel(new DefaultComboBoxModel<>(new String[]{"Dollars/Liter"}));
                        mileageUnit.setModel(new DefaultComboBoxModel<>(new String[]{"Kilometers/Liter"}));
                    }
                }
            }
        });

        // Calculate Button ActionListener
        calculateButton.addActionListener(new CalculateHandler(
                distanceField, distanceUnit, gasCostField, gasCostUnit,
                mileageField, mileageUnit, hotelCostField, foodCostField,
                attractionCostField, daysField, resultField
        ));

        frame.setVisible(true);
    }

    private static class CalculateHandler implements ActionListener {
        private JTextField distanceField, gasCostField, mileageField, hotelCostField, foodCostField, attractionCostField, daysField, resultField;
        private JComboBox<String> distanceUnit, gasCostUnit, mileageUnit;

        public CalculateHandler(JTextField distanceField, JComboBox<String> distanceUnit, JTextField gasCostField,
                                JComboBox<String> gasCostUnit, JTextField mileageField, JComboBox<String> mileageUnit,
                                JTextField hotelCostField, JTextField foodCostField, JTextField attractionCostField,
                                JTextField daysField, JTextField resultField) {
            this.distanceField = distanceField;
            this.distanceUnit = distanceUnit;
            this.gasCostField = gasCostField;
            this.gasCostUnit = gasCostUnit;
            this.mileageField = mileageField;
            this.mileageUnit = mileageUnit;
            this.hotelCostField = hotelCostField;
            this.foodCostField = foodCostField;
            this.attractionCostField = attractionCostField;
            this.daysField = daysField;
            this.resultField = resultField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                double distance = Double.parseDouble(distanceField.getText());
                double gasCost = Double.parseDouble(gasCostField.getText());
                int mileage = Integer.parseInt(mileageField.getText());
                int days = Integer.parseInt(daysField.getText());
                double hotelCost = Double.parseDouble(hotelCostField.getText());
                double foodCost = Double.parseDouble(foodCostField.getText());
                int attractionCost = Integer.parseInt(attractionCostField.getText());

                if (distanceUnit.getSelectedItem().equals("Kilometers")) {
                    distance = distance * 0.621371;
                }
                if (gasCostUnit.getSelectedItem().equals("Dollars/Liter")) {
                    gasCost = gasCost * 3.78541;
                }
                if (mileageUnit.getSelectedItem().equals("Kilometers/Liter")) {
                    mileage = (int) (mileage * 2.35215);
                }

                TripCost tripCost = new TripCost(distance, gasCost, mileage, days, hotelCost, foodCost, attractionCost);

                NumberFormat formatter = NumberFormat.getNumberInstance();
                formatter.setMinimumFractionDigits(2);
                resultField.setText("$" + formatter.format(tripCost.calculateCost()));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numerical values for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
};