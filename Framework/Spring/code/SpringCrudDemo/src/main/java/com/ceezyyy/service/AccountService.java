package com.ceezyyy.service;

import com.ceezyyy.entity.Account;

import java.util.List;

public interface AccountService {
    // create
    void saveAccount(Account account);

    // read one
    Account findAccountById(int id);

    // read all
    List<Account> findAll();

    // update
    void updateAccount(Account account);

    // delete
    void deleteAccountById(int id);

    // transfer
    boolean transfer(int from, int to, double amount);
}
