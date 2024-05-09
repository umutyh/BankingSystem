package com.linkplus.banking.system.controller;
import com.linkplus.banking.system.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank")
public class BankController
{

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService)
    {
        this.bankService = bankService;
    }

    @GetMapping("/total-transfer-amount")
    public ResponseEntity<BigDecimal> getTotalTransferAmount()
    {
        BigDecimal totalTransferAmount = bankService.getTotalTransferAmount();
        return new ResponseEntity<>(totalTransferAmount, HttpStatus.OK);
    }

    @GetMapping("/total-transaction-fee")
    public ResponseEntity<BigDecimal> getTotalTransactionFee()
    {
        BigDecimal totalTransactionFee = bankService.getTotalTransactionFee();
        return new ResponseEntity<>(totalTransactionFee, HttpStatus.OK);
    }
}
