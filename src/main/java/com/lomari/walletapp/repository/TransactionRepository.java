package com.lomari.walletapp.repository;

import com.lomari.walletapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
