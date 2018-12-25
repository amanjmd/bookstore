package com.example.bookstore.dataHandler;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.businessHandler.BookBO;
import com.example.bookstore.businessHandler.ProductBO;

public interface IBookStoreDataHandler {

	/**
	 * @param pProduct
	 * @param pBook
	 * @return 
	 * Decription : 
	 * This method creates the persistance entry for the product . 
	 * It checks if the Book Entity is present for the Isbn, It just saves the product . 
	 * if the Book details is not there in Book Entity , 
	 * it creates an entry for the book Entity and created the product for the same
	 * 
	 */
	ProductEntity addBook(ProductBO pProduct, BookBO pBook);

	/**
	 * @param pTitle
	 * @return
	 * This method returns the book match with the tiles starting with pTitile
	 */
	List<BookEntity> searchBookByTitle(String pTitle);

	/**
	 * @param pAuthor
	 * @return
	 * This method returns the book match with the tiles starting with pAuthor
	 */
	List<BookEntity> searchBookByAuthor(String pAuthor);

	/**
	 * @param pISBN
	 * @return
	 * This method returns the book match with the pISBN
	 */
	BookEntity searchBookByISBN(Integer pISBN);

	/**
	 * @param pProduct
	 * @return
	 */
	ProductEntity buyBook(ProductBO pProduct);

	/**
	 * @param pIsbn
	 * @return
	 * This Method Checks if a unsold product is available for the giver Isbn
	 */
	ProductEntity getAvailableProductForIsbn(Integer pIsbn);

}