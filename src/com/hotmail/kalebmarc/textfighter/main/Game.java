package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.*;

import com.hotmail.kalebmarc.textfighter.player.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import static com.hotmail.kalebmarc.textfighter.player.Health.getStr;
import static com.hotmail.kalebmarc.textfighter.player.Health.upgrade;
import static com.hotmail.kalebmarc.textfighter.player.Settings.menu;
import static com.hotmail.kalebmarc.textfighter.player.Settings.setDif;
import java.awt.BorderLayout;










public class Game {
	Game() {
	}

	//Enemies
	public static Enemy darkElf;
	public static Enemy ninja;
	public static Enemy giantSpider;
	public static Enemy zombie;
	public static Enemy goblin;
	public static Enemy ghost;
	public static Enemy barbarian;
	public static Enemy giantAnt;
	public static Enemy evilUnicorn;
	public static Enemy ogre;

	//Weapons
	public static Weapon fists;
	public static Weapon baseballBat;
	public static Weapon knife;
	public static Weapon pipe;
	public static Weapon pistol;
	public static Weapon smg;
	public static Weapon shotgun;
	public static Weapon rifle;
	public static Weapon sniper;

	//Amours
	public static Armour none = new Armour("None", 0, 0, 1);//DO NOT REMOVE
	public static Armour basic = new Armour("Basic", 400, 15, 5);
	public static Armour advanced = new Armour("Advanced", 750, 30, 7);

	//Food
	//TODO when the StatusEffect system is implemented, change effect types
	
