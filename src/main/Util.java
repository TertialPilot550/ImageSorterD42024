package main;
/**
 * @author sammc
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {

	/**
	 * Save image to the specified filepath
	 *
	 * @param image
	 * @param filepath
	 */
	public static void saveImage(BufferedImage image, String filepath) {
		try {
			ImageIO.write(image, "png", new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads an image from a file into a BufferedImage.
	 *
	 * @param String filename
	 * @return BufferedImage
	 */
	public static BufferedImage loadImage(String filepath) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			System.out.println("File not loaded: " + filepath);
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * Make the necessary directories for sorting the images
	 */
	public static void makeDirectories() {
		makeDirectory(SortFrame.GOOD_IMAGES_DIRECTORY);
		makeDirectory(SortFrame.BAD_IMAGES_DIRECTORY);
		makeDirectory(SortFrame.OTHER_IMAGES_DIRECTORY);
	}

	/**
	 * Creates a new directory at the specified filepath if it doesn't already exist
	 * @param filepath
	 */
	private static void makeDirectory(String filepath) {
		File directoryFile = new File(filepath);
		// If it doesn't exist or isn't a directory
		if (!directoryFile.exists() || !directoryFile.isDirectory()) {
			directoryFile.mkdir();
		}
	}

}
