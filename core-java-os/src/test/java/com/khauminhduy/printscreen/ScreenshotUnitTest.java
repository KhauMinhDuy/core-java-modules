package com.khauminhduy.printscreen;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ScreenshotUnitTest {

	@Test
	public void givenMainScreen_whenTakeScreenshot_thenSaveToFile() throws AWTException, IOException {
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage bufferedImage = new Robot().createScreenCapture(screenRect);
		File imageFile = new File("single-screen.jpg");
		ImageIO.write(bufferedImage, "jpg", imageFile);
		assertTrue(imageFile.exists());
	}
	
	@Test
	public void givenMultipleScreens_whenTakeScreenshot_thenSaveToFile() throws IOException, AWTException {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] screens = ge.getScreenDevices();
		Rectangle allScreenBounds = new Rectangle();
		for (GraphicsDevice screen : screens) {
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
            allScreenBounds.width += screenBounds.width;
            allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
        }
		
		BufferedImage capture = new Robot().createScreenCapture(allScreenBounds);
        File imageFile = new File("all-screens.jpg");
        ImageIO.write(capture, "jpg", imageFile);
        assertTrue(imageFile.exists());
		
	}
	
}
