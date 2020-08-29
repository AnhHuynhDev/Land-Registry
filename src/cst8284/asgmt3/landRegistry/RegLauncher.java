package cst8284.asgmt3.landRegistry;
import java.util.ArrayList;

	/**
	 * This is the main point of entry for this program, which is to dispatch the application by first
	 * instantiating a new RegView class, and afterward calling its launch() method.
	 * This runs the program.
	 * @author Thi Kim Anh Huynh
	 * @version 1.2
	 */

	public class RegLauncher {
	
	public static void main (String [] args) {

		RegView runReg = new RegView();
		runReg.launch();

	}

}
