/**
 * Class "IFInstituteDB": Contains information about IFInstituteDB.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package databaseLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import modelLayer.CompositeLine;
import modelLayer.Department;
import modelLayer.Room;

public interface IFInstituteDB {
	/**
	 * InterFace for the InstituteDB class.
	 * This InterFace is used for creating the methods InstituteDB need to contain.
	 * @param s
	 */
	public boolean addDepartment(Department d) throws SQLException;
	public boolean addRoom(Room r) throws SQLException;
	public boolean addCompositeLine(CompositeLine cl) throws SQLException;
	
//	public boolean updateRoom(Room r, int roomID);
//	public boolean updateDepartment(int depID);
	
	public void deleteCompositeLine(int parentInstId)throws SQLException;		//opdater p� bjarnediagram
	public void deleteInstitution(int instID);
	
	public ArrayList<Room> getAllRooms();
	public ArrayList<Department> getAllComposites();
//	public Department getDepartment(String departmentPhone);
}
