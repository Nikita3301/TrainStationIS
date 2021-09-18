package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class addCompanyWindow {
    private JPanel panel;
    private JTextField companyId1;
    private JTextField companyName1;
    private JTextArea companyInformation1;
    private JButton addCompanyButton;

    public addCompanyWindow() throws SQLException {
        JFrame frame = new JFrame("Add new company");
        frame.setResizable(false);
        frame.setLocation(new Point(600, 400));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT MAX(companyID) FROM riabov.company;");
        if (rs1.next()){
            int companyID = rs1.getInt(1);
            companyId1.setText(String.valueOf(companyID+1));
        }


        addCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps1 = con.prepareStatement("INSERT INTO riabov.company(companyID, companyName, companyInformation) Values (?,?,?);");

                    ps1.setString(1, null);
                    ps1.setString(2, companyName1.getText());
                    ps1.setString(3, companyInformation1.getText());
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
        new addCompanyWindow();
    }

}
