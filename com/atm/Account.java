package com.atm;

public class Account {
    private String id;
    private String name;
    private char sex;
    private double price;
    private int PassWord;
    private double WithdrawalAmount;

    public Account(String id, String name, char sex, double price, int passWord, double withdrawalAmount) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.price = price;
        PassWord = passWord;
        WithdrawalAmount = withdrawalAmount;
    }

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name+(sex=='男'? "Mr" : "Miss") ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPassWord() {
        return PassWord;
    }

    public void setPassWord(int passWord) {
        PassWord = passWord;
    }

    public double getWithdrawalAmount() {
        return WithdrawalAmount;
    }

    public void setWithdrawalAmount(double withdrawalAmount) {
        WithdrawalAmount = withdrawalAmount;
    }
}
