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

@WebServlet("/AdminSignUpServlet")
public class AdminSignUpServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		Admin admin = new Admin(0, request.getParameter("username"), request.getParameter("password"));

		AdminDAOImpl adminDAOImpl = new AdminDAOImpl();

		AdminDAOImpl.initDataBase();
		boolean res = adminDAOImpl.Adminsignup(admin);

		if (res) {
			out.println("<h2>Sign Up Successfull !</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("AdminSignUp.jsp");
			rd.forward(request, response);
			response.sendRedirect("AdminSignUp.jsp?success=1");
		}

		else {
			out.println("<h2>There was some error, please try again</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("AdminSignUp.jsp");
			rd.include(request, response);
		}

	}

}