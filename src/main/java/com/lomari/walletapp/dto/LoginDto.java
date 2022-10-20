package com.lomari.walletapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull(message = "email can not be null")
    private String email;
    @NotNull(message = "password cannot be null")
    private String password;
}
