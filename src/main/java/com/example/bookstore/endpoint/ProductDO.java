package com.example.bookstore.endpoint;

import com.example.bookstore.businessHandler.BookBO;
import com.example.bookstore.businessHandler.ProductBO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductDO {
	Integer ProductId;
	
	boolean sold;
	
	Integer isbn;
	
	public Integer getIsbn() {
		return this.isbn;
	}
	public void setIsbn(Integer isbn) {
		this.isbn=isbn;
	}
	
	public Integer getProductId() {
		return ProductId;
	}
	public void setProductId(Integer productId) {
		ProductId = productId;
	}
	
	public boolean isSold() {
		return sold;
	}
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	
	static ProductDO valueOf(ProductBO pProduct) {
		ProductDO product = new ProductDO();
		product.setIsbn(pProduct.getIsbn());
		product.setProductId(pProduct.getProductId());
		product.setSold(pProduct.isSold());
		return product;
	}
	
}
