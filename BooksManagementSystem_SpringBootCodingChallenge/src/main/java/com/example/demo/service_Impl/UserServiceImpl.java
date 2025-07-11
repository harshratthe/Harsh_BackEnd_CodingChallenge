package com.example.demo.service_Impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void registerUser(UserDTO userDto) {
		 if (userRepo.existsByEmail(userDto.getEmail())) {
		       
		        throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with this email");
		    }
		User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepo.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            Collections.emptyList()
        );
	}

}
