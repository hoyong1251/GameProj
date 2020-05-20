package com.hotmail.kalebmarc.textfighter.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Game_Panel extends JPanel {
	private Image img;
	private JLabel yourname;
	private JTextField textbox;
	
	public Game_Panel(Image img) {
		yourname= new JLabel("이름을 입력해 주세요");
		yourname.setFont(yourname.getFont().deriveFont(15.0f));
		textbox=new JTextField();



		this.img=img;
		setSize(new Dimension(500,700));
		//setLayout(null); 이거 넣으면 왜안되냐?
		
		setPreferredSize(new Dimension(500,700));
		
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		yourname.setBounds(180, 50, 200, 40);
		textbox.setBounds(180, 90, 200, 40);
		this.add(yourname);
		this.add(textbox);
		
	}
}
