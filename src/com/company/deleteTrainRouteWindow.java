package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class deleteTrainRouteWindow {
    private JTextField trainRouteId1;
    private JButton deleteButton;
    private JPanel panel;

    public deleteTrainRouteWindow() throws SQLException {
        JFrame frame = new JFrame("Delete train route");
        frame.setResizable(false);
        frame.setLocation(new Point(600, 400));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);



        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");
                    Statement stmt = con.createStatement();
                    stmt.execute("SET FOREIGN_KEY_CHECKS=0");
                    stmt.close();
                    PreparedStatement rs = con.prepareStatement("DELETE FROM riabov.trainroute where riabov.trainRoute.trainRouteID='" + Integer.parseInt(trainRouteId1.getText()) + "'");
                    rs.execute();
                    JOptionPane.showMessageDialog(null, "Delete successful");

                    frame.dispose();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Delete failed"+ "\n"+ ex.getMessage());
                }

            }
        });
    }

    public static void main(String[] args) throws SQLException {
        new deleteTrainRouteWindow();
    }
}
