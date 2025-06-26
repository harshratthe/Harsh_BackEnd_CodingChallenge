package com.example.demo.service_Impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Books;
import com.example.demo.Repository.BookRepository;


@Service
public class BookDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private BookRepository bookRepository;

	@Override
	public UserDetails loadUserByUsername(String isbn) throws UsernameNotFoundException {
		Books book = bookRepository.findByIsbn(isbn);

		if (book != null) {

			return new org.springframework.security.core.userdetails.User(book.getIsbn(), book.getB_password(),
					Collections.emptyList());
		}

		throw new UsernameNotFoundException("User not found with isbn: " + isbn);
	}
	
	
	
}
