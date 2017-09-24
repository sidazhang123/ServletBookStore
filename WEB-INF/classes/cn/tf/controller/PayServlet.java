package cn.tf.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tf.bean.CartItem;
import cn.tf.bean.NoticeList;
import cn.tf.domain.Customer;
import cn.tf.domain.OrderItem;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;
import cn.tf.utils.PaymentUtil;
import cn.tf.utils.SendMailThread;
import cn.tf.utils.SendReplyToSeller;

public class PayServlet extends HttpServlet {

	private BusinessService s = new BusinessServiceImpl();
	private Map<String, NoticeList> msg = new HashMap<String, NoticeList>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String ordernum = request.getParameter("ordernum");
		s.changeOrderStatus(1, ordernum);
		List<OrderItem> items = s.findOrderItemByOrderNum(ordernum);
		Customer buyer = (Customer) request.getSession().getAttribute("customer");
		for (OrderItem i : items) {

			String cus_code = i.getBook().getCode();
			String email = s.findByCode(cus_code).getEmail();
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			s.writeLog("buy", i.getBook().getId(),i.getBook().getName(), cus_code,buyer.getUsername(), timeStamp);
			if (msg.containsKey(email)) {
				NoticeList t = msg.remove(email);
				t.addBookName(i.getBook().getName());
				msg.put(email, t);
			} else {
				NoticeList t = new NoticeList();
				t.addBookName(i.getBook().getName());
				msg.put(email, t);
			}
		}
		
		for (Map.Entry<String, NoticeList> m : msg.entrySet()) {
			SendReplyToSeller smt = new SendReplyToSeller(m.getKey(), m.getValue());
			smt.start();
		}

		response.getWriter().write("Payment succeeds.");
		response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/servlet/ClientServlet?op=showOrders");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
