package ui;
import javax.swing.*;

import dao.AccountDao;
import dao.TransactionDao;
import model.Account;
import model.Transaction;
import model.User;

import java.awt.*;

public class TransactionFrame extends JFrame {
    public TransactionFrame(User user){
        setTitle("Transactions");
        setSize(400,300);
        setLayout(new FlowLayout());

        JComboBox<Account> acclist=new JComboBox<>();
        for(Account acc: AccountDao.getUserAccounts(User.getId())){
            acclist.addItem(acc);
        }

        JTextField amountField=new JPasswordField(10);
        JButton depositBtn=new JButton("Deposit");
        JButton withdrawBtn=new JButton("Withdraw");

        add(new JLabel("Select account "));
        add(acclist);
        add(new JLabel("Amount "));
        add(amountField);
        add(depositBtn);
        add(withdrawBtn);

        depositBtn.addActionListener(e->{
            performTransaction(acclist,amountField,"Withdraw ");
        });

        setVisible(true);
    }
    private void performTransaction(JComboBox<Account> accList,JTextField amountField,String type){
        Account acc=(Account)accList.getSelectedItem();
        Double amount=Double.parseDouble(amountField.getText());

        Transaction t=new Transaction();
        t.setAccountId(acc.getId());
        t.setType(type);
        t.setAmount(amount);

        if(TransactionDao.addTransaction(t)){
            JOptionPane.showMessageDialog(this,type +"Successful ");

        }else{
            JOptionPane.showMessageDialog(this,type+"Failed");
        }

    }
}
