package model;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int accountId;
    private String type;
    private double amount;
    private java.sql.Timestamp date;
    private Object dateTime;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getAccountId(){
        return accountId;
    }
    public void setAccountId(int accountId){
        this.accountId=accountId;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public Timestamp getDate(){
        return date;
    }

    public void setDate(java.sql.Timestamp date) {
        this.date=date;
    }

    public Object getDateTime() {
        return dateTime;
    }
}
