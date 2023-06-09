package dao;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Admin;
import model.Book;
import model.User;

import java.util.ArrayList;
import java.util.List;


public class DAO {
      private DataSource ds;

	private static String SELECT_ALL_BOOKS = "SELECT * FROM books";
	private static String SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
	private static String INSERT_BOOK = "INSERT INTO books (`id`, `title`, `author`, `genre`, `publish_year`, `copies`, `imgPath`)"
															+ " VALUES (NULL, ?, ?, ?, ?, ?, ? )";
	
	
	private static String UPDATE_BOOK_COPIES = "UPDATE `books` SET `copies`= ? WHERE `id` = ?";
	private static String DELETE_BOOK_BY_ID = "DELETE FROM books WHERE id = ?";
	
	private static String INSERT_USER = "INSERT INTO `users` (`id`, `email`) VALUES (NULL, ?)";
	private static String CHECK_USER = "SELECT * FROM `users` WHERE email = ?";
	
	private static String CHECK_ADMIN = "SELECT * FROM `admins` WHERE username = ?";
	
	public DAO(){
		
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
	
	public ArrayList<Book> selectAllBooks(){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<Book> listOfBooks = new ArrayList<>();
		Book book = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_ALL_BOOKS);
		
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				
				book = new Book();
				
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setGenre(rs.getString("genre"));
				book.setPublishYear(rs.getInt("publish_year"));
				book.setStock(rs.getInt("copies"));
				book.setImgPath(rs.getString("imgPath"));
				
				listOfBooks.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBooks; 
	}
	
	/////////////////////////////////////////////////////////
	
public Book selectBookById(int id){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		Book book = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_BOOK_BY_ID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){ 
				book = new Book();
				
				book.setId(rs.getInt("id")); 
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setGenre(rs.getString("genre"));
				book.setPublishYear(rs.getInt("publish_year"));
				book.setStock(rs.getInt("copies"));
				book.setImgPath(rs.getString("imgPath"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
///////////////////////////////////////////////////////////

public void updateBookCopies(int id, int newCopies){
	
	Connection con = null;
    PreparedStatement pstm = null;
    
    try {
    	
        con = ds.getConnection();
        pstm = con.prepareStatement(UPDATE_BOOK_COPIES);
        pstm.setInt(1, newCopies);
        pstm.setInt(2, id);
        
		pstm.execute();
		
	} catch (SQLException e) {
		e.printStackTrace();
		e.getMessage();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
		e.getMessage();
	}
	
}

//////////////////////////////////////////////////////////

public void insertBook(Book book) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(INSERT_BOOK);
		
		pstm.setString(1, book.getTitle());
		pstm.setString(2, book.getAuthor());
		pstm.setString(3, book.getGenre());
		pstm.setInt	  (4, book.getPublishYear());
		pstm.setInt   (5, book.getStock());
		pstm.setString(6, book.getImgPath());
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
///////////////////////////////////////////////////////////

public void deleteBookById(int id) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(DELETE_BOOK_BY_ID);
		pstm.setInt(1, id);
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

/////////////////////////////////////////////////////////////
	
public void insertUser(String email) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(INSERT_USER);
		
		pstm.setString(1, email);
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
/////////////////////////////////////////////////////

public User selectUser(String email) {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	User user = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(CHECK_USER);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		pstm.setString(1, email);
		pstm.execute();
		rs = pstm.getResultSet();
		
		if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
        }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return user;
}

///////////////////////////////////////////////////////////////////

public Admin selectAdmin(String email) {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Admin admin = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(CHECK_ADMIN);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		pstm.setString(1, email);
		pstm.execute();
		rs = pstm.getResultSet();
		
		if(rs.next()) {
			admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setUsername(rs.getString("username"));
        }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return admin;
}
/////////////////////////////////////////

}
