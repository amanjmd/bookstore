package com.example.bookstore.dataHandler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.businessHandler.BookBO;
import com.example.bookstore.businessHandler.ProductBO;

/**
 * @author amankapoor
 *	This is the main data Handler for the Application.
 */

@Component
public class BookStoreDataHandler implements IBookStoreDataHandler {

	@Autowired
	BookStoreRepository bookStoreRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.dataHandler.IBookStoreDataHandler#addBook(com.example.bookstore.businessHandler.ProductBO, com.example.bookstore.businessHandler.BookBO)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ProductEntity addBook(ProductBO pProduct,BookBO pBook ){
		try {
		BookEntity book = bookStoreRepository.findByIsbn(pProduct.getIsbn());
		if(book ==null) {
			book=bookStoreRepository.save(BookEntity.valueOf(pBook));
		}
		if(book != null) {
		ProductEntity newBook = ProductEntity.valueOf(pProduct);
		return productRepository.save(newBook);
		}}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.dataHandler.IBookStoreDataHandler#searchBookByTitle(java.lang.String)
	 */
	@Override
	public List<BookEntity> searchBookByTitle(String pTitle) {
		return bookStoreRepository.findAllByTitleStartingWith(pTitle) ;
	}
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.dataHandler.IBookStoreDataHandler#searchBookByAuthor(java.lang.String)
	 */
	@Override
	public List<BookEntity> searchBookByAuthor(String pAuthor) {
		return bookStoreRepository.findAllByAuthorStartingWith(pAuthor) ;
	}
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.dataHandler.IBookStoreDataHandler#searchBookByISBN(java.lang.Integer)
	 */
	@Override
	public BookEntity searchBookByISBN(Integer pISBN) {
		return bookStoreRepository.findByIsbn(pISBN) ;
	}
	
	
	 /* (non-Javadoc)
	 * @see com.example.bookstore.dataHandler.IBookStoreDataHandler#buyBook(com.example.bookstore.businessHandler.ProductBO)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ProductEntity buyBook(ProductBO pProduct){
		 ProductEntity productEntity = ProductEntity.valueOf(pProduct);
		 return productRepository.save(productEntity);
	}
	 
	
	 /* (non-Javadoc)
	 * @see com.example.bookstore.dataHandler.IBookStoreDataHandler#getAvailableProductForIsbn(java.lang.Integer)
	 */
	@Override
	public ProductEntity getAvailableProductForIsbn(Integer pIsbn){
		return productRepository.findAvailableProductForIsbn(pIsbn);
	}
}
