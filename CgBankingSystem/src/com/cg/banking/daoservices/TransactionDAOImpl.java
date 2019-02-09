package com.cg.banking.daoservices;

import java.util.HashMap;
import java.util.List;


import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;

public class TransactionDAOImpl implements TransactionDAO{

	@Override
	public Transaction save(Transaction transaction,long accountNumber) {
		
		
		return transaction;
	}

	@Override
	public boolean update(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction findOne(int transactionId,long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
