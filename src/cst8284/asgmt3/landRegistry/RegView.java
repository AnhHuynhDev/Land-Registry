package cst8284.asgmt3.landRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

	/**
	 * This class handles the input and output of information, handles output to the console
	 * as well as supplies the user interface to the program.
	 * @author Thi Kim Anh Huynh
	 * @version 1.2
	 */

public class RegView {
	
	/**
	 * This is the scanner to promp user input.
	 */
	
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * This is variables call RegControl
	 */
	
	private static RegControl rc;
	
	/**
	 * {@value #PROPERTIES_FILE} this is the file of properties.
	 * {@value #REGISTRANTS_FILE} this is the file of registrants.
	 */
	public static final String PROPERTIES_FILE = "LandRegistry.prop";
	public static final String REGISTRANTS_FILE = "LandRegistry.reg";
	
	/**
	 * {@value #ADD_NEW_REGISTRANT} This variable is called to add new registrant
	 * {@value #FIND_REGISTRANT} This variable is called to find registrant
	 * {@value #LIST_REGISTRANTS} This variable is called to list all registrants
	 * {@value #DELETE_REGISTRANT} This variable is called to delete registrant
	 * {@value #ADD_NEW_PROPERTY} This variable is called to add new property
	 * {@value #DELETE_PROPERTY} This variable is called to delete property
	 * {@value #CHANGE_PROPERTY_REGISTRANT} This variable is called to change registrant number of property
	 * {@value #LIST_PROPERTY_BY_REGNUM} This variable is called to list properties by using regNum
	 * {@value #LIST_ALL_PROPERTIES} This variable is called to all properties
	 * {@value #LOAD_LAND_REGISTRY_FROM_BACKUP} This variable is called to load land registry from backup
	 * {@value #SAVE_LAND_REGISTRY_TO_BACKUP} This variable is called to save land registry from backup
	 * {@value #EXIT} This is called to exit the program
	 */
	
	private static final int ADD_NEW_REGISTRANT = 1;
	private static final int FIND_REGISTRANT = 2;
	private static final int LIST_REGISTRANTS = 3;
	private static final int DELETE_REGISTRANT = 4;
	private static final int ADD_NEW_PROPERTY = 5;
	private static final int DELETE_PROPERTY = 6;
	private static final int CHANGE_PROPERTY_REGISTRANT = 7;
	private static final int LIST_PROPERTY_BY_REGNUM = 8;
	private static final int LIST_ALL_PROPERTIES = 9;
	private static final int LOAD_LAND_REGISTRY_FROM_BACKUP = 10;
	private static final int SAVE_LAND_REGISTRY_TO_BACKUP = 11;
	private static final int EXIT = 0;
	
	/**
	 * This calls new RegControl.
	 */
	public RegView() {
		rc = new RegControl();	 
	}
	
	/**
	 * This returns the new RegControl.
	 * @return new RegControl
	 */
	
	private RegControl getRegControl() {
		return this.rc;
	}
	
	/**
	 * This method executes the RegView application
	 */
	
	public static void launch() {
		int choice = 1;
		rc.loadFromFile(REGISTRANTS_FILE); 
		rc.loadFromFile(PROPERTIES_FILE); 
		
		while(choice!=0) {
			choice = displayMenu();
			scan.nextLine();
			try {
				executeMenuItem(choice);
			} catch (BadLandRegistryException e) {
				System.out.println("\nEXCEPTION!!!\n"+e.getHeader()+": "+e.getMessage());
			}
		}
		rc.saveToFile(rc.listOfRegistrants(), REGISTRANTS_FILE);
		rc.saveToFile(rc.listOfAllProperties(), PROPERTIES_FILE);
	}
	
	/**
	 * This method displays the selection menu for the user to choose
	 * @return the input of user
	 */
	
