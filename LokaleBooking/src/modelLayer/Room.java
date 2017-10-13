/**
 * Class "Room": Contains information about room and inherits from institution class.
 * The class contains the following attributes for room: roomId, capacity, tv, projector, rType and rStatus.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

public class Room extends Institution{
	
	/**
	 * Fields
	 */
	private int roomID;
	private int capacity;
	private boolean tv;
	private boolean projector;
	private EnumRoomType rType; 

	
	/**
	 * Constructor for the room class.
	 * This constructor is used for creating a room with input from the system.
	 * @param name
	 * @param capacity
	 * @param tv
	 * @param projector
	 * @param rType
	 */
	public Room(String name, int capacity, boolean tv, boolean projector, EnumRoomType rType) {
		super(name);
		this.capacity = capacity;
		this.tv = tv;
		this.projector = projector;
		this.rType = rType;
	}

	/**
	 * Constructor for the room class.
	 * This constructor is used for creating a room with input from the system.
	 * @param roomId - This field is needed when selecting a student from the database.
	 * @param name
	 * @param capacity
	 * @param tv
	 * @param projector
	 * @param rType
	 */
	public Room(int roomID, String name, int capacity, boolean tv, boolean projector, EnumRoomType rType) {
		super(name);
		this.roomID = roomID;
		this.capacity = capacity;
		this.tv = tv;
		this.projector = projector;
		this.rType = rType;
	}

	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isProjector() {
		return projector;
	}

	public void setProjector(boolean projector) {
		this.projector = projector;
	}

	public EnumRoomType getrType() {
		return rType;
	}

	public void setrType(EnumRoomType rType) {
		this.rType = rType;
	}

	public int getRoomID() {
		return roomID;
	}

	/**
	 * This method converts a room object into a string.
	 */
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", capacity=" + capacity + ", tv=" + tv + ", projector=" + projector
				+ ", rType=" + rType + ", getName()=" + getName() + "]";
	}
}
