package com.example.bookstore.businessHandler;

import java.math.BigDecimal;

import com.example.bookstore.dataHandler.BookEntity;
import com.example.bookstore.endpoint.BookDO;

/**
 * @author amankapoor
 *	This class is POJO for the Book Business Object. 
 */
public class BookBO {

	Integer ISBN;
	String title;
	String author;
	BigDecimal price;
	
	public Integer getISBN() {
		return ISBN;
	}
	public void setISBN(Integer iSBN) {
		ISBN = iSBN;
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
	
	public static BookBO valueOf(BookEntity pBook)
	{
		BookBO bookBO = new BookBO();
		bookBO.setAuthor(pBook.getAuthor());
		bookBO.setISBN(pBook.getIsbn());
		bookBO.setPrice(pBook.getPrice());
		bookBO.setTitle(pBook.getTitle());
		return bookBO;
	}
	
	public static BookBO valueOf(BookDO pBook)
	{
		BookBO bookBO = new BookBO();
		bookBO.setAuthor(pBook.getAuthor());
		bookBO.setISBN(pBook.getIsbn());
		bookBO.setPrice(pBook.getPrice());
		bookBO.setTitle(pBook.getTitle());
		return bookBO;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{"+"\n Title:"+this.title + "\n Author:" + this.author + "\n ISBN:" + this.ISBN +"}";
	}
}
