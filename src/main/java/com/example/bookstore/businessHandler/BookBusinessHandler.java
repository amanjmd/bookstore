package com.example.bookstore.businessHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.bookstore.dataHandler.BookEntity;
import com.example.bookstore.dataHandler.IBookStoreDataHandler;
import com.example.bookstore.endpoint.BookDO;

/**
 * @author amankapoor
 * This class is the  primary handler of the business logic for the application.
 */

@Component
public class BookBusinessHandler implements IBookBusinessHandler {
	
	@Autowired
	IBookStoreDataHandler bookStoreDataHandler;
	
	private RestTemplate restTemplate;
	
	@Value("${com.example.bookstore.MediaCoverageUrl}")
	private String mediaCoverageUrl;
	
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.businessHandler.IBookBusinessHandler#addBook(com.example.bookstore.endpoint.BookDO)
	 */
	@Override
	@Transactional 	
	public ProductBO addBook(BookDO pBook){
		ProductBO newProduct = new ProductBO();
		BookBO book = BookBO.valueOf(pBook);
		newProduct.setSold(false);
		newProduct.setIsbn(book.getISBN());
		return  ProductBO.valueOf(bookStoreDataHandler.addBook(newProduct,book));
	}
	
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.businessHandler.IBookBusinessHandler#searchBook(char, java.lang.String)
	 */
	@Override
	public List<BookBO> searchBook(char pSearchType, String pSearchString ) {
		List<BookBO> allBook =new ArrayList<BookBO>();
		switch(pSearchType) {
		case 'I':
			allBook.add(
					BookBO.valueOf(bookStoreDataHandler.searchBookByISBN(Integer.valueOf(pSearchString))));
			break;
		case 'T':
			bookStoreDataHandler.searchBookByTitle(pSearchString).stream().forEach(
					(BookEntity book)->{
						allBook.add(BookBO.valueOf(book));
					}
					)
			;
			
			break;
		case 'A':
			bookStoreDataHandler.searchBookByAuthor(pSearchString+"%").forEach(
					(BookEntity book)->{
						allBook.add(BookBO.valueOf(book));
					});
			
			break;
		default:
			
			
		}
		return allBook ;
	}
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.businessHandler.IBookBusinessHandler#getMediaCoverage(java.lang.String)
	 */
	@Override
	public List<String> getMediaCoverage(String pTitle){
		List<String> filteredTitles =  new ArrayList<>();
		restTemplate = new RestTemplate();
//		ResponseEntity<MediaCoverageBO[]> response = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/posts/",
//				MediaCoverageBO[].class);
		
		ResponseEntity<MediaCoverageBO[]> response = restTemplate.getForEntity(mediaCoverageUrl,
				MediaCoverageBO[].class);
		
		List<MediaCoverageBO> allMediaCoverage=Arrays.asList(response.getBody());
		allMediaCoverage.stream().filter(
		(MediaCoverageBO coverage)-> coverage.getTitle().contains(pTitle) ||coverage.getBody().contains(pTitle) 
		).forEach(
				(MediaCoverageBO coverage)->filteredTitles.add(coverage.getTitle()));;
		return filteredTitles;
	}
	
	
	/* (non-Javadoc)
	 * @see com.example.bookstore.businessHandler.IBookBusinessHandler#buyBook(java.lang.Integer)
	 */
	@Override
	@Transactional
	public  ProductBO buyBook(Integer pIsbn){
		ProductBO availableProduct = getAvailableProductForIsbn(pIsbn);
		if(availableProduct != null)
		{
			availableProduct.setSold(true);
			return ProductBO.valueOf(bookStoreDataHandler.buyBook(availableProduct));
		}
		return null ;
	}
	
	private ProductBO getAvailableProductForIsbn(Integer pIsbn){
		return ProductBO.valueOf(bookStoreDataHandler.getAvailableProductForIsbn(pIsbn));
	}
}
