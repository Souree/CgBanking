package com.cg.banking.services;

import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exception.BankingServiceDownException;
import com.cg.banking.exception.AccountBlockedException;
import com.cg.banking.exception.AccountNotFoundException;
import com.cg.banking.exception.BankingServiceDownException;
import com.cg.banking.exception.InsufficientAmountException;
import com.cg.banking.exception.InvalidAccountTypeException;
import com.cg.banking.exception.InvalidAmountException;
import com.cg.banking.exception.InvalidPinNumberException;

public interface BankingServices {
	Account openAccount(String accountType,float initBalance)throws InvalidAmountException,
	InvalidAccountTypeException,BankingServiceDownException ;
	
	float depositAmount(long accountNo, float amount)throws AccountNotFoundException,
	BankingServiceDownException,AccountBlockedException;
	
	float withdrawAmount(long accountNo,float amount,int pinNumber)throws InsufficientAmountException,
	AccountNotFoundException,InvalidPinNumberException
	,BankingServiceDownException,AccountBlockedException;
	
	boolean fundTransfer(long accountNoTo,long accountNoFrom,float transferAmount,int pinNumber)throws InsufficientAmountException
	,AccountNotFoundException,
	InvalidPinNumberException,BankingServiceDownException,AccountBlockedException;
	
	Account getAccountDetails(long accountNo)throws AccountNotFoundException,BankingServiceDownException;
	
	List<Account> getAllAccountDetails()throws BankingServiceDownException;
	
	List<Transaction> getAccountAllTransaction(long accountNo)throws BankingServiceDownException,AccountNotFoundException;
	
	public String accountStatus(long accountNo)throws BankingServiceDownException
	,AccountNotFoundException,AccountBlockedException;

}
