package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DATABASE_PATH;


@WebServlet("/bookDelete")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public DeleteBook() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String delError = "";

		if (id != null && id.length() > 0) {

			try {
				int bookID = Integer.parseInt(id);

				File file = new File(DATABASE_PATH.DATABASE_PATH);
				File tempFile = new File("temp.txt");

				BufferedReader reader = new BufferedReader(new FileReader(file));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

				String line;
				boolean found = false;

				while ((line = reader.readLine()) != null) {
					
					String[] bookData = line.split(";");
					int idFromFile = Integer.parseInt(bookData[0]);
					
					if (idFromFile == bookID) {
						found = true;
						continue;
					}
					
					writer.write(line + "\n"); 
				}
				reader.close();
				writer.close();

				if (!found) {
					delError += "Book with this ID not found!";
					request.setAttribute("delError", delError);
					request.getRequestDispatcher("book-delete.jsp").forward(request, response);

				} else {
					file.delete();
					tempFile.renameTo(file);
					request.setAttribute("delSuccess", "Book is successfully deleted from the database!");
					request.getRequestDispatcher("book-delete.jsp").forward(request, response);
				}

			} catch (NumberFormatException e) {
				delError += "Book ID must be a number!";
				request.setAttribute("delError", delError);
				request.getRequestDispatcher("book-delete.jsp").forward(request, response);
			}
		}
	}


}
