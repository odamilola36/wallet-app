package com.lomari.walletapp.service.serviceImpl;

import com.lomari.walletapp.models.User;
import com.lomari.walletapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAuthDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        Optional<User> userOptional = userRepository.findByEmail(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Email address not found"));
        user.setAccountNonLocked(true);
        return user;
    }
}
