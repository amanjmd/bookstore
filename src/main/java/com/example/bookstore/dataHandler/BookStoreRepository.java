package com.example.bookstore.dataHandler;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreRepository extends JpaRepository<BookEntity, Integer> {

	//THis methods can be user to get like %BOB%
	//List<BookEntity> findAllByTitleContaining(String title);
//	List<BookEntity> findAllByAuthorContaining(String author);
	
	List<BookEntity> findAllByTitleStartingWith(String title);
	List<BookEntity> findAllByAuthorStartingWith(String author);
	BookEntity findByIsbn(Integer Isbn);
	
}
