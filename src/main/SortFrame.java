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
	public static final String GOOD_IMAGES_DIRECTORY = "./png/goodImages";
	public static final String BAD_IMAGES_DIRECTORY = "./png/badImages";
	public static final String OTHER_IMAGES_DIRECTORY = "./png/otherImages";

	private static final String TITLE_STRING = "Press a key: (1: Good, 2: Bad, 3: Other, r: refresh, q: quit)";

	// Generated Serial ID
	private static final long serialVersionUID = -5997838658258154082L;

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
		} else if (e.getKeyChar() == 'q') {
			// End
			dispose();
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
	 * @param directoryPath
	 */
	private void saveCurrentImage(String directoryPath) {
		String imageName = "/" + currentIndex + ".png";
		Util.saveImage(currentImage, directoryPath + imageName);
	}

	/**
	 * Delete the currentImage from the source folder, cycles to the
	 * next image id, and load that image to the image panel.
	 */
	private void nextImage() {
		File file = new File(SOURCE_IMAGE_DIRECTORY + "/" + currentIndex + ".png");
		if (!file.delete()) {
			System.out.println("Error deleting image");
		}
		currentImage = loadTestImageNumber(++currentIndex);
		updateTitle();
		imagePanel.repaint();
	}

	/**
	 * Loads the image from the source image directory with the provided number as an ID.
	 * @param number
	 * @return
	 */
	private BufferedImage loadTestImageNumber(int number) {
		String filepath = SOURCE_IMAGE_DIRECTORY + "/" + number + ".png";
		System.out.println(filepath);
		return Util.loadImage(filepath);
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
