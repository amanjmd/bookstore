package com.example.bookstore.dataHandler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	@Query(value="select p from ProductEntity p where p.isbn = :isbn and sold='false' and rownum < 2")
	ProductEntity findAvailableProductForIsbn(@Param("isbn") Integer pIsbn);
}
