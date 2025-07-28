package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import dao.UserDao;
import model.User;

public class RegisterFrame extends JFrame {
    private JTextField nameField,usernameField;
    private JPasswordField passwordfield;
    private JButton registerBtn;

    public RegisterFrame(){
        setTitle("User Registration");
        setSize(350,250);
        setLayout(new GridLayout(4,2));

        add(new JLabel("Name"));
        nameField=new JTextField();
        add(nameField);

        add(new JLabel("Username"));
        usernameField=new JTextField();
        add(usernameField);

        add(new JLabel("Password"));
        passwordfield=new JPasswordField();
        add(passwordfield);

        registerBtn=new JButton("Register");
        add(registerBtn);

        registerBtn.addActionListener(e-> registerUser());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    private void registerUser(){
        String name=nameField.getText().trim();
        String userName=usernameField.getText().trim();
        String password=new String(passwordfield.getPassword());

        if(name.isEmpty() && userName.isEmpty() && password.isEmpty()){
            JOptionPane.showMessageDialog(this,"All fields are required!");
            return;
        }

        if(UserDao.isUserNameTaken(userName)){
            JOptionPane.showMessageDialog(this,"Username already taken");
            return;
        }

        User user=new User();
        user.setName(name);
        user.setUsername(userName);
        user.setPassword(password);

        if (UserDao.registerUser(user)){
            JOptionPane.showMessageDialog(this,"User registered successfully");
            dispose();
            new LoginFrame();
        }else{
            JOptionPane.showMessageDialog(this,"Registration failed");

        }

    }

}
