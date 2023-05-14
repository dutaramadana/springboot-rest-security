package com.program.restsecurity.service.impl;


import com.program.restsecurity.entity.User;
import com.program.restsecurity.repository.UserRepository;
import com.program.restsecurity.rest.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email: " + email + " is not found!"));
        return user;
    }


    public User registerUser(User user){

        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExist){
            throw new RuntimeException("Email already exist");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }


}
