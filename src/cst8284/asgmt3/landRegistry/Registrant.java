package cst8284.asgmt3.landRegistry;
import java.io.Serializable;

	/**
	 * This class implements the registrant information loaded in a land registry, 
	 * which permits an administrations and associations to record and track land possession.
	 * @author Thi Kim Anh Huynh
	 * @version 1.2
	 */
	
	public class Registrant implements Serializable{
	
	// By professor Dave HoutMan from Assignment 2 Document
	public static final long serialVersionUID = 1L;
	
	/**
	 * {@value #REGNUM_START} The start number of registrant number, which is 1000.
	 * {@value #currentRegNum} This variable is equal to {@value #REGNUM_START}.
	 */
	
	private static final int REGNUM_START = 1000;
	private static int currentRegNum = REGNUM_START;
	
	/**
	 *This is the registrant number.
	 */
	
	private final int REGNUM;
	
	/**
	 * This is the first name of registrant.
	 */
	private String firstName;
	
	/**
	 * This is the last name of registrant.
	 */
	
	private String lastName;
	
	/**
	 * This creates registrant, which is chained to another  
	 * property constructors with String firstLastNme.
	 */
	public Registrant() {
		this("unknown unknown");
	}
	
	/**
	 * This creates registrant with specific first and last name.
	 * @param firstLastName the first and last name of registrant
	 */
	
	public Registrant (String firstLastName) {	
		REGNUM = currentRegNum;
		incrToNextRegNum();
	
		//Reference: BalusC,.(2010, August). How to split a string in Java. In Stack Overflow. Retrieved from https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
		String[] parts = firstLastName.split(" ");
		this.setFirstName(parts[0]);
		this.setLastName(parts[1]);
		
	}
	/**
	 * This returns the registrant number of registrant
	 * @return the regNum of registrant
	 */
	public int getRegNum() {
		return this.REGNUM;
	}
	
	/**
	 * This increments the current regNum.
	 */
	
	private static void incrToNextRegNum() {
		currentRegNum++;
	}
	
	/**
	 * This return the first name of registrant
	 * @return first name of registrant
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * This method sets or updates the first name of registrant.
	 * It is called by the constructor.
	 * @param firstName first name of registrant
	 */
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * This return the last name of registrant
	 * @return last name of registrant
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * This method sets or updates the last name of registrant.
	 * It is called by the constructor.
	 * @param lastName last name of registrant
	 */
	
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * This compares whether the original first name and new first name, 
	 * the original last name and new new last name 
	 * the original regNum and new regNum are equal or not.
	 * @param obj the obj's Object of the registrant
	 * @return true if the original first name and new first name, 
	 * the original last name and new new last name 
	 * the original regNum and new regNum are equal
	 */
	
	public boolean equals (Object obj) {
		//Reference: Java Type Casting. (n.d.). In w3schools.com. Retrieved from https://www.w3schools.com/java/java_type_casting.asp
		Registrant other = (Registrant) obj;
		
		if(getFirstName().equals(other.getFirstName()) 
			&& getLastName().equals(other.getLastName())			
			&& getRegNum() == other.getRegNum()) 
		return true;
		return false;
	}
	
	/**
	 * This print the registrant information 
	 * including first name, last name and registrant number.
	 */
	
	public String toString () {
		return ("Name: " + firstName + " " + lastName + "\nRegistration Number: " + "#" + REGNUM);
		
	}





}
