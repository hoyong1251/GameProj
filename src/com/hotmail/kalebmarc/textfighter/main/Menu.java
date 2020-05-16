package com.hotmail.kalebmarc.textfighter.main;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;

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
			"─────────────────────────────\r\n" + 
			"     OpenSource Project\r\n" + 
			"\r\n" + 
			"     201711335   이호용\r\n" + 
			"─────────────────────────────\r\n" + 
			"	</pre>\r\n" + 
			"	<br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> \r\n" +  
			"</div>\r\n" + 
			"</html>";
	
	JButton menu1=new JButton("1) 게임 시작");
	JButton menu2=new JButton("2) 게임 정보");
	JButton menu3=new JButton("3) 게임 종료");
	ImageIcon img=new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\StartImage.jpg");
	
	
	JLabel imgbox=new JLabel(img);
	
	JLabel menu_label=new JLabel(menuStr);
	
	public MenuFrame() {
		super("Text-Fighter(by Hoyong)");
		
		this.setSize(500,700);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //실행 화면 위치 조정
		this.setResizable(false); // 프레임크기 조절 x
		this.setLayout(null);//레이아웃
		
		menu_label.setBounds(80, 50, 340, 400);
		menu1.setBounds(190, 500, 120, 30);
		menu2.setBounds(190, 550, 120, 30);
		menu3.setBounds(190, 600, 120, 30);
		imgbox.setSize(new Dimension(500,700));
		
		menu1.addActionListener(event->{
			Game.start();
			Saves.save();
		});
		menu2.addActionListener(event->{
			About.view(false);
		});
		menu3.addActionListener(event->{
			System.exit(0); //프로그램 종료
		});
		
		this.add(menu1);
		this.add(menu2);
		this.add(menu3);
		this.add(menu_label);
		this.add(imgbox);
	}
	public static void run() {
		new MenuFrame();
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
