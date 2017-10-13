/**
 * Class "Email": Contains information about email.
 * The class contains the following attributes for email: emailID and description.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

public class Email {
	
	/**
	 * Fields
	 */
	int emailID;
	String description;
	
	// States??
	
	/**
	 * Constructor for the email class.
	 * This constructor is used for creating an email with input from the system.
	 * @param description
	 */
	public Email(String description) {
		super();
		this.description = description;
	}
	
	/**
	 * Constructor for the email class.
	 * This constructor is used for creating an email with data from the database.
	 * @param emailId - This field is needed when selecting an email from the database.
	 * @param description
	 */
	public Email(int emailID, String description) {
		super();
		this.emailID = emailID; 
		this.description = description;
	}

	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public int getEmailID() {
		return emailID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
