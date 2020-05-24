package com.hotmail.kalebmarc.textfighter.main;

import static com.hotmail.kalebmarc.textfighter.player.Health.getStr;
import static com.hotmail.kalebmarc.textfighter.player.Settings.setDif;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hotmail.kalebmarc.textfighter.item.*;
import com.hotmail.kalebmarc.textfighter.player.*;
import com.hotmail.kalebmarc.textfighter.main.*;
import java.awt.BorderLayout;

 
       

 

class ImagePanel extends JPanel{
	private Image img;
	
	public ImagePanel(Image img){
		
		this.img=img;
		setSize(500,700);
		setPreferredSize(new Dimension(500,700));
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}





public class MainFrame {

	public JFrame frame;
	private JTextField txtFf;

	/**

	public void launch(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	 */
	public MainFrame() {
		initialize();
	}
	public JFrame getFrame(){
		return this.frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Game Game=new Game();
		//Weapon Weapon=new Weapon();
		frame = new JFrame();
		frame.setTitle("Text-Fighter by hoyong");
		frame.setPreferredSize(new Dimension(500,700));
		frame.setSize(500,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	
		
		ImagePanel panel= new ImagePanel(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\StartImage.jpg").getImage());


		

		
		ImagePanel BgImage= new ImagePanel(new ImageIcon("C:\\Users\\j9794\\git\\Text-Fighter\\src\\images\\StartImage.jpg").getImage());
		BgImage.setBounds(0, 0, 486, 663);
		frame.getContentPane().add(BgImage);
		frame.pack();
		BgImage.setLayout(null);
		

		
		JLabel lblNewLabel = new JLabel("---------------------------------");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 17));
		lblNewLabel.setBounds(76, 189, 348, 15);
		BgImage.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("---------------------------------");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 17));
		lblNewLabel_1.setBounds(76, 36, 348, 15);
		BgImage.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("201711335      \uC774\uD638\uC6A9");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.BOLD, 17));
		lblNewLabel_2.setBounds(97, 150, 306, 29);
		BgImage.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("OpenSource Project");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(97, 61, 306, 55);
		BgImage.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("\uAC8C\uC784 \uC2DC\uC791");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BgImage.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("나눔고딕", Font.BOLD, 16));
		btnNewButton.setBounds(186, 426, 121, 37);
		BgImage.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uAC8C\uC784 \uC815\uBCF4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About.view(false);
			}
		});
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setFont(new Font("나눔고딕", Font.BOLD, 16));
		btnNewButton_1.setBounds(186, 499, 121, 37);
		BgImage.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uAC8C\uC784 \uC885\uB8CC");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setFont(new Font("나눔고딕", Font.BOLD, 16));
		btnNewButton_2.setBounds(186, 564, 121, 37);
		BgImage.add(btnNewButton_2);
		

		
		frame.getContentPane().add(panel);
		frame.pack();
		panel.setLayout(null);
		panel.setVisible(false);
		panel.setBounds(0, 0, 496, 671);
		JLabel lblNewLabel_4 = new JLabel("press your name...");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(58, 29, 371, 36);
		panel.add(lblNewLabel_4);
		
		txtFf = new JTextField();
		txtFf.setFont(new Font("굴림", Font.BOLD, 18));
		txtFf.setBounds(88, 75, 223, 30);
		panel.add(txtFf);
		txtFf.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("\uBD88\uB7EC\uC624\uAE30");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			File f= new File("C:\\Users\\j9794\\git\\Text-Fighter\\target\\classes\\_"+User.name()+".TFsave");
			if(!f.exists()) {
				Ui.popup("파일이 존재하지 않습니다 \n 게임을 종료합니다", "경고", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			else {
				Saves.load();
				panel.setVisible(false);
				frame.dispose();
				Game.start();
				
				}
			}
		});
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNewButton_4.setBounds(88, 180, 100, 35);
		panel.add(btnNewButton_4);
		btnNewButton_4.setVisible(false);
		
		
		JButton btnNewButton_5 = new JButton("\uC0C8 \uAC8C\uC784");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File f= new File("C:\\Users\\j9794\\git\\Text-Fighter\\target\\classes\\_"+User.name()+".TFsave");
				if(!f.exists()) {
					Ui.popup("게임을 시작합니다", " ", JOptionPane.INFORMATION_MESSAGE);
					setDif(Game.getDifficulty(), true, false); //게임난이도는 Easy로 고정
					Health.set(100, 100);
					Enemy.encounterNew();
					Saves.save();
					panel.setVisible(false);
					frame.dispose();
					Game.start();

					
				}
				else {
				int result=JOptionPane.showConfirmDialog(null, "파일이 이미 존재합니다 \n 새로운 게임을 진행하면 기존 데이터가 삭제될수 있습니다\n 계속하시겠습니까? \n\n 아니오 선택시 게임종료", "경고", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					Ui.popup("게임을 시작합니다", " ", JOptionPane.INFORMATION_MESSAGE);
					setDif(Game.getDifficulty(), true, false); //게임난이도는 Easy로 고정
					Health.set(100, 100);
					Enemy.encounterNew();
					Saves.save();
					panel.setVisible(false);
					frame.dispose();
					Game.start();
				}
				else {
					System.exit(0);
				}
					}
				}		
		});
		btnNewButton_5.setBackground(new Color(192, 192, 192));
		btnNewButton_5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNewButton_5.setBounds(285, 180, 100, 35);
		panel.add(btnNewButton_5);
		btnNewButton_5.setVisible(false);
		
		JButton btnNewButton_3 = new JButton("\uD655\uC778");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=txtFf.getText();
				User.setName(str);
			//	Saves.savesPrompt();
				btnNewButton_4.setVisible(true);
				btnNewButton_5.setVisible(true);
			}
		});
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 18));
		btnNewButton_3.setBounds(313, 75, 72, 30);
		panel.add(btnNewButton_3);
		

		
		
	
	}
	


	
	
	

}

	
	

	

