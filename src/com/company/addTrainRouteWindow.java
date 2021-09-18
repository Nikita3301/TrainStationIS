package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class addTrainRouteWindow {
    private JTextField trainRouteId1;
    private JTextField dispatchStation1;
    private JTextField destinationStation1;
    private JTextField distance1;
    private JTextField dispatchStationNumber1;
    private JTextField destinationStationNumber1;
    private JTextField routeTime1;
    private JButton addButton;
    private JPanel panel;

    public addTrainRouteWindow() throws SQLException {
        JFrame frame = new JFrame("Add new train route");
        frame.setResizable(false);
        frame.setLocation(new Point(600, 400));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);


        //get train route id for new record
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT MAX(trainRouteID) FROM riabov.trainroute;");
        if (rs1.next()){
            int trainRouteID = rs1.getInt(1);
            trainRouteId1.setText(String.valueOf(trainRouteID+1));
        }


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps1 = con.prepareStatement("INSERT INTO riabov.trainroute(trainRouteID,dispatchStation,dispatchStation_number,destinationStation,destinationStationNumber,distance,routeTime) Values (?,?,?,?,?,?,?);");

                    ps1.setString(1, null);
                    ps1.setString(2, dispatchStation1.getText());
                    ps1.setString(3, dispatchStationNumber1.getText());
                    ps1.setString(4, destinationStation1.getText());
                    ps1.setString(5, destinationStationNumber1.getText());
                    ps1.setString(6, distance1.getText());
                    ps1.setString(7, routeTime1.getText());
                    frame.dispose();
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Add is successful");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Add failed"+ "\n"+ ex.getMessage());
//                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        new addTrainRouteWindow();
    }
}
