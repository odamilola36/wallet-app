package com.lomari.walletapp.enums;

import java.util.Set;

import static com.lomari.walletapp.enums.AccessLevel.*;

public enum UserRole {
    ADMIN(Set.of(ADMIN_WRITE, ADMIN_READ, REGULAR_WRITE, REGULAR_READ, GUEST_READ, GUEST_WRITE)),
    USER(Set.of(REGULAR_WRITE, REGULAR_READ)),
    GUEST(Set.of(GUEST_READ, GUEST_WRITE));

    private final Set<AccessLevel> accessLevels;

    UserRole(Set<AccessLevel> accessLevels) {
        this.accessLevels = accessLevels;
    }

    public Set<AccessLevel> getAccessLevels() {
        return accessLevels;
    }
}
