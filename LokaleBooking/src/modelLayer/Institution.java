/**
 * Class "Institution": Abstract class which contains information about institution.
 * The class contains the following attributes for institution: name.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

public abstract class Institution {
	
	/**
	 * Fields
	 */
	private String name;
	
	/**
	 * Constructor for the institution class. The constructor is not used for creating an object, but only for inheritance.
	 * @param name
	 */
	public Institution(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method converts an institution object into a string.
	 */
	@Override
	public String toString() {
		return "Institution [name=" + name + "]";
	}
}
