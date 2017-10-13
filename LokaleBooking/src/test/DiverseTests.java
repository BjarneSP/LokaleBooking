package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import controlLayer.UserCtrl;
import databaseLayer.BookingDB;
import databaseLayer.IFBookingDB;
import databaseLayer.IFInstituteDB;
import databaseLayer.IFUserDB;
import databaseLayer.InstituteDB;
import databaseLayer.UserDB;
import junit.framework.TestCase;
import modelLayer.Booking;
import modelLayer.BookingLine;
import modelLayer.EnumRoomStatus;
import modelLayer.EnumRoomType;
import modelLayer.EnumWeekDay;
import modelLayer.PowerUser;
import modelLayer.Room;
import modelLayer.Student;

public class DiverseTests extends TestCase {
	IFUserDB db = new UserDB();
	IFBookingDB bookingDB = new BookingDB();
	IFInstituteDB idb = new InstituteDB();
	UserCtrl uCtrl = new UserCtrl();
	Student lars = new Student("Lars", "Larsen", "no@way.com", 1234987);
	
	PowerUser p1 = new PowerUser("Bente","Bentesen", "Bente@bensen.com", "01", "1234");
	Room r1 = new Room("Rum1",6,true,true,EnumRoomType.StudyRoom);
	//tests if its possible to add a student
	@Test
	public void testAddStudent() {
		try {
			db.addStudent(lars);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(lars.toString(), db.selectStudent(1234987).toString());

	
	}
	//tests if its possible to add a PowerStudent
	public void testAddPowerUser(){
		try {
			db.addPowerUser(p1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(p1.toString(), db.selectPowerUser("01").toString());
	}
	//tests if its possible to add a Room
//	public void testAddRooms(){
//		try {
//			idb.addRoom(r1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		assertEquals(r1.toString(), idb.);  // mangler metoder
//		
//	}
	public void bookRooms(){
		
	}
	
	//tests if its possible to update a Student
	public void testUpdateStudent(){
		Student bo = new Student("Bo", "Boen", "Bo@way.com", 9876543);
		try {
			db.addStudent(bo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.updateStudent("Bo2", "Boen2", "Bo2@com", 9876543);
		assertNotSame(bo.getFirstName(), db.selectStudent(9876543).getFirstName());
	}
	

	
	public void testCreateBooking(){
		Room r = new Room(12, "SD3.1.21", 4, true, false, EnumRoomType.StudyRoom);
		Room r2 = new Room(13, "SD3.1.20", 4, true, false, EnumRoomType.StudyRoom);
		BookingLine bl = new BookingLine(EnumWeekDay.Tirsdag, r, false, EnumRoomStatus.FirstSlice);
		BookingLine bl2 = new BookingLine(EnumWeekDay.Tirsdag, r2, false, EnumRoomStatus.SecondSlice);
		Student s = new Student(5, "Ramanan", "sriskandarajah", "Ramanan@ucn.dk", 1234567);
		Booking b = new Booking(s);
		b.addBookingLine(bl2);
		b.addBookingLine(bl);
		IFBookingDB bDB = new BookingDB();

			try {
				bDB.addBooking(b);
				ArrayList bookings = bDB.getBookingsByStudentNo(1234567);
				assertEquals(b.getBookingLines().toString(), bookings.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
}

