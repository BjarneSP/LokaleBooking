package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelLayer.CompositeLine;
import modelLayer.Department;
import modelLayer.EnumRoomType;
import modelLayer.Room;

public class InstituteDB implements IFInstituteDB {

	@Override
	public boolean addDepartment(Department d) throws SQLException {

		PreparedStatement preparedStat = null;
		String baseQuery = "INSERT INTO Cities(postalCode, city) " 
							+ "VALUES(?, ?) " 
						 + "INSERT INTO Institutions(name) "
						 	+ "VALUES(?) " 
						 + "INSERT INTO Departments(instId, dAddress, dNo, postalCode, phone) "
						 	+ "VALUES(scope_identity(), ?, ?, ?, ?) "
						 	+ "SELECT TOP 1 instId "
							+ "FROM Departments "
							+ "ORDER BY instId DESC";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);
			// Cities
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setInt(1, Integer.parseInt(d.getPostCode()));
			preparedStat.setString(2, d.getName());
			// Institutions
			preparedStat.setString(3, d.getName());
			// Departments
			preparedStat.setString(4, d.getAdress());
			preparedStat.setInt(5, d.getdNo());
			preparedStat.setInt(5, Integer.parseInt(d.getPostCode()));
			preparedStat.setString(6, d.getPhone());
			
			preparedStat.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		}
		return false;
	}

	@Override
	public boolean addRoom(Room r) throws SQLException {
		PreparedStatement preparedStat = null;
		String baseQuery = "INSERT INTO Institutions(name) " 
						 	+ "VALUES(?) "
						 + "INSERT INTO Rooms(instId, capacity, tv, projector, typeId) "
						 	+ "VALUES(scope_identity(), ?, ?, ?, ?) "
						 	+ "SELECT TOP 1 instId "
							+ "FROM Rooms "
							+ "ORDER BY instId DESC";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			// Institutions
			preparedStat.setString(1, r.getName());
			// Rooms
			preparedStat.setInt(2, r.getCapacity());
			preparedStat.setBoolean(3, r.isTv());
			preparedStat.setBoolean(4, r.isProjector());
			preparedStat.setString(5, r.getrType().toString());
			
			preparedStat.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		} 
		return false;
	}

	@Override
	public boolean addCompositeLine(CompositeLine cl) throws SQLException {
		PreparedStatement preparedStat = null;
		Connection con = null;
		
		String baseCompositeQuery = "INSERT INTO CompositeLines(amount, instId, depId) "
								+ "VALUES(1, ?, ?)";
		
		try {
			con = DBConnection.getInstance().getDBcon();
			preparedStat = (PreparedStatement) con.prepareStatement(baseCompositeQuery);
			String amount = null;
			int instId = 0;
			boolean depId = false;
			preparedStat.setString(1, amount);
			preparedStat.setInt(2, instId);			
			preparedStat.setBoolean(3, depId);				
			preparedStat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;	
	}

//	@Override
//	public boolean updateRoom(Room r, int roomID){
//		PreparedStatement preparedStat = null;
//		String baseQuery = "UPDATE Rooms " 
//						 		+ "SET capacity = ?, tv = ?, projector = ?, typeId = ? "
//						 + "WHERE Rooms.instId = ? "
//						 + "UPDATE Institutions "
//						 + " SET room";
//		
//		 "UPDATE Users "
//			+ "SET firstName = ?, lastName = ?, email = ? "
//			+ "FROM PowerUsers "
//			+ "INNER JOIN Users "
//			+ "ON Users.userId = powerusers.userid "
//			+ "WHERE PowerUsers.puId = ? "
//			+ "UPDATE PowerUsers "
//			+ "SET uPassword = ?";
//
//		Connection con = null;
//		try {
//			con = DBConnection.getInstance().getDBcon();
//			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
//			preparedStat.setInt(1, r.getCapacity());
//			preparedStat.setBoolean(2, r.isTv());
//			preparedStat.setBoolean(3, r.isProjector());
//			preparedStat.setString(4, r.getrType().toString());
//			preparedStat.setInt(6, );
//			preparedStat.executeUpdate();
//			return true;
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}

