package com.lomari.walletapp.service;

import com.lomari.walletapp.dto.AuthResponseDto;
import com.lomari.walletapp.dto.LoginDto;
import com.lomari.walletapp.dto.RegisterDto;
import com.lomari.walletapp.exceptions.CustomException;

public interface AuthService {

    void register(RegisterDto registerDto);

    AuthResponseDto login(LoginDto login) throws CustomException;
}
