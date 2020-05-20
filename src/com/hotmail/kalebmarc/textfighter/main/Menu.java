package com.hotmail.kalebmarc.textfighter.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;


class MenuFrame extends JFrame {
	
	String menuStr="<html>\r\n" + 
			"<style>\r\n" + 
			"	pre{\r\n" + 
			"		font-size: 15px;\r\n" + 
			"	}\r\n" + 
			"</style>\r\n" + 
			"<div>\r\n" + 
			"<p>&emsp;&emsp;Code by <a href=\"https://github.com/hhaslam11/Text-Fighter\">https://github.com/hhaslam11/Text-Fighter</a></p>\r\n" +
			"	<pre>\r\n" + 
			"����������������������������������������������������������\r\n" + 
			"     OpenSource Project\r\n" + 
			"\r\n" + 
			"     201711335   ��ȣ��\r\n" + 
			"����������������������������������������������������������\r\n" + 
			"	</pre>\r\n" + 
			"	<br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> \r\n" +  
			"</div>\r\n" + 
			"</html>";
	
	JButton menu1=new JButton("1) ���� ����");
	JButton menu2=new JButton("2) ���� ����");
	JButton menu3=new JButton("3) ���� ����");
	ImageIcon img=new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\StartImage.jpg");
	
	
	JLabel imgbox=new JLabel(img);
	
	JLabel menu_label=new JLabel(menuStr);
	
	
	
	public MenuFrame() {
		super("Text-Fighter(by Hoyong)");			
		this.setSize(500,700);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //���� ȭ�� ��ġ ����
		this.setResizable(false); // ������ũ�� ���� x
	//	this.setLayout(null);//���̾ƿ�
		Game_Panel panel1=new Game_Panel(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\StartImage.jpg").getImage());
	
		menu_label.setBounds(80, 50, 340, 400);
		menu1.setBounds(190, 500, 120, 30);
		menu2.setBounds(190, 550, 120, 30);
		menu3.setBounds(190, 600, 120, 30);
		imgbox.setSize(new Dimension(500,700));
		this.add(menu1);
		this.add(menu2);
		this.add(menu3);
		this.add(menu_label);
		this.add(imgbox);
		
		menu1.addActionListener(event->{
			this.remove(imgbox);
			this.remove(menu_label);
			this.remove(menu1);
			this.remove(menu2);
			this.remove(menu3);
			this.add(panel1);
			this.pack();
			this.setSize(500, 700);
			
		//	this.paintComponents(g);
			//Game.start();
			//Saves.save();
		});
		menu2.addActionListener(event->{
			About.view(false);
		});
		menu3.addActionListener(event->{
			System.exit(0); //���α׷� ����
		});
		

	}

	
	
}













class Menu {
	
	private Menu(){}
	public static void load(){
		while(true){
			
			Ui.cls();
			//Menu Screen
			Ui.println("\n");
			Ui.println("_____________________________________________");
			Ui.println("|          WELCOME TO TEXT FIGHTER          |");
			Ui.println("|        A Text-Based Fighting Game         |");
			Ui.println("|*******************************************|");
			Ui.println("|                                           |");
			Ui.println("|   To get started, Type in a number below  |");
			Ui.println("|             and press enter.              |");
			Ui.println("|                                           |");
			Ui.println("| 1) Start Game                             |");
			Ui.println("| 2) About Game                             |");
			Ui.println("| 3) Exit                                   |");
			Ui.println("|             www.TextFighter.tk            |");
			Ui.println("|___________________________________________|");

            switch (Ui.getValidInt()) {
                case 1:
                    Ui.cls();
                    Ui.guiEnabled = false;
                    Game.start();

                    //Saves the game before exiting
                    Saves.save();
                    return;
                case 2:
                    About.view(false);
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }//Loop
    }//Method
}//Class
