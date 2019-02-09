package com.cg.banking.daoservices;

import java.util.ArrayList;
import java.util.List;


import com.cg.banking.beans.Account;
import com.cg.banking.util.BankingDBUtil;

public class AccountDAOImpl implements AccountDAO{
	@Override
	public Account save(Account account) {
		account.setAccountNo(BankingDBUtil.setACCOUNT_NO());
		account.setPinNumber(BankingDBUtil.setPIN_NO());
		account.setAccountStatus(BankingDBUtil.setSETACCOUNT_STATUS());
		BankingDBUtil.accountDetails.put(account.getAccountNo(),account);
		return account;
	}

	@Override
	public boolean update(Account account) {
		return true;
	}

	@Override
	public Account findOne(long accountNo) {
		 Account account =BankingDBUtil.accountDetails.get(accountNo);
		return account;
	}

	@Override
	public List<Account> findAll() {
		ArrayList<Account> accountDetails=new ArrayList<>(BankingDBUtil.accountDetails.values());
		return accountDetails;
	}

}
