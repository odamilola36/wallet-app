package com.lomari.walletapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AuthResponseDto {
    private String authToken;
    private String refreshToken;
}
