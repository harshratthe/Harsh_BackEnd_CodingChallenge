package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.BooksDTO;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Exceptions.ISBNAlreadyExistsException;

public interface BooksService {

	List<BooksDTO> getAllBooks();

    BooksDTO getBookByIsbn(String isbn) throws BookNotFoundException;

    void addBook(BooksDTO bookDto) throws ISBNAlreadyExistsException;

    void updateBook(String isbn, BooksDTO bookDto) throws BookNotFoundException;

    void deleteBookByIsbn(String isbn) throws BookNotFoundException;
	
	
	
}
