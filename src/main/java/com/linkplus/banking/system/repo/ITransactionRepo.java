package com.linkplus.banking.system.repo;
import com.linkplus.banking.system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepo extends JpaRepository<Transaction, Long> {
    // You can define custom query methods here if needed
}
