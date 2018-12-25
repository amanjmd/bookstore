package com.example.bookstore.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.businessHandler.BookBO;
import com.example.bookstore.businessHandler.IBookBusinessHandler;
import com.example.bookstore.businessHandler.ProductBO;
import com.example.bookstore.endpoint.Validators.RequestValidator;


@RestController
public class BookStoreEndpoint {

	@Autowired
	IBookBusinessHandler bookBusinessHandler;

	@RequestMapping(method=RequestMethod.POST,value = "/book",consumes=MediaType.APPLICATION_JSON,produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<BookDO> addBook(@RequestBody BookDO pBook ){
		System.out.println("Here");
//		BookDO pBook=null;
		if(!RequestValidator.isAddBookRequestValid(pBook)) {
			return ResponseEntity.badRequest().build();
		}
		if(bookBusinessHandler.addBook(pBook)== null){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return new ResponseEntity<>(pBook,HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET,value = "/book/isbn/{isbn}",produces=MediaType.APPLICATION_JSON)
	public  ResponseEntity<List<BookDO>> searchBookByIsbn(@PathVariable("isbn") String pIsbn) {
		try {
			Integer.parseInt(pIsbn);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		List<BookDO> AllBookDO = new ArrayList<BookDO>();
		List<BookBO> AllBookBO=bookBusinessHandler.searchBook('I', pIsbn);
		if(AllBookBO.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		AllBookBO.forEach((book)->AllBookDO.add(BookDO.valueOf(book)));
		return ResponseEntity.ok(AllBookDO);
	}
	
	@GetMapping(value="search/author/{author}")
	@ResponseBody
	public 	ResponseEntity<List<BookDO>> searchBookByAuthor(@PathVariable("author") String pAuthor ) {
		List<BookDO> AllBookDO = new ArrayList<BookDO>();
		List<BookBO> AllBookBO=bookBusinessHandler.searchBook('A', pAuthor.toUpperCase());
		if(AllBookBO.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		AllBookBO.forEach((book)->AllBookDO.add(BookDO.valueOf(book)));
		return ResponseEntity.ok(AllBookDO);
	}
	
	@GetMapping(value="search/title/{title}",produces=MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<List<BookDO>> searchBookByTitle(@PathVariable("title") String pTitle) {
		List<BookDO> AllBookDO = new ArrayList<BookDO>();
		List<BookBO> AllBookBO=bookBusinessHandler.searchBook('T', pTitle.toUpperCase());
		if(AllBookBO.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		AllBookBO.forEach((book)->AllBookDO.add(BookDO.valueOf(book)));
		return ResponseEntity.ok(AllBookDO);
	}
	
	@GetMapping(value="/coverage/{title}",produces=MediaType.APPLICATION_JSON)
	ResponseEntity<List<String>> getMediaCoverage(@PathVariable("title") String pTitle){
	List<String> AllTitles = bookBusinessHandler.getMediaCoverage(pTitle);
	if(AllTitles.isEmpty()) {
		return ResponseEntity.noContent().build();
	}
	return ResponseEntity.ok(AllTitles);
	}
	
	@PutMapping("/buy/{isbn}")
	@ResponseBody
	ResponseEntity<ProductDO> buyBook(@PathVariable("isbn") String pIsbn){
		Integer isbn=null;
		try {
			isbn=Integer.parseInt(pIsbn);
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		ProductBO newProduct = bookBusinessHandler.buyBook(isbn);
		if(newProduct==null)
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ProductDO.valueOf(newProduct)) ;
	}
}