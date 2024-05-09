package com.linkplus.banking.system.repo;
import com.linkplus.banking.system.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankRepo extends JpaRepository<Bank, Long> {
    // You can define custom query methods here if needed
}
