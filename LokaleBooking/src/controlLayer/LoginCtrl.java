package controlLayer;

import databaseLayer.IFUserDB;
import databaseLayer.UserDB;
import modelLayer.PowerUser;
import modelLayer.Student;
import modelLayer.User;

public class LoginCtrl {
	
	private static User currentUser;
	private static UserCtrl uCtrl = new UserCtrl();
	private static IFUserDB uDB = new UserDB();
	
	public static User getCurrentUser(){
		return currentUser;
	}
	
	public static void setCurrentUser(User currentUser){
		LoginCtrl.currentUser = currentUser;
	}
	
	public static boolean studentLogin(int studentNo) {
		Student s = uCtrl.selectStudent(studentNo);
		String studentPattern = "\\d{7}";
		if(uCtrl.checkPattern(String.valueOf(studentNo), studentPattern)){
			if (!s.equals(null)) {
				LoginCtrl.setCurrentUser(s);
				return true;
			}	
		}
		return false;
	}
	
	public static boolean powerUserLogin(String id, String password) {
		PowerUser pu = uDB.selectPowerUser(id);

		if (!(pu == (null)) && pu.getPassword().equals(password)) {
			LoginCtrl.setCurrentUser(pu);
			System.out.println("aeg");
			return true;
		}
		return false;
	}	
}
