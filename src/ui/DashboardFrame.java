package ui;

import dao.AccountDao;
import model.Account;
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

        JButton createAccountBtn=new JButton("Create Account");
        add(createAccountBtn);

        createAccountBtn.addActionListener(e -> {
            String accNum=JOptionPane.showInputDialog(this,"Enter account number !");
            if(accNum!=null && !accNum.trim().isEmpty()){

                Account acc=new Account();
                acc.setUserid(user.getId());
                acc.setAccount_number(accNum);
                acc.setBalance(0.0);

                if(AccountDao.create_account(acc)){
                    JOptionPane.showMessageDialog(this,"Account created !");
                }else{
                    JOptionPane.showMessageDialog(this,"Account creation is failed !");
                }
            }

        });


        JButton txnBtn = new JButton("Transactions");
        txnBtn.addActionListener(e -> {
            new TransactionFrame(user);
        });
        add(txnBtn);

















    }


}
