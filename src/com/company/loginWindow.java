package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;



public class loginWindow{
    private JPanel panel1;
    private JButton loginButton;
    private JTextField loginTextField;
    private JPasswordField passwordTextField;
    private JCheckBox showPasswordCheckBox;
    private JLabel img;
    private JTextField incorrectTextField;

    static JFrame loginFrame = new JFrame("Login");



    public loginWindow() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/riabov",
                            "root", "nikita070901");
                    String login = loginTextField.getText();
                    String password = passwordTextField.getText();

                    Statement stm = con.createStatement();
                    String sql = "select * from employee where login='"+login+"' and password='"+password+"'";
                    ResultSet rs = stm.executeQuery(sql);
                    if (rs.next()){
                        loginFrame.dispose();
                        new mainWindow();
//                        mainFrame();
                    }else{
                        incorrectTextField.setVisible(true);
                        incorrectTextField.setText("Incorrect username or password. ");


//                        JOptionPane.showMessageDialog(null, "Username and password not correct");
                        loginTextField.setText("");
                        passwordTextField.setText("");
                    }
                    con.close();

                }catch (Exception exp){
                    System.out.println(exp.getMessage());
                }
            }
        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()){
                    passwordTextField.setEchoChar((char)0);
                }else{
                    passwordTextField.setEchoChar('*');
                }
            }
        });


    }
    public static void loginFrame(){
        loginFrame.setContentPane(new loginWindow().panel1);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocation(new Point(500, 300));
        loginFrame.setResizable(false);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        loginFrame();
    }


}


