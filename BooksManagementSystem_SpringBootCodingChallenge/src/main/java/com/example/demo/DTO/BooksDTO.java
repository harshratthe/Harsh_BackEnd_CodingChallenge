package com.example.demo.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BooksDTO {

	 @NotBlank(message = "ISBN is required")
	    @Pattern(regexp = "^[0-9\\-]+$", message = "ISBN must contain only numbers and dashes")
	    private String isbn;
	 
	 
	 
	@NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

   

    @Min(value = 1900, message = "Publication year must be no earlier than 1900")
    @Max(value = 2025, message = "Publication year must be no later than 2025")
    private int publicationYear;



	public BooksDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BooksDTO(
			@NotBlank(message = "ISBN is required") @Pattern(regexp = "^[0-9\\-]+$", message = "ISBN must contain only numbers and dashes") String isbn,
			@NotBlank(message = "Title is required") String title,
			@NotBlank(message = "Author is required") String author,
			@Min(value = 1900, message = "Publication year must be no earlier than 1900") @Max(value = 2025, message = "Publication year must be no later than 2025") int publicationYear) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public int getPublicationYear() {
		return publicationYear;
	}



	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}



	@Override
	public String toString() {
		return "BooksDTO [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publicationYear="
				+ publicationYear + "]";
	}



	
	
	
}
