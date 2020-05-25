package com.hotmail.kalebmarc.textfighter.main;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Potion;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

class Shop {
    private Shop() {
    }

    public static void menu() {
    	
        while (true) {
            Ui.cls();
            Ui.println("-------------------------------------------------------------------");
            Ui.println("                        Welcome to the shop!                       ");
            Ui.println();
            Ui.println("Coins: " + Coins.get());
            Ui.println("Potions: " + Potion.get());
            Ui.println();
            Ui.println("-------------------------------------------------------------------");
            Ui.println("1) ����");
            Ui.println("2) ����");
            Ui.println("3) ��");
            Ui.println("4) ü�� ���׷��̵� ");
            Ui.println("5) XP"); 
            Ui.println("6) Back");
            Ui.println("-------------------------------------------------------------------");
            switch (Ui.getValidInt()) {
                case 1:
                    health();
                    break;
                case 2:
                    weapons();
                    break;
                case 3:
                    armour();
                    break;
                case 4:
                  //  property();
                    break;
                case 5:
                    xp();
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }

    private static void health() {

        while (true) {
            Ui.cls();
            Ui.println("-------------------------------------------------------------------");
            Ui.println("                               Health                              ");
            Ui.println();
            Ui.println();
            Ui.println("Coins: " + Coins.get());
            Ui.println("Potions: " + Potion.get());
            Ui.println();
            Ui.println("-------------------------------------------------------------------");
            Ui.println("2) POTION");
            Ui.println("   Price - " + Potion.pPrice + " coins");
            Ui.println("   Level - " + Potion.pLevel);
            Ui.println();
            Ui.println("5) Back");
            Ui.println("-------------------------------------------------------------------");
            switch (Ui.getValidInt()) {
                case 1:
                    Ui.cls();
                    //FirstAid.buy();
                    break;
                case 2:
                    Ui.cls();
                    Potion.buy();
                    break;
                case 3:
                    Ui.cls();
                    break;
                case 4:
                    Ui.cls();
                //    InstaHealth.buy();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    public static void weapons() {
        	String msg="";
            msg+="    Weapons    \n\n";
            msg+="Coins: " + Coins.get()+"\n";
            msg+="Level: " + Xp.getLevel()+"\n";
            for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
                    msg+=(i + 1) + ") " + Weapon.arrayWeapon.get(i).getName()+"\n";
                    msg+="   Price: " + Weapon.arrayWeapon.get(i).price+"\n";
                    msg+="   Level: " + Weapon.arrayWeapon.get(i).level+"\n";
                
            }

            
            String result=JOptionPane.showInputDialog(msg+"�����Ͻ� ������ ��ȣ�� �Է����ּ���");
            if(result!=null) {
            int select=Integer.parseInt(result);
            
                try { 
                	select--;
                    Weapon.arrayWeapon.get(select).buy();
                    return;

                } catch (Exception e) {
                    Ui.popup((select+1) + " �� ���� �޴��Դϴ�!","���",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        
    }

    public static void xp() {

        //Makes sure player has enough money
        boolean valid;

            //Makes sure player isn't level 10 already
            if (Xp.getLevel() == 100) {
                Ui.popup("����(100)�� �����ϼ̽��ϴ�! \n ���̻� ����ġ�� �ʿ�����ϴ�","�ȳ�",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String msg="";
            msg+="����: " + Xp.getLevel()+"\n";
            msg+="���� ����ġ: " + Xp.getFull()+"\n";
            msg+="����: " + Coins.get()+"\n";
            msg+="1 �������� 1 ����ġ�� �� �� �ֽ��ϴ�! \n";
            
            String result=JOptionPane.showInputDialog(msg+"�����Ͻ� ����ġ�� ������ �Է����ּ���!");
            if(result!=null) {
            int select=Integer.parseInt(result);
           // int buy = Ui.getValidInt();
            valid = true;

            //Tests
            if (select > Coins.get()) {
                //Not enough coins
                Ui.popup("������ �����մϴ�!","���",JOptionPane.INFORMATION_MESSAGE);
                valid = false;
            }
  //          if (Xp.getLevel() == 100) {
   //             Ui.msg("You are already level 100; which is the maximum level.");
   //             valid = false;
    //        }
            if (select <= 0) {
                Ui.popup("0���� ū ���ڸ� �Է��� �ּ���","���",JOptionPane.INFORMATION_MESSAGE);
                valid = false;
            }

            if (valid) {
                Ui.popup(select +"��ŭ�� ����ġ�� ����ϴ�!","���� ����",JOptionPane.INFORMATION_MESSAGE);
                //Results
                Xp.set(select, true);
                Coins.set(-select, true);
                Stats.xpBought += select;
            }
            
           }
            else { return;}
            


        
    }

    private static void buyAmmo() {


        while (true) {
            Ui.cls();
            Ui.println("-------------------------------------------------------------------");
            Ui.println("                                Ammo                               ");
            Ui.println();
            Ui.println("Coins: " + Coins.get());
            Ui.println("Level: " + Xp.getLevel());
            Ui.println();
            Ui.println("-------------------------------------------------------------------");
            ArrayList<Weapon> validWeapons = new ArrayList<Weapon>();
            for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
                if (Weapon.arrayWeapon.get(i).isBuyable() && !Weapon.arrayWeapon.get(i).melee && Weapon.arrayWeapon.get(i).owns()) {
                    Ui.println((validWeapons.size() + 1) + ") " + Weapon.arrayWeapon.get(i).getName());
                    Ui.println("   Price: " + Weapon.arrayWeapon.get(i).getAmmoPrice());
                    Ui.println("   Level: " + Weapon.arrayWeapon.get(i).level);
                    validWeapons.add(Weapon.arrayWeapon.get(i));
                }
            }
            Ui.println((validWeapons.size() + 1) + ") Back");

            while (true) {//Make it easy to break, without going back to main store menu

                int menuItem = Ui.getValidInt();

                try { //This is probably pretty bad practice. Using exceptions as a functional part of the program.. Use variables!
                    validWeapons.get(menuItem - 1).buyAmmo();
                    break;

                } catch (Exception e) {

                    if (menuItem == (validWeapons.size() + 1)) {
                        return;
                    }
                    Ui.println();
                    Ui.println(menuItem + " is not an option.");
                    Ui.pause();
                    Ui.cls();
                }
            }
        }
    }
    private static void property(){
        while (true){

            Ui.cls();
            Ui.println("________________________________________________");
            Ui.println("                    Property                    ");
            Ui.println("Level: " + Xp.getLevel());
            Ui.println("Coins: " + Coins.get());
            Ui.println("________________________________________________");

            //TODO do stuff to buy property
            Ui.pause();//temp


            return;
        }
    }
    public static void armour() {
    	String msg="";
        msg+="    Armours    \n\n";
        msg+="Coins: " + Coins.get()+"\n";
        msg+="Level: " + Xp.getLevel()+"\n";
        for (int i = 0; i < Armour.getArmours().size(); i++) {
                msg+=(i + 1) + ") " + Armour.getArmours().get(i).getName()+"\n";
                msg+="   Price: " + Armour.getArmours().get(i).getPrice()+"\n";
                msg+="   Level: " + Armour.getArmours().get(i).getLevel()+"\n";
            
        }

        
        String result=JOptionPane.showInputDialog(msg+"�����Ͻ� ���� ��ȣ�� �Է����ּ���");
        if(result!=null) {
        int select=Integer.parseInt(result);
        
            try { 
            	select--;
                Armour.getArmours().get(select).buy();
                return;

            } catch (Exception e) {
                Ui.popup((select+1) + " �� ���� �޴��Դϴ�!","���",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}