/**
 * Class "User": Abstract class which contains information about Users.
 * The class contains the following attributes for user: firstName, lastName and email.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */

package modelLayer;

public abstract class User {
	
	/**
	 * Fields
	 */
	private String firstName;
	private String lastName;
	private String email;
	
	/**
	 * Constructor for the user class. The constructor is not used for creating an object, but only for inheritance.
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * This method converts a user object into a string.
	 */
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
