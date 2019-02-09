package com.cg.banking.util;

import java.util.HashMap;

import com.cg.banking.beans.Account;

public class BankingDBUtil {
	public static HashMap<Long,Account> accountDetails = new HashMap<>();
	public static long accountNo=100;
	static int pinNumber=1000;
	static Integer transactionId = 1000;
	static String accountStatus ="TRUE";
	static String transaction_type_Deposit ="DEPOSIT";
	static String transaction_type_Withdraw ="Withdraw";
	
	public static long setACCOUNT_NO() {
		return ++accountNo;
		}
	public static int setPIN_NO() {
		return ++pinNumber;
	}
	public static String setSETACCOUNT_STATUS()
	{
		return accountStatus;
	}
	public static String getTransactionTypeDeposit() {
		return transaction_type_Deposit;
	}
	public static String getTransactionTypeWithdraw() {
		return transaction_type_Deposit;
	}
	public static Integer getTransaction_ID(){
		return ++transactionId;
	}
	
}
