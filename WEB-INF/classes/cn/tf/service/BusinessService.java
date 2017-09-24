package cn.tf.service;

import java.sql.SQLException;
import java.util.List;

import cn.tf.bean.CartItem2;
import cn.tf.commons.Page;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.domain.Customer;
import cn.tf.domain.Order;
import cn.tf.domain.OrderItem;
import net.sf.json.JSONArray;

public interface  BusinessService {
	
	//添加分类
	void addCategory(Category category);
	
	//查询分类
	List<Category>  findAllCategories();
	
	//根据分类名称查询该分类是否可用
	boolean isCategoryExists(String categoryName);
	
	
	void delCategory(String categoryName);

	void changeCartItemNum(String code,String bookid,String num);
	
	void writeLog(String mark, String bookid, String bookname, String code, String username, String timestamp);
	
	void cleanLog();
	
	Page findLogPage(String num, String mark);
	
	void addCartItemtoDB(Book book,String code);
	
	void delCartItem(String code,String bookid);
	//添加书籍信息
	void addBook(Book book);

	
	//根据分类id查询分类信息
	Category findCategoryById(String categoryId);
	
	

	//根据用户要查看的页码，返回封装了所有分页信息的page对象
	Page findPage(String num);
	//HERE
	Page findCusPage(String num);

	
	//根据用户要查看的页码和种类，返回封装了所有分页信息的page对象
	Page findPage(String num, List validNames,List validValues );

	
	Book findBookById(String bookId);
	
	//用户注册
	void registCustomer(Customer customer);
	
	Customer findByUsername(String username);
	
	Customer findByEmail(String email);
	//根据激活码获取用户信息
	Customer findByCode(String code);
	
	//激活客户的账户
	void activeCustomer(Customer customer);
	//HERE
	
	void banCustomer(Customer customer);
	
	//根据用户名或密码登录，如果账户没有激活就返回null
	Customer login(String username,String password);

	//生成订单
	void genOrder(Order order);

	//根据订单号查找订单
	Order findOrderByNum(String ordernum);

	//更新订单信息
	void updateOrder(Order order);
	
	//更新订单状态
	void changeOrderStatus(int status,String ordernum);

	//更新书的状态
	void updateBook(String bookId,String status);
		
	List<Order> findOrdersByCustomerId(String customerId);
	
	List<OrderItem> findOrderItemByOrderNum(String ordernum);

	Customer updateCustomer(Customer customer);
	List<JSONArray> generateJSON() throws SQLException;
	void writeEntities(List<Book> booklist) throws SQLException;
	

}
