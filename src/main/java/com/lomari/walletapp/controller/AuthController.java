package com.lomari.walletapp.controller;

import com.lomari.walletapp.dto.*;
import com.lomari.walletapp.exceptions.CustomException;
import com.lomari.walletapp.service.AuthService;
import com.lomari.walletapp.utils.ResponseBuilder;
import com.lomari.walletapp.utils.payload.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public static final String SUCCESS = "success";
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponseDto>> register(@Valid @RequestBody RegisterDto registerDto) throws CustomException {
        AuthResponseDto register = authService.register(registerDto);
        ApiResponse<AuthResponseDto> response = new ApiResponse<>(SUCCESS, HttpStatus.CREATED, register);
        return ResponseBuilder.buildApiResponse(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@Valid @RequestBody LoginDto loginDto) throws CustomException {
        AuthResponseDto login = authService.login(loginDto);
        ApiResponse<AuthResponseDto> response = new ApiResponse<>(SUCCESS, HttpStatus.OK, login);
        return ResponseBuilder.buildApiResponse(response);
    }

    @PostMapping("/change/password")
    public ResponseEntity<ApiResponse<AuthResponseDto>> changePassword(@Valid @RequestBody PasswordChangeDto passwordChangeDto) throws CustomException {
        authService.changePassword(passwordChangeDto);
        ApiResponse<AuthResponseDto> response = new ApiResponse<>(SUCCESS, HttpStatus.OK);
        return ResponseBuilder.buildApiResponse(response);
    }

    @GetMapping("/password/reset/start")
    public ResponseEntity<ApiResponse<AuthResponseDto>> resetPasswordStart(@RequestParam String email) throws CustomException {
        authService.resetPasswordInit(email);
        ApiResponse<AuthResponseDto> response = new ApiResponse<>(SUCCESS, HttpStatus.OK);
        return ResponseBuilder.buildApiResponse(response);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<ApiResponse<AuthResponseDto>> resetPassword(@Valid @RequestBody PasswordResetDto resetDto) throws CustomException {
        authService.resetPassword(resetDto);
        ApiResponse<AuthResponseDto> response = new ApiResponse<>(SUCCESS, HttpStatus.OK);
        return ResponseBuilder.buildApiResponse(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponseDto>> refreshToken(@RequestBody RefreshTokenDto refreshToken) throws CustomException {
        AuthResponseDto responseDto = authService.refreshToken(refreshToken);
        ApiResponse<AuthResponseDto> response = new ApiResponse<>(SUCCESS, HttpStatus.OK, responseDto);
        return ResponseBuilder.buildApiResponse(response);
    }
}
