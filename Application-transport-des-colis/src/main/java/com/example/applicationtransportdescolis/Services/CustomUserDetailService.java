package com.example.applicationtransportdescolis.Services;
import com.example.applicationtransportdescolis.Entities.User;
import com.example.applicationtransportdescolis.Repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepositorie userRepositorie;

    @Autowired
    public CustomUserDetailService(UserRepositorie userRepositorie) {
        this.userRepositorie = userRepositorie;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositorie.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
