package com.company;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Objects;


public class mainWindow {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panelka1;
    private JPanel panelka2;
    private JTabbedPane tabbedPane1;
    private JTextField passengerId1;
    private JCheckBox benefits1;
    private JTextField fullName1;
    private JTextField passportNumber1;
    private JTextField phoneNumber1;
    private JTextField otrainID;
    private JTextField oorderDate;
    private JTextField owagonNumber;
    private JTextField oseatNumber;
    private JTextField opassengerID;
    private JTextField odateOfDeparture;
    private JTextField odateOfArrival;
    private JTextField oprice;
    private JButton addTicketButton;
    private JTable trainTable;
    private JTable trainRouteTable;
    private JTable companyInfoTable;
    private JButton deleteTrainRouteButton;
    private JButton updateTrainRouteButton;
    private JButton addTrainRouteButton;
    private JButton passengerSearch;
    private JButton addPassengerButton;
    private JTextField oticketId;
    private JTextField otrainId;
    private JButton trainNumberInfoButton;
    private JButton passengerInfoButton;
    private JButton addCompanyButton;
    private JButton deleteCompanyButton;
    private JButton updateCompanyButton;
    private JButton addTrainButton;
    private JButton deleteTrainButton;
    private JButton updateTrainButton;
    public JTable ticketTable;
    private JButton ticketSearchButton;
    private JButton ticketDeleteButton;
    private JButton ticketUpdateButton;
    private JComboBox<String> searchComboBox1;
    private JButton updatePassengerButton;
    private JTable passengerTable;
    private JTextField emloyeeId;
    private JTextField employeeFullName;
    private JComboBox<String> genderComboBox;
    private JTextField employeePhone;
    private JTextField employeeLogin;
    private JTextField employeePassword;
    private JButton registerButton;


