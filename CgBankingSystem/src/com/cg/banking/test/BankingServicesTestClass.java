package com.cg.banking.test;

import com.cg.banking.beans.Account;
import com.cg.banking.exception.AccountBlockedException;
import com.cg.banking.exception.AccountNotFoundException;
import com.cg.banking.exception.BankingServiceDownException;
import com.cg.banking.exception.InsufficientAmountException;
import com.cg.banking.exception.InvalidAccountTypeException;
import com.cg.banking.exception.InvalidAmountException;
import com.cg.banking.exception.InvalidPinNumberException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;
import com.cg.banking.util.BankingDBUtil;

import org.junit.Assert;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class BankingServicesTestClass {
	Account account=new Account();

private static BankingServices service;
	
@BeforeClass
public static void setUpTestEnv() {
	service= new BankingServicesImpl();
}
@Before
public void setUpTestData() {
	
	Account account1 = new Account(101,201,"Savings","Active",1000, account.transactions);
	Account account2 = new Account( 102,202,"Savings","Active",1000, account.transactions);
	BankingDBUtil.accountDetails.put(account1.getAccountNo(), account1);
	BankingDBUtil.accountDetails.put(account2.getAccountNo(), account2);
	
	BankingDBUtil.accountNo=102;
}
@Test(expected= AccountNotFoundException.class)
public void getAccountDetailsForInvalidData()throws InvalidAccountTypeException, 
AccountNotFoundException, BankingServiceDownException{
	service.getAccountDetails(458);
}
@Test
public void getAccountDetailsForValidData() throws InvalidAccountTypeException, 
BankingServiceDownException, AccountNotFoundException {
	Account expectedAccount= new Account(101,201,"Savings","Active",1000, account.transactions);
	Account actualAccount =service.getAccountDetails(101);
	Assert.assertEquals(expectedAccount, actualAccount);
}
@Test(expected=AccountNotFoundException.class)
public void testDepositForInvalidData() throws InvalidAccountTypeException, 
AccountNotFoundException, BankingServiceDownException{
	service.getAccountDetails(564);
}
@Test
public void testDepositForValidData() throws InvalidAmountException, AccountNotFoundException,
InvalidAccountTypeException, AccountBlockedException, 
BankingServiceDownException{
	int expectedBalance =2000;
    int actualBalance=  (int) service.depositAmount(101, 1000);
    Assert.assertEquals(expectedBalance,actualBalance);
}
@Test
public void testWithdrawForValidData() throws InsufficientAmountException, AccountNotFoundException,
InvalidPinNumberException, BankingServiceDownException, AccountBlockedException {
	long expectedBalance =500;
	long actualBalance= (long)service.withdrawAmount(101, 500, 201);
	Assert.assertEquals(expectedBalance, actualBalance);
}
@Test(expected=AccountNotFoundException.class)
public void testWithdrawForInvalidData() throws AccountNotFoundException, InvalidAccountTypeException,
InvalidAmountException, InvalidPinNumberException, AccountBlockedException, InsufficientAmountException, 
AccountNotFoundException, BankingServiceDownException{
	service.withdrawAmount(456, 622, 201);
}
@Test(expected=AccountNotFoundException.class)
public void testFundTransferForInvalidData()throws AccountNotFoundException, InvalidAccountTypeException, 
InvalidAmountException, InvalidPinNumberException, AccountBlockedException, InvalidAccountTypeException, 
InsufficientAmountException, AccountNotFoundException, BankingServiceDownException{
	service.fundTransfer(402, 506, 5000, 208);
}
@Test
public void testfundTransferForvalidData() throws AccountNotFoundException, InvalidAccountTypeException,
InvalidAmountException, InvalidPinNumberException, AccountBlockedException, InvalidAccountTypeException,
InsufficientAmountException,AccountNotFoundException, BankingServiceDownException{
//	int expectedWithdrawAccBal=500;
//	int actualWithdrawAccBal= (int)service.withdrawAmount(101, 500, 201);
	long expectedDepositAccBal =1500;
	long actualDepositAccBal=(long)service.depositAmount(102,500 );
//	Assert.assertEquals(expectedWithdrawAccBal, actualWithdrawAccBal);
	Assert.assertEquals(expectedDepositAccBal, actualDepositAccBal);
}
@After
public void tearDownTestData() {
	BankingDBUtil.accountDetails.clear();
	BankingDBUtil.accountNo=100;
}
}