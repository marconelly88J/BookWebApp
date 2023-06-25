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
import model.Book;


@WebServlet("/adminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AdminController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		DAO dao = new DAO();
		String action = request.getParameter("action");
		String errorMsg = "";
		
		switch (action) {
		case "addBook":
			request.getRequestDispatcher("add-book.jsp").forward(request, response);
			break;
			
		case "bookService":
			try {
				ArrayList<Book> books = new ArrayList<>();
				books = dao.selectAllBooks();
				
				request.setAttribute("list_all_books", books);
				request.getRequestDispatcher("book-service.jsp").forward(request, response);

			} catch (FileNotFoundException e) {
				errorMsg = "File not found: "+e.getMessage();
				request.setAttribute("error", errorMsg);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (IOException e) {
				errorMsg = "error: "+e.getMessage();
				request.setAttribute("error", errorMsg);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			request.getRequestDispatcher("book-service.jsp").forward(request, response);
			break;
		
		default:
			errorMsg += "Invalid action!"; 
			break;
		}
		
	}

}
