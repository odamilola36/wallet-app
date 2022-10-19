package com.lomari.walletapp.service.serviceImpl;

import com.lomari.walletapp.config.JwtConfig;
import com.lomari.walletapp.dto.AuthResponseDto;
import com.lomari.walletapp.dto.LoginDto;
import com.lomari.walletapp.dto.RegisterDto;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

//    private final UserMapper userMapper;

    private final UserService userService;

    private final JwtConfig jwtConfig;

    private final AuthenticationManager authManager;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserService userService, JwtConfig jwtConfig, AuthenticationManager authManager) {
        this.passwordEncoder = passwordEncoder;
//        this.userMapper = userMapper;
        this.userService = userService;
        this.jwtConfig = jwtConfig;
        this.authManager = authManager;
    }


    @Override
    public void register(RegisterDto registerDto) {

        String passwordSalt = RandomStringUtils.random(8, true, true);
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword().concat(passwordSalt));
        registerDto.setPassword(encodedPassword);
        Role role = new Role();
        role.setRole(UserRole.USER);
        Wallet wallet = new Wallet();
        wallet.setCurrency(
                Currency.getCurrency(registerDto.getCurrencyCode())
                        .orElseThrow(() -> new InvalidCurrencyException("Currency not supported")));

//        User user = userMapper.registerDtoToUser(registerDto, role, wallet, passwordSalt);

        User user = new User();
        log.info("user object {}", user);
        userService.createUser(user);

    }

    @Override
    public AuthResponseDto login(LoginDto login) throws CustomException {
        User user = userService.getUser(login.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
            authManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            throw new CustomException(e);
        }
        String jwt = jwtConfig.generateToken(user, true);
        String refreshToken = jwtConfig.generateToken(user, false);
        return new AuthResponseDto(jwt, refreshToken);
    }
}
