package controllers;


import model.Book;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;


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
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String error = "";

		if(action != null) {

			switch (action) {

			// LIST ALL BOOKS ///////////////////////////////////////
			case "listBooks":

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
				break;

			// Author Search //////////////////////////////////////
			case "authorSearch":

				if (author != null && !author.isEmpty()) {
					ArrayList<Book> booksByAuthor = new ArrayList<>();

					try {
						ArrayList<Book> books = dao.selectAllBooks();
						String searchAuthor = author.toLowerCase();

						for (Book book : books) {
							String bookAuthor = book.getAuthor().toLowerCase();
							if (bookAuthor.contains(searchAuthor)) {
								booksByAuthor.add(book);
							}
						}

						if(!booksByAuthor.isEmpty()) {
							request.setAttribute("booksSearch", booksByAuthor);
							request.getRequestDispatcher("user_search.jsp").forward(request, response);
						}else {
							error = "No books with the specified author.";
							request.setAttribute("error", error);
							request.getRequestDispatcher("index_user.jsp").forward(request, response);
						}

					} catch (FileNotFoundException e) {
						error = "Book not found: " + e.getMessage();
						request.setAttribute("error", error);
						request.getRequestDispatcher("index_user.jsp").forward(request, response);
					} catch (IOException e) {
						error = "Error: " + e.getMessage();
						request.setAttribute("error", error);
						request.getRequestDispatcher("index_user.jsp").forward(request, response);
					}
				} else {
					error += "Must enter author name first!";
					request.setAttribute("error", error);
					request.getRequestDispatcher("index_user.jsp").forward(request, response);
				}
				break;

			// Title Search ////////////////////////////////////	
			case "titleSearch":

				if (title != null && !title.isEmpty()) {
					ArrayList<Book> booksByTitle = new ArrayList<>();

					try {
						ArrayList<Book> books = dao.selectAllBooks();
						String searchTitle = title.toLowerCase();

						for (Book book : books) {
							String bookTitle = book.getTitle().toLowerCase();
							if (bookTitle.contains(searchTitle)) {
								booksByTitle.add(book);
							}
						}

						if(!booksByTitle.isEmpty()) {
							request.setAttribute("booksSearch", booksByTitle);
							request.getRequestDispatcher("user_search.jsp").forward(request, response);
						}else {
							error = "No books with the specified title.";
							request.setAttribute("error", error);
							request.getRequestDispatcher("index_user.jsp").forward(request, response);
						}

					} catch (FileNotFoundException e) {
						error = "Book not found: " + e.getMessage();
						request.setAttribute("error", error);
						request.getRequestDispatcher("index_user.jsp").forward(request, response);
					} catch (IOException e) {
						error = "Error: " + e.getMessage();
						request.setAttribute("error", error);
						request.getRequestDispatcher("index_user.jsp").forward(request, response);
					}
				} else {
					error += "Must enter title name first!";
					request.setAttribute("error", error);
					request.getRequestDispatcher("index_user.jsp").forward(request, response);
				}
				break;

			//////// Genre Search //////////////////////////////////////	
			case "genreSearch":

				if (genre != null && !genre.isEmpty()) {
					ArrayList<Book> booksByGenre = new ArrayList<>();

					try {
						ArrayList<Book> books = dao.selectAllBooks();
						
						for (Book book : books) {
							// String bookTitle = book.getTitle().toLowerCase();
							if (book.getGenre().equals(genre)) {
								booksByGenre.add(book);
							}
						}

						if(!booksByGenre.isEmpty()) {
							request.setAttribute("booksSearch", booksByGenre);
							request.getRequestDispatcher("user_search.jsp").forward(request, response);
						}else {
							error = "No books with the specified genre.";
							request.setAttribute("error", error);
							request.getRequestDispatcher("index_user.jsp").forward(request, response);
						}

					} catch (FileNotFoundException e) {
						error = "Book not found: " + e.getMessage();
						request.setAttribute("error", error);
						request.getRequestDispatcher("index_user.jsp").forward(request, response);
					} catch (IOException e) {
						error = "Error: " + e.getMessage();
						request.setAttribute("error", error);
						request.getRequestDispatcher("index_user.jsp").forward(request, response);
					}
				} else {
					error += "Must choose genre first!";
					request.setAttribute("error", error);
					request.getRequestDispatcher("index_user.jsp").forward(request, response);
				}
				break;

			default:
				error += "Invalid action!";
				request.setAttribute("error", error);
				request.getRequestDispatcher("index_user.jsp").forward(request, response);
				break;
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}


