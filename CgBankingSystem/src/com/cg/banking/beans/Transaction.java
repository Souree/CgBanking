package com.cg.banking.beans;

public class Transaction {
@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", transactionType="
				+ transactionType + "]";
	}

private int transactionId;
private float amount;
private String transactionType;

public Transaction(float amount,String transactionType)
	{
		super();
		this.amount=amount;
		this.transactionType=transactionType;
	}

public Transaction(int transactionId,float amount,String transactionType) {
	super();
	this.amount=amount;
	this.transactionId=transactionId;
	this.transactionType=transactionType;
}

public Transaction() {
	// TODO Auto-generated constructor stub
}

public int getTransactionId() {
	return transactionId;
}

public void setTransactionId(int transactionId) {
	this.transactionId = transactionId;
}

public float getAmount() {
	return amount;
}

public void setAmount(float amount) {
	this.amount = amount;
}

public String getTransactionType() {
	return transactionType;
}

public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}

}
