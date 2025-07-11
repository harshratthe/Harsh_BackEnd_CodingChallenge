package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Books {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int b_id;

	@Column
    private String isbn;
	

	
    @Column
    private String b_title;

    @Column
    private String b_author;

    

    @Column
    private int b_publicationYear;



	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Books(int b_id, String isbn, String b_title, String b_author, int b_publicationYear) {
		super();
		this.b_id = b_id;
		this.isbn = isbn;
		this.b_title = b_title;
		this.b_author = b_author;
		this.b_publicationYear = b_publicationYear;
	}



	public int getB_id() {
		return b_id;
	}



	public void setB_id(int b_id) {
		this.b_id = b_id;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getB_title() {
		return b_title;
	}



	public void setB_title(String b_title) {
		this.b_title = b_title;
	}



	public String getB_author() {
		return b_author;
	}



	public void setB_author(String b_author) {
		this.b_author = b_author;
	}



	public int getB_publicationYear() {
		return b_publicationYear;
	}



	public void setB_publicationYear(int b_publicationYear) {
		this.b_publicationYear = b_publicationYear;
	}



	@Override
	public String toString() {
		return "Books [b_id=" + b_id + ", isbn=" + isbn + ", b_title=" + b_title + ", b_author=" + b_author
				+ ", b_publicationYear=" + b_publicationYear + "]";
	}



	

	
	
	
}
