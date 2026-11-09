package com.olak.tyotuntiseuranta.web.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;





@Service 
public class UserDetailsServiceimpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceimpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Käyttäjää ei löytynyt: " + username));

        return new org.springframework.security.core.userdetails.User(
                currentUser.getUsername(),
                currentUser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(currentUser.getRole())
        );
    }
}
