package com.project.cbstask.controller;

import com.project.cbstask.model.Account;
import com.project.cbstask.model.request.CreateAccount;
import com.project.cbstask.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccount createAccount) {
        Account account = bankService.createAccount(createAccount);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable("accountNumber") String accountNumber) {
        BigDecimal balance = bankService.getBalance(accountNumber);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<String> deposit(@PathVariable("accountNumber") String accountNumber, @RequestParam BigDecimal amount) {
        bankService.deposit(accountNumber, amount);
        return new ResponseEntity<>("Deposit successful", HttpStatus.OK);
    }

    @PostMapping("/withdraw/{accountNumber}")
    public ResponseEntity<String> withdraw(@PathVariable("accountNumber") String accountNumber, @RequestParam BigDecimal amount) {
        bankService.withdraw(accountNumber, amount);
        return new ResponseEntity<>("Withdrawal successful", HttpStatus.OK);
    }
}
