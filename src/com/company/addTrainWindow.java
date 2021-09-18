package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class addTrainWindow {
    private JPanel panel;
    private JTextField trainId1;
    private JTextField trainNumber1;
    private JTextField trainRouteId1;
    private JTextField trainClassification1;
    private JTextField companyId1;
    private JButton addTrainButton;

    public addTrainWindow() throws SQLException {
        JFrame frame = new JFrame("Add new train");
        frame.setResizable(false);
        frame.setLocation(new Point(600, 400));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT MAX(trainID) FROM riabov.train;");
        if (rs1.next()){
            int trainID = rs1.getInt(1);
            trainId1.setText(String.valueOf(trainID+1));
        }

        addTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps1 = con.prepareStatement("INSERT INTO riabov.train(trainID, trainNumber, trainRouteID, trainClassification, companyID) Values(?,?,?,?,?);");
                    ps1.setString(1, null);
                    ps1.setInt(2, Integer.parseInt(trainNumber1.getText()));
                    ps1.setInt(3, Integer.parseInt(trainRouteId1.getText()));
                    ps1.setString(4, trainClassification1.getText());
                    ps1.setInt(5, Integer.parseInt(companyId1.getText()));
                    frame.dispose();
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Add is successful");
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Add failed" + "\n" + ex.getMessage() );

                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        new addTrainWindow();
    }
}
