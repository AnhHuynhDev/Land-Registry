package cst8284.asgmt3.landRegistry;

	/**
	 * This class throw exception when user enter the bad input.
	 * @author Thi Kim Anh Huynh
	 * @version 1.2
	 */

	public class BadLandRegistryException extends RuntimeException {
	
	/**
	 * This is the header when throwing exception.
	 */
	
	private String header;
	
	/**
	 * This return the header when user enter the bad input.
	 * It is called by the launch method in RegView class when the user enter the bad input.
	 * @return the header of bad input exception
	 */
	
	public String getHeader() {
		return this.header;
	}
	
	/**
	 * This method sets the header when user enter the bad input.
	 * It is called by the constructor.
	 * @param header the header of bad input exception
	 */
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * This creates the BadLandRegistryException, which is chained to another
	 * BadLandRegistryException constructors with specific header and message.
	 */
	
	BadLandRegistryException(){
		this ("Bad land registry data entered", "Please try again");
	}
	
	/**
	 * This creates property with specific header and message.
	 * @param header the header of bad land registry input exception
	 * @param message the message of bad land registry input exception
	 */
	
	BadLandRegistryException (String header, String message) {
		super(message);
		setHeader(header);	
	}
	

}
