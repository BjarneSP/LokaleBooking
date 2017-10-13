/**
 * Class "IFBookingDB": Contains information about IFBookingDB.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package databaseLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import modelLayer.Booking;
import modelLayer.BookingLine;
import modelLayer.EnumRoomStatus;
import modelLayer.EnumWeekDay;

public interface IFBookingDB {
	
	/**
	 * InterFace for the BookingDB class.
	 * This InterFace is used for creating the methods BookingDB need to contain.
	 * @param s
	 */
	
	// Add
	public boolean addBooking(Booking b) throws SQLException;
//	public void addBookingLine(BookingLine bl);
	
	// Get
	public ArrayList<BookingLine> getBookingsByStudentNo(int studentNo) throws SQLException;
	public ArrayList<BookingLine> getAllBookingLines();
	public ArrayList<BookingLine> getAllBookingsByRoomId(int roomID);
//	public Booking getBookingByDepartment(String departmentPhone);
	
	// Delete
	public void deleteBooking(int bookingId) throws SQLException;
	public boolean deleteBookingLine(EnumWeekDay weekDay, EnumRoomStatus rStatus, int studentNo) throws SQLException;
	public boolean deleteBookingOnDay(EnumWeekDay weekDay);
//	public void deleteExpired(EnumRoomStatus roomStatus, EnumWeekDay weekDay) throws SQLException;
	
	// Update
	public boolean checkInBookingLine(EnumWeekDay weekDay, EnumRoomStatus rStatus);
	public void deleteOldBookingLines(EnumRoomStatus roomStatus, EnumWeekDay weekDay);
//	public void updateBooking(int bookingId, EnumRoomStatus status);
	
	

	
	
}
