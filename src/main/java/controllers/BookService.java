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


@WebServlet("/BookService")
public class BookService extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BookService() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DAO dao = new DAO();
		String action = request.getParameter("action");
		String errorMsg = "";
		
		if(action != null) {
			
			switch (action) {
			case "deleteBook":
				
				try {
					dao.deleteBookById(Integer.parseInt(request.getParameter("id")));
					
					ArrayList<Book> books = new ArrayList<>();
					books = dao.selectAllBooks();
					
					request.setAttribute("list_all_books", books);
					request.getRequestDispatcher("book-service.jsp").forward(request, response);

				} catch (FileNotFoundException e) {
					errorMsg += "Book not found: "+e.getMessage();
					request.setAttribute("error", errorMsg);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} catch (IOException e) {
					errorMsg += "error: "+e.getMessage();
					request.setAttribute("error", errorMsg);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				request.getRequestDispatcher("book-service.jsp").forward(request, response);
				break;

			default:
				break;
			}
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
