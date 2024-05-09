package com.linkplus.banking.system.service;
import com.linkplus.banking.system.entity.Account;
import com.linkplus.banking.system.entity.Transaction;
import com.linkplus.banking.system.exceptions.AccountNotFoundException;
import com.linkplus.banking.system.exceptions.InsufficientBalanceException;
import com.linkplus.banking.system.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService
{
    private final ITransactionRepo transactionRepo;
    private final AccountService accountService;

    @Autowired
    public TransactionService(ITransactionRepo transactionRepo, AccountService accountService)
    {
        this.transactionRepo = transactionRepo;
        this.accountService = accountService;
    }

    public Transaction transferMoney(Long senderAccountId, Long recipientAccountId, BigDecimal amount)
    {
        Account senderAccount = accountService.getAccountById(senderAccountId);
        Account recipientAccount = accountService.getAccountById(recipientAccountId);

        if (senderAccount != null && recipientAccount != null)
        {
            BigDecimal senderNewBalance = senderAccount.getBalance().subtract(amount);
            BigDecimal recipientNewBalance = recipientAccount.getBalance().add(amount);

            if (senderNewBalance.compareTo(BigDecimal.ZERO) >= 0)
            {
                senderAccount.setBalance(senderNewBalance);
                recipientAccount.setBalance(recipientNewBalance);

                Transaction transaction = new Transaction();
                transaction.setSender(senderAccount);
                transaction.setRecipient(recipientAccount);
                transaction.setAmount(amount);
                transaction.setTimestamp(LocalDateTime.now());

                accountService.updateAccount(senderAccount);
                accountService.updateAccount(recipientAccount);

                return transactionRepo.save(transaction);
            }
        }

        return null;
    }


    public List<Transaction> getAllTransactions()
    {
        return transactionRepo.findAll();
    }
}
