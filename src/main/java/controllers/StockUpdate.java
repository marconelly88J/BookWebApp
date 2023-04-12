package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DATABASE_PATH;


@WebServlet("/stockUpdate")
public class StockUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StockUpdate() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String stock = request.getParameter("stock");

		String errorMsg = "";

		if(action != null) {
			if(id != null && !id.isEmpty() && stock != null && !stock.isEmpty()) {

				try {
					int bookID = Integer.parseInt(id);
					int copies = Integer.parseInt(stock);

					try {
						boolean found = false;
						List<String> lines = Files.readAllLines(Paths.get(DATABASE_PATH.DATABASE_PATH));

						// trazim liniju koja pocinje sa unetim ID i menjam kopije
						for (int i = 0; i < lines.size(); i++) {

							String line = lines.get(i);
							if (line.startsWith(Integer.toString(bookID) + ";")) {

								String[] parts = line.split(";");
								parts[5] = Integer.toString(copies);
								lines.set(i, String.join(";", parts));
								found = true;
								break;
							}
						}
						if (found) {
							Files.write(Paths.get(DATABASE_PATH.DATABASE_PATH), lines);
							request.setAttribute("successMsg", "Copies successfully updated!");
							request.getRequestDispatcher("update-book.jsp").forward(request, response);
						} else {
							request.setAttribute("errorMsg", "Book ID not found in database!");
							request.getRequestDispatcher("update-book.jsp").forward(request, response);
						}

					} catch (FileNotFoundException e) {
						errorMsg += "Cant find DATA_BASE!";
						request.setAttribute("errorMsg", errorMsg);
						request.getRequestDispatcher("update-book.jsp").forward(request, response);
					}
				} catch (NumberFormatException e) {
					errorMsg += "Book ID and copies must be numbers!";
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("update-book.jsp").forward(request, response);
				}
			}else {
				errorMsg += "Must fill in both fields!";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("update-book.jsp").forward(request, response);
			}
		}

	}

}
