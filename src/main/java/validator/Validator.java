package validator;

import dao.DAO;
import model.Admin;
import model.User;

public class Validator {
	
	public static boolean userExists(String email) {
		DAO dao = new DAO();
		User user = null;
		try {
			user = dao.selectUser(email);
		} catch (Exception e) {
			e.getMessage();
		}
		if(user != null)
			return true;
		return false;

	}

	public static boolean isAdmin(String email) {
		DAO dao = new DAO();
		Admin admin = null;
		try {
			admin = dao.selectAdmin(email);
		} catch (Exception e) {
			e.getMessage();
		}
		if(admin != null)
			return true;
		return false;
	}

	///////////////////////////////////////////////////
	public static boolean isValidEmail(String email) {

		if( email != null && !email.isEmpty() && email.contains("@") )   
			return true;
		return false;
			
	}
	
}
