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
