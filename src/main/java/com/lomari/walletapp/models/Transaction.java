package com.lomari.walletapp.models;

import com.lomari.walletapp.enums.Currency;
import com.lomari.walletapp.enums.Status;
import com.lomari.walletapp.enums.TransactionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Transaction extends BaseClass{

    @ManyToOne
    private User user;

    @Enumerated(value= EnumType.ORDINAL)
    @Column(nullable = false, name = "currency")
    private Currency currency;

    @Column(nullable = false, name = "amount")
    private BigDecimal amount;

    @Enumerated(value= EnumType.ORDINAL)
    @Column(nullable = false, name = "type")
    private TransactionType TransactionType;

    @Enumerated(value= EnumType.ORDINAL)
    private Status status = Status.PENDING;
}
