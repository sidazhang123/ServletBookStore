package cn.tf.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tf.bean.Cart;
import cn.tf.bean.CartItem;
import cn.tf.commons.Page;
import cn.tf.dao.BookDao;
import cn.tf.dao.impl.BookDaoImpl;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.domain.Customer;
import cn.tf.domain.Order;
import cn.tf.domain.OrderItem;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;
import cn.tf.utils.OrderNumUtil;
import cn.tf.utils.SendMailThread;
import cn.tf.utils.WebUtil;
import net.sf.json.JSONArray;

public class ClientServlet extends HttpServlet {

	private BusinessService s = new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("op");
		List<Category>  cs=s.findAllCategories();
		request.getSession().setAttribute("cs", cs);
		if ("listBooks".equals(op)) {
			listBooks(request, response,op);
		} else if ("listBookByCategory".equals(op)) {
			listBookByCategory(request, response,op);
		} else if ("buyBook".equals(op)) {
			buyBook(request, response);
		} else if ("delOneItem".equals(op)) {
			delOneItem(request, response);
		} else if ("changeNum".equals(op)) {
			changeNum(request, response);
		} else if ("showCart".equals(op)) {
			showCart(request, response);
		} else if ("customerRegist".equals(op)) {
			customerRegist(request, response);
		} else if ("activeCustomer".equals(op)) {
			activeCustomer(request, response);
		} else if ("login".equals(op)) {
			login(request, response,op);
		} else if ("logout".equals(op)) {
			logout(request, response);
		} else if ("genOrder".equals(op)) {
			genOrder(request, response);
		} else if ("showOrders".equals(op)) {
			showOrders(request, response);
		} else if ("listOrderItems".equals(op)) {
			listOrderItems(request, response);
		} else if ("changeCustomerInfo".equals(op)) {
			changeCustomerInfo(request, response);
		}else if ("advancedsearch".equals(op)){
			advancedsearch(request,response);
		}else if("detail".equals(op)){
			detail(request,response);
		}else if ("welcome".equals(op)){
			welcome(request,response,op);
		}else if("sale".equals(op)){
			sale(request,response);
		}else if ("pause".equals(op)){
			pause(request,response);
		}else if ("relist".equals(op)){
			relist(request,response);
		}else if ("vis".equals(op)){
			try {
				vis(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	private void listOrderItems(HttpServletRequest request, HttpServletResponse response)throws
	IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("temp", null);
		List<OrderItem> items = s.findOrderItemByOrderNum(request.getParameter("ordernum"));
		
		request.setAttribute("itemList", items);
		request.getRequestDispatcher("/listOrderItems.jsp").forward(request, response);
		
	}
	
	// changeCustomerInfo
	private void changeCustomerInfo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			response.getWriter().write("log in first");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath());
			return;
		}
		boolean wrongPW = false;
		String code = customer.getCode();
		Customer cus = s.findByCode(code);
		if ((request.getParameter("originalpw") != null || request.getParameter("originalpw") != "")
				&& !customer.getPassword().equals(cus.getPassword())) {
			wrongPW = true;

		} else {
			if(request.getParameter("originalpw").toString().length()>1&&request.getParameter("newpw").toString().length()>1){
				cus.setPassword(request.getParameter("newpw"));
			}
		}
//		if (request.getParameter("username").equals("admin")
//				|| s.findByUsername(request.getParameter("username")) != null) {
//			response.getWriter().write("Invalid username.");
//			response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/personalInfo.jsp");
//		} else 
		if (request.getParameter("email").toString()!=customer.getEmail().toString() && s.findByEmail(request.getParameter("email")) != null) {
			response.getWriter().write("Invalid Email.");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/personalInfo.jsp");
		}
		cus.setAddress(request.getParameter("address"));
		cus.setFirstname(request.getParameter("firstname"));
		cus.setLastname(request.getParameter("lastname"));
		cus.setEmail(request.getParameter("email"));
		cus.setNickname(request.getParameter("nickname"));
		cus.setPhone(request.getParameter("phone"));
		cus.setCreditcardno(request.getParameter("creditcardno"));
		cus.setYearofbirth(request.getParameter("yearofbirth"));

