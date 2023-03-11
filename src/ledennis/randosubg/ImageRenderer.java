package ledennis.randosubg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageRenderer {
	
	private static final double 
		maxWidth = 1034,
		maxHeight = 590;
	
	public static void draw(Graphics g, BufferedImage img) {
		int x = 20; int y = 40;
		double[] size = fitImageSize(img);
		int width = (int) size[0];
		int height = (int) size[1];
		
		g.clearRect(x, y, (int)maxWidth, (int)maxHeight);
		g.drawImage(img, x, y, width, height, null);
	}
	
	private static double[] fitImageSize(BufferedImage img) {
		double imgWidth = img.getWidth();
		double imgHeight = img.getHeight();
		
		double ratio = imgWidth / imgHeight;
		
		// check max width
		double height = maxWidth / ratio;
		if(height <= maxHeight) return new double[] { maxWidth, height };
		
		// use max height
		double width = maxHeight * ratio;
		return new double[] { width, maxHeight };
	}
	
	public static BufferedImage getErrorImage(String msg) {
		BufferedImage img = new BufferedImage(1000, 500, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, 1000, 500);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.BOLD, 50));
		g.drawString(msg, 100, 100);
		
		return img;
	}
	
}
