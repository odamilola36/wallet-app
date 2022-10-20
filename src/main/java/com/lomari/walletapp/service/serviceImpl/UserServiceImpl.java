package com.lomari.walletapp.service.serviceImpl;

import com.lomari.walletapp.models.User;
import com.lomari.walletapp.repository.RoleRepository;
import com.lomari.walletapp.repository.UserRepository;
import com.lomari.walletapp.repository.WalletRepository;
import com.lomari.walletapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, WalletRepository walletRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(User user) {
        roleRepository.save(user.getRole());
        walletRepository.save(user.getWallet());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }
}
