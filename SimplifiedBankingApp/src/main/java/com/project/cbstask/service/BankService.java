package com.project.cbstask.service;

import com.project.cbstask.model.Account;
import com.project.cbstask.model.request.CreateAccount;

import java.math.BigDecimal;

public interface BankService {
    Account createAccount(CreateAccount createAccountRequest);

    BigDecimal getBalance(String accountNumber);

    void deposit(String accountNumber, BigDecimal amount);

    void withdraw(String accountNumber, BigDecimal amount);
}
