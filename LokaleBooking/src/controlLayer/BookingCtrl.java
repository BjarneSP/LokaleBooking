package controlLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import databaseLayer.BookingDB;
import databaseLayer.IFBookingDB;
import modelLayer.Booking;
import modelLayer.BookingLine;
import modelLayer.EnumRoomStatus;
import modelLayer.EnumWeekDay;
import modelLayer.Student;
import modelLayer.User;

public class BookingCtrl implements IFObserver {
	
//	private EmailCtrl eCtrl;
//	private InstituteCtrl iCtrl;
//	private UserCtrl uCtrl;
//	private static ArrayList<BookingLine> currentBookingLines;
	private IFBookingDB bDB;
	
	public BookingCtrl() {
		super();
		
//		eCtrl = new EmailCtrl();
//		iCtrl = new InstituteCtrl();
//		uCtrl = new UserCtrl();
		bDB = new BookingDB();
	}
	
	//Timekeeper
	@Override
	public void update(EnumRoomStatus rStatus, EnumWeekDay wDay) {		
		bDB.deleteOldBookingLines(rStatus,wDay);
	}
	
	public boolean createBooking(ArrayList<BookingLine> bookingLines){
		
		User u = LoginCtrl.getCurrentUser();
			
		System.out.println("u: " + u.toString());
		if(u instanceof Student && !(u==null)){
			System.out.println("Test 1");
			Student s = (Student) u;
			Booking b = new Booking(s);
	
			for(BookingLine bl : bookingLines){
				b.addBookingLine(bl);
			}
			
			System.out.println(b.toString());
			
			try {
				bDB.addBooking(b);
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return false;
	}
	
	public void deleteBooking(int bookingId){
		try {
			bDB.deleteBooking(bookingId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<BookingLine> getBookingLinesCurrentUser(){
		User u = LoginCtrl.getCurrentUser();
		if(u instanceof Student){
			Student s = (Student) u;
			ArrayList<BookingLine> bookings;
			
			try {
				bookings = bDB.getBookingsByStudentNo(s.getStudentNo());
				return bookings;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<BookingLine> getAllBookingsByRoomId(int roomId){
		return bDB.getAllBookingsByRoomId(roomId);
	}
	
	public void deleteBookingLine(EnumWeekDay weekDay, EnumRoomStatus rStatus, int studentNo){
		try {
			bDB.deleteBookingLine(weekDay,rStatus, studentNo);
		} catch (SQLException e) {
			e.getMessage();
//			e.printStackTrace();
		}
	}

	public boolean checkIn(BookingLine bl){
		if(bDB.checkInBookingLine(bl.getWeekDay(), bl.getrStatus())){
			return true;
		}
		return false;
	}
	
	public boolean checkOut(EnumWeekDay weekDay, EnumRoomStatus rStatus, int studentNo){
		try {
			if(bDB.deleteBookingLine(weekDay,rStatus,studentNo)){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<BookingLine> getAllBookingLines(){
		ArrayList<BookingLine> bookingLines = bDB.getAllBookingLines();
		return bookingLines;
	}
}
