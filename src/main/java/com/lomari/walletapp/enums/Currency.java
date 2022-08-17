package com.lomari.walletapp.enums;

import java.util.Optional;

public enum Currency {
    NGN("NGN"),
    EUR("EUR"),
    GBP("GBP"),
    USD("USD"),
    JPY("JPY"),
    AUD("AUD"),
    CAD("AUD");

    private final String currency;

    Currency(String currency){
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public static Optional<Currency> getCurrency(String currency){
        return Optional.of(Currency.valueOf(currency.toUpperCase()));
    }
}
