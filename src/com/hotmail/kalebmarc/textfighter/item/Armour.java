package com.hotmail.kalebmarc.textfighter.item;

import com.hotmail.kalebmarc.textfighter.main.Ui;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Xp;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Armour {

    private static ArrayList<Armour> armours = new ArrayList<>(3);
    private String name;
    private int price;
    private int damResist;//x%
    private int level;
    private boolean owns;
    private boolean equipped;

    public Armour(String name, int price, int damResist, int level) {
        this.name = name;
        this.price = price;
        this.damResist = damResist;
        this.level = level;
        armours.add(this);
    }

    public static Armour getEquipped() {
        for (Armour i : armours) {
            if (i.isEquipped()) return i;
        } 
        Ui.print("No armour equipped");
        return null;
    }

    public static ArrayList<Armour> getArmours() {
        return armours;
    }

    public static int get() {
        return armours.indexOf(getEquipped());
    }

    public static void set(int i) {
        armours.get(i).equipped = true;
    }

    public static void choose() {
    	String msg="";
            msg+="----------------------------\n";
            msg+="방어구를 장착합니다\n";
            msg+="장착중인 방어구: " + getEquipped().toString()+"\n";
            msg+="----------------------------\n\n";
           
            for (int i = 0; i < getArmours().size(); i++) {
               
                   msg+=(i + 1) + ") " + getArmours().get(i).getName()+"\n";
                	
            	}
            
            JOptionPane option =new JOptionPane();
            
            String result=JOptionPane.showInputDialog(msg+"착용할 장비의 번호를 입력해주세요");
            if(result!=null) {
            int select=Integer.parseInt(result);

            //Get valid weapon index

                try { //This is probably pretty bad practice. Using exceptions as a functional part of the program.. Use variables!
                    //Results go here:
                	select--;
                    Armour.getArmours().get(select).equip();
                    return;

                } catch (Exception e) {
                    Ui.popup((select + 1) + "번은 없는 메뉴입니다","경고",JOptionPane.INFORMATION_MESSAGE);
                }
                }
            }
        
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.equals("")) return;
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDamResist() {
        return this.damResist;
    }

    public void setDamResist(int damResist) {
        this.damResist = damResist;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isOwns() {
        return this.owns;
    }

    public void setOwns(boolean owns) {
        this.owns = owns;
    }

    public boolean isEquipped() {
        return this.equipped;
    }

    public void equip() {
        if (!(this.owns)) {
            Ui.popup("아직 이 방어구를 획득하지 못했습니다.", "경고", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        this.equipped = true;//To make sure something is already equipped
        getEquipped().unequip();
        this.equipped = true;
        Ui.popup(this.toString()+" 을 장착했습니다!", "성공",JOptionPane.INFORMATION_MESSAGE);
    }

    public void equipSilent() {
        if (!(this.owns)) {
            return;
        }
        this.equipped = true;//To make sure something is already equipped
        getEquipped().unequip();
        this.equipped = true;
    }

    public void unequip() {
        this.equipped = false;
    }

    public String toString() {
        if (this.getName().equals("None")) return "No armour";
        return this.getName() + " armour";
    }

    public boolean buy() {
        if (Xp.getLevel() < this.getLevel()) {
            Ui.println("You have to be at least level " + this.getLevel() + " to buy this!");
            Ui.pause();
            return false;
        } else if (this.isOwns()) {
            Ui.println("You already own this.");
            Ui.pause();
            return false;
        } else if (this.getPrice() <= Coins.get()) {
            Coins.set(-this.price, true);
            setOwns(true);
            equipSilent();
            Ui.pause();
            return true;
        } else {
            Ui.println("You do not have enough coins.");
            Ui.pause();
            return false;
        }
    }

    public void viewAbout() {

        final int BORDER_LENGTH = 39;

        //Start of armour Info
        Ui.cls();
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.println();
        for (int i = 0; i < ((BORDER_LENGTH / 2) - (this.getName().length() / 2)); i++)
            Ui.print(" ");//Set correct spacing to get name in middle of box
        Ui.println(this.toString());
        Ui.println("Price: " + this.price + " coins");
        Ui.println("Damage Resistance(%): " + this.damResist + "%");
        Ui.println("Level needed: " + this.level);
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.pause();
        Ui.cls();
        //End of armour Info
    }

}
