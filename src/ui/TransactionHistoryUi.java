package ui;

import dao.Session;
import dao.TransactionDao;
import model.Transaction;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TransactionHistoryUi extends JFrame {
    public TransactionHistoryUi(String accountNumber){
        setTitle("Transaction history - "+accountNumber);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid account number provided.");
            dispose();
            return;
        }

        String cols[]={"ID","TYPE","AMOUNT","TABLE"};
        DefaultTableModel model=new DefaultTableModel(cols,0);

        List<Transaction> list=TransactionDao.getTransactionByAccount(Integer.parseInt(accountNumber));
        for(Transaction t:list){
            Object[] row = {t.getId(), t.getType(), t.getAmount(), t.getDateTime()};
            model.addRow(row);
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);

        if (!Session.isLoggedIn()) {
            JOptionPane.showMessageDialog(null, "You must be logged in!");
            return;
        }
        String account = Session.currentAccount;
    }

}
