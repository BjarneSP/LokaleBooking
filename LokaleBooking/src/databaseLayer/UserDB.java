/**
 * Class "UserDB": Contains information about UserDB.
 * The class contains the following attributes for UserDB: firstName, lastName, email, userId, studentNo, id and password.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.PowerUser;
import modelLayer.Student;

public class UserDB implements IFUserDB {
	/**
	 * Fields
	 */
	// User
	String firstName = null;
	String lastName = null;
	String email = null;
	int userId = 0;
	// Student
	int studentNo = 0;
	// PowerUser
	String id = null;
	String password = null;
	
	/**
	 * addStudent are used for creating a new Student in the database.
	 * @param s
	 */
	@Override
	public void addStudent(Student s) throws SQLException {
		
		PreparedStatement preparedStat = null;
		
		
		String baseQuery = "INSERT INTO Users(firstName, lastName, email, typeId) "
						 + "VALUES(?, ?, ?, ?)"
						 + "INSERT INTO Students(userId, studentNo)"
						 + "VALUES(scope_identity(), ?)";
				
		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setString(1, s.getFirstName());
			preparedStat.setString(2, s.getLastName());
			preparedStat.setString(3, s.getEmail());
			preparedStat.setInt(4, 2);
			preparedStat.setInt(5, s.getStudentNo());
			preparedStat.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			con.rollback();
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
	
	/**
	 * addPowerUser are used for creating a new PowerUser in the database.
	 * @param pu
	 */
	@Override
	public void addPowerUser(PowerUser pu) throws SQLException {
		PreparedStatement preparedStat = null;
		String baseQuery = "INSERT INTO Users(firstName, lastName, email, typeId) "
				+ "VALUES(?, ?, ?, ?)"
				+ "INSERT INTO PowerUsers(userId, puId, uPassword)"
				+ "VALUES(scope_identity(), ?, ?)";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setString(1, pu.getFirstName());
			preparedStat.setString(2, pu.getLastName());
			preparedStat.setString(3, pu.getEmail());
			preparedStat.setInt(4, 1);
			preparedStat.setString(5, pu.getId());
			preparedStat.setString(6, pu.getPassword());
			preparedStat.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			con.rollback();
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
	
	/**
	 * updateStudent are used for updating details about a student in the database.
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param studentNo
	 */
	@Override
	public void updateStudent(String firstName, String lastName,  String email, int studentNo) {
		PreparedStatement preparedStat = null;
		String baseQuery = "UPDATE Users "
				+ "SET firstName = ?, lastName = ?, email = ? "
				+ "FROM Students "
				+ "INNER JOIN Users "
				+ "ON Users.userId = students.userId "
				+ "WHERE students.studentNo = ?";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setString(1, firstName);
			preparedStat.setString(2, lastName);
			preparedStat.setString(3, email);
			preparedStat.setInt(4, studentNo);
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
	
	/**
	 * updatePowerUser are used for updating details about a PowerUser in the database.
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param puId
	 * @param studentNo
	 */
	@Override
	public void updatePowerUser(String firstName, String lastName, String email, String puId, String uPassword) throws SQLException {
		PreparedStatement preparedStat = null;
		String baseQuery = "UPDATE Users "
				+ "SET firstName = ?, lastName = ?, email = ? "
				+ "FROM PowerUsers "
				+ "INNER JOIN Users "
				+ "ON Users.userId = powerusers.userid "
				+ "WHERE PowerUsers.puId = ? "
				+ "UPDATE PowerUsers "
				+ "SET uPassword = ?";
				

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setString(1, firstName);
			preparedStat.setString(2, lastName);
			preparedStat.setString(3, email);
			preparedStat.setString(4, puId);
			preparedStat.setString(5, uPassword);
			
			preparedStat.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			con.rollback();
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

	/**
	 * deleteStudent are used for deleting a Student depending on studentNo in the database.
	 * @param studentNo
	 */
	@Override
	public void deleteStudent(int studentNo) {
		PreparedStatement preparedStat = null;
		String baseQuery = "DELETE Users " 
						 + "FROM Students "
						 + "WHERE Users.userId = Students.userId AND Students.studentNo = ?";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setInt(1, studentNo);
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

	/**
	 * deletePowerUser are used for deleting a PowerUser depending on puId in the database.
	 * @param puId
	 */
	@Override
	public void deletePowerUser(String puId) {
		PreparedStatement preparedStat = null;
		String baseQuery = "DELETE Users "
						 + "FROM PowerUsers "
				         + "WHERE Users.userId = PowerUsers.userId AND PowerUsers.puId = ?";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setString(1, puId);
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

	/**
	 * selectStudent are used for getting a student depending on studentNo from the database.
	 * @param puId
	 */
	@Override
	public Student selectStudent(int studentNo) {
		Student getStudent = null;

		// variabler for data fra db
		ResultSet projectResult;

		// forbereder db access
		PreparedStatement preparedStat = null; // Representere en precompiled
												// SQL statement
		String baseQuery = "SELECT * " 
						 + "FROM Students " 
						 + "INNER JOIN Users "
						 + "ON Users.userId = Students.userId "
						 + "WHERE Students.studentNo = ?";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			// laver PreparedStatement object for parameteriseret queries
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setInt(1, studentNo);
			projectResult = preparedStat.executeQuery();

			while (projectResult.next()) {
				userId = projectResult.getInt("userId");
				firstName = projectResult.getString("firstName");
				lastName = projectResult.getString("lastName");
				email = projectResult.getString("email");
				studentNo = projectResult.getInt("studentNo");

				getStudent = new Student(userId, firstName, lastName, email, studentNo);
				System.out.println(getStudent.toString());
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
		return getStudent;
	}

	/**
	 * selectPowerUser are used for getting a student depending on puId from the database.
	 * @param puId
	 */
	@Override
	public PowerUser selectPowerUser(String puId) {
		
		PowerUser getPowerUser = null;
		// variabler for data fra db
		ResultSet projectResult;

		// forbereder db access
		PreparedStatement preparedStat = null; // Representere en precompiled
												// SQL statement
		String baseQuery = "SELECT * " 
						 + "FROM PowerUsers " 
						 + "INNER JOIN Users "
						 + "ON Users.userId = PowerUsers.userId "
						 + "WHERE puId = ?";

		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			// laver PreparedStatement object for parameteriseret queries
			preparedStat = (PreparedStatement) con.prepareStatement(baseQuery);
			preparedStat.setString(1, puId);
			projectResult = preparedStat.executeQuery();

			while (projectResult.next()) {
				userId = projectResult.getInt("userId");
				firstName = projectResult.getString("firstName");
				lastName = projectResult.getString("lastName");
				email = projectResult.getString("email");
				puId = projectResult.getString("puId");
				password =projectResult.getString("uPassword"); 

				getPowerUser = new PowerUser(userId, firstName, lastName, email, puId, password);
				System.out.println(getPowerUser.toString());
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
		return getPowerUser;
	}
}

