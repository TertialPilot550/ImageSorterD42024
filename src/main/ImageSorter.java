package main;
/**
 * @author sammc
 */
public class ImageSorter {

	/*
	 * Instructions:
	 * 
	 *		- make sure you have java runtime enviornment installed. open the project in a java ide.
	 * 		- place the '/png' folder containing the graph images you want to sort into the project directory (not /src)
	 * 			- make sure the folder is still named '/png'. I recommend placing only you portion of the images so that
	 * 			  you don't accidentally do more than was assigned to you.
	 *  	- replace the START_INDEX variable with the first Image ID you would like to open
	 * 		- run the program.
	 *
	 * The program will make three subfolders within the /png folder. It is important that the base folder is named /png.
	 *
	 * A key press of 1 will move the image into the /png/goodImages subfolder.
	 * A key press of 2 will move the image into the /png/badImages subfolder.
	 * A key press of 3 will move the image into the /png/otherImages subfolder, for later review.
	 *
	 * A key press of q, or closing the window, will quit the program.
	 */

	// Select the first image ID to open
	// Samuel McDaniel: 26,925–33,656
	// pace: ~2700 per hour
	// range: 6,731
	// total completion time estimation: 2.5 hours
	private static final int START_INDEX = 29169; // REPLACE ME TODO

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SortFrame sortFrame = new SortFrame(START_INDEX);
	}

}
