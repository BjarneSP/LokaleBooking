package guiLayer;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import modelLayer.BookingLine;
import modelLayer.EnumRoomStatus;
import modelLayer.EnumRoomType;
import modelLayer.EnumWeekDay;
import modelLayer.Room;

public class RoomRow {
	
	private Room r; 

	private SimpleStringProperty room;

	private SimpleStringProperty monday1;
	private SimpleStringProperty monday2;

	private SimpleStringProperty tuesday1;
	private SimpleStringProperty tuesday2;

	private SimpleStringProperty wednesday1;
	private SimpleStringProperty wednesday2;

	private SimpleStringProperty thursday1;
	private SimpleStringProperty thursday2;

	private SimpleStringProperty friday1;
	private SimpleStringProperty friday2;


	public RoomRow(Room r){
		this.r = r;
		
		this.room = new SimpleStringProperty(r.getName());
		this.monday1 = new SimpleStringProperty(null);
		this.monday2 = new SimpleStringProperty(null);
		this.tuesday1 = new SimpleStringProperty(null);
		this.tuesday2 = new SimpleStringProperty(null);
		this.wednesday1 = new SimpleStringProperty(null);
		this.wednesday2 = new SimpleStringProperty(null);
		this.thursday1 = new SimpleStringProperty(null);
		this.thursday2 = new SimpleStringProperty(null);
		this.friday1 = new SimpleStringProperty(null);
		this.friday2 = new SimpleStringProperty(null);
	}
	
	private void dayAndTimeCheck(EnumWeekDay wDay, EnumRoomStatus status){
		
//		if (wDay.equals(EnumWeekDay.Mandag)) {
//			if (status.equals(EnumRoomStatus.Available)) {
//			} else if (status.equals(EnumRoomStatus.FirstSlice)) {
//				this.monday1 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.SecondSlice)) {
//				this.monday2 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.FullyBooked)) {
//				this.monday1 = new SimpleStringProperty(" ");
//				this.monday2 = new SimpleStringProperty(" ");
//			}
//		} else if (wDay.equals(EnumWeekDay.Tirsdag)) {
//			if (status.equals(EnumRoomStatus.Available)) {
//			} else if (status.equals(EnumRoomStatus.FirstSlice)) {
//				this.tuesday1 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.SecondSlice)) {
//				this.tuesday2 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.FullyBooked)) {
//				this.tuesday1 = new SimpleStringProperty(" ");
//				this.tuesday2 = new SimpleStringProperty(" ");
//			}
//		} else if (wDay.equals(EnumWeekDay.Onsdag)) {
//			if (status.equals(EnumRoomStatus.Available)) {
//			} else if (status.equals(EnumRoomStatus.FirstSlice)) {
//				this.wednesday1 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.SecondSlice)) {
//				this.wednesday2 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.FullyBooked)) {
//				this.wednesday1 = new SimpleStringProperty(" ");
//				this.wednesday2 = new SimpleStringProperty(" ");
//			}
//		} else if (wDay.equals(EnumWeekDay.Torsdag)) {
//			if (status.equals(EnumRoomStatus.Available)) {
//			} else if (status.equals(EnumRoomStatus.FirstSlice)) {
//				this.thursday1 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.SecondSlice)) {
//				this.thursday2 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.FullyBooked)) {
//				this.thursday1 = new SimpleStringProperty(" ");
//				this.thursday2 = new SimpleStringProperty(" ");
//			}
//		} else if (wDay.equals(EnumWeekDay.Fredag)) {
//			if (status.equals(EnumRoomStatus.Available)) {
//			} else if (status.equals(EnumRoomStatus.FirstSlice)) {
//				this.friday1 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.SecondSlice)) {
//				this.friday2 = new SimpleStringProperty(" ");
//			} else if (status.equals(EnumRoomStatus.FullyBooked)) {
//				this.friday1 = new SimpleStringProperty(" ");
//				this.friday1 = new SimpleStringProperty(" ");
//			}
//		} else {
//			System.err.println("RoomRow virker ikke");
//		}	
	}

	///////// Table values ///////////

	public Room getR() {
		return r;
	}

	public void setR(Room r) {
		this.r = r;
	}

	public SimpleStringProperty roomProperty() {
		return room;
	}

	public void setRoom(String room) {
		this.room = new SimpleStringProperty(room);
	}

	public SimpleStringProperty monday1Property() {
		return monday1;
	}

	public void setMonday1(String monday1) {
		this.monday1 = new SimpleStringProperty(monday1);
	}

	public SimpleStringProperty monday2Property() {
		return monday2;
	}

	public void setMonday2(String monday2) {
		this.monday2 = new SimpleStringProperty(monday2);
	}

	public SimpleStringProperty tuesday1Property() {
		return tuesday1;
	}

	public void setTuesday1(String tuesday1) {
		this.tuesday1 = new SimpleStringProperty(tuesday1);
	}

	public SimpleStringProperty tuesday2Property() {
		return tuesday2;
	}

	public void setTuesday2(String tuesday2) {
		this.tuesday2 = new SimpleStringProperty(tuesday2);
	}

	public SimpleStringProperty wednesday1Property() {
		return wednesday1;
	}

	public void setWednesday1(String wednesday1) {
		this.wednesday1 = new SimpleStringProperty(wednesday1);
	}

	public SimpleStringProperty wednesday2Property() {
		return wednesday2;
	}

	public void setWednesday2(String wednesday2) {
		this.wednesday2 = new SimpleStringProperty(wednesday2);
	}

	public SimpleStringProperty thursday1Property() {
		return thursday1;
	}

	public void setThursday1(String thursday1) {
		this.thursday1 = new SimpleStringProperty(thursday1);
	}

	public SimpleStringProperty thursday2Property() {
		return thursday2;
	}

	public void setThursday2(String thursday2) {
		this.thursday2 = new SimpleStringProperty(thursday2);
	}

	public SimpleStringProperty friday1Property() {
		return friday1;
	}

	public void setFriday1(String friday1) {
		this.friday1 = new SimpleStringProperty(friday1);
	}

	public SimpleStringProperty friday2Property() {
		return friday2;
	}

	public void setFriday2(String friday2) {
		this.friday2 = new SimpleStringProperty(friday2);
	}

	@Override
	public String toString() {
		return "RoomRow [r=" + r + ", room=" + room + ", monday1=" + monday1 + ", monday2=" + monday2 + ", tuesday1="
				+ tuesday1 + ", tuesday2=" + tuesday2 + ", wednesday1=" + wednesday1 + ", wednesday2=" + wednesday2
				+ ", thursday1=" + thursday1 + ", thursday2=" + thursday2 + ", friday1=" + friday1 + ", friday2="
				+ friday2 + "]";
	}
	
	
}
