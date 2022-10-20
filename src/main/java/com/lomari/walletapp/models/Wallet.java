package com.lomari.walletapp.models;

import com.lomari.walletapp.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OrderBy;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Wallet extends BaseClass{

    @OneToMany
    @OrderBy(clause = "createdAt Desc")
    private Set<Transaction> transactions;

    private Currency currency;

    private BigDecimal balance;

}
