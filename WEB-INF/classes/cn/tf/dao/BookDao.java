package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Book;

public interface  BookDao {

   void addBook(Book book);
	
	int getTotalRecordsNum();
	
	List<Book> findPageBooks(int startIndex,int size);

	int getTotalRecordsNum(List validNames,List validValues);

	List<Book> findPageBooks(int startIndex, int pageSize, List validNames,List validValues);

	Book findOne(String bookId);
	void update(String bookId,String status);

	
	
	
	

}
