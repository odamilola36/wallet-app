package com.lomari.walletapp.dto;

import com.lomari.walletapp.validators.annotations.PasswordConstraint;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lomari.walletapp.models.User} entity
 */
public record PasswordChangeDto(@PasswordConstraint String newPassword, String oldPassword) implements Serializable {
}