package controllers;


import model.Book;
import model.Genre;
import model.Status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

@WebServlet("/findBooks")
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Books() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		
		String action = request.getParameter("action");
		String error = "";


		//////////////////  List Books  //////////////////////////
		if (action.equals("listBooks")) {

			try {
				ArrayList<Book> books = new ArrayList<>();
				books = dao.selectAllBooks();
				
				request.setAttribute("list_all_books", books);
				request.getRequestDispatcher("list-books.jsp").forward(request, response);

			} catch (FileNotFoundException e) {
				error = "File not found: "+e.getMessage();
				request.setAttribute("error", error);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (IOException e) {
				error = "error: "+e.getMessage();
				request.setAttribute("error", error);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
				
		} else {
			error += "Invalid action!";
			request.setAttribute("error", error);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action") != null ? request.getParameter("action") : "";

		if(action.equals("bookDelete")) {
///////////////////////////////////// DELETE BOOK page ////////////////////////////////////
			request.getRequestDispatcher("book-delete.jsp").forward(request, response);
		}else if(action.equals("addBook")) {
///////////////////////////////////// ADD BOOK page ////////////////////////////////////
				request.getRequestDispatcher("add-book.jsp").forward(request, response);	
///////////////////////////////////// UPDATE COPIES page ////////////////////////////////////	
		}else if(action.equals("stockUpdate")) {
			request.getRequestDispatcher("update-book.jsp").forward(request, response);


		}
	}

}
