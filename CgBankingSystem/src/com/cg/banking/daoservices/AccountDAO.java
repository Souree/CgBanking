package com.cg.banking.daoservices;

import java.util.List;

import com.cg.banking.beans.Account;

public interface AccountDAO {
public Account save(Account account);
public boolean update(Account account);
public Account findOne(long accountNo);
public List<Account> findAll();
}
