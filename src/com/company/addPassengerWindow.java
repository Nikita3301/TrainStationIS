package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class addPassengerWindow {
    private JCheckBox benefits;
    private JTextField fullName;
    private JTextField passportNumber;
    private JTextField phoneNumber;
    private JPanel panel;
    private JButton addPassengerButton;

    public addPassengerWindow(){
        JFrame frame = new JFrame("Add new passenger");
        frame.setResizable(false);
        frame.setLocation(new Point(600, 400));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);


        addPassengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");
                    PreparedStatement ps1 = con.prepareStatement("INSERT INTO riabov.passenger(passengerID,fullName,passportNumber,phoneNumber,benefits) Values(?,?,?,?,?);");
                    ps1.setString(1, null);
                    ps1.setString(2, fullName.getText());
                    ps1.setString(3, passportNumber.getText());
                    ps1.setString(4, phoneNumber.getText());
                    ps1.setBoolean(5, benefits.isSelected());
                    frame.dispose();
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Add is successful");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Add failed" + "\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new addPassengerWindow();
    }
}
