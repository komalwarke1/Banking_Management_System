package ui;

import dao.AccountDao;
import dao.Session;
import model.Account;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    public Session session;
    private String accNum; // ✅ make it accessible across methods

    public DashboardFrame(Session session){
        this.session=session;
    }

    public DashboardFrame(User user) {
        setTitle("Dashboard - "+user.getName());
        setSize(500,400);
        setLayout(new FlowLayout());

        add(new JLabel("Welcome "+user.getName()+" !"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JButton createAccountBtn = new JButton("Create Account");
        add(createAccountBtn);

        JButton txnBtn = new JButton("Transactions");
        txnBtn.addActionListener(e -> {
            new TransactionFrame(user);
        });
        add(txnBtn);

        createAccountBtn.addActionListener(e -> {
            accNum = JOptionPane.showInputDialog(this, "Enter account number !");
            if (accNum != null && !accNum.trim().isEmpty()) {
                Account acc = new Account();
                acc.setUserid(user.getId());
                acc.setAccount_number(accNum);
                acc.setBalance(0.0);

                if (AccountDao.create_account(acc)) {
                    JOptionPane.showMessageDialog(this, "Account created !");
                } else {
                    JOptionPane.showMessageDialog(this, "Account creation failed !");
                }

            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            Session.logout();
            dispose();
            new LoginFrame();
        });
        add(logoutButton);
    }
}
