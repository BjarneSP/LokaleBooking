/**
 * Class "BookingDB": Contains information about BookingDB.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelLayer.Booking;
import modelLayer.BookingLine;
import modelLayer.EnumRoomStatus;
import modelLayer.EnumRoomType;
import modelLayer.EnumWeekDay;
import modelLayer.Room;

public class BookingDB implements IFBookingDB {

	/**
	 * Adding a Booking object to the database
	 * 
	 * @param b
	 */
	@Override
	public boolean addBooking(Booking b) throws SQLException {
		Connection con = null;
		PreparedStatement getBooking = null;
		PreparedStatement insertBooking = null;
		PreparedStatement insertBookingLine = null;
		ResultSet getBookingResult = null;

		String queryInsertBooking = "INSERT INTO Bookings(userId) " 
								  + "VALUES(?)";
		
		String queryGetBooking = "SELECT TOP 1 bookingId "
							   + "FROM Bookings "
							   + "ORDER BY bookingId DESC";

		String queryInsertBookingLine = "INSERT INTO Bookinglines(roomId, bId, dayId, checkedIn, statusId) "
				 					  + "VALUES(? ,? ,? , ?, ?)";

		try {
			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);

			insertBooking = (PreparedStatement) con.prepareStatement(queryInsertBooking);
			insertBooking.setInt(1, b.getS().getUserId());
			System.out.println("UserID: " + b.getS().getUserId());
			insertBooking.executeUpdate();
			
			getBooking = (PreparedStatement) con.prepareStatement(queryGetBooking);
			int bookingId = 0;
			getBookingResult = getBooking.executeQuery();
			
			while(getBookingResult.next()){
				bookingId = getBookingResult.getInt("bookingId");
				System.out.println("BookingId: " + bookingId);
			}

			for (BookingLine bl : b.getBookingLines()) {
				insertBookingLine = (PreparedStatement) con.prepareStatement(queryInsertBookingLine);
				insertBookingLine.setInt(1, bl.getLineRoom().getRoomID());
					int dayId = 0;
					if (bl.getWeekDay() == EnumWeekDay.Mandag) {
						dayId = 1;  
					}
					if (bl.getWeekDay() == EnumWeekDay.Tirsdag) {
						dayId = 2;
					}
					if (bl.getWeekDay() == EnumWeekDay.Onsdag) {
						dayId = 3;
					}
					if (bl.getWeekDay() == EnumWeekDay.Torsdag) {
						dayId = 4;
					}
					if (bl.getWeekDay() == EnumWeekDay.Fredag) {
						dayId = 5;
					}
					System.out.println("dayId: " + dayId);
					
				insertBookingLine.setInt(2, bookingId);
				insertBookingLine.setInt(3, dayId);
				
					int statusId = 0;
					if (bl.getrStatus() == EnumRoomStatus.Available) {
						statusId = 1;  
					}
					if (bl.getrStatus() == EnumRoomStatus.FirstSlice) {
						statusId = 2;
					}
					if (bl.getrStatus() == EnumRoomStatus.SecondSlice) {
						statusId = 3;
					}
					if (bl.getrStatus() == EnumRoomStatus.FullyBooked) {
						statusId = 4;
					}
					System.out.println("statusId: " + statusId);
					
				insertBookingLine.setBoolean(4, false);
				insertBookingLine.setInt(5, statusId);
				insertBookingLine.executeUpdate();
			}

			con.commit();
			return true;

		} catch (SQLException e) {
			e.getMessage();
			con.rollback();
//		} finally {
//			if (con != null) {
//				try {
////					con.close();
//				} catch (Exception innerE) {
//					System.out.println(innerE.getMessage());
//				}
//			}
		}
		return false;
	}

	/**
	 * getBookingsByStudentNo are used for getting bookings depending on
	 * studentNo from in the database.
	 * 
	 * @param studentNo
	 */
	@Override
	public ArrayList<BookingLine> getBookingsByStudentNo(int studentNo) throws SQLException {

		ArrayList<BookingLine> bookingLines = new ArrayList<>();
		ResultSet bookingResult;
		PreparedStatement getBookings = null;
		Connection con = null;

		String getBookingLinesQuery = "SELECT * " 
									+ "FROM Bookinglines AS bl " 
		+ "INNER JOIN Bookings AS b "
				+ "ON b.bookingId = bl.bId " + "INNER JOIN WeekDays " + "ON bl.dayId = WeekDays.dayId "
				+ "INNER JOIN Rooms " + "ON Rooms.instId = bl.roomId  " + "INNER JOIN RoomTypes "
				+ "ON Rooms.typeId = RoomTypes.typeId " + "INNER JOIN StatusRoom "
				+ "ON bl.statusId = StatusRoom.statusId  " + "INNER JOIN Institutions "
				+ "ON Institutions.instId = Rooms.instId " + "INNER JOIN Students " + "ON Students.userId = b.userId "
				+ "INNER JOIN Users " + "ON Students.userId = Users.userId " + "WHERE Students.studentNo = ?";
		try {
			con = DBConnection.getInstance().getDBcon();
			getBookings = (PreparedStatement) con.prepareStatement(getBookingLinesQuery);
			getBookings.setInt(1, studentNo);
			bookingResult = getBookings.executeQuery();
			
		
			
			while (bookingResult.next()) {
				String weekDay = bookingResult.getString("dayOWeek");
				int instId = bookingResult.getInt("instId");
				int capacity = bookingResult.getInt("capacity");
				boolean tv = bookingResult.getBoolean("tv");
				boolean projector = bookingResult.getBoolean("projector");
				String name = bookingResult.getString("name");
				String roomStatus = bookingResult.getString("rStatus");
				String roomType = bookingResult.getString("roomType");
				boolean checkedIn = bookingResult.getBoolean("checkedIn");

				Room r = new Room(instId, name, capacity, tv, projector, EnumRoomType.valueOf(roomType));
				BookingLine bl = new BookingLine(EnumWeekDay.valueOf(weekDay), r, checkedIn,
						EnumRoomStatus.valueOf(roomStatus));
				bookingLines.add(bl);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return bookingLines;
	}

	/**
	 * deleteBooking are used for deleting booking depending on bookingId from
	 * in the database.
	 * 
	 * @param bookingId
	 */
	@Override
	public void deleteBooking(int bookingId) throws SQLException {
		PreparedStatement pStat = null;
		String baseQuery = "DELETE from Bookings " 
						 + "FROM Rooms " 
						 + "INNER JOIN Bookinglines "
						 	+ "ON Bookinglines = rooms.instId "
						 + "Where Bookings.bookingId = Bookinglines.bId AND Bookings.bookingId = ? " 
						 + "UPDATE Rooms "
						 	+ "SET statusId = 1";
		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBcon();
			con.setAutoCommit(false);
			pStat = (PreparedStatement) con.prepareStatement(baseQuery);
			pStat.setInt(1, bookingId);
			pStat.executeUpdate();
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
	 * deleteBookingLine are used for deleting bookingLine depending on
	 * bookingId, roomId and weekDay from in the database.
	 * 
	 * @param bookingId
	 * @param roomId
	 * @param weekDay
	 */
	@Override
	public boolean deleteBookingLine(EnumWeekDay weekDay, EnumRoomStatus rStatus, int studentNo) throws SQLException {
		Connection con = null;
		PreparedStatement pStat = null;

		String baseQuery = "DELETE Bookinglines " + "FROM Bookinglines " + "INNER JOIN WeekDays "
				+ "ON Bookinglines.dayId = WeekDays.dayId " + "INNER JOIN StatusRoom "
				+ "ON Bookinglines.statusId = StatusRoom.statusId " + "INNER JOIN Bookings"
				+ "ON Bookings.bookingId = Bookinglines.bId " + "INNER JOIN Students "
				+ "ON Bookings.userId = Students.userId " + "WHERE StatusRoom.rStatus = ? "
				// + "WHERE Bookinglines.bId = ? "
				// + "AND BookingLines.roomId = ? "
				+ "AND Students.studentNo = ? " + "AND WeekDays.dayOWeek = ?";
		try {
			con = DBConnection.getInstance().getDBcon();
			pStat = (PreparedStatement) con.prepareStatement(baseQuery);
			pStat.setString(1, rStatus.toString());
			pStat.setInt(2, studentNo);
			pStat.setString(3, weekDay.toString());
			pStat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	public boolean deleteBookingOnDay(EnumWeekDay weekDay) {
		Connection con = null;
		PreparedStatement pStat = null;

		String baseQuery = "DELETE Bookinglines " + "FROM Bookinglines " + "INNER JOIN WeekDays "
				+ "ON Bookinglines.dayId = WeekDays.dayId " + "WHERE WeekDays.dayOWeek = ?";
		try {
			con = DBConnection.getInstance().getDBcon();
			pStat = (PreparedStatement) con.prepareStatement(baseQuery);
			pStat.setString(1, weekDay.toString());
			pStat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	/**
	 * checkInBookingLine are used for checking in on bookingLine depending on
	 * bl and bId from in the database.
	 * 
	 * @param bl
	 * @param bId
	 */
	@Override
	public boolean checkInBookingLine(EnumWeekDay weekDay, EnumRoomStatus rStatus) {
		PreparedStatement preparedStat = null;
		Connection con = null;

		String checkInQuery = "UPDATE Bookinglines " 
							+ "SET checkedIn = ? " 
							+ "FROM Bookinglines "
							+ "INNER JOIN WeekDays " 
								+ "ON WeekDays.dayId = Bookinglines.dayId " 
							+ "INNER JOIN StatusRoom "
								+ "ON StatusRoom.statusId = BookingLines.statusId " 
							+ "WHERE WeekDays.dayOWeek = ? "
								+ "AND StatusRoom.rStatus = ?";

		try {
			con = DBConnection.getInstance().getDBcon();
			boolean b = true;
			preparedStat = (PreparedStatement) con.prepareStatement(checkInQuery);
			preparedStat.setBoolean(1, b);
			preparedStat.setString(2, weekDay.toString());
			preparedStat.setString(3, rStatus.toString());
			preparedStat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.getMessage();
		} 
		return false;
	}

	@Override
	public void deleteOldBookingLines(EnumRoomStatus roomStatus, EnumWeekDay wDay) {

		Connection con = null;
		PreparedStatement pStat = null;

		String baseQuery = "DELETE Bookinglines " 
						 + "FROM Bookinglines " 
						 + "INNER JOIN WeekDays "
						 	+ "ON Bookinglines.dayId = WeekDays.dayId "
						 + "INNER JOIN StatusRoom "
						 	+ "ON BookingLines.statusId = StatusRoom.statusId "
						 + "WHERE WeekDays.dayOWeek = ? "
						 	+ "AND StatusRoom.rStatus = ?";
		try {
			con = DBConnection.getInstance().getDBcon();
			pStat = (PreparedStatement) con.prepareStatement(baseQuery);
			pStat.setString(1, roomStatus.toString());
			pStat.setString(2, wDay.toString());
			pStat.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	/**
	 * getAllBookingLines are used for getting all bookingLines from in the
	 * database.
	 * 
	 * @param bl
	 * @param bId
	 */
	public ArrayList<BookingLine> getAllBookingLines() {

		ArrayList<BookingLine> bookingLines = new ArrayList<>();
		ResultSet bookingResult;
		PreparedStatement getBookings = null;
		Connection con = null;

		String baseQuery = "SELECT Institutions.name, Bookinglines.bId, Bookinglines.checkedIn, Bookinglines.roomId, StatusRoom.rStatus, WeekDays.dayOWeek, Rooms.instId, Rooms.capacity, Rooms.tv, Rooms.projector, RoomTypes.roomType "
						 + "FROM Bookings " 
						 	+ "INNER JOIN Bookinglines " 
						 		+ "ON Bookinglines.bId = Bookings.bookingId "
						 	+ "INNER JOIN Rooms " 
						 		+ "ON Bookinglines.roomId = Rooms.instId " 
						 	+ "INNER JOIN Institutions "
						 		+ "ON Rooms.instId = Institutions.instID " 
						 	+ "INNER JOIN StatusRoom "
						 		+ "ON Bookinglines.statusId = StatusRoom.statusId " 
						 	+ "INNER JOIN RoomTypes "
						 		+ "ON Rooms.typeId = RoomTypes.typeId " 
						 	+ "INNER JOIN WeekDays "
						 		+ "ON WeekDays.dayId = Bookinglines.dayId";
		try {
			con = DBConnection.getInstance().getDBcon();
			getBookings = (PreparedStatement) con.prepareStatement(baseQuery);
			bookingResult = getBookings.executeQuery();

			while (bookingResult.next()) {
				int roomID = bookingResult.getInt("roomId");
				String name = bookingResult.getString("name");
				int capacity = bookingResult.getInt("capacity");
				boolean tv = bookingResult.getBoolean("tv");
				boolean projector = bookingResult.getBoolean("projector");
				String rType = bookingResult.getString("roomType");
				String rStatus = bookingResult.getString("rStatus");
				String weekDay = bookingResult.getString("dayOWeek");
				boolean checkedIn = bookingResult.getBoolean("checkedIn");

				Room r = new Room(roomID, name, capacity, tv, projector, EnumRoomType.valueOf(rType));
				BookingLine bl = new BookingLine(EnumWeekDay.valueOf(weekDay), r, checkedIn,
						EnumRoomStatus.valueOf(rStatus));
				bl.setrStatus(EnumRoomStatus.valueOf(rStatus));
				bookingLines.add(bl);
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
		return bookingLines;
	}

	public ArrayList<BookingLine> getAllBookingsByRoomId(int roomID) {
		ArrayList<BookingLine> bookingLines = new ArrayList<>();
		ResultSet bookingResult;
		PreparedStatement getBookings = null;
		Connection con = null;

		String baseQuery = "SELECT Bookinglines.bId, bookinglins.checkedIn, Bookinglines.roomId, StatusRoom.rStatus, WeekDays.dayOWeek, Rooms.instId, Rooms.capacity, Rooms.tv, Rooms.projector, RoomTypes.roomType"
				+ "FROM Bookings " + "INNER JOIN Bookinglines " + "ON Bookinglines.bId = Bookings.bookingId "
				+ "INNER JOIN Rooms " + "ON Bookinglines.roomId = Rooms.instId " + "INNER JOIN Institutions "
				+ "ON Rooms.instId = Institutions.instID " + "INNER JOIN StatusRoom "
				+ "ON Bookinglines.statusId = StatusRoom.statusId " + "INNER JOIN RoomTypes "
				+ "ON Rooms.typeId = RoomTypes.typeId " + "INNER JOIN WeekDays "
				+ "ON WeekDays.dayId = Bookinglines.dayId " + "WHERE Rooms.instId = ?";
		try {
			con = DBConnection.getInstance().getDBcon();
			getBookings = (PreparedStatement) con.prepareStatement(baseQuery);
			getBookings.setInt(1, roomID);
			bookingResult = getBookings.executeQuery();

			while (bookingResult.next()) {
				String name = bookingResult.getString("name");
				int capacity = bookingResult.getInt("capacity");
				boolean tv = bookingResult.getBoolean("tv");
				boolean projector = bookingResult.getBoolean("projector");
				String rType = bookingResult.getString("rType");
				String rStatus = bookingResult.getString("rStatus");
				String weekDay = bookingResult.getString("dayOWeek");
				boolean checkedIn = bookingResult.getBoolean("checkedIn");

				Room r = new Room(roomID, name, capacity, tv, projector, EnumRoomType.valueOf(rType));
				BookingLine bl = new BookingLine(EnumWeekDay.valueOf(weekDay), r, checkedIn,
						EnumRoomStatus.valueOf(rStatus));
				bl.setrStatus(EnumRoomStatus.valueOf(rStatus));
				bookingLines.add(bl);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return bookingLines;
	}
}
