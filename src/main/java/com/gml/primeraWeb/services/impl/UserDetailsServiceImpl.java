package com.gml.primeraWeb.services.impl;


import com.gml.primeraWeb.entities.User;
import com.gml.primeraWeb.repositories.UserRepository;
import com.gml.primeraWeb.services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new MyUserDetails(user);
    }
}
