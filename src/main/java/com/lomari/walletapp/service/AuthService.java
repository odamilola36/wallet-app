package com.lomari.walletapp.service;

import com.lomari.walletapp.dto.*;
import com.lomari.walletapp.exceptions.CustomException;

public interface AuthService {

    AuthResponseDto register(RegisterDto registerDto) throws CustomException;

    AuthResponseDto login(LoginDto login) throws CustomException;

    AuthResponseDto refreshToken(RefreshTokenDto refreshToken) throws CustomException;

    void changePassword(PasswordChangeDto passwordChangeDto) throws CustomException;

    void resetPasswordInit(String email) throws CustomException;

    void resetPassword(PasswordResetDto resetDto) throws CustomException;
}
