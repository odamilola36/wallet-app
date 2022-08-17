package com.lomari.walletapp.service.serviceImpl;

import com.lomari.walletapp.models.User;
import com.lomari.walletapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void createUser(User user) {

    }

    @Override
    public Optional<User> getUser(String username) {
        return Optional.empty();
    }
}