//	@Override
//	public boolean updateDepartment(int depID) {
//
//		PreparedStatement preparedStat = null;
//		String baseQuery = "UPDATE Departments " + "SET dAddress = ?, dNo = ?, postalCode = ?, phone = ? "
//				+ "WHERE Departments.instId = ?";
//
//		Connection con = null;
//		try {
//			con = DBConnection.getInstance().getDBcon();
//			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
//			preparedStat.setString(1, dAddress);
//			preparedStat.setInt(2, dNo);
//			preparedStat.setInt(3, postalCode);
//			preparedStat.setInt(4, phone);
//			preparedStat.setInt(6, depID);
//			preparedStat.executeUpdate();
//			return true;
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (con != null) {
//				try {
//					// con.close();
//				} catch (Exception innerE) {
//					System.out.println(innerE.getMessage());
//				}
//			}
//		}
//		return false;
//	}

	@Override
	public void deleteInstitution(int instID) {
		PreparedStatement preparedStat = null;
		String baseQuery = "DELETE Institutions " + "WHERE Institution.instId = ?";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setInt(1, instID);
			preparedStat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					// con.close();
				} catch (Exception innerE) {
					System.out.println(innerE.getMessage());
				}
			}
		}
	}

	@Override
	public void deleteCompositeLine(int parentInstId) throws SQLException {
		PreparedStatement selectStat = null;
		PreparedStatement deleteCompStat = null;
		PreparedStatement deleteInstStat = null;

		ResultSet selectResult;

		Connection con = null;
		ArrayList<Integer> depIds = new ArrayList<>();

		String selectQuery = "SELECT Compositelines.instId " + "WHERE Compositelines.depId = ?";

		String deleteCompQuery = "DELETE from Compositelines " + "WHERE Compositelines.depId = ?";

		String deleteInstQuery = "DELETE from Institutions " + "WHERE Institutions.instId = ?";

		try {

			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);

			selectStat = (PreparedStatement) con.prepareStatement(selectQuery);
			selectStat.setInt(1, parentInstId);
			selectResult = selectStat.executeQuery();

			while (selectResult.next()) {
				int instId = selectResult.getInt("instId");
				depIds.add(instId);
			}

			deleteCompStat = (PreparedStatement) con.prepareStatement(deleteCompQuery);
			deleteCompStat.setInt(1, parentInstId);
			deleteCompStat.executeQuery();

			for (int i : depIds) {
				deleteInstStat = (PreparedStatement) con.prepareStatement(deleteInstQuery);
				deleteInstStat.setInt(1, i);
				deleteInstStat.executeUpdate();
			}
			con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		} finally {
			if (con != null) {
				try {
					// con.close();
				} catch (Exception innerE) {
					System.out.println(innerE.getMessage());
				}
			}
		}

	}

	@Override
	public ArrayList<Room> getAllRooms() {
		
		ArrayList<Room> list = new ArrayList<Room>();
		ResultSet projectResult;
		Connection con = null;
		PreparedStatement preparedStat = null;
		
		String baseQuery = "SELECT Institutions.name, Rooms.instId, Rooms.capacity, Rooms.tv, Rooms.projector, RoomTypes.roomType "
						 + "FROM Rooms "
						 + "INNER JOIN Institutions "
						 	+ "ON Rooms.instId = Institutions.instId "
						 + "INNER JOIN RoomTypes "
						 	+ "ON RoomTypes.typeId = Rooms.typeId";

		try {
			con = DBConnection.getInstance().getDBcon();
			preparedStat = con.prepareStatement(baseQuery);
			projectResult = preparedStat.executeQuery();

			while (projectResult.next()) {
				String name = projectResult.getString("name");
				int capacity = projectResult.getInt("capacity");
				boolean tv = projectResult.getBoolean("tv");
				boolean projector = projectResult.getBoolean("projector");
				String roomType = projectResult.getString("roomType");
				int instId = projectResult.getInt("instId"); 
				Room r = new Room(instId, name, capacity, projector, tv, EnumRoomType.valueOf(roomType));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Department> getAllComposites() {
		String name = null;

		// finder project p� baggrund af nummer - ved at bruge PreparedStatement
		ArrayList<Department> list = new ArrayList<Department>();
//		ArrayList<Room> rList = new ArrayList<Room>();

		// variabler for data fra db
		ResultSet projectResult;
		ResultSet roomProjectResult;

		// forbereder db access
		PreparedStatement preparedStat = null; // Representere en precompiled
												// SQL statement
		PreparedStatement roomPreparedStat = null;

		String baseQuery = "SELECT DISTINCT Departments.instId, Institutions.name, Departments.dAddress, Departments.dNo, Cities.postalCode, Cities.city, Departments.phone "
				+ "FROM CompositeLines " 
					+ "INNER JOIN Departments " 
						+ "ON Departments.instId = CompositeLines.depId "
					+ "INNER JOIN Cities " 
							+ "ON Departments.postalCode = Cities.postalCode " 
					+ "INNER JOIN Institutions "
					+ "ON Institutions.instId = Departments.instId";

		String baseRoomQuery = "SELECT Institutions.instId, Institutions.name, Rooms.capacity, Rooms.tv, Rooms.projector, RoomTypes.roomType, CompositeLines.amount "
				+ "FROM Rooms " 
				+ "INNER JOIN CompositeLines " 
				+ "ON CompositeLines.instId = Rooms.instId "
				+ "INNER JOIN Departments " 
				+ "ON Departments.instId = CompositeLines.depId "
				+ "INNER JOIN Institutions " 
				+ "ON Institutions.instId = Rooms.instId " 
				+ "INNER JOIN RoomTypes "
				+ "ON RoomTypes.typeId = Rooms.typeId " 
				+ "WHERE Departments.instId = ?";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			// laver PreparedStatement object for parameteriseret queries
			preparedStat = con.prepareStatement(baseQuery);
			projectResult = preparedStat.executeQuery();

			while (projectResult.next()) {
				int instId = 0;
				String dAddress = null;
				int dNo = 0;
				;
				String postalCode = null;
				String city = null;
				String phone = null;
				instId = projectResult.getInt("instId");
				name = projectResult.getString("name");
				dAddress = projectResult.getString("dAddress");
				dNo = projectResult.getInt("dNo");
				postalCode = projectResult.getString("postalCode");
				city = projectResult.getString("city");
				phone = projectResult.getString("phone");
				Department d = new Department(instId, name, dAddress, dNo, postalCode, city, phone);
				list.add(d);
				// list.add(e)
				// list.add(name);
			}
			for (Department d : list) {
				roomPreparedStat = con.prepareStatement(baseRoomQuery);
				roomPreparedStat.setInt(1, d.getDepID());
				roomProjectResult = roomPreparedStat.executeQuery();
				int instId = 0;
				String roomType = null;

				while (roomProjectResult.next()) {
					instId = roomProjectResult.getInt("instId");
					name = roomProjectResult.getString("name");
					int capacity = roomProjectResult.getInt("capacity");
					boolean tv = roomProjectResult.getBoolean("tv");
					boolean projector = roomProjectResult.getBoolean("projector");
					roomType = roomProjectResult.getString("roomType");
					int amount = roomProjectResult.getInt("amount");
					Room r = new Room(instId, name, capacity, tv, projector, EnumRoomType.valueOf(roomType));
					
					CompositeLine cl = new CompositeLine(amount, r);
					d.addCompositeLine(cl);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				try {
					// con.close();
				} catch (Exception innerE) {
					System.out.println(innerE.getMessage());
				}
			}
		}

		return list;
	}

//	@Override
//	public Department getDepartment(String departmentPhone) {
//		Department dep = null;
//
//		String name = null;
//		String phone = null;
//		String postalCode = null;
//		String city = null;
//
//		// variabler for data fra db
//		ResultSet projectResult;
//
//		// forbereder db access
//		PreparedStatement preparedStat = null; // Representere en precompiled
//												// SQL statement
//		String baseQuery = "SELECT Institutions.name = ?, Departments.dAddress = ?, Departments.dNo = ?, Departments.postalCode = ?, Cities.city = ?, Departments.phone = ? "
//				+ "FROM Departments " + "INNER JOIN Institutions " + "ON Institutions.instId = Departments.instId "
//				+ "WHERE Departments.phone = ?";
//
//		Connection con = null;
//		try {
//			con = DBConnection.getInstance().getDBcon();
//			// laver PreparedStatement object for parameteriseret queries
//			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
//			preparedStat.setString(1, name);
//			preparedStat.setString(2, dAddress);
//			preparedStat.setInt(3, dNo);
//			preparedStat.setString(4, postalCode);
//			preparedStat.setString(5, city);
//			preparedStat.setString(6, phone);
//			preparedStat.setString(7, phone);
//			projectResult = preparedStat.executeQuery();
//
//			while (projectResult.next()) {
//				name = projectResult.getString("name");
//				dAddress = projectResult.getString("dAddress");
//				dNo = projectResult.getInt("dNo");
//				postalCode = projectResult.getString("postalCode");
//				city = projectResult.getString("city");
//				phone = projectResult.getString("phone");
//
//				dep = new Department(name, dAddress, dNo, postalCode, city, phone);
//				System.out.println(dep.toString());
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if (con != null) {
//				try {
//					// con.close();
//				} catch (Exception innerE) {
//					System.out.println(innerE.getMessage());
//				}
//			}
//		}
//		return dep;
//	}

	public ArrayList<Department> getAllInstitutions() {

		ResultSet projectResult;
		PreparedStatement preparedStat = null;
		Connection con = null;
		ArrayList<Department> parents = new ArrayList<>();

		String baseQuery = "SELECT Departments.instId, Institutions.name, Departments.dAddress, Departments.dNo, Cities.postalCode, Cities.city, Departments.phone "
				+ "FROM CompositeLines " 
					+ "INNER JOIN Departments " 
						+ "ON Departments.instId = CompositeLines.depId "
					+ "INNER JOIN Cities " 
						+ "ON Departments.postalCode = Cities.postalCode " 
					+ "INNER JOIN Institutions "
						+ "ON Institutions.instId = Departments.instId";

		try {
			con = DBConnection.getInstance().getDBcon();
			preparedStat = con.prepareStatement(baseQuery);
			projectResult = preparedStat.executeQuery();

			while (projectResult.next()) {
				int instId = projectResult.getInt("instId");
				String name = projectResult.getString("name");
				String dAddress = projectResult.getString("dAddress");
				int dNo = projectResult.getInt("dNo");
				String postalCode = projectResult.getString("postalCode");
				String city = projectResult.getString("city");
				String phone = projectResult.getString("phone");

				Department d = new Department(instId, name, dAddress, dNo, postalCode, city, phone);
				parents.add(d);
			}

//			for (Department dep : parents) {
//				ArrayList<CompositeLine> inst = getSubNodes(dep);
//				if (!inst.isEmpty()) {
//					for (CompositeLine cl : inst) {
//						dep.addCompositeLine(cl);
//					}
//				}
//			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		return parents;

	}

//	public ArrayList<CompositeLine> getSubNodes(Institution i) {
//		
//		ResultSet departmentResult;
//		ResultSet roomResult;
//		PreparedStatement departmentStat = null;
//		PreparedStatement roomStat = null;
//		Connection con = null;
//		
//		if(i instanceof Department){
//			
//			String departmentQuery = "SELECT * "
//								+ "FROM CompositeLines "
//								+ "INNER JOIN Departments "
//								+ "ON departments.instId = CompositeLines.instId "
//								+ "WHERE CompositeLines.depId = ?";
//			
//			departmentStat = con.prepareStatement(departmentQuery);
//			departmentStat.setInt(1,((Department) i).getDepID());
//			departmentResult = departmentStat.executeQuery();
//			
//			while (departmentResult.next()) {
//				int instId = departmentResult.getInt("instId");
//				String name = departmentResult.getString("name");
//				int capacity = departmentResult.getInt("capacity");
//				boolean tv = departmentResult.getBoolean("tv");
//				boolean projector = departmentResult.getBoolean("projector");
//				String roomType = departmentResult.getString("roomType");
//				String rStatus = departmentResult.getString("rStatus");
//				int amount = departmentResult.getInt("amount");
//				
//				Institution inst = 
//				
//				Room r = new Room(instId, name, capacity, tv, projector, EnumRoomType.valueOf(roomType));
//				r.setrStatus(EnumRoomStatus.valueOf(rStatus));
//				CompositeLine cl = new CompositeLine(amount, r, d);
//				d.addCompositeLine(cl);
//			}
//			
//			
//			getSubNodes();
//			
//		}
//		else if(i instanceof Room){
//			String roomQuery = "SELECT * "
//					+ "FROM CompositeLines "
//					+ "INNER JOIN Rooms "
//					+ "ON Rooms.instId = CompositeLines.instId "
//					+ "WHERE CompositeLines.depId = ?";
//			
//			roomStat = con.prepareStatement(roomQuery);
//			roomStat.setInt(1,((Room) i).getRoomID());
//			departmentResult = roomStat.executeQuery();
//		}
//		else{
//			return null;
//		}
//		
//		return null;
//	}
}
