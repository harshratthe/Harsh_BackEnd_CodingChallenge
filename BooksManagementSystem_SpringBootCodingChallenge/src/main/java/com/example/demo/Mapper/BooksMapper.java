package com.example.demo.Mapper;

import org.springframework.stereotype.Component;

import com.example.demo.DTO.BooksDTO;
import com.example.demo.Entity.Books;

@Component
public class BooksMapper {

	 public Books toEntity(BooksDTO dto) {
	        Books book = new Books();
	        book.setB_title(dto.getTitle());
	        book.setB_author(dto.getAuthor());
	        book.setIsbn(dto.getIsbn());
	        book.setB_publicationYear(dto.getPublicationYear());
	        
	        return book;
	    }
	
	 
	 
	 public BooksDTO toDTO(Books book) {
	        BooksDTO dto = new BooksDTO();
	        dto.setTitle(book.getB_title());
	        dto.setAuthor(book.getB_author());
	        dto.setIsbn(book.getIsbn());
	        dto.setPublicationYear(book.getB_publicationYear());
	       
	        return dto;
	    }
	 
	 
	
	
}
