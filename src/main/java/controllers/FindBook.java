package controllers;

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

import model.Book;
import model.DATABASE_PATH;
import model.Genre;
import model.Status;


@WebServlet("/findBook")
public class FindBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FindBook() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String bookAuthor = request.getParameter("author");
		String bookTitle = request.getParameter("title");
		String bookGenre = request.getParameter("genre");
		
		String errorMsg = "";
		ArrayList<Book> bookSearch = new ArrayList<>();
		
		BufferedReader br;
		
		if(action != null) {
			if(action.equals("authorSearch")) {
				///////////////////////// Search author ///////////////////////////////
				if(bookAuthor != null && !bookAuthor.isEmpty()) {
					
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
							
							if( author.toLowerCase().contains(bookAuthor.toLowerCase()) ) {
								Book book = new Book(id, title, author, genre, publishYear, stock, Status.DOSTUPNA);
								bookSearch.add(book);
							}
							
						}
						br.close();
						request.setAttribute("bookSearch", bookSearch);
						request.getRequestDispatcher("show-book.jsp").forward(request, response);

					} catch (FileNotFoundException e) {
						errorMsg += "File not found";
						request.setAttribute("errorMsg", errorMsg);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}catch (NumberFormatException e) {
							errorMsg += "Author can't be number!";
							request.setAttribute("errorMsg", errorMsg);
							request.getRequestDispatcher("index.jsp").forward(request, response);
					} catch (IOException e) {
						errorMsg += "I/O error!";
						request.setAttribute("errorMsg", errorMsg);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					
				}else{
					errorMsg += "Enter author name first! It can't be number!";
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
			}else if(action.equals("titleSearch")) {
				///////////////////////// Search title ///////////////////////////////
				if(bookTitle != null && !bookTitle.isEmpty()) {
					
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
							
							if( title.toLowerCase().contains(bookTitle.toLowerCase()) ) {
								Book book = new Book(id, title, author, genre, publishYear, stock, Status.DOSTUPNA);
								bookSearch.add(book);
								
							}
							
						}
						br.close();
						request.setAttribute("bookSearch", bookSearch);
						request.getRequestDispatcher("show-book.jsp").forward(request, response);

					} catch (FileNotFoundException e) {
						errorMsg += "File not found";
						request.setAttribute("errorMsg", errorMsg);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}catch (NumberFormatException e) {
							errorMsg += "Author can't be number!";
							request.setAttribute("errorMsg", errorMsg);
							request.getRequestDispatcher("index.jsp").forward(request, response);
					} catch (IOException e) {
						errorMsg += "I/O error!";
						request.setAttribute("errorMsg", errorMsg);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					
				}else{
					errorMsg += "Enter title first! It can't be number!";
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
			}else if(action.equals("genreSearch")) {
				///////////////////////// Search genre ///////////////////////////////
				if(bookGenre != null && !bookGenre.isEmpty()) {
					boolean bookFound = false;
					try {
						br = new BufferedReader(new FileReader(DATABASE_PATH.DATABASE_PATH));
						String line;
						while ((line = br.readLine()) != null) {
							String[] bookData = line.split(";");
							
							int id = Integer.parseInt(bookData[0]);
							String title = bookData[1];
							String author = bookData[2];
							Genre genre = Genre.valueOf(bookData[3]);
							int publishYear = Integer.parseInt(bookData[4]);
							int stock = Integer.parseInt(bookData[5]);
							
							String strGenre = String.valueOf(genre);
							
							if( bookGenre.equals(strGenre)) {
								Book book = new Book(id, title, author, genre, publishYear, stock, Status.DOSTUPNA);
								bookSearch.add(book);
								bookFound = true;
							}
						}
						br.close();
						
						if (!bookFound) {
			                errorMsg += "No books in library with " + bookGenre + " genre";
			                request.setAttribute("errorMsg", errorMsg);
			                request.getRequestDispatcher("show-book.jsp").forward(request, response);
			            }else {
			            	request.setAttribute("bookSearch", bookSearch);
							request.getRequestDispatcher("show-book.jsp").forward(request, response);
			            }
				
					} catch (FileNotFoundException e) {
						errorMsg += "File not found";
						request.setAttribute("errorMsg", errorMsg);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					
					} catch (Exception e) {
						errorMsg += "I/O error!";
						request.setAttribute("errorMsg", errorMsg);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					
				}else{
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}

			}else {
				errorMsg += "Invalid action!";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} // action.equals() kraj
			
		} //null action
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
