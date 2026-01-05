package org.example.bnx.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bnx.user.userRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceimpl implements UserDetailsService {
    private final userRepo repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repository.findByEmail(userEmail)

                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userEmail));
    }
}