	private static int displayMenu() {
		System.out.println("");
		System.out.println("Enter a selection from the following menu:");
		System.out.println("1. Enter a new registrant");
		System.out.println("2. Find registrant by registrant number");
		System.out.println("3. Display list of registrants");
		System.out.println("4. Delete Registrant");
		System.out.println("5. Register a new property");
		System.out.println("6. Delete Property");
		System.out.println("7. Change a property's registrant");
		System.out.println("8. Display all properties with the same registration number");
		System.out.println("9. Display all registered properties");
		System.out.println("10. Load land registry from backup file");
		System.out.println("11. Save land registry to backup file");
		System.out.println("0. Exit program");
		System.out.println("");
		return scan.nextInt();
	}
	
	/**
	 * This method choose the appropriate selection view base on the choice number
	 * @param choice the number of the chosen menu 
	 */
	
	private static void executeMenuItem (int choice) {
		switch (choice) {
		
		case ADD_NEW_REGISTRANT: 
			viewAddNewRegistrant();
			break;
			
		case FIND_REGISTRANT:			
			viewFindRegistrant();
			break;
			
		case LIST_REGISTRANTS:		
			viewListOfRegistants();
			break;
			
		case DELETE_REGISTRANT:
			viewDeleteRegistrant();
			break;
			
		case ADD_NEW_PROPERTY:
			viewAddNewProperty();
			break;
			
		case DELETE_PROPERTY:
			viewDeleteProperty();
			break;
			
		case CHANGE_PROPERTY_REGISTRANT:
			viewChangePropertyRegistrant();
			break;
			
		case LIST_PROPERTY_BY_REGNUM:
			viewListPropertyByRegNum();
			break;			
			
		case LIST_ALL_PROPERTIES:
			viewListAllProperties();
			break;
			
		case LOAD_LAND_REGISTRY_FROM_BACKUP:
			viewLoadLandRegistryFromBackUp();
			break;		
			
		case SAVE_LAND_REGISTRY_TO_BACKUP:
			viewSaveLandRegistryToBackUp();
			break;		
			
		case EXIT:
			System.out.print("Existing Land Registry");
			break;
		}		
	}
	
	/**
	 * This method received a message parameter and print out that message, also prompt user to input a string
	 * @param s the message of the print message
	 * @return the String of the user input
	 */
	
	//Reference: Written by professor Dave HoutMan from Assignment 1 document
	
	private static String getResponseTo (String s) {
		System.out.print(s);
		return(scan.nextLine());
	}
	
	/**
	 * Display a request to enter registration number, and prompt user to input an int
	 * @return the Integer of the registration number of user input
	 */
	
	private static int requestRegNum() {
		System.out.print("Enter registration number: ");
		int reg = scan.nextInt();
		scan.nextLine();
		return reg;
	}
	
	/**
	 * This method make new Registrant by prompting the user to enter name.
	 * This will check the requirement format of user input as well.
	 * @return the new Registrant the user just made 
	 * @throws BadLandRegistryException if the input of user 
	 * (check isNullValue, isContainTwoParts) is invalid
	 */
	
	private static Registrant makeNewRegistrantFromUserInput() {
		String firstLastName = getResponseTo("Enter registant's First and Last name: ");	
		if(RegControl.isNullValue(firstLastName))
			throw new BadLandRegistryException("Null value entered", "An attempt was made to pass a null value to a variable.");
			
		if(!RegControl.isContainTwoParts(firstLastName.split(" ")))
			throw new BadLandRegistryException("Missing value", "Missing an input value");

		Registrant userReg = new Registrant(firstLastName);
		return(userReg);
	}

	/**
	 * This method make new Property prompting the user to enter dimension and coordinates.
	 * This will check the requirement format of user input as well.
	 * @return the new Property the user just made
	 * @throws BadLandRegistryException if the input of user 
	 * (check isNullValue, isContainTwoParts) is invalid
	 */
	
