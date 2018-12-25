package com.example.bookstore.businessHandler;

import com.example.bookstore.dataHandler.ProductEntity;
import com.example.bookstore.endpoint.ProductDO;

/**
 * @author amankapoor
 *	This is the POJO for a product . This object is used for any business validation and modifications.
 */
public class ProductBO {
	Integer ProductId;
	Integer isbn;
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

	public static ProductBO valueOf(ProductEntity pProductEntity) {
		if(pProductEntity != null)
		{
			ProductBO product = new ProductBO();
			product.setSold(pProductEntity.isSold());
			product.setIsbn(pProductEntity.getIsbn());
			if(pProductEntity != null)
				product.setProductId(pProductEntity.getProductId());
			return product;
		}
		return null;
	}

	public static ProductBO valueOf(ProductDO pProductDO) {
		ProductBO product = new ProductBO();
		product.setSold(pProductDO.isSold());
		product.setIsbn(pProductDO.getIsbn());
		if(pProductDO != null)
			product.setProductId(pProductDO.getProductId());
		return product;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{" + "\nProduct Id :" + this.ProductId + "\n Book Details :\n" + this.getIsbn() + "\n Sold :" + this.isSold() +"\n}";
	}
}
