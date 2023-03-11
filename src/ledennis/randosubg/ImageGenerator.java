package ledennis.randosubg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageGenerator {
	
	private File lastFile;
	
	private List<File> foundFiles = new ArrayList<>();
	
	public BufferedImage nextImage(String path) {
		BufferedImage img;
		while((img = nextImage0(path)) == null) {
		}
		return img;
	}
	
	private BufferedImage nextImage0(String path) {
		File dir = new File(path, "Songs");
		if(!dir.exists() || dir.isFile()) {
			return ImageRenderer.getErrorImage("wrong songs folder");
		}
		
		File[] songs = dir.listFiles();
		if(songs.length < 1) {
			return ImageRenderer.getErrorImage("no songs");
		}
		File song = songs[new Random().nextInt(songs.length)];
		if(!song.isDirectory()) {
			return ImageRenderer.getErrorImage("song is not a folder");
		}
		
		File[] images = song.listFiles((d, name) -> {
			return name.endsWith(".png") || name.endsWith(".jpg");
		});
		if(images.length < 1) {
			return ImageRenderer.getErrorImage("no images");
		}
		File image = images[new Random().nextInt(images.length)];
		if(!image.isFile()) {
			return ImageRenderer.getErrorImage("image is not a file");
		}

		if(foundFiles.contains(image)) {
			return null;
		}
		lastFile = image;
		foundFiles.add(image);
		
		try {
			return ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
			return ImageRenderer.getErrorImage("IOException: " + e.getLocalizedMessage());
		}
	}
	
	public File lastFile() {
		return lastFile;
	}
	
}
