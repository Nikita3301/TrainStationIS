package com.company;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;

public class searchTicketWindow {
    private JPanel panel;
    private JTextField orderDate;
    private JTextField wagonNumber;
    private JTextField seatNumber;
    private JTextField passengerID;
    private JTextField dateOfDeparture;
    private JTextField dateOfArrival;
    private JTextField price;
    private JButton searchTicketButton;
    private JTextField ticketId;
    private JTextField trainId;
    public String sql;



    public searchTicketWindow() throws SQLException {
        JFrame frame = new JFrame("Search ticket");
        frame.setResizable(false);
        frame.setLocation(new Point(600, 400));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");


        searchTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sql = "SELECT * FROM riabov.ticket where ticketID like '" + Integer.parseInt(ticketId.getText()) + "'";


                try {
                    PreparedStatement ps5 = con.prepareStatement(sql);
                    ResultSet rs5 = ps5.executeQuery();
//                    setModel(DbUtils.resultSetToTableModel(rs5));
                } catch (SQLException  ex) {
                    ex.printStackTrace();
                }

                frame.dispose();


            }
        });
        ticketId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);




            }
        });
    }




    public static void main(String[] args) throws SQLException {
        new searchTicketWindow();
    }
}
