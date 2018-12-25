package com.example.bookstore.dataHandler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	@Query(value="select * from Product_Entity e where e.product_Id=(select min(p.product_id) from Product_Entity p where p.sold='false' group by p.isbn having p.isbn=:isbn)",nativeQuery=true)
	ProductEntity findAvailableProductForIsbn(@Param("isbn") Integer pIsbn);
}
