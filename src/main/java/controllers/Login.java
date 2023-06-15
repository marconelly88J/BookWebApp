package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

import model.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Login() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = new DAO();
		String action = request.getParameter("action");
		String email = request.getParameter("email");

		if(action != null) {

			if(isAdmin(email)) {
				User admin = new User();
				request.getSession().setAttribute("admin_session", admin);
				response.sendRedirect("index_admin.jsp");
			}else {

				// validacija email-a za user login
				if(checkEmail(email)) {
					// da li postoji user_email u bazi
					if( userExists(email)) {
						User user = new User(email);
						HttpSession userSession = request.getSession();
						userSession.setAttribute("user_session", user);
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

	public boolean userExists(String email) {
		DAO dao = new DAO();
		User user = dao.selectUser(email);
		if(user != null)
			return true;
		return false;

	}

	public boolean isAdmin(String email) {
		if(email.equals("admin"))
			return true;
		return false;
	}

	///////////////////////////////////////////////////
	public boolean checkEmail(String email) {

		if( email == null || email.isEmpty() || !email.contains("@") ) 
			return false;
		else 
			return true;

	}


}
