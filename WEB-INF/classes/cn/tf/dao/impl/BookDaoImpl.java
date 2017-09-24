package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.tf.dao.BookDao;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.utils.C3P0Util;

public class BookDaoImpl implements BookDao {

	private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	@Override
	public void addBook(Book book) {
		try {
			qr.update("insert into books(id,name,author,price,path,filename,des,categoryId,year,venue,code,status,seller) values (?,?,?,?,?,?,?,?,?,?,?,?,?)", 
					book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getPath(),
					book.getFilename(),book.getDes(),book.getCategory()==null?null:book.getCategory().getId(),book.getYear(),book.getVenue(),book.getCode(),book.getStatus(),book.getSeller());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public int getTotalRecordsNum() {
		
		try {
			Long num=(Long) qr.query("select count(id) from books ",new ScalarHandler(1));
			return num.intValue();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Book> findPageBooks(int startIndex, int size) {
	
		try {
			List<Book>  books=qr.query("select * from books limit ?,? ",new BeanListHandler<Book>(Book.class),startIndex,size);
			if(books!=null){
				for (Book book : books) {
					String sql = "select * from categorys where id=(select categoryId from books where id=?)";
					Category category = qr.query(sql, new BeanHandler<Category>(Category.class), book.getId());
					book.setCategory(category);
				}
			}
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public int getTotalRecordsNum(List validNames,List validValues) {
		try {
			String sql="select count(id) from books where ";
			for (int i=0;i<validNames.size();i++){
				if(i==0){
					sql += validNames.get(i).toString()+" like '%"+validValues.get(i).toString()+"%' ";
				}else{
					sql+="and "+validNames.get(i).toString()+" like '%"+validValues.get(i).toString()+"%' ";
				}
				
			}
			
			//"select count(id) from books where categoryId=? and name = ? "
			Long num=(Long) qr.query(sql,new ScalarHandler(1));
			System.out.println(num.intValue());
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	@Override
	public List<Book> findPageBooks(int startIndex, int pageSize,
			List validNames,List validValues) {
			String categoryId=null;
		try {
			String sql="select * from books where ";
			for (int i=0;i<validNames.size();i++){
				if(validNames.get(i).equals("categoryId")){
					categoryId = validValues.get(i).toString();
				}
				if(validNames.size()==1){
					sql+=validNames.get(i).toString()+" like '%"+validValues.get(i).toString()+"%' limit  ?,? ";
				}
				else if(i==0){
					sql += validNames.get(i).toString()+" like '%"+validValues.get(i).toString()+"%' ";
				}else if(i==validNames.size()-1){
					sql+="and "+validNames.get(i).toString()+" like '%"+validValues.get(i).toString()+"%' limit  ?,? ";
				}else{
					sql+="and "+validNames.get(i).toString()+" like '%"+validValues.get(i).toString()+"%' ";
					}
				}
			//"select * from books  where categoryId=? and name =? limit  ?,? "
			List<Book>  books=qr.query(sql,new BeanListHandler<Book>(Book.class),startIndex,pageSize);
			if(books!=null){
				for (Book book : books) {
					String sql2 = "select * from categorys where id=?";
					Category category = qr.query(sql2, new BeanHandler<Category>(Category.class),categoryId);
					book.setCategory(category);
				}
			}
			System.out.println(books.get(0).getName());
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	//根据多的查少的
	@Override
	public Book findOne(String bookId) {
		
		try {
			Book book = qr.query("select * from books where id=?", new BeanHandler<Book>(Book.class), bookId);
			if(book!=null){
					String sql = "select * from categorys where id=(select categoryId from books where id=?)";
					Category category = qr.query(sql, new BeanHandler<Category>(Category.class), book.getId());
					book.setCategory(category);
			}
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//更新书的状态
		public void update(String bookId,String status) {
			try {
				qr.update("update books set status = ? where id=?", status, bookId);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

}
