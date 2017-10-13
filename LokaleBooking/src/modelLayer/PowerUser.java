/**
 * Class "PowerUser": Contains information about powerUser and inherits from User class.
 * The class contains the following attributes for powerUser: userId, puId and uPassword.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

public class PowerUser extends User {

	private int userId; 
	private String puId;
	private String uPassword;
	
	/**
	 * Constructor for the powerUser class.
	 * This constructor is used for creating a powerUser with input from the system.
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param puId
	 * @param uPassword
	 */
	public PowerUser(String firstName, String lastName, String email, String puId, String uPassword) {
		super(firstName, lastName, email);
		this.puId = puId;
		this.uPassword = uPassword;
	}
	
	/**
	 * Constructor for the powerUser class.
	 * This constructor is used for creating a powerUser with data from the database.
	 * @param userId - This field is needed when selecting a powerUser from the database.
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param puId
	 * @param uPassword
	 */
	public PowerUser(int userId, String firstName, String lastName, String email, String puId, String uPassword) {
		super(firstName, lastName, email);
		this.userId = userId;
		this.puId = puId;
		this.uPassword = uPassword;
	}
	
	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public int getUserId() {
		return userId;
	}

	public String getId() {
		return puId;
	}
	
	public void setId(String puId) {
		this.puId = puId;
	}
	
	public String getPassword() {
		return uPassword;
	}
	
	public void setPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	/**
	 * This method converts a powerUser object into a string.
	 */
	@Override
	public String toString() {
		return "PowerUser [puId=" + puId + ", uPassword=" + uPassword + ", toString()=" + super.toString() + "]";
	}
}
	
	
	

	


