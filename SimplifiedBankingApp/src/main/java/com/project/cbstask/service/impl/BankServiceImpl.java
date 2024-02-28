package com.project.cbstask.service.impl;

import com.project.cbstask.model.Account;
import com.project.cbstask.model.request.CreateAccount;
import com.project.cbstask.repository.AccountRepository;
import com.project.cbstask.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final AccountRepository accountRepository;

    @Value("${app.max.account.length:10}")
    private Integer maxAccountNumberLength;

    @Override
    public Account createAccount(CreateAccount createAccountRequest) {
        Account account = new Account();
        account.setAccountName(createAccountRequest.getAccountName());
        account.setAccountNumber(generateAccountNumber());
        account.setAccountSchemeCode(createAccountRequest.getAccountSchemeCode());
        account.setAccountBalance(BigDecimal.ZERO);
        account.setDateCreated(new Date());
        return accountRepository.save(account);
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        return getAccount(accountNumber)
                .getAccountBalance()
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        BigDecimal newBalance = account.getAccountBalance().add(amount);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        BigDecimal currentBalance = account.getAccountBalance();
        if(currentBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        BigDecimal newBalance = currentBalance.subtract(amount);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
    }

    String generateAccountNumber() {
        var numberSeries = "0123456789";
        StringBuilder sb = new StringBuilder(maxAccountNumberLength);
        for(int i = 0; i < maxAccountNumberLength; i++) {
            int index = (int) (numberSeries.length() * Math.random());
            sb.append(numberSeries.charAt(index));
        }
        return sb.toString();
    }
}
