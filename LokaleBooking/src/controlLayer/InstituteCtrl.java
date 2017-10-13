package controlLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import databaseLayer.IFInstituteDB;
import databaseLayer.InstituteDB;
import modelLayer.CompositeLine;
import modelLayer.Department;
import modelLayer.PowerUser;
import modelLayer.Room;
import modelLayer.User;

public class InstituteCtrl {
	
	IFInstituteDB iDB;
	User u;
	
	public InstituteCtrl(){
		super();
		iDB = new InstituteDB();
	}
	
//	public ArrayList<Room> getAvailableRooms(String departmentPhone){
//		ArrayList<Room> allRooms = iDB.getAllRooms(departmentPhone);
//		ArrayList<Room> availableRooms = new ArrayList<>();
//		
//		for(Room r : allRooms){
//			if(!r.getrStatus().equals(EnumRoomStatus.FullyBooked)){
//				availableRooms.add(r);
//			}
//		}
//		return availableRooms;
//	}
	
	public boolean createDepartment(Department d){
		u = LoginCtrl.getCurrentUser();
		
		if(u instanceof PowerUser && !u.equals(null)){		
			try {
				iDB.addDepartment(d);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean createRoom(Room r){
		u = LoginCtrl.getCurrentUser();
		
		if(u instanceof PowerUser && !u.equals(null)){
			try {
				iDB.addRoom(r);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean createCompositeLine(CompositeLine cl) throws SQLException{
		u = LoginCtrl.getCurrentUser();
		
		if(u instanceof PowerUser && !u.equals(null)){
			iDB.addCompositeLine(cl);
			return true;
		}
		return false;
	}
	
//	public boolean updateRoom(int roomID){
//		u = LoginCtrl.getCurrentUser();
//		
//		if(u instanceof PowerUser && !u.equals(null)){
//			iDB.updateRoom(roomID);
//			return true;
//		}
//		return false;
//	}
	
//	public boolean updateDepartment(int depID){
//		u = LoginCtrl.getCurrentUser();
//		
//		if(u instanceof PowerUser && !u.equals(null)){
//			iDB.updateDepartment(depID);
//			return true;
//		}
//		return false;
//	}
	
	public void deleteInstitution(int instID){
		u = LoginCtrl.getCurrentUser();
		
		if(u instanceof PowerUser && !u.equals(null)){
			iDB.deleteInstitution(instID);
		}
	}
	
	public void deleteCompositeLine(int parentInstId){
		u = LoginCtrl.getCurrentUser();
		
		if(u instanceof PowerUser && !u.equals(null)){
			try {
				iDB.deleteCompositeLine(parentInstId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Room> getAllRooms(){
//		u = LoginCtrl.getCurrentUser();
		
//		if(u instanceof PowerUser && !u.equals(null)){
			return iDB.getAllRooms();
//		}
//		return null;
	}
	
	public ArrayList<Department> getAllComposites(){	
			return iDB.getAllComposites();
	}
	
//	public Department getDepartment(String departmentPhone){
//		u = LoginCtrl.getCurrentUser();
//		
//		if(u instanceof PowerUser && !u.equals(null)){
//			return iDB.getDepartment(departmentPhone);
//		}
//		return null;
//	}
}
