package com.Login.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Login.Model.LoginValidator;
import com.Login.Model.Register;
import com.Login.Model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
	private Register register;
	private LoginValidator validator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		register = new Register();
		validator = new LoginValidator();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("login") != null) {
			//response.getWriter().println("<h3>login button is pressed</h3>");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (validator.validateUser(username, password))
				response.getWriter().println("<h3>Login Successfully</h3>");
			else {
				response.getWriter().println("<h3>Username/Password is incorrect!!</h3>");
				response.setHeader("Refresh", "4;login.html");
			}
		} else if (request.getParameter("create") != null) {
			// response.getWriter().println("<h3>create button is
			// pressed</h3>");
			user = new User(request.getParameter("name"), request.getParameter("username"),
					request.getParameter("email"), request.getParameter("password"));
			if (register.insert(user))
				response.getWriter().println(
						"<h3>Register Successfully</h3><p><i>Redirect to the login page. Don't Refresh!!<i></p>");
			response.setHeader("Refresh", "6;login.html");

		}
	}

}
