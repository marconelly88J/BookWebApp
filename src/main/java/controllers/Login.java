package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAO;
import model.Admin;
import model.Book;
import model.User;
import validator.Validator;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Login() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// terminate user session and redirect to login page
		
		
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = new DAO();
		String action = request.getParameter("action");
		String email = request.getParameter("email");

		if(action != null) {

			if(Validator.isAdmin(email)) {
				Admin admin = new Admin(email);
				request.getSession().setAttribute("admin_session", admin);
				response.sendRedirect("index_admin.jsp");
			}else {
				// validacija email-a za email/user login
				if(Validator.isValidEmail(email)) {
					// da li postoji email u bazi
					if( Validator.userExists(email)) {
						User user = new User(email);
						request.getSession().setAttribute("user_session", user);
						response.sendRedirect("index_user.jsp");
					}else {
						User newUser = new User(email);
						dao.insertUser(email);
						request.getSession().setAttribute("user_session", newUser);
						response.sendRedirect("index_user.jsp");
					}
				}else {
					String msg = "Must enter valid email!"; 
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		}
	}




}
