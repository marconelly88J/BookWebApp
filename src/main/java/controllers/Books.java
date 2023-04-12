package controllers;

import model.DATABASE_PATH;
import model.Book;
import model.Genre;
import model.Status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Books() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		String error = "";


		//////////////////  List Books  //////////////////////////
		if (action.equals("listBooks")) {

			ArrayList<Book> books = new ArrayList<>();
			BufferedReader br;

			try {
				br = new BufferedReader(new FileReader(DATABASE_PATH.DATABASE_PATH));
				String line;
				while ((line = br.readLine()) != null) {
					String[] bookData = line.split(";");
					int id = Integer.parseInt(bookData[0]);
					String title = bookData[1];
					String author = bookData[2];
					Genre genre = Genre.valueOf(bookData[3].toUpperCase());
					int publishYear = Integer.parseInt(bookData[4]);
					int stock = Integer.parseInt(bookData[5]);

					Book book = new Book(id, title, author, genre, publishYear, stock, Status.DOSTUPNA);
					books.add(book);
				}

				br.close();
				request.setAttribute("books", books);
				request.getRequestDispatcher("list-books.jsp").forward(request, response);

			} catch (FileNotFoundException e) {
				error = "File not found";
				request.setAttribute("error", error);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (IOException e) {
				error = "I/O error!";
				request.setAttribute("error", error);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
				
		} else {
			error += "Invalid input!";
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
