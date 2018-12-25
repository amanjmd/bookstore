package com.example.bookstore.dataHandler;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.bookstore.businessHandler.ProductBO;

@Entity
public class ProductEntity {

	@Id
	@GeneratedValue
	Integer ProductId;
	
	@Column
	Integer isbn;
	
	@Column
	boolean sold;

	public Integer getProductId() {
		return ProductId;
	}

	public void setProductId(Integer productId) {
		ProductId = productId;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public static ProductEntity valueOf(ProductBO pBook) {
		ProductEntity product = new ProductEntity();
		product.setIsbn(pBook.getIsbn());
		product.setSold(pBook.isSold());
		if(pBook.getProductId() != null) {
			product.setProductId(pBook.getProductId());
		}
		return product;
	}
	
	
	
}
