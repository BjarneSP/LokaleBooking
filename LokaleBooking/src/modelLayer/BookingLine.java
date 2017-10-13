/**
 * Class "BookingLine": Contains information about bookingLine.
 * The class contains the following attributes for user: checkedIn, weekDay and lineRoom.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

public class BookingLine {
	
	/**
	 * Fields
	 */
	private EnumWeekDay weekDay; 
	private boolean checkedIn;
	private Room lineRoom;
	private EnumRoomStatus rStatus;
	
	/**
	 * Constructor for the bookingLine class.
	 * @param checkedIn
	 * @param weekDay
	 * @param lineRoom
	 */
	public BookingLine(EnumWeekDay weekDay, Room lineRoom, boolean checkedIn,EnumRoomStatus rStatus) {
		super();
		this.checkedIn = checkedIn;
		this.weekDay = weekDay;
		this.lineRoom = lineRoom;
		this.rStatus = rStatus;
	}

	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public EnumWeekDay getWeekDay() {
		return weekDay;
	}
	
	public void setWeekDay(EnumWeekDay weekDay) {
		this.weekDay = weekDay;
	}

	public Room getLineRoom() {
		return lineRoom;
	}

	public void setLineRoom(Room lineRoom) {
		this.lineRoom = lineRoom;
	}
	
	public EnumRoomStatus getrStatus() {
		return rStatus;
	}

	public void setrStatus(EnumRoomStatus rStatus) {
		this.rStatus = rStatus;
	}

	@Override
	public String toString() {
		return "BookingLine [weekDay=" + weekDay + ", checkedIn=" + checkedIn + ", lineRoom=" + lineRoom + ", rStatus="
				+ rStatus + "]";
	}

	
	
}