	private static Property makeNewPropertyFromUserInput() {
		int numReg = requestRegNum();
		
		String coordinates = getResponseTo("Enter top and left coordinates of property (as X, Y): ");
		if(RegControl.isNullValue(coordinates))
			throw new BadLandRegistryException("Null value entered", "An attempt was made to pass a null value to a variable.");
		
		// Reference: Split() String method in Java with examples (n.d.). In GreeksforGreeks. Retrieved from https://www.geeksforgeeks.org/split-string-java-examples/
		String[] coors = coordinates.split(", "); 
		if(!RegControl.isContainTwoParts(coors))
			throw new BadLandRegistryException("Missing value", "Missing an input value");
			
		String lengthWidth = getResponseTo("Enter length and width of property (as length, width): ");
		String[] lw = lengthWidth.split(", ");
		if(!RegControl.isContainTwoParts(lw))
			throw new BadLandRegistryException("Missing value", "Missing an input value");

		//Reference: Java Convert String to int (n.d.). In JavaTpoint. Retrieved from https://www.javatpoint.com/java-string-to-int
		Property prop =  new Property(Integer.valueOf(lw[0]), Integer.valueOf(lw[1]), Integer.valueOf(coors[0]), Integer.valueOf(coors[1]), numReg);
	
		return prop;
	}
	
	
	/**
	 * This method show new registrant the user added
	 */
	
	public static void viewAddNewRegistrant() {
		
		Registrant newReg = makeNewRegistrantFromUserInput();
		
		if(rc.addNewRegistrant(newReg)==null) 
			System.out.println("Could not add new registrant; registrant array is full.");
		else {
			System.out.println("Registrant added: ");
			System.out.println(newReg.toString());
		}	
	}
	
	/**
	 * This method help user fins the registrant by entering regNum
	 */
	
	public static void viewFindRegistrant() {
		
		int num = requestRegNum();
		Registrant searchReg = rc.findRegistrant(num);
		
		if (searchReg==null) {
			System.out.println("No registran founded!");
		} else {
			System.out.println(searchReg.toString());
		}
	}
	
	/**
	 * This method list all registransts
	 */
	
	public static void viewListOfRegistants() {
		if (rc.listOfRegistrants().size()!=0) {
			for (int i = 0; i < rc.listOfRegistrants().size(); i++) {
				System.out.println("List of registrants: \n");
				System.out.println(rc.listOfRegistrants().get(i).toString());
			}
		} else {
			System.out.println("No Registrants load yet");
		}
	}
	
	/**
	 * This method helps user delete registrant by entering the registrant number
	 * This will check the requirement format of user input as well.
	 * @throws BadLandRegistryException if the input of user (check isNullValue) is invalid
	 */
	
	public static void viewDeleteRegistrant() {
		
		System.out.print("Enter registration number of property to delete: ");
		int reg = scan.nextInt();
		scan.nextLine();		
		rc.deleteRegistrant(reg);
		
		System.out.print("You are about to delete a registrant number and all the its associated properties; "
						+ "do you wish to continue? (Enter ‘Y’ to proceed.)");
		String respond = scan.nextLine();
		
		if(RegControl.isNullValue(respond))
			throw new BadLandRegistryException("Missing value", "An attempt was made to pass a null value to a variable.");
		
		if (respond.equals("Y") || respond.equals("y")) {
			rc.deleteProperties(rc.listOfProperties(reg));
			System.out.print("Registrant and related properties deleted");
		} 
	}
 	
	/**
	 * This method show new property the user added
	 */
	
	public static void viewAddNewProperty() {		
		
		Property prop = makeNewPropertyFromUserInput();
		
		Property newProp = rc.addNewProperty(prop);
		
		if (!newProp.equals(prop)){
			System.out.println("New property could not be registered;");
			System.out.println("There is already a property registered at:");
			System.out.println(newProp.toString());
		} else {
			System.out.println("New property has been registered as: ");
			System.out.println(prop.toString());
		}
	}
	
	/**
	 * This method helps user delete property by entering the registrant number
	 */
	
