package com.lomari.walletapp.service.serviceImpl;

import com.lomari.walletapp.config.JwtConfig;
import com.lomari.walletapp.dto.*;
import com.lomari.walletapp.enums.Currency;
import com.lomari.walletapp.enums.UserRole;
import com.lomari.walletapp.exceptions.CustomException;
import com.lomari.walletapp.exceptions.InvalidCurrencyException;
import com.lomari.walletapp.mappers.UserMapper;
import com.lomari.walletapp.models.Role;
import com.lomari.walletapp.models.User;
import com.lomari.walletapp.models.Wallet;
import com.lomari.walletapp.service.AuthService;
import com.lomari.walletapp.service.UserService;
import com.lomari.walletapp.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final UserService userService;

    private final JwtConfig jwtConfig;

    private final AuthenticationManager authManager;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserMapper userMapper, UserService userService, JwtConfig jwtConfig, AuthenticationManager authManager) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userService = userService;
        this.jwtConfig = jwtConfig;
        this.authManager = authManager;
    }


    @Override
    public AuthResponseDto register(RegisterDto registerDto) throws CustomException {
        String passwordSalt = RandomStringUtils.random(8, true, true);
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword().concat(passwordSalt));
        registerDto.setPassword(encodedPassword);
        Role role = new Role();
        role.setRole(UserRole.USER);
        Wallet wallet = new Wallet();
        wallet.setCurrency(
                Currency.getCurrency(registerDto.getCurrencyCode())
                        .orElseThrow(() -> new InvalidCurrencyException("Currency %s not supported".
                                formatted(registerDto.getCurrencyCode()))));

        User user = userMapper.registerDtoToUser(registerDto, role, wallet, passwordSalt);

        log.info("user object {}", user);
        userService.createUser(user);

        return new AuthResponseDto();
    }

    @Override
    public AuthResponseDto login(LoginDto login) throws CustomException {
        User user = userService.getUser(login.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword().concat(user.getPasswordSalt()));
            authManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            throw new CustomException(e);
        }
        String jwt = jwtConfig.generateToken(user, true);
        String refreshToken = jwtConfig.generateToken(user, false);
        return userMapper.userToAuthResponseDto(user, jwt, refreshToken);
    }

    @Override
    public AuthResponseDto refreshToken(RefreshTokenDto refreshToken) throws CustomException {
        String email = jwtConfig.extractUsername(refreshToken.refreshToken());
        Optional<User> userOptional = userService.getUser(email);
        if (userOptional.isEmpty())
            throw new CustomException("user with email %s does not exist".formatted(email));
        User user = userOptional.get();
        if (jwtConfig.isValidToken(refreshToken.refreshToken(), user))
            throw new CustomException("token expired, kindly login");
        String jwt = jwtConfig.generateToken(user, true);
        String refresh = jwtConfig.generateToken(user, false);
        return userMapper.userToAuthResponseDto(user, jwt, refresh);
    }

    @Override
    public void changePassword(PasswordChangeDto passwordChangeDto) throws CustomException {
        String authUserName = SecurityUtils.getAuthUserName();
        User user = userService.getUser(authUserName).orElseThrow();
        boolean matches = passwordEncoder
                .matches(passwordChangeDto.oldPassword().concat(user.getPasswordSalt()), user.getPassword());
        if (!matches) {
            throw new CustomException("Provided password does not match with old password");
        }
        user.setPassword(passwordChangeDto.newPassword().concat(user.getPasswordSalt()));
        userService.saveUser(user);

    }

    @Override
    public void resetPasswordInit(String email) throws CustomException {
        Optional<User> userOptional = userService.getUser(email);
        if (userOptional.isEmpty())
            throw new CustomException("user with email %s does not exist".formatted(email));
        User user = userOptional.get();
        user.setPassword(null);
        user.setPasswordResetKey(RandomStringUtils.random(7, true, true));
        userService.saveUser(user);
    }

    @Override
    public void resetPassword(PasswordResetDto resetDto) throws CustomException {
        Optional<User> userOptional = userService.getUser(resetDto.email());
        if (userOptional.isEmpty())
            throw new CustomException("user with email %s does not exist".formatted(resetDto.email()));
        User user = userOptional.get();
        if ( !user.getPasswordResetKey().equalsIgnoreCase(resetDto.passwordResetKey()))
            throw new CustomException("password keys does not match");
        user.setPassword(passwordEncoder.encode(resetDto.password().concat(user.getPasswordSalt())));
        userService.saveUser(user);
    }
}
