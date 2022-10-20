package com.lomari.walletapp.dto;

import com.lomari.walletapp.models.User;
import com.lomari.walletapp.validators.annotations.PasswordConstraint;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
public record PasswordResetDto(@Email(message = "Email should be a valid email") String email, String passwordResetKey,
                               @PasswordConstraint String password) implements Serializable {
}