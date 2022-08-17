package com.lomari.walletapp.repository;

import com.lomari.walletapp.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
