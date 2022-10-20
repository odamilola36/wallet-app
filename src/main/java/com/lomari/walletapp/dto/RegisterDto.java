package com.lomari.walletapp.dto;

import com.lomari.walletapp.validators.annotations.CurrencyConstraint;
import com.lomari.walletapp.validators.annotations.EmailConstraint;
import com.lomari.walletapp.validators.annotations.PasswordConstraint;
import com.lomari.walletapp.validators.annotations.UsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterDto {

    @EmailConstraint
    private String email;

    @NotNull
    @Size(min = 2, message = "first name should be longer than one character")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "last name should be longer than one character")
    private String lastName;

    @PasswordConstraint
    private String password;

    @UsernameConstraint
    private String username;

    @CurrencyConstraint
    private String currencyCode;

    @Past(message = "date of birth should be in the past")
    private LocalDate dateOfBirth;
}
