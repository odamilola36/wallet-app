package com.lomari.walletapp.enums;

public enum AccessLevel {
    GUEST_READ("g_read"),
    GUEST_WRITE("g_write"),
    ADMIN_READ("a_read"),
    ADMIN_WRITE("a_write"),
    REGULAR_READ("r_read"),
    REGULAR_WRITE("r_write");

    private final String access;

    AccessLevel(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }
}
