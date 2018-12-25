package com.example.bookstore.dataHandler;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.bookstore.businessHandler.BookBO;

/**
 * @author amankapoor
 *	This is the Entity class for the Book Entity
 */
@Entity
public class BookEntity {

	@Column
	@Id
	Integer isbn;
	@Column
	String title;
	@Column
	String author;
	@Column
	BigDecimal price;

	
	
	public Integer getIsbn() {
		return isbn;
	}
	public void setIsbn(Integer iSBN) {
		this.isbn=iSBN;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String pTitle) {
		this.title = pTitle;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String pAuthor) {
		this.author = pAuthor;
	}
	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal pPrice) {
		this.price = pPrice;
	}
	
	public static BookEntity valueOf(BookBO pBook)
	{
		BookEntity bookEntity = new BookEntity();
		bookEntity.setAuthor(pBook.getAuthor().toUpperCase());
		bookEntity.setIsbn(pBook.getISBN());
		bookEntity.setPrice(pBook.getPrice());
		bookEntity.setTitle(pBook.getTitle().toUpperCase());
		return bookEntity;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{"+"\n Title:"+this.title + "\n Author:" + this.author + "\n ISBN:" + this.isbn +"}";
	}
}
