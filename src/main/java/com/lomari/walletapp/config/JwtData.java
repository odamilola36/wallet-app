package com.lomari.walletapp.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtData {
    private String secret;
    private String prefix;
    private Long authTokenExpiry;
    private Long refreshTokenExpiry;
}
