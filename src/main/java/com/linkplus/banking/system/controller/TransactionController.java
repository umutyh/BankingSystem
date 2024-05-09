package com.linkplus.banking.system.controller;
import com.linkplus.banking.system.entity.Transaction;
import com.linkplus.banking.system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController
{
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService)
    {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferMoney(@RequestParam Long senderAccountId,
                                                     @RequestParam Long recipientAccountId,
                                                     @RequestParam BigDecimal amount)
    {
        Transaction transaction = transactionService.transferMoney(senderAccountId,
                                                                    recipientAccountId, amount);
        if (transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions()
    {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
