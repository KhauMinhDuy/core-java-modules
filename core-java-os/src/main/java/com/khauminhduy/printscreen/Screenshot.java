package com.khauminhduy.printscreen;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Screenshot {

	private final String path;
	
	public Screenshot(String path) {
		this.path = path;
	}
	
	public void getScreenshot(int timeToWait) throws AWTException, IOException {
		Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		Robot robot = new Robot();
		BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
		ImageIO.write(bufferedImage, "jpg", new File("path"));
	}
	
}
