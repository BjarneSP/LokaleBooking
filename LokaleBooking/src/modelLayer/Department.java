/**
 * Class "Department": Contains information about department and inherits from institution class.
 * The class contains the following attributes for department: depId, adress, dNo, postCode and city.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

import java.util.ArrayList;

public class Department extends Institution {
	
	/**
	 * Fields
	 */
	private int depID;
	private String adress;
	private int dNo;
	private String postCode;
	private String city;
	private String phone;
	ArrayList<CompositeLine> cl;

	/**
	 * Constructor for the department class.
	 * This constructor is used for creating a department with input from the system.
	 * @param name
	 * @param adress
	 * @param dNo
	 * @param postCode
	 * @param city
	 * @param phone
	 */
	public Department(String name, String adress, int dNo, String postCode, String city, String phone) {
		super(name);
		this.adress = adress;
		this.dNo = dNo;
		this.postCode = postCode;
		this.city = city;
		this.phone = phone;
		cl = new ArrayList<CompositeLine>();
	}
	
	/**
	 * Constructor for the department class.
	 * This constructor is used for creating a department with input from the system.
	 * @param depId - This field is needed when selecting a department from the database.
	 * @param name
	 * @param adress
	 * @param dNo
	 * @param postCode
	 * @param city
	 * @param phone
	 */
	public Department(int depID, String name, String adress, int dNo, String postCode, String city, String phone) {
		super(name);
		this.depID = depID;
		this.adress = adress;
		this.dNo = dNo;
		this.postCode = postCode;
		this.city = city;
		this.phone = phone;
		cl = new ArrayList<CompositeLine>();
	}

	public ArrayList<CompositeLine> getCl() {
		return cl;
	}

	public void setCl(ArrayList<CompositeLine> cl) {
		this.cl = cl;
	}

	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public int getDepID() {
		return depID;
	}
	
	public void setDepID(int depID) {
		this.depID = depID;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public int getdNo() {
		return dNo;
	}

	public void setdNo(int dNo) {
		this.dNo = dNo;
	}
	
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * This method adds one or more rooms to a department.
	 */
	public void addCompositeLine(CompositeLine col) {
		cl.add(col);
	}
	
	public void removeCompositeLine(CompositeLine col){
		cl.remove(col);
	}

	/**
	 * This method converts a department object into a string.
	 */
	@Override
	public String toString() {
		return "Department [adress=" + adress + ", postCode=" + postCode + ", city=" + city + ", phone=" + phone + "]";
	}
	
	
	
}