    public mainWindow() throws SQLException{

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riabov", "root", "nikita070901");

        PreparedStatement ps1 = con.prepareStatement("select * from riabov.passenger");
        ResultSet rs = ps1.executeQuery();
        passengerTable.setModel(DbUtils.resultSetToTableModel(rs));

        //employeeId in add employee tab
        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT MAX(employeeID) FROM riabov.employee;");
        if (rs1.next()) {
            int employeeID = rs1.getInt(1);
            emloyeeId.setText(String.valueOf(employeeID + 1));
        }






        // all data to table in train tab
        PreparedStatement ps2 = con.prepareStatement("select * from riabov.train");
        ResultSet rs2 = ps2.executeQuery();
        trainTable.setModel(DbUtils.resultSetToTableModel(rs2));


        // all data to table in train route tab
        PreparedStatement ps3 = con.prepareStatement("select * from riabov.trainroute");
        ResultSet rs3 = ps3.executeQuery();
        trainRouteTable.setModel(DbUtils.resultSetToTableModel(rs3));

        // all data to table in company tab
        PreparedStatement ps4 = con.prepareStatement("select * from riabov.company");
        ResultSet rs4 = ps4.executeQuery();
        companyInfoTable.setModel(DbUtils.resultSetToTableModel(rs4));

        // all date to table in tickets tab
        PreparedStatement ps5 = con.prepareStatement("select * from riabov.ticket");
        ResultSet rs5 = ps5.executeQuery();
        ticketTable.setModel(DbUtils.resultSetToTableModel(rs5));


        // add new ticket
        addTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    PreparedStatement ps1 = con.prepareStatement("INSERT INTO riabov.ticket(ticketID, trainID, orderDate, wagonNumber, seatNumber, passengerID, dateOfDeparture, dateOfArrival, price) Values(?,?,?,?,?,?,?,?,?);");
                    ps1.setString(1, null);
                    ps1.setString(2, otrainId.getText());
                    ps1.setString(3, oorderDate.getText());
                    ps1.setString(4, owagonNumber.getText());
                    ps1.setString(5, oseatNumber.getText());
                    ps1.setString(6, opassengerID.getText());
                    ps1.setString(7, odateOfDeparture.getText());
                    ps1.setString(8, odateOfArrival.getText());
                    ps1.setString(9, oprice.getText());
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Add is successful");
                    otrainId.setText("");
                    oorderDate.setText("");
                    owagonNumber.setText("");
                    oseatNumber.setText("");
                    opassengerID.setText("");
                    odateOfDeparture.setText("");
                    odateOfArrival.setText("");
                    oprice.setText("");
                } catch (SQLException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Add failed"+"\n"+ex.getMessage());

                }

            }
        });

        // add new train route
        addTrainRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    new addTrainRouteWindow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // add new passenger
        addPassengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addPassengerWindow();
            }
        });

        // search passenger
        passengerSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new searchPassenger();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // table with train ID and train number connection
        trainNumberInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                JTable table1 = new JTable();
                PreparedStatement ps5 = null;
                try {
                    ps5 = con.prepareStatement("select trainID, trainNumber from riabov.train");
                    ResultSet rs5 = ps5.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(rs5));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                JScrollPane scrollPane1 = new JScrollPane(table1);
                frame1.add(scrollPane1, BorderLayout.CENTER);
                frame1.setSize(300, 150);
                frame1.setVisible(true);

            }
        });

        // table with passenger ID and full name connection
        passengerInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame();
                JTable table2 = new JTable();
                PreparedStatement ps6 = null;
                try {
                    ps6 = con.prepareStatement("select passengerID, fullName, passportNumber from riabov.passenger");
                    ResultSet rs6 = ps6.executeQuery();
                    table2.setModel(DbUtils.resultSetToTableModel(rs6));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                JScrollPane scrollPane2 = new JScrollPane(table2);
                frame2.add(scrollPane2, BorderLayout.CENTER);
                frame2.setSize(600, 400);
                frame2.setVisible(true);
            }
        });

        // delete train route
        deleteTrainRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new deleteTrainRouteWindow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // update train route table
        updateTrainRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps3 = con.prepareStatement("select * from riabov.trainroute");
                    ResultSet rs3 = ps3.executeQuery();
                    trainRouteTable.setModel(DbUtils.resultSetToTableModel(rs3));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // update company table
        updateCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps4 = con.prepareStatement("select * from riabov.company");
                    ResultSet rs4 = ps4.executeQuery();
                    companyInfoTable.setModel(DbUtils.resultSetToTableModel(rs4));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // delete company record by ID
        deleteCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteCompanyWindow();
            }
        });

        // add company
        addCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new addCompanyWindow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        addTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new addTrainWindow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        deleteTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteTrainWindow();
            }
        });
        updateTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps2 = con.prepareStatement("select * from riabov.train");
                    ResultSet rs2 = ps2.executeQuery();
                    trainTable.setModel(DbUtils.resultSetToTableModel(rs2));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });




        ticketSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String value = Objects.requireNonNull(searchComboBox1.getSelectedItem()).toString();
                    String textField = JOptionPane.showInputDialog("Input a value");

                    PreparedStatement ps5 = con.prepareStatement("SELECT * FROM riabov.ticket where "+value+" like '%" + textField + "%' ;");
                    ResultSet rs5 = ps5.executeQuery();
                    ticketTable.setModel(DbUtils.resultSetToTableModel(rs5));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        ticketDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteTicketWindow();
            }
        });
        ticketUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps5 = con.prepareStatement("select * from riabov.ticket");
                    ResultSet rs5 = ps5.executeQuery();
                    ticketTable.setModel(DbUtils.resultSetToTableModel(rs5));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        updatePassengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps1 = con.prepareStatement("select * from riabov.passenger");
                    ResultSet rs = ps1.executeQuery();
                    passengerTable.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement st = con.createStatement();
                    ResultSet rs1 = st.executeQuery("SELECT MAX(employeeID) FROM riabov.employee;");
                    if (rs1.next()) {
                        int employeeID = rs1.getInt(1);
                        emloyeeId.setText(String.valueOf(employeeID + 1));
                    }
                    String genderValue = Objects.requireNonNull(genderComboBox.getSelectedItem()).toString();
                    PreparedStatement ps1 = con.prepareStatement("INSERT INTO riabov.employee(employeeID, fullName, gender, phoneNumber, login, password) Values(?,?,?,?,?,?);");
                    ps1.setString(1, null);
                    ps1.setString(2, employeeFullName.getText());
                    ps1.setString(3, genderValue);
                    ps1.setString(4, employeePhone.getText());
                    ps1.setString(5, employeeLogin.getText());
                    ps1.setString(6, employeePassword.getText());
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Add is successful");
                    employeeFullName.setText("");
                    genderComboBox.setSelectedItem("Other");
                    employeePhone.setText("");
                    employeeLogin.setText("");
                    employeePassword.setText("");
                    ResultSet rs2 = st.executeQuery("SELECT MAX(employeeID) FROM riabov.employee;");
                    if (rs2.next()) {
                        int employeeID = rs2.getInt(1);
                        emloyeeId.setText(String.valueOf(employeeID + 1));
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Add failed"+"\n"+ex.getMessage());
                }
            }

        });



        searchComboBox1.addItem("ticketID");
        searchComboBox1.addItem("trainID");
        searchComboBox1.addItem("orderDate");
        searchComboBox1.addItem("wagonNumber");
        searchComboBox1.addItem("seatNumber");
        searchComboBox1.addItem("passengerID");
        searchComboBox1.addItem("dateOfDeparture");
        searchComboBox1.addItem("dateOfArrival");
        searchComboBox1.addItem("price");
        genderComboBox.addItem("Other");
        genderComboBox.addItem("Male");
        genderComboBox.addItem("Female");



        JFrame frame = new JFrame("Main Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(panel1);
        frame.pack();
        frame.setVisible(true);
//        con.close();

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        loginWindow.loginFrame();
    }
}







