package com.lomari.walletapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppDataConfig {

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    @Value("${jwt.auth.expiration}")
    private Long authTokenExpirationDate;
    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationDate;

    @Bean
    public JwtData getJwtData() {
        return JwtData.builder()
                .secret(SECRET_KEY)
                .prefix(tokenPrefix)
                .authTokenExpiry(authTokenExpirationDate)
                .refreshTokenExpiry(refreshTokenExpirationDate)
                .build();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
