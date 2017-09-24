package cn.tf.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.tf.dao.BookDao;
import cn.tf.dao.impl.BookDaoImpl;
import cn.tf.domain.Book;
import cn.tf.domain.Order;
import cn.tf.utils.C3P0Util;

public class Cart implements Serializable{
	private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	private Map<String, CartItem> items = new HashMap<String, CartItem>();
	private float price;//总价
	private int number;//总数量
	public Map<String, CartItem> getItems() {
		return items;
	}
	//向items中添加一项
	public void addBook2Items(Book book){
		//书在items中有：数量加1
		if(items.containsKey(book.getId())){
			CartItem item = items.get(book.getId());
			item.setNumber(item.getNumber()+1);
		}else{
		//没有：创建一个新项
			CartItem item = new CartItem(book);
			item.setNumber(1);
			items.put(book.getId(), item);
		
	
		}
		
	}
	public void addCartItemtoDB(Book book, String code)throws RuntimeException, SQLException{
			CartItem item = items.get(book.getId());
		
			if (item.getNumber()==1){
				qr.update("insert into cart (id,name,price,number,code) values(?,?,?,?,?)", book.getId(),book.getName(),book.getPrice(),item.getNumber(),code);
			}else{
			
				qr.update("update cart set number=? where code=? and id=?", item.getNumber(),code,book.getId());
			
			}
		
	}
	public void changeCartItemNum(String code,String bookid,String num){
		try {
			qr.update("update cart set number=? where code=? and id=?", num,code,bookid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	public void delCartItem(String code,String bookid){
		try {
			qr.update("delete from cart where code=? and id=?", code,bookid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void findCartItemsByCode(String code){
		try {
			BookDao bookDao=new BookDaoImpl();
			List<CartItem2> cart_list = qr.query("select * from cart where code=?", new BeanListHandler<CartItem2>(CartItem2.class), code);
			for(CartItem2 i:cart_list){
				CartItem item = new CartItem(bookDao.findOne(i.getId()));
				item.setNumber(Integer.parseInt(i.getNumber()));
				items.put(i.getId(), item);
			
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public float getPrice() {
		price = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			price+=me.getValue().getPrice();
		}
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumber() {
		number = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			number+=me.getValue().getNumber();
		}
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
