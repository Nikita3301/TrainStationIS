package com.company;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class searchPassenger {
    private JTextField passengerId1;
    private JTextField fullName1;
    private JTextField passportNumber1;
    private JTextField phoneNumber1;
    private JCheckBox benefits1;
    private JTable table1;
    private JButton searchButton;
    private JPanel panel;
    private JScrollPane scrollPanel;
    private JButton deleteButton;
    private JTextField deleteTextField;
    private JButton showAllButton;

    public searchPassenger() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = null;
                try {
                    Statement st = con.createStatement();
                    if (!passengerId1.getText().equals("")) {
                        sql = "SELECT * FROM riabov.passenger where passengerID like '" + Integer.parseInt(passengerId1.getText()) + "'";
                    } else if (!fullName1.getText().equals("")) {
                        sql = "SELECT * FROM riabov.passenger where fullName like '%" + fullName1.getText() + "%'";
                    } else if (!passportNumber1.getText().equals("")) {
                        sql = "SELECT * FROM riabov.passenger where passportNumber like '" + passportNumber1.getText() + "'";
                    } else if (!phoneNumber1.getText().equals("")) {
                        sql = "SELECT * FROM riabov.passenger where phoneNumber like '" + phoneNumber1.getText() + "'";
                    } else if (benefits1.isSelected()) {
                        sql = "SELECT * FROM riabov.passenger where benefits like true";
                    } else if (!benefits1.isSelected()) {
                        sql = "SELECT * FROM riabov.passenger where benefits like false";
                    }
                    ResultSet rs = st.executeQuery(sql);
                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement rs = con.prepareStatement("DELETE FROM riabov.passenger where passengerID='" + Integer.parseInt(deleteTextField.getText()) + "';");
                    rs.execute();
                    JOptionPane.showMessageDialog(null, "Delete successful");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM riabov.passenger;");
                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JFrame frame = new JFrame("Search");
        frame.setResizable(false);
        frame.setContentPane(panel);
        frame.setLocation(new Point(500, 300));
        frame.setVisible(true);
        frame.pack();
        frame.setVisible(true);


        passengerId1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                fullName1.setText(null);
                passportNumber1.setText(null);
                phoneNumber1.setText(null);
                benefits1.setSelected(false);
            }
        });
        fullName1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                passengerId1.setText(null);
                passportNumber1.setText(null);
                phoneNumber1.setText(null);
                benefits1.setSelected(false);
            }
        });
        passportNumber1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                passengerId1.setText(null);
                fullName1.setText(null);
                phoneNumber1.setText(null);
                benefits1.setSelected(false);
            }
        });
        benefits1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passengerId1.setText(null);
                fullName1.setText(null);
                passportNumber1.setText(null);
                phoneNumber1.setText(null);
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        new searchPassenger();
    }
}