	private static Scanner scan = new Scanner(System.in);
	public static  JPanel GamePanel;
	public static JFrame gameframe;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start() {
	
	JFrame gameframe=new JFrame();
	gameframe.setTitle("Game");
	gameframe.setPreferredSize(new Dimension(500,700));
	gameframe.setSize(500,700);
	gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gameframe.setLocationRelativeTo(null);
	gameframe.setVisible(true);

	ImagePanel homepanel = new ImagePanel(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\homeImg.jpg").getImage());
	ImagePanel shoppanel = new ImagePanel(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\shopImg.jpg").getImage());

	
	
	
	
	
		
	JPanel GamePanel =new JPanel();
	GamePanel.setBackground(Color.WHITE);
	GamePanel.setBounds(0,0,496,671);
	
	GamePanel.setVisible(false);
	GamePanel.setLayout(null);
	
	JLabel lblNewLabel_5 = new JLabel("Text-Fighter");
	lblNewLabel_5.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 16));
	lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_5.setBounds(165, 10, 144, 36);
	GamePanel.add(lblNewLabel_5);
	
	JLabel lblNewLabel_6 = new JLabel("----"+User.name()+"----");
	lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
	lblNewLabel_6.setBounds(40, 61, 313, 40);
	GamePanel.add(lblNewLabel_6);
	
	JLabel lblNewLabel_7 = new JLabel("레벨: "+ Xp.getLevel() + "   " + Xp.getFull());
	lblNewLabel_7.setBounds(53, 96, 381, 40);
	GamePanel.add(lblNewLabel_7);
	
	JLabel lblNewLabel_8 = new JLabel("체력: "+ getStr());
	lblNewLabel_8.setBounds(53, 137, 381, 30);
	GamePanel.add(lblNewLabel_8);
	
	JLabel lblNewLabel_9 = new JLabel("코인: "+Coins.get());
	lblNewLabel_9.setBounds(53, 180, 381, 30);
	GamePanel.add(lblNewLabel_9);
	
	JLabel lblNewLabel_10 = new JLabel("체력물약: "+Potion.get());
	lblNewLabel_10.setBounds(53, 223, 381, 30);
	GamePanel.add(lblNewLabel_10);
	
	JLabel lblNewLabel_11 = new JLabel("착용중인 장비: "+Weapon.get().getName());
	lblNewLabel_11.setBounds(53, 265, 381, 30);
	GamePanel.add(lblNewLabel_11);
	
	JLabel lblNewLabel_12 = new JLabel("착용중인 방어구: "+ Armour.getEquipped().toString());
	lblNewLabel_12.setBounds(53, 305, 381, 30);
	GamePanel.add(lblNewLabel_12);
	
	JLabel lblNewLabel_13 = new JLabel("----"+Enemy.get().getName()+"----");
	lblNewLabel_13.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
	lblNewLabel_13.setBounds(40, 392, 313, 30);
	GamePanel.add(lblNewLabel_13);
	
	JLabel lblNewLabel_14 = new JLabel("몬스터 체력: "+ Enemy.get().getHeathStr());
	lblNewLabel_14.setBounds(53, 435, 381, 30);
	GamePanel.add(lblNewLabel_14);
	
	JLabel lblNewLabel_15 = new JLabel("몬스터 포션: "+ Enemy.get().getEnemy_potion());
	lblNewLabel_15.setBounds(53, 471, 381, 30);
	GamePanel.add(lblNewLabel_15);
	
	JButton btnNewButton_6 = new JButton("\uC2F8\uC6B0\uAE30 ");
	btnNewButton_6.setBackground(new Color(152, 251, 152));
	btnNewButton_6.setFont(new Font("HY엽서L", Font.BOLD, 15));
	btnNewButton_6.setBounds(100, 520, 120, 36);
	GamePanel.add(btnNewButton_6);
	btnNewButton_6.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int fightPath = Random.RInt(100);
			if (fightPath <= 50) Ui.popup(Enemy.get().dealDamage(), "전투결과", JOptionPane.INFORMATION_MESSAGE);
			if (fightPath > 50) Ui.popup(Weapon.get().dealDam(), "전투결과", JOptionPane.INFORMATION_MESSAGE);
			Saves.save();
			lblNewLabel_7.setText("레벨: "+ Xp.getLevel() + "   " + Xp.getFull());
			lblNewLabel_8.setText("체력: "+ getStr());
			lblNewLabel_9.setText("코인: "+Coins.get());
			lblNewLabel_10.setText("체력물약: "+Potion.get());
			lblNewLabel_11.setText("착용중인 장비: "+Weapon.get().getName());
			lblNewLabel_12.setText("착용중인 방어구: "+ Armour.getEquipped().toString());
			lblNewLabel_13.setText("----"+Enemy.get().getName()+"----");
			lblNewLabel_14.setText("몬스터 체력: "+ Enemy.get().getHeathStr());
			lblNewLabel_15.setText("몬스터 포션: "+ Enemy.get().getEnemy_potion());
			GamePanel.revalidate();
			GamePanel.repaint();
		}
	});
	
	JButton btnNewButton_7 = new JButton("\uBB3C\uC57D \uC0AC\uC6A9\r\n");
	btnNewButton_7.setBackground(new Color(255, 182, 193));
	btnNewButton_7.setFont(new Font("HY엽서L", Font.BOLD, 15));
	btnNewButton_7.setBounds(270, 520, 120, 36);
	GamePanel.add(btnNewButton_7);
	btnNewButton_7.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int option=JOptionPane.showConfirmDialog(null, "정말 포션을 사용하시겠습니까? ", "안내", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION) {
				Potion.use();
			}else {
				
			}
			Saves.save();
			lblNewLabel_8.setText("체력: "+ getStr());
			lblNewLabel_10.setText("체력물약: "+Potion.get());
			GamePanel.revalidate();
			GamePanel.repaint();
		}
	});
	
	JButton btnNewButton_8 = new JButton("\uC9D1\uC5D0 \uAC00\uAE30");
	btnNewButton_8.setBackground(new Color(230, 230, 250));
	btnNewButton_8.setFont(new Font("HY엽서L", Font.BOLD, 15));
	btnNewButton_8.setBounds(40, 592, 120, 36);
	GamePanel.add(btnNewButton_8);
	btnNewButton_8.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Saves.save();
			
			GamePanel.setVisible(false);
			gameframe.getContentPane().add(homepanel);
			homepanel.setVisible(true);
			gameframe.pack();

			
		}
	});
	
	JButton btnNewButton_9 = new JButton("\uC0C1\uC810 \uAC00\uAE30");
	btnNewButton_9.setBackground(new Color(224, 255, 255));
	btnNewButton_9.setFont(new Font("HY엽서L", Font.BOLD, 15));
	btnNewButton_9.setBounds(190, 592, 120, 36);
	GamePanel.add(btnNewButton_9);
	btnNewButton_9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Saves.save();
			GamePanel.setVisible(false);
			gameframe.getContentPane().add(shoppanel);
			shoppanel.setVisible(true);
			gameframe.pack();
			
		}
	});
	
	JButton btnNewButton_10 = new JButton("\uC800\uC7A5\uD6C4 \uC885\uB8CC");
	btnNewButton_10.setForeground(new Color(255, 250, 250));
	btnNewButton_10.setBackground(new Color(0, 0, 0));
	btnNewButton_10.setFont(new Font("HY엽서L", Font.BOLD, 13));
	btnNewButton_10.setBounds(340, 592, 120, 36);
	GamePanel.add(btnNewButton_10);
	btnNewButton_10.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Saves.save();
			System.exit(0);
		}
	});
	gameframe.getContentPane().add(GamePanel);
	gameframe.pack();
	GamePanel.setVisible(true);
	
	
	
	
	
	
	homepanel.setBounds(0, 0, 486, 663);
	homepanel.setLayout(null);
	homepanel.setVisible(false);
	
	JButton Button_6 = new JButton("\uB4A4\uB85C\uAC00\uAE30");
	Button_6.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Saves.save();			
			lblNewLabel_11.setText("착용중인 장비: "+Weapon.get().getName());
			lblNewLabel_12.setText("착용중인 방어구: "+ Armour.getEquipped().toString());
			homepanel.setVisible(false);
			GamePanel.revalidate();
			GamePanel.repaint();
			GamePanel.setVisible(true);
			
		}
	});
	Button_6.setBackground(new Color(255, 250, 250));
	Button_6.setFont(new Font("궁서", Font.BOLD, 15));
	Button_6.setBounds(193, 608, 121, 33);
	homepanel.add(Button_6);

	
	JButton Button_8 = new JButton("\uB0B4 \uC815\uBCF4"); //내정보
	Button_8.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {	
			Ui.popup(Stats.view(), "정보", JOptionPane.INFORMATION_MESSAGE);
		}
	});
	Button_8.setBackground(new Color(0, 206, 209));
	Button_8.setFont(new Font("굴림체", Font.BOLD, 15));
	Button_8.setBounds(23, 99, 119, 36);
	homepanel.add(Button_8);
	
	JButton Button_9 = new JButton(); //장비착용
	Button_9.setIcon(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\weapon.jpg"));
	Button_9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Weapon.choose();	
		}
	});
	Button_9.setFont(new Font("HY목각파임B", Font.BOLD, 14));
	Button_9.setBounds(243, 377, 98, 42);
	homepanel.add(Button_9);
	
	JButton Button_10 = new JButton();//방어구 착용
	Button_10.setIcon(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\armor.jpg"));
	Button_10.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Armour.choose();
		}
	});
	Button_10.setFont(new Font("HY목각파임B", Font.BOLD, 14));
	Button_10.setBounds(231, 482, 120, 42);
	homepanel.add(Button_10);
	
	
	
	
	shoppanel.setVisible(false);
	shoppanel.setBounds(0, 0, 486, 663);
	shoppanel.setLayout(null);
	
	JButton btnNewButton = new JButton("");
	btnNewButton.setIcon(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\weapon2.jpg"));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		}
	});
	btnNewButton.setBounds(188, 170, 120, 42);
	shoppanel.add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("");
	btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\armor2.jpg"));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		}
	});
	btnNewButton_1.setBounds(189, 250, 120, 42);
	shoppanel.add(btnNewButton_1);
	
	JButton btnNewButton_2 = new JButton("");
	btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\potion.png"));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		}
	});
	btnNewButton_2.setBounds(205, 322, 60, 60);
	shoppanel.add(btnNewButton_2);
	
	JButton btnNewButton_3 = new JButton("");
	btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\xp.jpg"));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		}
	});
	btnNewButton_3.setBounds(24, 441, 120, 86);
	shoppanel.add(btnNewButton_3);
	
	JButton btnNewButton_4 = new JButton("");
	btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\health.jpg"));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		}
	});
	btnNewButton_4.setBounds(188, 414, 91, 49);
	shoppanel.add(btnNewButton_4);
	
	JButton btnNewButton_5 = new JButton("\uB4A4\uB85C \uAC00\uAE30");
	btnNewButton_5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			lblNewLabel_7.setText("레벨: "+ Xp.getLevel() + "   " + Xp.getFull());
			lblNewLabel_8.setText("체력: "+ getStr());
			lblNewLabel_9.setText("코인: "+Coins.get());
			lblNewLabel_10.setText("체력물약: "+Potion.get());
			lblNewLabel_11.setText("착용중인 장비: "+Weapon.get().getName());
			lblNewLabel_12.setText("착용중인 방어구: "+ Armour.getEquipped().toString());
			lblNewLabel_13.setText("----"+Enemy.get().getName()+"----");
			lblNewLabel_14.setText("몬스터 체력: "+ Enemy.get().getHeathStr());
			lblNewLabel_15.setText("몬스터 포션: "+ Enemy.get().getEnemy_potion());
			shoppanel.setVisible(false);
			GamePanel.revalidate();
			GamePanel.repaint();
			GamePanel.setVisible(true);
		}
	});
	btnNewButton_5.setFont(new Font("궁서체", Font.BOLD, 15));
	btnNewButton_5.setBackground(new Color(255, 255, 255));
	btnNewButton_5.setBounds(344, 561, 130, 37);
	shoppanel.add(btnNewButton_5);

	
	
	
	}//Method 
	


	

	
	
	
	
	
	
	
	
	

	private static void town() {

		int menuChoice;

		//TOWN MENU
		while (true) {
			Ui.cls();
			Ui.println("------------------------------------------------------------------");
			Ui.println("                      WELCOME TO THE TOWN                         ");
			Ui.println("--Score Info--");
			Ui.println("     Kill Streak: " + Stats.kills);
			Ui.println("     Highest Kill Streak: " + Stats.highScore);
			Ui.println("--Player Info--");
			Ui.println("     Health: " + getStr());
			Ui.println("     Coins: " + Coins.get());
            Ui.println("     Potions: "+ Potion.get());
			Ui.println("     Equipped Weapon: " + Weapon.get().getName());
			Ui.println("------------------------------------------------------------------");
			Ui.println("1) Casino x");
			Ui.println("2) Home x");
			Ui.println("3) Bank x");
			Ui.println("4) Shop");
			Ui.println("5) Upgrade Health");
			Ui.println("6) Back");
			Ui.println("------------------------------------------------------------------");

			menuChoice = Ui.getValidInt();

			switch (menuChoice) {
				case 1:
	//				Casino.menu();
					break;
				case 2:
					//home();
					break;
				case 3:
	//				Bank.menu();
					break;
				case 4:
					Shop.menu();
					break;
				case 5:
					upgrade();
					break;
				case 6:
					return;
				default:
					break;
			}//Switch
		}//While Loop
	}//Method

	private static void home() {

		int menuChoice;

		//HOME MENU
		while (true) {
			Ui.cls();
			Ui.println("------------------------------------------------------------------");
			Ui.println("                          WELCOME HOME                            ");
			Ui.println("--Score Info--");
			Ui.println("     Kill Streak: " + Stats.kills);
			Ui.println("     Highest Kill Streak: " + Stats.highScore);
			Ui.println("--Player Info--");
			Ui.println("     Health: " + getStr());
			Ui.println("     Coins: " + Coins.get());
            Ui.println("     Potions: " + Potion.get());
			Ui.println("     Equipped Weapon: " + Weapon.get().getName());
			
			//방어구 출력
			Ui.println("------------------------------------------------------------------");
			Ui.println("1) Equip weapon");
			Ui.println("2) Equip Armour");
			Ui.println("3) View Item Chest x");
			Ui.println("4) Achievements x");
			Ui.println("5) Stats"); //팝업안내
			Ui.println("6) About x");
			Ui.println("7) Settings x");
			Ui.println("8) Help"); //팝업
			Ui.println("9) Back");
			Ui.println("------------------------------------------------------------------");

			menuChoice = Ui.getValidInt();

			switch (menuChoice) {
				case 1:
					Weapon.choose();
					break;
				case 2:
					Armour.choose();
					break;
				case 3:
					Chest.view();
					break;
				case 4:
					break;
				case 5:
					Stats.view();
					break;
				case 6:
					About.view(true);
					break;
				case 7:
					menu();
					break;
				case 8:
			//		Help.view();
				case 9:
					return;
				default:
					break;
			}//Switch
		}//While loop
	}//Method

	static String getDifficulty() {
		
		/*
		 * DIFFICULTY SELECTION
		 * Prompts user to get what difficulty
		 * they want to play on. Sets variables
		 * according.
		
		Ui.cls();
		Ui.println("_____________________________________________");
		Ui.println("|                                           |");
		Ui.println("|       What difficulty would you           |");
		Ui.println("|            like to play on?               |");
		Ui.println("|                                           |");
		Ui.println("| 1) Easy                                   |");
		Ui.println("| 2) Hard                                   |");
		Ui.println("|___________________________________________|");

		if (!scan.hasNextInt()) {
			Ui.cls();
			return "Easy";
		} else {
			int difficultyChoice = scan.nextInt();
			if (difficultyChoice == 2) {
				Ui.cls();
				return "Hard";
			} else {
				Ui.cls();
				return "Easy";
			}
		}*/
		return "Easy";
	}
}