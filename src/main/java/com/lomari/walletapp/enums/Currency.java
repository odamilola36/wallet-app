package com.lomari.walletapp.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Currency {
    NGN("NGN"),
    EUR("EUR"),
    GBP("GBP"),
    USD("USD"),
    CAD("AUD");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public static Optional<Currency> getCurrency(String currency) {
        boolean match = Arrays.stream(Currency.values()).anyMatch(c -> c.currency.equalsIgnoreCase(currency));
        return match ? Optional.of(Currency.valueOf(currency.toUpperCase())) : Optional.empty();
    }

    public static boolean isSupportedCurrency(String currency) {
        return getCurrency(currency).isPresent();
    }
}
