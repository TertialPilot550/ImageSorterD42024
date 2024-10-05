package main;

/**
 * @author sammc
 */
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SortFrame extends JFrame implements KeyListener {

	public static final String SOURCE_IMAGE_DIRECTORY = "./png";
	public static final String GOOD_IMAGES_DIRECTORY = SOURCE_IMAGE_DIRECTORY + "/goodImages";
	public static final String BAD_IMAGES_DIRECTORY = SOURCE_IMAGE_DIRECTORY + "/badImages";
	public static final String OTHER_IMAGES_DIRECTORY = SOURCE_IMAGE_DIRECTORY + "/otherImages";

	private static final String TITLE_STRING = "Press a key: (1: Good, 2: Bad, 3: Manual, u: undo)";

	// Generated Serial ID
	private static final long serialVersionUID = -5997838658258154082L;

	private boolean wasPrevious;
	private int currentIndex;
	private BufferedImage currentImage;
	private JPanel imagePanel;

	public SortFrame(int startIndex) {
		this.currentIndex = startIndex;
		init();
	}

	private void init() {
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addKeyListener(this);
		updateTitle();

		wasPrevious = false;

		Util.makeDirectories(); // Make the folders to sort the images into

		currentImage = loadTestImageNumber(currentIndex);

		imagePanel = new JPanel() {
			// Generated Serial ID
			private static final long serialVersionUID = 2370217236110499307L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), null);
			}
		};
		imagePanel.setVisible(true);
		imagePanel.setFocusable(false);
		imagePanel.setSize(getWidth(), getHeight() - 30);
		add(imagePanel);
		imagePanel.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == '1') {
			// Good image
			saveGoodImage();
			nextImage();
		} else if (e.getKeyChar() == '2') {
			// Bad Image
			saveBadImage();
			nextImage();
		} else if (e.getKeyChar() == '3') {
			// ? Folder
			saveOtherImage();
			nextImage();
		} else if (e.getKeyChar() == 'u') {
			// Undo
			undo();

		}
	}

	private void updateTitle() {
		setTitle(currentIndex + ".png: " + TITLE_STRING);
	}

	private void saveGoodImage() {
		saveCurrentImage(GOOD_IMAGES_DIRECTORY);
	}

	private void saveBadImage() {
		saveCurrentImage(BAD_IMAGES_DIRECTORY);
	}

	private void saveOtherImage() {
		saveCurrentImage(OTHER_IMAGES_DIRECTORY);
	}

	/**
	 * Saves the currentImage instance variable to the specified directory
	 * 
	 * @param directoryPath
	 */
	private void saveCurrentImage(String directoryPath) {
		String imageName = "/" + currentIndex + ".png";
		Util.saveImage(currentImage, directoryPath + imageName);
	}

	/**
	 * Delete the currentImage from the source folder, cycles to the next image id,
	 * and load that image to the image panel.
	 */
	private void nextImage() {
		File file = new File(SOURCE_IMAGE_DIRECTORY + "/" + currentIndex + ".png");
		if (!file.delete()) {
			System.out.println("Error deleting image");
		}
		currentImage = loadTestImageNumber(++currentIndex);
		updateTitle();
		imagePanel.repaint();
		wasPrevious = true;
	}

	/**
	 * Loads the image from the source image directory with the provided number as
	 * an ID.
	 * 
	 * @param number
	 * @return
	 */
	private BufferedImage loadTestImageNumber(int number) {
		String filepath = SOURCE_IMAGE_DIRECTORY + "/" + number + ".png";
		return Util.loadImage(filepath);
	}

	private void undo() {
		if (!wasPrevious) {
			System.out.println("ERROR COMPLETING UNDO, no previous image");
			return;
		}
		// Find previous image
		int previousIndex = currentIndex - 1;
		String directoryPath = getDirectoryContainingTestImageNumber(previousIndex);
		if (directoryPath == null) {
			System.out.println("ERROR COMPLETING UNDO, finding image");
			return;
		}
		
		String imagePath = directoryPath + "/" + previousIndex + ".png";
		BufferedImage image = Util.loadImage(imagePath);
		
		
		// Delete previous image from it's assigned folder

		// Move image back to the source directory
		Util.saveImage(image, SOURCE_IMAGE_DIRECTORY + "/" + previousIndex + ".png");
		
		
		// Delete previous image from it's assigned folder
		File imageFile = new File(imagePath);
		if (!imageFile.delete()) {
			System.out.println("ERROR COMPLETING UNDO, deletion");
			return;
		}
		
		// Update the currentIndex
		wasPrevious = false;
		currentIndex = previousIndex;
		// load the image again
		currentImage = loadTestImageNumber(currentIndex);
		imagePanel.repaint();
		
	}

	private String getDirectoryContainingTestImageNumber(int number) {
		String filename = "/" + number + ".png";
		if (fileExists(GOOD_IMAGES_DIRECTORY + filename)) {
			return GOOD_IMAGES_DIRECTORY;
		} else if (fileExists(BAD_IMAGES_DIRECTORY + filename)) {
			return BAD_IMAGES_DIRECTORY;
		} else if (fileExists(OTHER_IMAGES_DIRECTORY + filename)) {
			return OTHER_IMAGES_DIRECTORY;
		}
		return null;
	}

	private boolean fileExists(String filepath) {
		File file = new File(filepath);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Unused

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Unused

	}

}
