package ledennis.randosubg;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private static GUI gui;

	private JButton bCopy = new JButton();
	private JButton bRandom = new JButton();
	private JTextField tf_path = new JTextField();
	
	private ImageGenerator generator;

	public GUI() {
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 1074;
		int frameHeight = 729;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Random osu! Background Generator");
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);

		bCopy.setBounds(712, 624, 153, 41);
		bCopy.setText("Copy");
		bCopy.setMargin(new Insets(2, 2, 2, 2));
		bCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bCopy_ActionPerformed(evt);
			}
		});
		cp.add(bCopy);
		bRandom.setBounds(880, 624, 153, 41);
		bRandom.setText("Random");
		bRandom.setMargin(new Insets(2, 2, 2, 2));
		bRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bRandom_ActionPerformed(evt);
			}
		});
		cp.add(bRandom);
		tf_path.setBounds(24, 624, 673, 41);
		tf_path.setText(System.getenv("localappdata").replaceAll("\\\\", "/") + "/osu!");
		cp.add(tf_path);
		
		generator = new ImageGenerator();

		setVisible(true);
	}

	public static void main(String[] args) {
		new GUI();
	}

	public void bCopy_ActionPerformed(ActionEvent evt) {
		File file = generator.lastFile();
		if(file == null) return;
		FileSelection selection = new FileSelection(file);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
	}

	public void bRandom_ActionPerformed(ActionEvent evt) {
		ImageRenderer.draw(this.getGraphics(), generator.nextImage(tf_path.getText()));
	}
}
