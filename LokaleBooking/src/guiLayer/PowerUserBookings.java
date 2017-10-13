package guiLayer;

import javafx.beans.property.SimpleStringProperty;

public class PowerUserBookings {
	    private final SimpleStringProperty name;
	    private final SimpleStringProperty weekDay;
	    private final SimpleStringProperty capacity;
	    private final SimpleStringProperty tv;
	    private final SimpleStringProperty projector;
	    private final SimpleStringProperty rType;

	    private final SimpleStringProperty checkedIn;
	    private final SimpleStringProperty roomId;
	    
		public PowerUserBookings(String name, String weekDay, 
				String capacity,String tv, 
				String projector, String rType, String checkedIn, String roomId) {
			super();
			this.name = new SimpleStringProperty(name);
			this.weekDay = new SimpleStringProperty(weekDay);
			this.capacity = new SimpleStringProperty(capacity);
			this.tv = new SimpleStringProperty(tv);
			this.projector = new SimpleStringProperty(projector);
			this.rType = new SimpleStringProperty(rType);
			this.checkedIn = new SimpleStringProperty(checkedIn);
			this.roomId = new SimpleStringProperty(roomId);
		}
		public String getName() {
			return name.get();
		}
		public String getWeekDay() {
			return weekDay.get();
		}
		public String getCapacity() {
			return capacity.get();
		}
		public String getTv() {
			return tv.get();
		}
		public String getProjector() {
			return projector.get();
		}
		
		public String getRType() {
			return rType.get();
		}
	    
		public String getCheckedIn() {
			return checkedIn.get();
		}

		public String getRoomId() {
			return roomId.get();
		}
	 
}