	public static void viewDeleteProperty() {
		
		System.out.print("Enter registration number of property to delete: ");
		int reg = scan.nextInt();
		scan.nextLine();
		

		ArrayList<Property> deleteProp = rc.listOfProperties(reg);
		
		if (rc.findRegistrant(reg) == null && deleteProp.size() == 0) {
			System.out.print("No properties are associated with that registration number");
		} else {
			System.out.println("Properties associated with that registration number are:" );
			for (int i =0; i<deleteProp.size(); i++)
				System.out.println(deleteProp.get(i));
			
			
			System.out.println("You are about to delete " + deleteProp.size() + " properties; do you wish to continue?");
			System.out.print("Enter ‘Y’ to proceed.");		
			String respond = scan.nextLine();
			
			if (respond.equals("Y")) {
				rc.deleteProperties(deleteProp);
				System.out.println("Property/ies deleted");
			}		
		}		
	}
	
	/** 
	 * This method show change the registrant number of the property
	 */
	
	public static void viewChangePropertyRegistrant() {
		System.out.print("Enter original registrants number: ");
		int orgNum = scan.nextInt();
		System.out.print("Enter new registrant number: ");
		int newNum = scan.nextInt();
			
		ArrayList<Property> oldProps = rc.listOfProperties(orgNum);	
		ArrayList<Property>	changeProps = rc.changePropertyRegistrant(oldProps, newNum);
			
		
		if (changeProps == null || changeProps.size() == 0) {
			System.out.println("Registant not exist");
		} else  {
			System.out.println("Operation completed; the new registration number, " + newNum + ", has replaced " + orgNum + " in all affected properties");
		} 
		
	}
	
	/**
	 * This method lists property by promting regNum through user	
	 */
	
	public static void viewListPropertyByRegNum() {
		ArrayList<Property> numProp = new ArrayList<>();
		numProp = rc.listOfProperties(requestRegNum());
		
		if (numProp == null) {
			System.out.println("No registrant founded!");
		}else {
			
			for(int i =0; i< numProp.size(); i++) {
				System.out.println(numProp.get(i).toString());
			}		
		}
	}
	
	/**
	 * This method lists all properties
	 */
	
	public static void viewListAllProperties() {
		ArrayList<Property> listProp = rc.listOfAllProperties();

		if(listProp.size() != 0) {
			for(int i=0; i < listProp.size(); i++)
				System.out.println(listProp.get(i).toString());
		} else {
			System.out.println("Property Registry empty; no properties to display");
			}
	}
	
	/**
	 * This method will delete the existing land and load
	 */
	
	public static void viewLoadLandRegistryFromBackUp() {
		
		System.out.println("You are about to overwrite existing records; do you wish to continue? (Enter ‘Y’ to proceed.)");
		String respond = scan.nextLine();
		
		if (respond.equals("Y") || respond.equals("y")) {
			if (rc.loadFromFile(REGISTRANTS_FILE) != null && rc.loadFromFile(PROPERTIES_FILE) != null){
				
				rc.refreshProperties();
				rc.refreshRegistrants();
				System.out.println("Land Registry has been loaded from backup file");
			} else
			System.out.println("Land Registry has not been loaded from backup file");
		} 
			
	}
	
	/**
	 * This method save Registrants and Properties to files
	 */
	
	public static void viewSaveLandRegistryToBackUp() {
		
		if(rc.saveToFile(rc.listOfRegistrants(), REGISTRANTS_FILE) 
			&& rc.saveToFile(rc.listOfAllProperties(), PROPERTIES_FILE))
			System.out.println("Land Registry has been backed up to file");
		else 
			System.out.println("Land Registry has not been backed up to file");
	}
	
	/**
	 * This method receive the ArrayList displayList, then
	 * combine all of it and pring toString
	 */
	
	private static <T> String toString (ArrayList<T> displayList) {
		String result = "";	
		for (int i = 0; i < displayList.size(); i++) 	
			result += displayList.get(i).toString() + "\n";
		
		return result;
	}
	
}
