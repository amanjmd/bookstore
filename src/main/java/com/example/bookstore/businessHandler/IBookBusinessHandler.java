package com.example.bookstore.businessHandler;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.endpoint.BookDO;

public interface IBookBusinessHandler {

	/**
	 * @param pBook : Domain Object which is received from Client
	 * @return ProductBO : Product Business Object is returned after a new Book is Added . 
	 * Description : If the Book with Isbn is present is in the Book Entity table , A new product is added . 
	 * 				 But is no book with the given Isbn is present , book with the Isbn is added and a new 
	 * 				 product is created with the given Isbn and sold flag = false;  
	 */
	ProductBO addBook(BookDO pBook);

	/**
	 * @param pSearchType 
	 			Permissible Values : I,A,T
	 * @param pSearchString : This is the input for searching a book by Author , title or Isbn
	 * @return
	 */
	List<BookBO> searchBook(char pSearchType, String pSearchString);

	/**
	 * @param pTitle : This is the title of the book for which media coverage is to be searched
	 * @return
	 */
	List<String> getMediaCoverage(String pTitle);

	/**
	 * @param pIsbn This is the Isbn , for which the book has to be bought
	 * @return ProductBO this is the product which is bought and 
	 * 		   it contains the details of the copy of the book that is being sold
	 */
	ProductBO buyBook(Integer pIsbn);

}