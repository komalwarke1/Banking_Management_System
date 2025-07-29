package ui;

import dao.UserDao;
import model.User;

import javax.swing.*;
import java.awt.GridLayout;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginBtn;

    public LoginFrame(){
        setTitle("User login");
        setSize(400,300);
        setLayout(new GridLayout(3,4));

        add(new JLabel("Username:"));
        usernameField=new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField=new JTextField();
        add(passwordField);

        loginBtn=new JButton("loginBtn");
        add(loginBtn);

        loginBtn.addActionListener(e->loginUser());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    private void loginUser(){
        String username=usernameField.getText().trim();
        String password=new String(passwordField.getText().trim());

        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"All fields are required");
            return;
        }
        User user= UserDao.Login(username,password);
        if(user!=null){
            JOptionPane.showMessageDialog(this,"Welcome "+user.getName()+"!");
            dispose();
            new DashboardFrame(user);
        }else{
            JOptionPane.showMessageDialog(this,"Invalid credentials!");
        }

    }

}






















