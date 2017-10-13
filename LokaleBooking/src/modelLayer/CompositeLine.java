/**
 * Class "CompositeLine": Contains information about compositeLine.
 * The class contains the following attributes for compositeLine: amount, institution and department.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

public class CompositeLine {
	
	/**
	 * Fields
	 */
	private int amount;
	private Institution institution;
	
	/**
	 * Constructor for the compositeLine class.
	 * @param amount
	 * @param institution
	 * @param department
	 */
	public CompositeLine(int amount, Institution institution) {
		super();
		this.amount = amount;
		this.institution = institution;
	}

	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
}
