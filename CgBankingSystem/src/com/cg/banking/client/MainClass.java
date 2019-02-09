package com.cg.banking.client;

import java.io.IOException;
import java.util.Scanner;

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

public class MainClass {
	static Scanner sc = new Scanner(System.in);
	static BankingServices services = new BankingServicesImpl();
	public static void main(String args[]) throws InvalidAmountException, InvalidAccountTypeException, BankingServiceDownException, AccountNotFoundException, IOException, AccountBlockedException, InsufficientAmountException, InvalidPinNumberException {
		mainScreen();
		int choiceOfClient = sc.nextInt();
		choiceMenu(choiceOfClient);

	}
	private static void choiceMenu(int choiceOfClient) throws InvalidAmountException, InvalidAccountTypeException, BankingServiceDownException, AccountNotFoundException, IOException, AccountBlockedException, InsufficientAmountException, InvalidPinNumberException {
		switch(choiceOfClient)
		{
		case 1:
			try{System.out.println("Enter the type of account you want to open");
			String accountType=sc.next();
			System.out.println("Enter the initial balance");
			float accountBalance =sc.nextFloat();
			Account account = services.openAccount(accountType,accountBalance);
			System.out.println(account);
			break;}
			catch(InvalidAccountTypeException | InvalidAmountException e) {
				e.printStackTrace();
				break;}

		case 2:
			try{System.out.println("Enter the Account Number");
			long accountNumber=sc.nextLong();
			System.out.println(services.getAccountDetails(accountNumber));
			break;}
			catch(AccountNotFoundException e) {e.printStackTrace();break;}

		case 3:
			try{System.out.println("All Account in Database are:-");
			System.out.println(services.getAllAccountDetails());
			break;}
			catch(BankingServiceDownException e){e.printStackTrace();break;}

		case 4:
			try{System.out.println("Enter the account number");
			long accountNo = sc.nextLong();
			System.out.println("Enter the amount");
			float amount =sc.nextFloat();
			services.depositAmount(accountNo, amount);
			break;}
			catch(AccountNotFoundException | BankingServiceDownException | AccountBlockedException e) {e.printStackTrace();break;}

		case 5:
			try{System.out.println("Enter the account number");
			long accountNumber1=sc.nextLong();
			System.out.println("Enter the Pin number");
			int pin =sc.nextInt();
			System.out.println("Enter the amount you want to withdraw:");
			float amount1=sc.nextFloat();

			if(services.withdrawAmount(accountNumber1, amount1, pin)>0.0)
				services.withdrawAmount(accountNumber1, amount1, pin);
			break;}
			catch(InsufficientAmountException|AccountNotFoundException|InvalidPinNumberException|BankingServiceDownException|AccountBlockedException e) 
			{e.printStackTrace();break;}

		case 6:
			try{System.out.println("Enter the account number of receiver:");
			long accountNoTo=sc.nextLong();
			System.out.println("Enter the account number of sender:");
			long accountNoFrom = sc.nextLong();
			System.out.println("Enter the transver amount:");
			float transferAmount = sc.nextFloat();
			System.out.println("Enter the Pin Number:");
			int pinNumber=sc.nextInt();
			services.fundTransfer(accountNoTo, accountNoFrom, transferAmount, pinNumber);
			break;}
			catch(InsufficientAmountException|AccountNotFoundException|InvalidPinNumberException|BankingServiceDownException|AccountBlockedException e) {e.printStackTrace();break;}


		case 7:
			try{System.out.println("Enter the Account Number");
			long accountNumberForTransactions = sc.nextLong();
			System.out.println(services.getAccountAllTransaction(accountNumberForTransactions));
			break;}
			catch(BankingServiceDownException | AccountNotFoundException e) {
				e.printStackTrace();break;
			}

		case 8:
			System.exit(0);

		default:
			System.out.println("Invalid choice!!. Please try again..");
		}
		sc.nextLine();
		main(null);}

	public static void mainScreen() {
		System.out.println("\n\n_______________________________________Welcome to Ankgirti  Bank_______________________________________");
		System.out.println("Please Enter any one of the choices:-");
		System.out.println("1. Open a new Account");
		System.out.println("2. Get your Account Details");
		System.out.println("3. Get all account Details");
		System.out.println("4. Deposit Amount");
		System.out.println("5. Withdraw Amount");
		System.out.println("6. Fund Transfer");
		System.out.println("7. Transactions");
		System.out.println("8. Exit\n");

	}
}
