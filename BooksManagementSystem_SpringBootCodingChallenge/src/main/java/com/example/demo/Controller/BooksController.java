package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.BooksDTO;
import com.example.demo.Entity.Books;
import com.example.demo.service.BooksService;

import com.example.demo.util.JwtUtil;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/books")
public class BooksController {

	 @Autowired
	    private BooksService booksService;

	    // Add a new book (protected route)
	    @PostMapping("/add")
	    public ResponseEntity<BooksDTO> addBook(@RequestBody BooksDTO booksDto) {
	        booksService.addBook(booksDto);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("info", "Book Added Successfully");
	        return new ResponseEntity<>(booksDto, headers, HttpStatus.CREATED);
	    }

	    // Get all books (public route)
	    @GetMapping("/getAll")
	    public ResponseEntity<List<BooksDTO>> getAllBooks() {
	        List<BooksDTO> books = booksService.getAllBooks();
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("info", "All Books Fetched Successfully");
	        return new ResponseEntity<>(books, headers, HttpStatus.OK);
	    }

	    // Get book by ISBN (public route)
	    @GetMapping("/BookByIsbn/{isbn}")
	    public ResponseEntity<BooksDTO> getBookByIsbn(@PathVariable String isbn) {
	        BooksDTO bookDto = booksService.getBookByIsbn(isbn);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("info", "Book Fetched by ISBN");
	        return new ResponseEntity<>(bookDto, headers, HttpStatus.OK);
	    }

	    // Update book (protected route)
	    @PutMapping("/update/{isbn}")
	    public ResponseEntity<BooksDTO> updateBook(@PathVariable String isbn, @RequestBody BooksDTO booksDto) {
	        booksService.updateBook(isbn, booksDto);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("info", "Book Updated Successfully");
	        return new ResponseEntity<>(booksDto, headers, HttpStatus.OK);
	    }

	    // Delete book (protected route)
	    @DeleteMapping("/delete/{isbn}")
	    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
	        booksService.deleteBookByIsbn(isbn);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("info", "Book Deleted Successfully");
	        return new ResponseEntity<>("Book deleted successfully!", headers, HttpStatus.OK);
	    }
	
	
	
	 }
