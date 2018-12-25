package com.example.bookstore.endpoint.Validators;

import com.example.bookstore.endpoint.BookDO;

public class RequestValidator {

	public static boolean isAddBookRequestValid(BookDO pBook)
	{
				if((pBook.getAuthor() == null || pBook.getAuthor().equals(""))) 
				{
					return false;
				}
				if(pBook.getIsbn()== null || pBook.getIsbn()==0) {
					return false;
				}
				if(pBook.getPrice()==null || pBook.getPrice().intValue()==0){
					return false;
				}
				if(pBook.getTitle()==null || pBook.getTitle().equals("")) {
					return false;
				}
			
		return true;
	}
}
