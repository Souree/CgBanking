package com.cg.banking.daoservices;

import java.util.List;

import com.cg.banking.beans.Transaction;

public interface TransactionDAO {
	Transaction save(Transaction transaction,long accountNumber);
	boolean update(Transaction transaction);
	Transaction findOne(int transactionId,long accountNumber);
	List<Transaction> findAll();
}
