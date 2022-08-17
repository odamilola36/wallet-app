package com.lomari.walletapp.service;

import com.lomari.walletapp.models.User;

import java.util.Optional;

public interface UserService {
    void createUser(User user);

    Optional<User> getUser(String username);
}