		session.setAttribute("customer", cus);
		s.updateCustomer(cus);
		response.getWriter().write("Update completes.\n");
		if (wrongPW == true) {
			response.getWriter().write("But password is not changed because the input is wrong.");
		}
		response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/personalInfo.jsp");

	}

	// 订单详情
	private void showOrders(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 检测是否登录；
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			response.getWriter().write("Log in first");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath()+"/login.jsp");
			return;
		}
		List<Order> orders = s.findOrdersByCustomerId(customer.getId());
		request.setAttribute("orders", orders);

		request.getRequestDispatcher("/listOrders.jsp").forward(request, response);

	}

	// 生成订单
	private void genOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 检测是否登录；
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			response.getWriter().write("Log in first");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath()+"/login.jsp");
			return;
		}

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		Order order = new Order();
		order.setOrdernum(OrderNumUtil.genOrderNum());
		order.setPrice(cart.getPrice());
		order.setNumber(cart.getNumber());
		order.setCustomer(customer);

		List<OrderItem> oItems = new ArrayList<OrderItem>();
		// 设置订单项
		for (Map.Entry<String, CartItem> me : cart.getItems().entrySet()) {
			OrderItem item = new OrderItem();
			item.setId(UUID.randomUUID().toString());
			item.setNumber(me.getValue().getNumber());
			item.setPrice(me.getValue().getPrice());
			item.setBook(me.getValue().getBook());
			oItems.add(item);
		}
		// 建立和订单的关系
		order.setItems(oItems);
		s.genOrder(order);
		session.setAttribute("order", order);
		session.setAttribute("cart", null);
		response.getWriter().write("Order generated.");
		response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/servlet/ClientServlet?op=showOrders");

	}

	// 注销登录
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("customer");
		response.getWriter().write("log out successful！");
		response.setHeader("Refresh", "2;URL=" + request.getContextPath());

	}

	// 用户登录
	private void login(HttpServletRequest request, HttpServletResponse response,String op) throws IOException, ServletException {
		request.getSession().setAttribute("temp1", op);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Customer customer = s.login(username, password);
		if (customer == null) {
			response.getWriter().write("Wrong username or password，or still not active！");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/login.jsp");
			return;
		}
		if (customer.getIsbanned() == true) {
			response.getWriter().write("you are banned！");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/login.jsp");
			return;
		}
		request.getSession().setAttribute("customer", customer);
		showCart(request, response);
		response.getWriter().write("Log in successful!");
		response.setHeader("Refresh", "2;URL=" + request.getContextPath());

	}

	// 激活邮箱
	private void activeCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String code = request.getParameter("code");

		Customer customer = s.findByCode(code);
		if (customer == null) {
			response.getWriter().write("Something wrong, please active again");
			return;
		}
		customer.setActived(true);
		s.activeCustomer(customer);
		response.getWriter().write("Active successful!");
		response.setHeader("Refresh", "2;URL=" + request.getContextPath());

	}

	// 新用户注册，发送激活邮件
	private void customerRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 封装数据
		Customer customer = WebUtil.fillBean(request, Customer.class);
		customer.setCode(UUID.randomUUID().toString());

		// 发送邮件,单独启动一个线程
		SendMailThread smt = new SendMailThread(customer);
		smt.start();

		if (s.findByUsername(customer.getUsername()) != null || customer.getUsername().equals("admin")) {
			response.getWriter().write("Invalid username.");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/regist.jsp");
		} else if (s.findByEmail(customer.getEmail()) != null || customer.getEmail().equals("")
				|| customer.getEmail() == null) {
			response.getWriter().write("Invalid Email.");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/regist.jsp");
		}
		s.registCustomer(customer);

		request.setAttribute("message", "Regist successful ，we have sent an email to the email address  " + customer.getEmail() + ", please use it to active your account");
		request.getRequestDispatcher("/message.jsp").forward(request, response);

	}

	// load cart table into session attribute "cart" when login.
	private void showCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		System.out.println("customer"+customer);
		if (customer == null) {
			response.getWriter().write("Log in first please!");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath()+"/login.jsp");
			return;
		}
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		Customer cus = (Customer) request.getSession().getAttribute("customer");
		cart.findCartItemsByCode(cus.getCode());
		session.setAttribute("cart", cart);
		if(session.getAttribute("temp1")==null){
			
			request.getRequestDispatcher("/showCart.jsp").forward(request, response);
		}
		session.setAttribute("temp1",null);
	}

	// 改变数量
	private void changeNum(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String bookId = request.getParameter("bookId");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		CartItem item = cart.getItems().get(bookId);
		item.setNumber(Integer.parseInt(request.getParameter("num")));
		Customer cus = (Customer) request.getSession().getAttribute("customer");
		cart.changeCartItemNum(cus.getCode(), bookId, request.getParameter("num"));
		response.sendRedirect(request.getContextPath() + "/showCart.jsp");
	}

	// 删除购物车中的一项
	private void delOneItem(HttpServletRequest request, HttpServletResponse response) throws IOException {


		String bookId = request.getParameter("bookId");
		Book book = s.findBookById(bookId);

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.getItems().remove(bookId);
		Customer cus = (Customer) request.getSession().getAttribute("customer");
		cart.delCartItem(cus.getCode(), bookId);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		s.writeLog("rmv", bookId, book.getName(), cus.getCode(),cus.getUsername(), timeStamp);
		response.sendRedirect(request.getContextPath() + "/showCart.jsp");

	}

	// 加入购物车
	private void buyBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 得到书籍
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		System.out.println("customer"+customer);
		if (customer == null) {
			response.getWriter().write("Log in first please!");
			response.setHeader("Refresh", "2;URL=" + request.getContextPath()+"/login.jsp");
			return;
		}
		String bookId = request.getParameter("bookId");
		Book book = s.findBookById(bookId);

		// 购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		Customer cus = (Customer) request.getSession().getAttribute("customer");
		
		cart.addBook2Items(book);
		try {
			cart.addCartItemtoDB(book, cus.getCode());
		} catch (RuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 提示
		request.setAttribute("message", "Purchase successfully！<a href='javascript:window.history.back()'>Return</a>");
		try {
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 按照分类查询书籍的分页信息
	private void listBookByCategory(HttpServletRequest request,
			HttpServletResponse response,String op) {
		List<Category>  cs=s.findAllCategories();
		request.setAttribute("cs", cs);
	     Field[] allAttribute = Book.class.getDeclaredFields();
		//查询所有商品的分页数据
		String num=request.getParameter("num");
//		String categoryId=request.getParameter("categoryId");
//		String title = request.getParameter("name");
		 Enumeration<String> allNames = request.getParameterNames();
		 ArrayList<String> validNames = new ArrayList<String>();
		 ArrayList<String> validValues = new ArrayList<String>();
		 while(allNames.hasMoreElements()){
			 String currentName = allNames.nextElement();
			 for (int i=0;i<allAttribute.length;i++){
				 if (currentName.equals(allAttribute[i].getName()) && request.getParameter(currentName)!=""){
					 validNames.add(currentName);
					 validValues.add(request.getParameter(currentName));
				 }
			 }
			 
		 }
		 validNames.add("status");
		 validValues.add("1");
		
		Page page=s.findPage(num,validNames,validValues);
		ArrayList<Book> booklist = new ArrayList<Book>();
		if (page!=null){
			request.getSession().setAttribute("temp", op);
			for (int i=0;i< page.getRecords().size();i++){
				Book book = (Book)page.getRecords().get(i);
				String bookid = book.getId();
				booklist.add(s.findBookById(bookid));
			}
			System.out.println("booklist:"+booklist);
			page.setRecords(booklist);
			request.getSession().setAttribute("sr",page.getRecords()); 
				String url = "/servlet/ClientServlet?op=listBookByCategory";
				for (int i =0; i<validNames.size();i++){
					url+="&"+validNames.get(i).toString()+"="+validValues.get(i).toString();
				}
				page.setUrl(url);
				request.setAttribute("page", page);
		}
		
		try {
			request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response,String op)
			throws ServletException, IOException {
		// 查询所有分类
		HttpSession session = request.getSession();
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getSession().setAttribute("temp", op);
		// 查询所有商品的分页数据
		String num = request.getParameter("num");
		Page page = s.findPage(num);
		page.setUrl("/servlet/ClientServlet?op=listBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/listBooks.jsp").forward(request, response);

	}
	//高级搜索
	private void advancedsearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询所有分类
		List<Category>  cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/advancedSearch.jsp").forward(request, response);
	}
	//detail
	private void detail(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		List<Category>  cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		request.setAttribute("option", request.getSession().getAttribute("temp"));
		request.getSession().setAttribute("temp", null);
		//得到书籍
		String bookId=request.getParameter("bookId");
		Book book=s.findBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}
	private void welcome(HttpServletRequest request,
			HttpServletResponse response,String op) throws ServletException, IOException {
		//查询所有分类
		HttpSession session = request.getSession();
		List<Category>  cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		
		//查询所有商品的分页数据
		String num=request.getParameter("num");
		ArrayList<String> validNames = new ArrayList<String>();
		ArrayList<String> validValues = new ArrayList<String>();
		validNames.add("status");
		validValues.add("1");
		Page page=s.findPage(num,validNames,validValues);
		if(page != null){
			request.getSession().setAttribute("temp", op);
			ArrayList<Book> randomBooks = new ArrayList<Book>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			if (page.getRecords().size()<10){
				
				request.setAttribute("page", page);
			}
			else{Boolean ten = false;
				while(ten==false){
					Random random = new Random();
					int j = random.nextInt(page.getRecords().size())%(page.getRecords().size());
					if (!list.contains(j)){
						randomBooks.add((Book)page.getRecords().get(j));
					}
					list.add(j);
					if (randomBooks.size()==10){
						ten=true;
					}
				}
				page.setRecords(randomBooks);
				request.setAttribute("page", page);
			}
			page.setUrl("/servlet/ClientServlet?op=welcome");
		}
		request.getRequestDispatcher("/welcome.jsp").forward(request, response);
	}
	private void sale(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category>  cs=s.findAllCategories();
		HttpSession session = request.getSession();
		session.setAttribute("temp", null);
		request.setAttribute("cs", cs);
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String code = customer.getCode();
		//查询所有商品的分页数据
		String num=request.getParameter("num");
		ArrayList<String> validNames = new ArrayList<String>();
		ArrayList<String> validValues = new ArrayList<String>();
		validNames.add("code");
		validValues.add(code);
		Page page=s.findPage(num,validNames,validValues);
		if(page !=null){
			page.setUrl("/servlet/ClientServlet?op=sale");
			request.setAttribute("page", page);
		}
		request.getRequestDispatcher("/saleManagement.jsp").forward(request, response);
	}
	//pause
		private void pause(HttpServletRequest request,
				HttpServletResponse response) throws IOException,ServletException{
			List<Category>  cs=s.findAllCategories();
			request.setAttribute("cs", cs);
			String bookId=request.getParameter("bookId");
			String status = "0";
			s.updateBook(bookId, status);
			sale(request,response);
		}
		//relist
		private void relist(HttpServletRequest request,
				HttpServletResponse response) throws IOException,ServletException{
			List<Category>  cs=s.findAllCategories();
			request.setAttribute("cs", cs);
			String bookId=request.getParameter("bookId");
			String status = "1";
			s.updateBook(bookId, status);
			sale(request,response);
		}
		// graph store and visualization
		private void vis(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException, SQLException {
			BookDao bookDao=new BookDaoImpl();
			//List<Book> b= bookDao.findPageBooks(0,5);            //if "sr" cannot pass the object well, use this line to do a test for my work.           
			List<Book> bb=(List<Book>) request.getSession().getAttribute("sr"); // This 2 lines shoule be able to perform the function.
			System.out.println("bb:"+bb);
			s.writeEntities(bb);
			List<JSONArray> data =s.generateJSON();
			request.getSession().setAttribute("data", data);
			response.sendRedirect(request.getContextPath() + "/graph.jsp");
			
//			s.writeEntities(b);
		}
		

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
