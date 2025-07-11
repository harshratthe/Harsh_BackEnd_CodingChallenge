package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.DTO.UserDTO;

public interface UserService {
	void registerUser(UserDTO userDto);
    UserDetails loadUserByUsername(String email);
}
