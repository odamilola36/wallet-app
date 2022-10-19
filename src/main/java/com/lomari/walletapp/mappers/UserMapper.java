package com.lomari.walletapp.mappers;

import com.lomari.walletapp.dto.RegisterDto;
import com.lomari.walletapp.models.Role;
import com.lomari.walletapp.models.User;
import com.lomari.walletapp.models.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source ="role", target = "role")
    User registerDtoToUser(RegisterDto registerDto, Role role, Wallet wallet, String passwordSalt);
}

