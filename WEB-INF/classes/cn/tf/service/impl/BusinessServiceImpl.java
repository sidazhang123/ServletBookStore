package cn.tf.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import cn.tf.bean.Cart;
import cn.tf.bean.CartItem2;
import cn.tf.commons.Page;
import cn.tf.dao.BookDao;
import cn.tf.dao.CategoryDao;
import cn.tf.dao.CustomerDao;
import cn.tf.dao.GraphDao;
import cn.tf.dao.LogDao;
import cn.tf.dao.OrderDao;
import cn.tf.dao.impl.BookDaoImpl;
import cn.tf.dao.impl.CategoryDaoImpl;
import cn.tf.dao.impl.CustomerDaoImpl;
import cn.tf.dao.impl.GraphDaoImpl;
import cn.tf.dao.impl.LogDaoImpl;
import cn.tf.dao.impl.OrderDaoImpl;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.domain.Customer;
import cn.tf.domain.Log;
import cn.tf.domain.Order;
import cn.tf.domain.OrderItem;
import cn.tf.service.BusinessService;
import net.sf.json.JSONArray;

public class BusinessServiceImpl implements BusinessService {

	private CategoryDao categoryDao=new CategoryDaoImpl();
	private CustomerDao customerDao=new CustomerDaoImpl();
	private OrderDao orderDao=new OrderDaoImpl();
	private Cart cartDao=new Cart();
	private BookDao bookDao=new BookDaoImpl();
	private LogDao logDao = new LogDaoImpl();
	private GraphDao graphDao=new GraphDaoImpl();
	@Override
	public void addCategory(Category category){// TODO Auto-generated method stub
			category.setId(UUID.randomUUID().toString());
			categoryDao.save(category);
	}

	@Override
	public List<Category> findAllCategories() {
		
		return categoryDao.findAll();
	}
	@Override
	public void changeCartItemNum(String code,String bookid,String num){
		cartDao.changeCartItemNum(code, bookid, num);

	}
	
	@Override
	public void addCartItemtoDB(Book book,String code){
		try {
			cartDao.addCartItemtoDB(book, code);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void delCartItem(String code,String bookid){
		cartDao.delCartItem(code, bookid);
	}
	@Override
	public boolean isCategoryExists(String categoryName) {
		Category category=categoryDao.findByName(categoryName);
		return category==null?false:true;
	}

	@Override
	public void  delCategory(String categoryName) {
		categoryDao.deleteByName(categoryName);
		
	}

	@Override
	public void addBook(Book book) {
		if(book==null)
			throw new IllegalArgumentException("error");
		if(book.getCategory()==null)
			throw new IllegalArgumentException("error");
		book.setId(UUID.randomUUID().toString());
		bookDao.addBook(book);
		
	}
	public void updateBook(String bookId,String status){
		bookDao.update(bookId,status);
	}
	@Override
	public Category findCategoryById(String categoryId) {
		
		return categoryDao.findOne(categoryId);
	}

	@Override
	public Page findPage(String num) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum=bookDao.getTotalRecordsNum();
		Page page=new Page(pageNum,totalRecordsNum);
		List<Book>  records=bookDao.findPageBooks(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);

		return page;
	}
	
	@Override
	//HERE
	public Page findCusPage(String num) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum= customerDao.getAllCusNum();
		Page page=new Page(pageNum,totalRecordsNum);
		List<Customer>  records=customerDao.listCustomers(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);

		return page;
	}
	//HERE
	@Override
	public void banCustomer(Customer customer) {
		if(customer==null)
			throw new RuntimeException("数据不能为空");
		if(customer.getId()==null)
			throw new RuntimeException("更新数据的主键不能为空");
		
		customerDao.update(customer);
		
	}
	

	@Override
	public Page findPage(String num, List validNames,List validValues) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum=bookDao.getTotalRecordsNum( validNames, validValues);
		if (totalRecordsNum==0){
			return null;
		}
		Page page=new Page(pageNum,totalRecordsNum);
		List<Book>  records=bookDao.findPageBooks(page.getStartIndex(), page.getPageSize(),validNames,validValues);
		page.setRecords(records);

		return page;
	}

	@Override
	public Book findBookById(String bookId) {
		
		return bookDao.findOne(bookId);

	}
	@Override
	public Customer findByUsername(String username){
		return customerDao.findByUsername(username);
	}
	@Override
	public Customer findByEmail(String email){
		return customerDao.findByEmail(email);
	}

	@Override
	public void registCustomer(Customer customer) {
		customer.setId(UUID.randomUUID().toString());
		customerDao.save(customer);
		
	}

	@Override
	public Customer findByCode(String code) {
		
		return customerDao.findByCode(code);
	}

	@Override
	public void activeCustomer(Customer customer) {
		if(customer==null)
			throw new RuntimeException("数据不能为空");
		if(customer.getId()==null)
			throw new RuntimeException("更新数据的主键不能为空");
		
		customerDao.update(customer);
		
	}
	@Override
	public Customer updateCustomer(Customer customer){
		if(customer==null)
			throw new RuntimeException("数据不能为空");
		if(customer.getId()==null)
			throw new RuntimeException("更新数据的主键不能为空");
		
		customerDao.update(customer);
		return customer;
	}
	

	@Override
	public Customer login(String username, String password) {
		
		Customer customer = customerDao.find(username,password);
		if(customer==null)
			return null;
		if(!customer.isActived())
			return null;
		
		return customer;
	}

	//生成订单
	@Override
	public void genOrder(Order order) {
		if(order==null)
				throw new RuntimeException("订单不能为空");
		if(order.getCustomer()==null)
			throw new RuntimeException("订单的客户不能为空");
		orderDao.save(order);
		
	}

	@Override
	public Order findOrderByNum(String ordernum) {
		
		return orderDao.findByNum(ordernum);
	}

	@Override
	public void updateOrder(Order order) {
		orderDao.update(order);
		
	}

	@Override
	public void changeOrderStatus(int status, String ordernum) {
		Order order=findOrderByNum(ordernum);
		System.out.println(ordernum);
		System.out.println(order==null);
		order.setStatus(status);
		updateOrder(order);
	}
	@Override
	public void writeLog(String mark, String bookid,String bookname, String code,String username, String timestamp){
		logDao.append(mark, bookid,bookname, code,username, timestamp);
	}
	@Override
	public void cleanLog(){
		logDao.cleanAll();
	}
	@Override
	public Page findLogPage(String num, String mark) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum=logDao.recordNum();
		Page page=new Page(pageNum,totalRecordsNum);
		List<Log>  records=logDao.findPageLog(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);

		return page;
	}
	@Override
	public List<Order> findOrdersByCustomerId(String customerId) {
		
		return orderDao.findByCustomerId(customerId);
	}

	@Override
	public List<OrderItem> findOrderItemByOrderNum(String ordernum) {
	
		return orderDao.findOrderItem(ordernum);
	}
	public List<JSONArray> generateJSON() throws SQLException{
		return graphDao.generateJSON();
	}
	
	public void writeEntities(List<Book> booklist) throws SQLException{
		graphDao.writeEntities(booklist);
	}

}
