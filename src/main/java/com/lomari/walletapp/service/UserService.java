package com.lomari.walletapp.service;

import com.lomari.walletapp.models.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);

    Optional<User> getUser(String username);

    void saveUser(User user);
}
