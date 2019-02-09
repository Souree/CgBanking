package com.cg.banking.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.cg.banking.beans.Account;
import com.cg.banking.beans.Person;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.daoservices.TransactionDAO;
import com.cg.banking.daoservices.TransactionDAOImpl;
import com.cg.banking.exception.AccountBlockedException;
import com.cg.banking.exception.AccountNotFoundException;
import com.cg.banking.exception.BankingServiceDownException;
import com.cg.banking.exception.InsufficientAmountException;
import com.cg.banking.exception.InvalidAccountTypeException;
import com.cg.banking.exception.InvalidAmountException;
import com.cg.banking.exception.InvalidPinNumberException;
import com.cg.banking.util.BankingDBUtil;

public class BankingServicesImpl implements BankingServices{
	AccountDAO servicesDao= new AccountDAOImpl();
	TransactionDAO transactionDao = new TransactionDAOImpl();
	@Override
	public Account openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServiceDownException {
		if(accountType.equalsIgnoreCase("Current")|| accountType.equalsIgnoreCase("Savings")) {
			if(initBalance>0)
			{Account account = new Account(accountType,initBalance);
			account.transactions = new HashMap<>();
			account=servicesDao.save(account);
			return account;}
			else
				throw new InvalidAmountException("Invalid Amount Exception");
		}
		else throw new InvalidAccountTypeException("Invalid Account Type Exception");
	}

	@Override
	public float depositAmount(long accountNo, float amount)
			throws AccountNotFoundException, BankingServiceDownException, AccountBlockedException {
		if(servicesDao.findOne(accountNo)==null){
			throw new AccountNotFoundException("Account not Found, Please Enter valid Account number");}
			Account account = getAccountDetails(accountNo);
			account.setAccountBalance(account.getAccountBalance()+amount);
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setTransactionType(BankingDBUtil.getTransactionTypeDeposit());
			transaction.setTransactionId(BankingDBUtil.getTransaction_ID());
			Integer transId = transaction.getTransactionId();
			account.transactions.put(transId, transaction);
			return account.getAccountBalance();
	}

	@Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
	AccountNotFoundException, InvalidPinNumberException, BankingServiceDownException, AccountBlockedException {
		if(servicesDao.findOne(accountNo)==null){
			throw new AccountNotFoundException("Account not Found, Please Enter valid Account number");}
		else {
			Account account = getAccountDetails(accountNo);
			if(pinNumber==account.getPinNumber()) 
			{	
				if(account.getAccountBalance()>amount&&account.getAccountBalance()>500) 
					{account.setAccountBalance(account.getAccountBalance()-amount);
					Transaction transaction = new Transaction();
					transaction.setAmount(amount);
					transaction.setTransactionType(BankingDBUtil.getTransactionTypeWithdraw());
					transaction.setTransactionId(BankingDBUtil.getTransaction_ID());
					Integer transId = transaction.getTransactionId();
					account.transactions.put(transId, transaction);
					return account.getAccountBalance();}
				else throw new InsufficientAmountException("Insufficient Amount in Account");
			}
			else throw new InvalidPinNumberException();
		}

	}

	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServiceDownException, AccountBlockedException {
		withdrawAmount(accountNoFrom, transferAmount, pinNumber);
		depositAmount(accountNoTo, transferAmount);
		return true;
	}

	@Override
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServiceDownException {
		Account account = new Account();
		if(servicesDao.findOne(accountNo)==null){
			throw new AccountNotFoundException("Account not Found, Please Enter valid Account number");}
		else{
			account=servicesDao.findOne(accountNo);
			return account;}
	}

	@Override
	public List<Account> getAllAccountDetails() throws BankingServiceDownException {
		List<Account> accountDetails=servicesDao.findAll();
		if(accountDetails.size()==0)
		{throw new BankingServiceDownException("No Account Details Available");}
		else
			return accountDetails;
	}

	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServiceDownException, AccountNotFoundException {
		if(servicesDao.findOne(accountNo)==null){
			throw new AccountNotFoundException("Account not Found, Please Enter valid Account number");}
		else
		{Account account = servicesDao.findOne(accountNo);
		ArrayList arrayList = new ArrayList<>(account.transactions.values());
		if(arrayList.size()==0)
			throw new BankingServiceDownException("No Transactions Available");
		else
			return arrayList;}
	}

	@Override
	public String accountStatus(long accountNo)
			throws BankingServiceDownException, AccountNotFoundException, AccountBlockedException {

		return null;
	}

}
