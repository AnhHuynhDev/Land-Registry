package cst8284.asgmt3.landRegistry;
import java.util.ArrayList;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

	/**
	 * This class implements the real action in land registry application
	 * including ArrayList methods, Utility methods, Controller methods 
	 * and method test that throws exception
	 * @author Thi Kim Anh Huynh
	 * @version 1.2
	 */
	
	public class RegControl implements Serializable {

	/**
	 * This is the ArrayList of registrants
	 */
	
	private ArrayList<Registrant> registrants = new ArrayList<>();
	
	/**
	 * This is the ArrayList of properties
	 */
	
	private ArrayList<Property> properties = new ArrayList<>();

	
	public RegControl(){}
	
	/**
	 * This is the method test for bad registrant input. 
	 * This will check the registrant number that user enter is correct form or not.
	 * It has to contain number only, not any character.
	 * @param num the registrant number that user input
	 * @return true if the registrant number format is correct
	 */
	
	public static boolean isValidRegNum(String num) {
		try {
			return Integer.parseInt(num)>=1000;
		} catch (NumberFormatException e) {}
		return false;	
	}
	
	/**
	 * This is the method test for bad registrant input.  
	 * This will check whether the registrant number is available or not. 
	 * @param reg the registrant number that user input
	 * @return true if the registrant number exits
	 */

	public static boolean isRegsAvailable(RegControl reg) {
		return reg.listOfRegistrants().size() != 0;
	}
	
	/**
	 * This is the method test for bad registrant input.
	 * This will check whether the registrants has the registrant number or not.
	 * @param num the registrant number that user input
	 * @param reg the registrant of the registrant list
	 * @return true if registrant has that registration number
	 */
	
	public static boolean isRegExists(int num, RegControl reg) {
		for (int i = 0; i < reg.getRegistrants().size(); i++) {
			Registrant re = reg.getRegistrants().get(i);
				if (re.getRegNum() == num)
					return true;
		}
		return false;
	}
	
	/**
	 * This is the method test for bad property input. 
	 * This will check that whether the property is overlap or not.
	 * @param prop the property that user enter
	 * @param reg the property of the property list
	 * @return true if the property is not overlap
	 */
	
	public static boolean isPropOverlap(Property prop, RegControl reg) {
		for (int i = 0; i < reg.getProperties().size(); i++) {  
			 Property element = reg.getProperties().get(i);
				 if(element.overlaps(prop))
					 return true;	 
		 }
		 return false;
	}
	
	/**
	 * This is the method test for bad property input.
	 * This will check if the property size meets the condition or not,
	 * returns the right and the bottom of property.
	 * @param prop the property that user input
	 * @return true if the right and the bottom of property is less or equal to 1000
	 */
	
	public static boolean isPropSizeAvaiable(Property prop) {
		return prop.getXRight()<=1000 && prop.getYBottom()<=1000;
	}
	
	/**
	 * This is the method test for bad property input.
	 * This will check if the property size meets the condition or not,
	 * returns the length and the width of property.
	 * @param prop the property that user input
	 * @return true if the length property is more than 20m and the width of propery is more than 10m
	 */
	
	public static boolean isPropertyMinimumSize(Property prop) {
		return prop.getXLength()>20 && prop.getYWidth()>10;
	}
	
	/**
	 * This is the method test for bad general input.
	 * This will check to see whether the input that user enter from 
	 * first name, last name, regNum, or property coordinate is missing or not.
	 * @param string the input that user enter from first name, last name, regNum, or property coordinate
	 * @return true if the input from user is correct form, which has the size of two words/numbers.
	 */
	
	public static boolean isContainTwoParts(String[] string) {
		return string.length==2;
	}
	
	/**
	 * This is the method test for bad general input.
	 * This will check to see whether the user enter value from 
	 * first name, last name, regNum, or property coordinate or not.
	 * @param string the input that user enter from first name, last name, regNum, or property coordinate
	 * @return true if the input of user meets requirements, which means that
	 * the input can not be null or just white spaces.
	 */
	
	// Reference: Smotricz, C. (2016, March 5). How do I check that a Java String is not all whitespaces? Retrieved from stackoverflow: https://stackoverflow.com/questions/3247067/how-do-i-check-that-a-java-string-is-not-all-whitespaces
	public static boolean isNullValue(String string) {
		return string=="" || string==null || string.trim().length() == 0;
	}
	
	/**
	 * This is the list of all registrants from Registrant.
	 * @return the list of all registrants
	 */
	
	private ArrayList<Registrant> getRegistrants() {
		return this.registrants;
	}
	
	/**
	 * This is the list of all properties from Property.
	 * @return the list of all properties
	 */
	
	private ArrayList<Property> getProperties() { 
		return this.properties;
	}
	
	/**
	 * This is the method to allow user add new registrant.
	 * @param reg the new registrant is added
	 * @return the new registrant
	 */
	
	public Registrant addNewRegistrant (Registrant reg) {
		this.getRegistrants().add(reg);
		return reg;
	}
	
	/**
	 * This is the method to find registrant by registrant number.
	 * This will check whether the new registrant number is the same as any registrant number of exist registrant,
	 * as well as the requirement format of user input.
	 * @param regNum the new registrant number that user enter
	 * @return the registrant if the new registrant number is same as any registrant number of exist registrant
	 * @throws BadLandRegistryException if the input of user (check isValidRegNum, isRegsAvailable, isRegExists) is invalid
	 */
	
	public Registrant findRegistrant (int regNum) {
		
		if(!isValidRegNum(String.valueOf(regNum)))
			throw new BadLandRegistryException("Invalid Registration number", "Registration number must contain digits only; alphabetic and special characters are prohibited");
		if(!isRegsAvailable(this))
			throw new BadLandRegistryException("No registrants available", "There are no registrants currently listed");	
		if(!isRegExists(regNum, this))
			throw new BadLandRegistryException("Unregistered value", "There is no registrant having that registration number");
		
		for (int i = 0; i < getRegistrants().size(); i++) {
			Registrant reg = getRegistrants().get(i);
				if (reg.getRegNum() == regNum)
					return reg;
		}
		return null;
	}
	
	/**
	 * This methods is list of all registrants.
	 * @return the list of all registrants.
	 */
	
	public ArrayList<Registrant> listOfRegistrants() {	
		return getRegistrants();
	}
	
	/**
	 * This method is allow user delete registrant by using the exit registrant number.
	 * This will check the requirement format of user input.
	 * @param regNum the registrant number that user enter
	 * @return the new registrant list, which is no longer have the previous delete one.
	 * @throws BadLandRegistryException if the input of user (check isValidRegNum, isRegsAvailable, isRegExists) is invalid
	 */

	public Registrant deleteRegistrant (int regNum) {
		
		if(!isValidRegNum(String.valueOf(regNum)))
			throw new BadLandRegistryException("Invalid Registration number", "Registration number must contain digits only; alphabetic and special characters are prohibited");
		if(!isRegsAvailable(this))
			throw new BadLandRegistryException("No registrants available", "There are no registrants currently listed");
		if(!isRegExists(regNum, this))
			throw new BadLandRegistryException("Unregistered value", "There is no registrant having that registration number");
		
		Registrant deleteReg = findRegistrant(regNum);
		getRegistrants().remove(deleteReg);
		return deleteReg;
	}
	
	/**
	 * This is the method to allow user add new property if the new property is not overlap with the exit one.
	 * This will check the requirement format of user input as well.
	 * @param prop the new property is added
	 * @return the new property added
	 * @throws BadLandRegistryException if the input of user 
	 * (check isPropOverlap, isPropSizeAvaiable, isPropertyMinimumSize) is invalid
	 */
	
	public Property addNewProperty (Property prop) {
		Property overlapProp = propertyOverlaps(prop);
		
		if(isPropOverlap(prop, this))
			throw new BadLandRegistryException("Property overlap", "The property entered overlaps with an existing property with coordinates "+overlapProp.getXLeft()+" "+overlapProp.getYTop()+" and size "+overlapProp.getXLength()+", "+overlapProp.getYWidth());
		if(!RegControl.isPropSizeAvaiable(prop))
			throw new BadLandRegistryException("Property exceeds available size", "The property requested extends beyond the boundary of the available land");
		
		if(!RegControl.isPropertyMinimumSize(prop))
			throw new BadLandRegistryException("Property below minimum size", "Properties cannot be dimensions smaller than 20 X 10");
			
		
		if (overlapProp==null) {
			this.getProperties().add(prop);
			return prop;
		}
		return overlapProp;
	}
	
	/**
	 * This method allows user delete property.
	 * @param deleteList the list of property that need to be deleted
	 * @return true if the deletList is deleted successfully
	 */
	
	public boolean deleteProperties (ArrayList<Property> deleteList) {

    	if ((deleteList == null)||(deleteList.size() == 0))
    		return false;
    	else this.getProperties().remove(deleteList);
			return true;
	}
	
	/**
	 * This method allows user change the original registrant number to the new one.
	 * This will check the requirement format of user input as well.
	 * @param oldRegNumPropertyArrayList the original registrant number
	 * @param newRegNum the new registrant number 
	 * @return the new registrant number, which has replaced in all affected properties
	 * @throws BadLandRegistryException if the input of user 
	 * (check isValidRegNum, isRegsAvailable, isRegExists) is invalid
	 */
	
	
	public ArrayList<Property> changePropertyRegistrant (ArrayList<Property> oldRegNumPropertyArrayList, int newRegNum) {
		
		if(!isValidRegNum(String.valueOf(newRegNum)))
			throw new BadLandRegistryException("Invalid Registration number", "Registration number must contain digits only; alphabetic and special characters are prohibited");
		if(!isRegsAvailable(this))
			throw new BadLandRegistryException("No registrants available", "There are no registrants currently listed");
		if(!isRegExists(newRegNum, this))
			throw new BadLandRegistryException("Unregistered value", "There is no registrant having that registration number");
		
		Registrant findReg = findRegistrant(newRegNum);
		if (findReg == null) 
			return null;
	
		ArrayList<Property> newProperties = new ArrayList<Property>();
		for (Property element: oldRegNumPropertyArrayList) {
			Property newProperty = new Property(element, newRegNum);
			this.getProperties().remove(element);
			newProperties.add(newProperty);	
		}	
		
		this.getProperties().addAll(newProperties);

		return newProperties;	
	}
	
	/**
	 * This method list of all properties in the list
	 * @return the list of all properties
	 */
	
	public ArrayList<Property> listOfAllProperties() {
		return getProperties();
	} 
	
	/**
	 * This method allows user to display only the property with specific regNum.
	 * This will check the requirement format of user input as well.
	 * @param regNum the registrant number that user want to display properties
	 * @return the list of property with specific regNum
	 * @throws BadLandRegistryException if the input of user 
	 * (check isValidRegNum, isRegsAvailable, isRegExists) is invalid
	 */
	
	public ArrayList<Property> listOfProperties (int regNum) {	
		if(!isValidRegNum(String.valueOf(regNum)))
			throw new BadLandRegistryException("Invalid Registration number", "Registration number must contain digits only; alphabetic and special characters are prohibited");
		if(!isRegsAvailable(this))
			throw new BadLandRegistryException("No registrants available", "There are no registrants currently listed");	
		if(!isRegExists(regNum, this))
			throw new BadLandRegistryException("Unregistered value", "There is no registrant having that registration number");
		
		if (regNum == 0) 
			return listOfAllProperties();
		
		ArrayList<Property> result = new ArrayList<>();

		for (Property i: getProperties()) {
			if (i.getRegNum()==regNum) 
				result.add(i);			
		}
		return result;		
	}
	
	/**
	 * This method checks whether the property is overlap or not
	 * @param prop the property that is going to be checked
	 * @return the original property if the new property is overlap
	 */
	
	private Property propertyOverlaps (Property prop) {
		 for (int i = 0; i < getProperties().size(); i++) {  
			 Property element = getProperties().get(i);
				 if(element.overlaps(prop))
					 return element;	 
		 }
		 return null;
	}
	
	/**
	 * This method will receive the ArrayList and saves the ArrayList to file
	 * @param <T> the type of ArrayList
	 * @param source the source that is going to be saved
	 * @param fileName the name of the file to be saved
	 * @return true if the file saves successful
	 */

	public <T> boolean saveToFile (ArrayList<T> source, String fileName) {	
		
		//Reference: By professor Dave HoutMan from Hybrid Week 6 - Java File I/O Part 2
		File myFile = new File(fileName);
		if (myFile.exists()){
			myFile.delete();
		}
		
		try {
			FileOutputStream saveFile = new FileOutputStream(fileName);
			ObjectOutputStream file = new ObjectOutputStream(saveFile);
			file.writeObject(source);
			file.close();
		} catch (FileNotFoundException e) {		 
			e.printStackTrace();
			System.out.println("File not found");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * This method loads the program of user input from file
	 * @param <T> type of ArrayList
	 * @param fileName the name of the file to be loaded
	 * @return the file that loads successful
	 */

	public <T> ArrayList<T> loadFromFile (String fileName) {
		
		ArrayList<T> dataInfo = null;
		
		//Reference: By professor Dave HoutMan from Hybrid Week 6 - Java File I/O Part 2
		
		try {
			FileInputStream readFile = new FileInputStream(fileName);
			ObjectInputStream file = new ObjectInputStream(readFile);
				
			dataInfo = (ArrayList<T>)(file.readObject());

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
			System.out.println("Array of employees has been transfered from file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataInfo;	
	}
	
	/**
	 * This method is clear the properties first, 
	 * then add all properties in RegView.PROPERTIES_FILE from load from file.
	 */
	
	public void refreshProperties() {
		getProperties().clear();
		getProperties().addAll(loadFromFile(RegView.PROPERTIES_FILE));
		
	}
	
	/**
	 * This method is clear the registrants first, 
	 * then add all registrants in RegView.REGISTRANTS_FILE from load from file.
	 */
	
	public void refreshRegistrants() {
		getRegistrants().clear();
		getRegistrants().addAll(loadFromFile(RegView.REGISTRANTS_FILE));
			
	}
	
	
	
}
