/**
 * Class "Student": Contains information about student and inherits from User class.
 * The class contains the following attributes for student: userId, studentNo.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */
package modelLayer;

public class Student extends User {
	
	/**
	 * Fields
	 */
	private int userId; 
	private int studentNo; 

	/**
	 * Constructor for the student class.
	 * This constructor is used for creating a student with input from the system.
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param studentNo
	 */
	public Student(String firstName, String lastName, String email, int studentNo) {
		super(firstName, lastName, email);
		this.userId = userId;
		this.studentNo = studentNo;
	}
	
	/**
	 * Constructor for the student class.
	 * This constructor is used for creating a student with data from the database.
	 * @param userId - This field is needed when selecting a student from the database.
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param studentNo
	 */
	public Student(int userId, String firstName, String lastName, String email, int studentNo) {
		super(firstName, lastName, email);
		this.userId = userId;
		this.studentNo = studentNo;
	}

	/**
	 * Getters and Setters for the relevant fields in the class.
	 */
	public int getUserId() {
		return userId;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + "]";
	}
	
	
}
