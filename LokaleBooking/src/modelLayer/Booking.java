/**
 * Class "Booking": Contains information about booking.
 * The class contains the following attributes for booking: bookingId, s and bookingLines.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

import java.util.ArrayList;

public class Booking {

	/**
	 * Fields
	 */
	private int bookingID; 
	private Student s; 
	private ArrayList<BookingLine> bookingLines; 

	/**
	 * Constructor for the booking class.
	 * This constructor is used for creating a booking with input from the system.
	 * @param s
	 */
	public Booking(Student s) {
		super();
		this.s = s;
		this.bookingLines = new ArrayList<>();
	}
	
	/**
	 * Constructor for the booking class.
	 * This constructor is used for creating a booking with input from the system.
	 * @param bookingId - This field is needed when selecting a booking from the database.
	 * @param s

	 */
	public Booking(int bookingID, Student s) {
		super();
		this.bookingID = bookingID;
		this.s = s;
		this.bookingLines = new ArrayList<>();
	}
	
	/**
	 * This method adds a bookingLine to a booking.
	 */
	public void addBookingLine(BookingLine bl){
		bookingLines.add(bl);
	}
	
	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public ArrayList<BookingLine> getBookingLines() {
		return bookingLines;
	}

	public void setBookingLines(ArrayList<BookingLine> bookingLines) {
		this.bookingLines = bookingLines;
	}

	public int getBookingID() {
		return bookingID;
	}

	/**
	 * This method converts a booking object into a string.
	 */
	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ", s=" + s + ", bookingLines=" + bookingLines + "]";
	}
}