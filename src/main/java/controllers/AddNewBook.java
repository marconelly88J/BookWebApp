package controllers;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.DATABASE_PATH;
import model.Genre;
import model.Status;


@WebServlet("/addNewBook")
public class AddNewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddNewBook() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String genre = request.getParameter("genre");
		String publishYear = request.getParameter("publishYear");
		String stock = request.getParameter("stock");

		String errorMsg = "";
	
			if (id != null && id.length() > 0 && title != null && title.length() > 0 && author != null && author.length() > 0 &&
					genre != null && genre.length() > 0 && publishYear != null && publishYear.length() > 0 &&
					stock != null && stock.length() > 0) {
				try {
					int bookID = Integer.parseInt(id);
					int pYear = Integer.parseInt(publishYear);
					int copies = Integer.parseInt(stock);
					Genre bookGenre = Genre.valueOf(genre.toUpperCase());
				
					Book book = new Book(bookID, title, author, bookGenre, pYear, copies, Status.DOSTUPNA);

					try {
						PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_PATH, true));

						writer.println(book.getId() + ";" + "\"" + book.getTitle() + "\"" + ";" + book.getAuthor() + ";" +
								book.getGenre() + ";" + book.getPublishYear() + ";" + book.getStock() + ";");
						writer.flush();
						writer.close();

						String successMsg = "New book added successfully!";
						request.setAttribute("successMsg", successMsg);
						request.getRequestDispatcher("add-book.jsp").forward(request, response);
						
					} catch (FileNotFoundException e) {
						errorMsg += "Cant write to database!<br>";
						request.setAttribute("errorMsg", errorMsg);
					}

				} catch (NumberFormatException e) {
					errorMsg += "Some fields must be numbers!";
					request.setAttribute("errorMsg", errorMsg);
				} 
			} else {
				errorMsg += "Must input all fields!<br>";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("add-book.jsp").forward(request, response);
			} 
		
		
	}

}
