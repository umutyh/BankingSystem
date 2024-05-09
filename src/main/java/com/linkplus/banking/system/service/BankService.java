package com.linkplus.banking.system.service;
import com.linkplus.banking.system.entity.Transaction;
import com.linkplus.banking.system.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class BankService
{
    private final ITransactionRepo transactionRepo;
    private static final BigDecimal FLAT_FEE_AMOUNT = BigDecimal.TEN;
    private static final BigDecimal PERCENT_FEE_RATE = BigDecimal.valueOf(0.05);

    @Autowired
    public BankService(ITransactionRepo transactionRepo)
    {
        this.transactionRepo = transactionRepo;
    }

    public BigDecimal getTotalTransferAmount()
    {
        List<Transaction> transactions = transactionRepo.findAll();
        BigDecimal totalTransferAmount = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            totalTransferAmount = totalTransferAmount.add(transaction.getAmount());
        }
        return totalTransferAmount;
    }

    public BigDecimal getTotalTransactionFee()
    {
        List<Transaction> transactions = transactionRepo.findAll();
        BigDecimal totalTransactionFee = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            BigDecimal transactionAmount = transaction.getAmount();
            BigDecimal fee = calculateFee(transactionAmount);
            totalTransactionFee = totalTransactionFee.add(fee);
        }
        return totalTransactionFee;
    }
    private BigDecimal calculateFee(BigDecimal transactionAmount)
    {
        BigDecimal flatFee = FLAT_FEE_AMOUNT.setScale(2, RoundingMode.HALF_UP);
        BigDecimal percentFee = transactionAmount.multiply(PERCENT_FEE_RATE).setScale(2, RoundingMode.HALF_UP);
        return flatFee.max(percentFee);
    }
}
