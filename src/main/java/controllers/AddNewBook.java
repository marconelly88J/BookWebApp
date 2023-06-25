package controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAO;
import model.Book;

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

		DAO dao = new DAO();

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String genre = request.getParameter("genre");
		String publishYear = request.getParameter("publishYear");
		String stock = request.getParameter("stock");
		String imgPath = request.getParameter("imgPath");

		String errorMsg = "";
		
		String prefix = "img/";
		String sufix = ".jpg";
		String book_cover_path = prefix+imgPath+sufix;

		if (title != null && title.length() > 0 && author != null && author.length() > 0 &&
				genre != null && genre.length() > 0 && publishYear != null && publishYear.length() > 0 &&
				stock != null && stock.length() > 0 && imgPath != null && imgPath.length() > 0 ) {
			try {
				int pYear = Integer.parseInt(publishYear);
				int copies = Integer.parseInt(stock);
				Book book = new Book(title, author, pYear, copies, genre, book_cover_path);
				dao.insertBook(book);

				String successMsg = "New book added successfully!";
				request.setAttribute("successMsg", successMsg);
				request.getRequestDispatcher("add-book.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				errorMsg += "Some fields must be numbers!";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("add-book.jsp").forward(request, response);
			} 
		} else {
			errorMsg += "Must input all fields!<br>";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("add-book.jsp").forward(request, response);
		} 
	}
}
