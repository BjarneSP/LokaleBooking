/**
 * Class "IFUserDB": Contains information about IFUserDB.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package databaseLayer;

import java.sql.SQLException;

import modelLayer.PowerUser;
import modelLayer.Student;

public interface IFUserDB {
	/**
	 * InterFace for the UserDB class.
	 * This InterFace is used for creating the methods UserDB need to contain.
	 * @param s
	 */
	public Student selectStudent(int studentNo);
	public PowerUser selectPowerUser(String id);
	
	public void addStudent(Student s)throws SQLException ;
	public void addPowerUser(PowerUser pu)throws SQLException ;
	
	public void updateStudent(String firstName, String lastName,  String email, int studentNo);
	public void updatePowerUser(String firstName, String lastName, String email, String puId, String uPassword) throws SQLException ;
	
	public void deleteStudent(int studentNo);
	public void deletePowerUser(String puId);
}
