package ui;

import model.User;

import javax.swing.*;
import java.awt.FlowLayout;

public class DashboardFrame extends JFrame {
    public DashboardFrame(User user) {
        setTitle("Dashboard -"+user.getName());
        setSize(500,400);
        setLayout(new FlowLayout());

        add(new JLabel("Welcome "+user.getName()+" !"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
