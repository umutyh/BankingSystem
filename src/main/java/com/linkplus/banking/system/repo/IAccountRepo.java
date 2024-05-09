package com.linkplus.banking.system.repo;
import com.linkplus.banking.system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo extends JpaRepository<Account, Long> {
    // You can define custom query methods here if needed
}
