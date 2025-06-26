package com.example.demo.service_Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.BooksDTO;
import com.example.demo.Entity.Books;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Exceptions.ISBNAlreadyExistsException;
import com.example.demo.Mapper.BooksMapper;
import com.example.demo.Repository.BookRepository;
import com.example.demo.service.BooksService;

import jakarta.transaction.Transactional;

@Service
public class BooksImplementation implements BooksService{

	@Autowired
    private BookRepository bookrepos;

    @Autowired
    private BooksMapper bookmapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public List<BooksDTO> getAllBooks() {
		List<Books>books=bookrepos.findAll();
		return books.stream().map(book->bookmapper.toDTO(book)).collect(Collectors.toList());	
		
	}

	
	@Override
	public BooksDTO getBookByIsbn(String isbn) throws BookNotFoundException {
		Books book=bookrepos.findByIsbn(isbn);
		if(book==null) {
			throw new BookNotFoundException("Book not found with ISBN: " + isbn);
		}
		return bookmapper.toDTO(book);
	}

	@Override
	@Transactional
	public void addBook(BooksDTO bookDto) throws ISBNAlreadyExistsException {
		 if (bookrepos.existsByIsbn(bookDto.getIsbn())) {
	            throw new ISBNAlreadyExistsException("Book with ISBN already exists: " + bookDto.getIsbn());
	        }
	        Books book = bookmapper.toEntity(bookDto);
	        book.setB_password(passwordEncoder.encode(book.getB_password()));

	        bookrepos.save(book);
		
	}

	@Override
	@Transactional
	public void updateBook(String isbn, BooksDTO bookDto) throws BookNotFoundException {
		Books existingBook = bookrepos.findByIsbn(isbn);
        if (existingBook == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }

        existingBook.setB_title(bookDto.getTitle());
        existingBook.setB_author(bookDto.getAuthor());
        existingBook.setB_publicationYear(bookDto.getPublicationYear());
        bookrepos.save(existingBook);
		
	}

	@Override
	@Transactional 
	public void deleteBookByIsbn(String isbn) throws BookNotFoundException {
		Books book = bookrepos.findByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }
        bookrepos.deleteByIsbn(isbn);
		
	}

}
