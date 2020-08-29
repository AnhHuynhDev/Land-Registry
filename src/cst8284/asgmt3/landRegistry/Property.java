package cst8284.asgmt3.landRegistry;
import java.io.Serializable;

	/**
	 * This class implements the property information loaded in a land registry, 
	 * which permits an administrations and associations to record and track land possession.
	 * @author Thi Kim Anh Huynh
	 * @version 1.2
	 */
	
	public class Property implements Serializable{
	
	// By professor Dave HoutMan from Assignment 2 Document
	public static final long serialVersionUID = 1L;
	
	/**
	 * {@value #TAX_RATE_PER_M2} This tax rate is per m2. It is used to calculate Property Taxes.
	 */
	
	private static final double TAX_RATE_PER_M2 = 12.50;
	
	/**
	 * {@value #DEFAULT_REGNUM} This is the default of registrant number.
	 */
	
	private static final int DEFAULT_REGNUM = 999;
	
	/**
	 * This variables describe the coordinates of property, which is left and top. 
	 */
	
	private int xLeft, yTop;
	
	/**
	 * This variables describe the dimensions of property, which is length and width.
	 */
	
	private int xLength, yWidth;
	
	/**
	 * regNum is the registrant number.
	 */
	
	private int regNum;
	
	/**
	 * This variable describe the area of properties.
	 */
	
	private int area;
	
	/**
	 * This variable describe the taxes of properties.
	 */
	
	private double taxes;
	
	/**
	 * This creates property, which is chained to another property constructors 
	 * with specific length, width, left and top.
	 */
	public Property() {
		this(0, 0, 0, 0);
	}
	
	/**
	 * This creates property with specific length, width, left and top, 
	 * then chained to another Property with Property prop and int RegNum
	 * @param xLength the length of property
	 * @param yWidth the width of property
	 * @param xLeft the left of property
	 * @param yTop the top of property
	 */
	public Property (int xLength, int yWidth, int xLeft, int yTop) {
		this(xLength, yWidth, xLeft, yTop, DEFAULT_REGNUM);
	}

	/**
	 * This creates prop of Property and regNum, then chained to another 
	 * Property with specific length, width, left and top and regNum
	 * @param prop the prop Property of property
	 * @param regNum the registrant number of the registrant
	 */
	public Property (Property prop, int regNum) {
		this(prop.getXLength(), prop.getYWidth(), prop.getXLeft(), prop.getYTop(), regNum);
	}

	/**
	 * This creates property with specific length, width, left and top, and regNum
	 * @param xLength the length of property
	 * @param yWidth the width of property
	 * @param xLeft the left of property
	 * @param yTop the top of property
	 * @param regNum the registrant number of the registrant
	 */
	public Property (int xLength, int yWidth, int xLeft, int yTop, int regNum) {
		this.setXLength(xLength);
		this.setYWidth(yWidth);
		this.setXLeft(xLeft);
		this.setYTop(yTop);
		this.setRegNum(regNum);
	}
	
	/**
	 * This returns the left of property
	 * It is called by the constructor, addNewProperty method 
	 * and isPropOverlap method test in RegControl class when throwing exception 
	 * @return left's property
	 */
	public int getXLeft() {
		return this.xLeft;
	}
	
	/**
	 * This method sets or updates the value of left's property.
	 * @param left the left position of the property
	 */
	
	public void setXLeft(int left) {
		this.xLeft = left;
	}

	/**
	 * This returns the left plus the length of property
	 * It is called by the constructor and the method test(isPropSizeAvaiable) in regControl class
	 * @return the total of the left and the length of property
	 */
	
	public int getXRight() {
		return this.getXLeft() + this.getXLength();
	}
	
	/**
	 * This returns the top of property
	 * It is called by the constructor, addNewProperty method 
	 * and isPropOverlap method test in RegControl class when throwing exception 
	 * @return the top of the property
	 */

	public int getYTop() {
		return this.yTop;
	}
	
	/**
	 * This method sets or updates the value of top's property.
	 * @param top the top of property
	 */
	
	public void setYTop (int top) {
		this.yTop = top;
	}
	
	/**
	 * This returns the top plus the width of property
	 * It is called by the constructor and the method test(isPropSizeAvaiable) in regControl class
	 * @return the total of the top and the width of property
	 */
	
	public int getYBottom() {
		return this.getYTop() + this.getYWidth();
	}
	
	/**
	 * This returns the width of property
	 * It is called by the constructor, isPropertyMinimumSize 
	 * and isPropOverlap method test in RegControl class when throwing exception 
	 * @return the width of property
	 */
	
	public int getYWidth() {
		return this.yWidth;
	}
	
	/**
	 * This method sets or updates the value of width's property.
	 * @param width the width of property
	 */
	
	public void setYWidth(int width) {
		this.yWidth = width;
	}
	
	/**
	 * This returns the length of property
	 * It is called by the constructor, the method test(isPropertyMinimumSize)
	 * and isPropOverlap method test in RegControl class when throwing exception 
	 * @return the length of property
	 */
	
	public int getXLength() {
		return this.xLength;
	}
	
	/**
	 * This method sets or updates the value of length's property.
	 * @param length the length of property
	 */
	
	public void setXLength(int length) {
		this.xLength = length;
	}
	
	/**
	 * This returns the registrant number of property
	 * It is called by the constructor, addNewRegistrant method, findRegistrant method, 
	 * listOfProperties method, isRegExists method test in regControl Class
	 * @return registrant number of property
	 */
	
	public int getRegNum() {
		return this.regNum;
	}
	
	/**
	 * This method sets or updates the value of registrant number.
	 * @param regNum the registrant number of the property
	 */
	
	private void setRegNum(int regNum) {
		this.regNum = regNum;
	}
	
	/**
	 * This returns the area of property
	 * @return the area of property
	 */
	
	public int getArea() {
		return this.area;
	}
	
	/**
	 * This returns the annual taxes to be paid.
	 * @return the taxes of the property
	 */
	
	public double getTaxes() {
		return this.taxes;
	}
	
	/**
	 * This prints the property information including 
	 * the left, length, top, width and taxes to be paid of property.
	 */
	
	public String toString() {
		return ("\nCoordinates: " + getXLeft() + ", " + getYTop()
				+ "\nLength: " + getXLength() + " m	" + "Width: " + getYWidth() + " m"
				+ "\nRegistrant: " +"#" + getRegNum()
				+ "\nArea: " + getXLength() * getYWidth() + " m2"
				+ "\nProperty Taxes: $" + (getXLength() * getYWidth()) * TAX_RATE_PER_M2);
	}
	
	/**
	 * This compares whether the original left and new left,
	 * the original top and new top, the original length and new length,
	 * the original width and new with are equal or not.
	 * @param obj the obj of property
	 * @return true if the the original left and new left,
	 * the original top and new top, the original length and new length,
	 * the original width and new with are equal.
	 */
	
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Property)) 
			return false;
		
		//Reference: Java Type Casting. (n.d.). In w3schools.com. Retrieved from https://www.w3schools.com/java/java_type_casting.asp
		Property compare = (Property) obj;

		return (getXLeft() == compare.getXLeft()
			 && getYTop() == compare.getYTop()
		  	 && hasSameSides(compare));
		
	}
	
	/**
	 * This compares whether the original length and new length,
	 * the original width and new width are equal or not.
	 * @param prop the prop of property
	 * @return true if the original length and new length, the original width and new width are equal
	 */
	
	public boolean hasSameSides(Property prop) {
		
		return (getXLength() == prop.getXLength()&& getYWidth() == prop.getYWidth());
	}
	
	/**
	 * This check whether the property overlaps or not by using the top, with, length, left of property
	 * @param prop the prop of property
	 * @return true if top is less than top plus width, top plus width greater than top,
	 * left is less than left plus length and left plus length greater than left. 
	 */
	
	public boolean overlaps(Property prop) {
		if (   prop.getYTop() < getYTop()+getYWidth() 
			&& prop.getYTop() + prop.getYWidth() > getYTop()
			&& prop.getXLeft() < getXLeft() + getXLength() 
			&& prop.getXLeft() + prop.getXLength() > getXLeft())
			return true;

		return false;
		
	}

}
