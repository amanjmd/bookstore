package com.example.bookstore.endpoint;


import java.math.BigDecimal;

import com.example.bookstore.businessHandler.BookBO;

public class BookDO {

	Integer isbn;
	String title;
	String author;
	BigDecimal price;
	
	
	public Integer getIsbn() {
		return this.isbn;
	}
	public void setIsbn(Integer iSBN) {
		this.isbn = iSBN;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public static BookDO valueOf(BookBO pBook)
	{
		if(pBook != null) {
		BookDO bookDO = new BookDO();
		bookDO.setAuthor(pBook.getAuthor());
		bookDO.setIsbn(pBook.getISBN());
		bookDO.setPrice(pBook.getPrice());
		bookDO.setTitle(pBook.getTitle());
		return bookDO;
		}
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{"+"\n Title:"+this.title + "\n Author:" + this.author + "\n ISBN:" + this.isbn +"}";
	}
}
