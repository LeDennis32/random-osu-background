package ledennis.randosubg;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class FileLogger {
	
	private PrintWriter out;
	
	public FileLogger(String filename) {
		try {
			File file = new File(filename);
			String previousContent = "";
			if(!file.exists()) {
				file.createNewFile();
			} else {
				// write previous content
				InputStream in = new FileInputStream(file);
				int b;
				while((b = in.read()) != -1) {
					previousContent += (char) b;
				}
				in.close();
			}
			out = new PrintWriter(file);
			out.println(previousContent);
			out.println("New log: " + now());
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void log(String text) {
		if(out == null) return;
		out.println("[" + now() + "] " + text);
		out.flush();
	}
	
	private String now() {
		ZonedDateTime now = ZonedDateTime.now();
		return now.getYear() + "-" + add0(now.getMonthValue()) + "-" + add0(now.getDayOfMonth())
			+ " " + add0(now.getHour()) + ":" + add0(now.getMinute()) + ":" + add0(now.getSecond());
	}
	
	// adds leading 0 to single digit integers
	private String add0(int i) {
		if(i < 10) return "0" + i;
		return String.valueOf(i);
	}
	
}
