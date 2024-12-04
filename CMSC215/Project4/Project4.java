package project4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Project 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel intervalPanel = new JPanel(new GridBagLayout());
        intervalPanel.setBorder(BorderFactory.createTitledBorder("Input Intervals"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Interval 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        intervalPanel.add(new JLabel("Time Block 1 - Start (e.g., 09:00 AM):"), gbc);

        gbc.gridx = 1;
        JTextField interval1StartField = new JTextField(10);
        intervalPanel.add(interval1StartField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        intervalPanel.add(new JLabel("Time Block 1 - End (e.g., 11:00 AM):"), gbc);

        gbc.gridx = 1;
        JTextField interval1EndField = new JTextField(10);
        intervalPanel.add(interval1EndField, gbc);

        // Interval 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        intervalPanel.add(new JLabel("Time Block 2 - Start (e.g., 10:00 AM):"), gbc);

        gbc.gridx = 1;
        JTextField interval2StartField = new JTextField(10);
        intervalPanel.add(interval2StartField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        intervalPanel.add(new JLabel("Time Block 2 - End (e.g., 12:00 PM):"), gbc);

        gbc.gridx = 1;
        JTextField interval2EndField = new JTextField(10);
        intervalPanel.add(interval2EndField, gbc);

        // Panel for Time Input
        JPanel timePanel = new JPanel(new GridBagLayout());
        timePanel.setBorder(BorderFactory.createTitledBorder("Input Time to Check"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        timePanel.add(new JLabel("Time to Check (e.g., 10:30 AM):"), gbc);

        gbc.gridx = 1;
        JTextField checkTimeField = new JTextField(10);
        timePanel.add(checkTimeField, gbc);

        // Panel for Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton compareIntervalsButton = new JButton("Compare Time Blocks");
        JButton checkTimeButton = new JButton("Check Time");

        buttonPanel.add(compareIntervalsButton);
        buttonPanel.add(checkTimeButton);

        frame.add(intervalPanel, BorderLayout.NORTH);
        frame.add(timePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        compareIntervalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Time start1 = new Time(interval1StartField.getText());
                    Time end1 = new Time(interval1EndField.getText());
                    Time start2 = new Time(interval2StartField.getText());
                    Time end2 = new Time(interval2EndField.getText());

                    Interval<Time> interval1 = new Interval<>(start1, end1);
                    Interval<Time> interval2 = new Interval<>(start2, end2);

                    if (interval1.subinterval(interval2)) {
                        JOptionPane.showMessageDialog(frame, "Time block 1 is during time block 2");
                    } else if (interval2.subinterval(interval1)) {
                        JOptionPane.showMessageDialog(frame, "Time block 2 is is during time block 1");
                    } else if (interval1.overlaps(interval2)) {
                        JOptionPane.showMessageDialog(frame, "The times overlap");
                    } else {
                        JOptionPane.showMessageDialog(frame, "The times do not overlap");
                    }
                } catch (InvalidTime invalidTime) {
                    JOptionPane.showMessageDialog(frame, "Invalid time: " + invalidTime.getMessage());
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(frame, "Invalid time block: Start time should be less than or equal to end time.");
                }
            }
        });

        checkTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Time checkTime = new Time(checkTimeField.getText());
                    Time start1 = new Time(interval1StartField.getText());
                    Time end1 = new Time(interval1EndField.getText());
                    Time start2 = new Time(interval2StartField.getText());
                    Time end2 = new Time(interval2EndField.getText());

                    Interval<Time> interval1 = new Interval<>(start1, end1);
                    Interval<Time> interval2 = new Interval<>(start2, end2);

                    boolean inInterval1 = interval1.within(checkTime);
                    boolean inInterval2 = interval2.within(checkTime);

                    if (inInterval1 && inInterval2) {
                        JOptionPane.showMessageDialog(frame, "Both time blocks contain " + checkTime);
                    } else if (inInterval1) {
                        JOptionPane.showMessageDialog(frame, "Only time block 1 contains the time " + checkTime);
                    } else if (inInterval2) {
                        JOptionPane.showMessageDialog(frame, "Only time block 2 contains the time " + checkTime);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Neither time block contains " + checkTime);
                    }
                } catch (InvalidTime invalidTime) {
                    JOptionPane.showMessageDialog(frame, "Invalid time " + invalidTime.getMessage());
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(frame, "Invalid time block: Start time should be less than or equal to end time.");
                }
            }
        });
    }
}
