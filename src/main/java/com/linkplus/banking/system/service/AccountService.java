package com.linkplus.banking.system.service;
import com.linkplus.banking.system.entity.Account;
import com.linkplus.banking.system.repo.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class AccountService {
    private final IAccountRepo accountRepo;

    @Autowired
    public AccountService(IAccountRepo accountRepo)
    {
        this.accountRepo = accountRepo;
    }

    public Account createAccount(String accountNumber, String name, BigDecimal initialBalance)
    {
        initialBalance = initialBalance.setScale(2, RoundingMode.HALF_UP);
        try {
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setName(name);
            account.setBalance(initialBalance);
            return accountRepo.save(account);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create account: " + e.getMessage());
        }
    }

    public List<Account> getAllAccounts()
    {
        return accountRepo.findAll();
    }

    public Account getAccountById(Long id)
    {
        try {
            return accountRepo.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve account: " + e.getMessage());
        }
    }

    public BigDecimal getAccountBalance(Long accountId)
    {
        Account account = accountRepo.findById(accountId).orElse(null);
        return account != null ? account.getBalance() : null;
    }

    public Account deposit(Long accountId, BigDecimal amount)
    {
        Account account = accountRepo.findById(accountId).orElse(null);
        if (account != null) {
            BigDecimal newBalance = account.getBalance().add(amount);
            account.setBalance(newBalance);
            return accountRepo.save(account);
        }
        return null;
    }
    public Account withdraw(Long accountId, BigDecimal amount)
    {
        Account account = accountRepo.findById(accountId).orElse(null);
        if (account != null) {
            BigDecimal newBalance = account.getBalance().subtract(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                account.setBalance(newBalance);
                return accountRepo.save(account);
            }
        }
        return null;
    }

    public Account updateAccount(Account account)
    {
        return accountRepo.save(account);
    }
}
