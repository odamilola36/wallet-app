package com.lomari.walletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String currencyCode;
    private Date dateOfBirth;
}
