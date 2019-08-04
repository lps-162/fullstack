package com.euler.topguns.entities;


public class Account {

    private int id;

    private String accountNumber;
    private String accountBranch;
    private double accountBalance;


    public Account() {
    	
    }
    
    public Account(int id, String accountNumber, 
    			   String accountBranch, double accountBalance) {
    	this.id = id;
        this.accountNumber = accountNumber;
        this.accountBranch = accountBranch;
        this.accountBalance = accountBalance;
    }

    public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double d) {
        this.accountBalance = d;
    }
}