package com.lomari.walletapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseDto {
    private String authToken;
    private String refreshToken;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
}
