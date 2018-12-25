package com.example.bookstore;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.businessHandler.BookBO;
import com.example.bookstore.businessHandler.IBookBusinessHandler;
import com.example.bookstore.businessHandler.ProductBO;
import com.example.bookstore.endpoint.BookDO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {

	Integer ISBNCounter = 12345;

	@Autowired
	IBookBusinessHandler bookstoreBusinessHandler;
	
	@Test
	public void addBookTest() {
		BookDO book=createNewBookBOEntity();
		book.setIsbn(1);
		ProductBO returnBook=bookstoreBusinessHandler.addBook(book);
		// System.out.println(returnBook);
		assert(returnBook.getProductId() != null);
	}
	
	@Test
	public void searchBookByISBNTest() {
		BookDO addedBook=createNewBookBOEntity();
		addedBook.setIsbn(1000);
		 // System.out.println(addedBook.getISBN());
		Integer id = bookstoreBusinessHandler.addBook(addedBook).getProductId() ;
		// System.out.println(id);
		
		List<BookBO> AllBooks = bookstoreBusinessHandler.searchBook('I', addedBook.getIsbn().toString()) ;
		
		assert(!AllBooks.isEmpty());
	}
	
	@Test
	public void getMediaCoverage() {
	
		assert(bookstoreBusinessHandler.getMediaCoverage("quas").size()==26);
	}
	
	@Test
	public void searchBookByAuthorNameTest() {
		//Creating Test data
		BookDO addedBook=createNewBookBOEntity();
		BookDO addedBook2=createNewBookBOEntity();
		addedBook2.setAuthor("Bob");
		addedBook2.setIsbn(7896);
		BookDO addedBook3=createNewBookBOEntity();
		addedBook3.setIsbn(7897);
		addedBook3.setAuthor("Caroline");
		bookstoreBusinessHandler.addBook(addedBook);
		bookstoreBusinessHandler.addBook(addedBook);
		bookstoreBusinessHandler.addBook(addedBook2);
		bookstoreBusinessHandler.addBook(addedBook3);
		
		//List SHould Be empty if No authors Match the criteria 
		List<BookBO> AllBooks = bookstoreBusinessHandler.searchBook('A', "Z".toUpperCase()) ;
		for(BookBO lBook : AllBooks) {
			// System.out.println(lBook);
		}
		assert(AllBooks.isEmpty());
		
		//Author Starting With A
		AllBooks = bookstoreBusinessHandler.searchBook('A', "A".toUpperCase()) ;
		StringBuilder AuthorsFiltered = new StringBuilder(); 
		for(BookBO lBook : AllBooks) {
			AuthorsFiltered.append(lBook.getAuthor());
		}
		
		assert(AuthorsFiltered.toString().equals(addedBook.getAuthor().toUpperCase()));
	}
	
	@Test
	public void buyBookTest() {
	
		BookDO addedBook=createNewBookBOEntity();
		
		bookstoreBusinessHandler.addBook(addedBook);
		bookstoreBusinessHandler.addBook(addedBook);
		
		assert(bookstoreBusinessHandler.buyBook(addedBook.getIsbn()).isSold());
		assert(bookstoreBusinessHandler.buyBook(addedBook.getIsbn()).isSold());
		
		
		
	}
	private BookDO createNewBookBOEntity() {
		BookDO book = new BookDO();
		book.setAuthor("Alan");
		book.setIsbn(ISBNCounter++);
		book.setPrice(new BigDecimal(100.00));
		book.setTitle("Endgame");
		return book;
	}
}

