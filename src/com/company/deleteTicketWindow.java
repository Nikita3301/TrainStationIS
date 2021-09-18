package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class deleteTicketWindow {
    private JTextField ticketId1;
    private JButton deleteTicketButton;
    private JPanel panel;


    public deleteTicketWindow() {
        JFrame frame = new JFrame("Delete train route");
        frame.setResizable(false);
        frame.setLocation(new Point(600, 400));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);

        deleteTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");
                    Statement stmt = con.createStatement();
                    stmt.execute("SET FOREIGN_KEY_CHECKS=0");
                    stmt.close();
                    PreparedStatement rs = con.prepareStatement("DELETE FROM riabov.ticket where ticketID='" + Integer.parseInt(ticketId1.getText()) + "';");
                    rs.execute();
                    JOptionPane.showMessageDialog(null, "Delete successful");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Delete failed"+ "\n"+ ex.getMessage());
                }
            }
        });


    }

    public static void main(String[] args) {
        new deleteTicketWindow();
    }
}
