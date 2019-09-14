package gamedata;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class InfoPg extends JFrame {
	JButton b, start;
	JPanel p, p1;
	GameMain game;
	Graphics g;
	head top;
	static boolean flag = false;
	private BufferedImage image;
	static int gamew = 700, gameht = 700;

	public InfoPg(final GameMain game) {
		start = new JButton("START");
		this.game = game;

		repaint();

		p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(start, BorderLayout.SOUTH);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {

				option();

			}
		});
		setTitle("TerranGuard");
		setSize(gamew, gameht);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(p);
	}

	public void set_flag() {
		GameMain.fg = true;
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		try {
			image = ImageIO.read(new File(".\\.\\gamebg.png"));
			g2d.drawImage(image, 0, 0, null);
		} catch (IOException ex) {
			System.out.print("error in loading image" + ex.getMessage());
		}

	}

	public void option() {
		JPanel panel1 = new JPanel();
		JLabel lbl = new JLabel("Enter your Name: ");
		JTextField txt = new JTextField(10);

		panel1.add(lbl);
		panel1.add(txt);
		String[] options = { "OK" };
		ButtonGroup btngrp = new ButtonGroup();
		int selectedoption = JOptionPane.showOptionDialog(null, panel1, "Player name", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (selectedoption == 0 && txt.getText().length() != 0) {

			JPanel panel2 = new JPanel();
			JLabel lbl1 = new JLabel("Choose Theme");
			JRadioButton ra2 = new JRadioButton("City Theme");
			JRadioButton ra = new JRadioButton("Space Theme");
			JRadioButton ra1 = new JRadioButton("Nature Theme");
			panel2.add(lbl1);
			btngrp.add(ra);
			btngrp.add(ra1);
			btngrp.add(ra2);
			panel2.add(ra);
			panel2.add(ra1);
			panel2.add(ra2);
			int selectedoption2 = JOptionPane.showOptionDialog(null, panel2, "Theme Name", JOptionPane.NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (ra1.isSelected())
				GameMain.flag = 1;
			if (ra2.isSelected())
				GameMain.flag = 2;
			if (selectedoption2 == 0)
				set_flag();
		} else {
			JOptionPane.showMessageDialog(null, "Please enter Name");
		}

		repaint();

	}

}
