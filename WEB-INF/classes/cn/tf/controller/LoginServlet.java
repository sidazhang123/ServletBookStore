package cn.tf.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tf.domain.User;
import cn.tf.service.PrivilegeService;
import cn.tf.service.impl.PrivilegeServiceImpl;

public class LoginServlet extends HttpServlet {

	private PrivilegeService s=new PrivilegeServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=s.login(username,password);
		if(user==null){
			response.getWriter().write("Wrong username or password");
			response.setHeader("Refresh","2;URL="+request.getContextPath()+"/passport/login.jsp");
			return;
		
		}
		
		request.getSession().setAttribute("user",user);
		response.getWriter().write("log in successful");
		response.setHeader("Refresh","1;URL="+request.getContextPath()+"/manage/index.jsp");
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
