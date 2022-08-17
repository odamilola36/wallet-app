package com.lomari.walletapp.mappers;

import com.lomari.walletapp.dto.RegisterDto;
import com.lomari.walletapp.models.Role;
import com.lomari.walletapp.models.User;
import com.lomari.walletapp.models.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source =".", target = "wallet")
    @Mapping(source =".", target = "role")
    @Mapping(source =".", target = "passwordSalt")
    User registerDtoToUser(RegisterDto registerDto, Role role, Wallet wallet, String passwordSalt);
}

