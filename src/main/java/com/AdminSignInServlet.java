package com;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;
import model.AdminDAOImpl;

@WebServlet("/AdminSignInServlet")
public class AdminSignInServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String username= request.getParameter("username");
		String password=request.getParameter("password");
		if(username.equals("?") && password.equals("?"))
		{
			out.println("<h2>Admin Sign In Successfull !</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("AdminWelcome.html");
			rd.forward(request, response);
			
			
		}
		else
		{
		Admin admin = new Admin(0,username,password);
		
		AdminDAOImpl adminDAOImpl = new AdminDAOImpl();
		
		AdminDAOImpl.initDataBase();
		boolean res = adminDAOImpl.Adminsignin(admin);
		
		if(res) {
			out.println("<h2>Admin Sign In Successfull !</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("AdminWelcome.html");
			rd.forward(request, response);
		}
		
		else {
			out.println("<h2>Credentials did not match please try one more time</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("AdminSignUp.jsp");
			rd.include(request, response);
		}
		}

	}

}
