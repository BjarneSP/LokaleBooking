package controlLayer;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import databaseLayer.IFUserDB;
import databaseLayer.UserDB;
import modelLayer.PowerUser;
import modelLayer.Student;

public class UserCtrl {
	
	Student currentStudent;
	IFUserDB userDB = new UserDB();
	
	public Student getCurrentStudent() {
		return currentStudent;
	}

	public void setCurrentStudent(Student currentStudent) {
		this.currentStudent = currentStudent;
	}

	public boolean checkPattern(String input, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(input);
		if(m.matches()){
			return true;
		}
		return false;
	}

	public boolean updateStudent(int studentNo, String firstName, String lastName, String email) {
		if (!userDB.selectStudent(studentNo).equals(null)) {
			userDB.updateStudent(firstName, lastName, email, studentNo);
		}
		return false;
	}

	public boolean updatePowerUser(String puId, String password, String firstName, String lastName, String email) {
		if (!userDB.selectPowerUser(puId).equals(null)) {
			try {
				userDB.updatePowerUser(firstName, lastName, email, puId, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public boolean deleteStudent(int studentNo) {
		if (!userDB.selectStudent(studentNo).equals(null)) {
			userDB.deleteStudent(studentNo);
			return true;
		}
		return false;

	}

	public boolean deletePowerUser(String id) {
		if (!userDB.selectPowerUser(id).equals(null)) {
			userDB.deletePowerUser(id);
			return true; 
		}
		return false;
	}
	
	public boolean createStudent(int studentNo, String firstName, String lastName, String email){
		if(userDB.selectStudent(studentNo)==null){
			Student s = new Student(firstName, lastName, email, studentNo);
			try {
				userDB.addStudent(s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true; 
		}
		return false;
	}
	
	public boolean createPowerUser(String puId, String uPassword, String firstName, String lastName, String email){
		if(userDB.selectPowerUser(puId)==null){
			PowerUser pu = new PowerUser(puId, uPassword, firstName, lastName, email);
			try {
				userDB.addPowerUser(pu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true; 
		}
		return false; 	
	}
	
	public Student selectStudent(int studentNo){
		if(!(userDB.selectStudent(studentNo) == null)){
			Student s = userDB.selectStudent(studentNo);
			return s;
		}
		else{
			return null;
		}
	}
	
	public PowerUser selectPowerUser(String puId){
		if(!userDB.selectPowerUser(puId).equals(null)){
			PowerUser pu = userDB.selectPowerUser(puId);
			return pu;
		}
		else{
			return null;
		}
	}
}
