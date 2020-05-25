package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Health;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

import javax.swing.*;
import java.util.ArrayList;

public class Weapon {

    //Weapon List
    public static final ArrayList<Weapon> arrayWeapon = new ArrayList<>();
    //Properties
    public static int BULLET_DAMAGE;
    //Variables
    public static Weapon starting;
    private static Weapon current = null;
    public int price;
    public int level;
    public boolean melee;
    public boolean owns;
    private int damageMin;
    private int damageMax;
    private double chanceOfMissing;
    private String name;
    private boolean buyable;
    //Ammo
    private int ammo;
    private int ammoUsed;
    private int ammoPrice;//Per 1
    private int ammoIncludedWithPurchase;

    public Weapon(String name, int ammoUsed, int ammoIncludedWithPurchase, boolean buyable, int price, //For guns
                  int ammoPrice, int level, double chanceOfMissing, boolean firstInit, boolean changeDif) {

        this.name = name;
        this.ammoUsed = ammoUsed;
        this.ammoIncludedWithPurchase = ammoIncludedWithPurchase;
        this.buyable = buyable;
        this.price = price;
        this.ammoPrice = ammoPrice;
        this.level = level;
        this.chanceOfMissing = chanceOfMissing;
        this.melee = false;

        if (!changeDif) {
            arrayWeapon.add(this);
        }

        if (firstInit) {
            this.owns = false;

        }

    }

    public Weapon(String name, boolean startingWeapon, boolean buyable, int price, int level,//For Melee
                  int damageMin, int damageMax, boolean firstInit, boolean changeDif) {
        this.name = name;
        this.buyable = buyable;
        this.price = price;
        this.level = level;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.melee = true;

        if (!changeDif) {
            arrayWeapon.add(this);
        }

        if (firstInit) {
            if (startingWeapon) {//If first init, see if player starts with this or not.
                this.owns = true;
                current = this;
                starting = this;
            } else {
                this.owns = false;
            }
        }
    }

    public static Weapon get() {
        return current;
    }

    static int getIndex(Weapon i) {
        return arrayWeapon.indexOf(i);
    }

    public static void set(Weapon x) {
        current = x;
    }

    public static void set(int i) {
        current = arrayWeapon.get(i);
    }

    public static void choose() {
    	String msg="";
        msg+="----------------------------\n";
        msg+="무기를 장착합니다\n";
        msg+="장착중인 무기: " +current.getName()+"\n";
        msg+="----------------------------\n\n";
       
        for (int i = 0; i < arrayWeapon.size(); i++) {
           
               msg+=(i + 1) + ") " + arrayWeapon.get(i).getName()+"\n";
            	
        	}
        
        JOptionPane option =new JOptionPane();
        
        String result=JOptionPane.showInputDialog(msg+"착용할 장비의 번호를 입력해주세요");
        if(result!=null) {
        int select=Integer.parseInt(result);

        //Get valid weapon index

            try { //This is probably pretty bad practice. Using exceptions as a functional part of the program.. Use variables!
                //Results go here:
            	select--;
                if (!arrayWeapon.get(select).owns) {
                	Ui.popup("아직 이 무기를 획득하지 못했습니다.", "경고", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                current = arrayWeapon.get(select);
                Ui.popup(arrayWeapon.get(select).getName()+" 을 장착했습니다!", "성공",JOptionPane.INFORMATION_MESSAGE);
                return;

            } catch (Exception e) {
                Ui.popup((select + 1) + "번은 없는 메뉴입니다","경고",JOptionPane.INFORMATION_MESSAGE);
            }
            }
        else return;

        
    }

    private static void noAmmo() {
        Ui.popup("You've run out of ammo!", "Warning", JOptionPane.WARNING_MESSAGE);
        Weapon.current = Weapon.starting;
    }

    public static void displayAmmo() {
        if (!(Weapon.get().melee)) {
            Ui.println("     Ammo: " + Weapon.get().getAmmo());
        }
    }

    public String getName() {
        return name;
    }

    public boolean owns() {
        return owns;
    }

    public void setAmmo(int amount, boolean add) {
        if (this.melee) return;
        if (add) {
            this.ammo += amount;
        } else {
            this.ammo = amount;
        }
    }

    public int getAmmo() {
        return this.ammo;
    }

    public String dealDam() {
        int damageDealt = 0;
        damageDealt = Random.RInt(this.damageMin, this.damageMax);
/*
        if (this.melee) {
            /*
             * Melee Attack
			 
            damageDealt = Random.RInt(this.damageMin, this.damageMax);
        } else {

			/*
			 * Gun Attack
			 
            if (getAmmo() >= this.ammoUsed) {

                for (int i = 1; i <= this.ammoUsed; i++) {
                    if (Random.RInt(100) > this.chanceOfMissing) {
                        damageDealt += BULLET_DAMAGE;
                        Stats.bulletsThatHit++;
                    }

                    //Results
                    setAmmo(-1, true);
                    Stats.bulletsFired += 1;
                }

            } else {
                noAmmo();
                damageDealt = 0;
            }
        }
*/
        //Display stuff
        com.hotmail.kalebmarc.textfighter.player.Stats.totalDamageDealt += damageDealt;
        com.hotmail.kalebmarc.textfighter.player.Xp.setBattleXp(damageDealt, true);
        String msg="";
        if (Enemy.get().getHealth() <= Enemy.get().getHealthMax() / 2){
           if( Enemy.get().usePotion()) {
            Ui.popup(" "+ Enemy.get().getName()+ " 가 포션을 사용했습니다. 몬스터 체력이 20 올랐습니다\n"+"\tEnemy health: "+Enemy.get().getHeathStr(), "", JOptionPane.INFORMATION_MESSAGE);
           }
          }
        
        double resist = Armour.getEquipped().getDamResist() / 100.0;
        damageDealt = (int) (damageDealt - (damageDealt * resist));
        Enemy.get().health-=damageDealt;
        msg+="----------------------------------------------------\n";
        msg+=" " + Enemy.get().getName() + " 을 공격 했습니다! \n";
        msg+="몬스터가 체력을 " + damageDealt + " 만큼 잃었습니다.\n";
       	msg+="----------------------------------------------------\n";
        msg+="내 체력: " + Health.getStr()+"\n";
        msg+="몬스터 체력: " + Enemy.get().getHeathStr()+"\n";
        if( Enemy.get().health<=0) {Enemy.get().die(); msg="다음 전투로 넘어갑니다...";}
        
        
		return msg; 
    }

    public void viewAbout() {

        final int BORDER_LENGTH = 39;

        //Start of weapon Info
        Ui.cls();
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.println();
        for (int i = 0; i < ((BORDER_LENGTH / 2) - (this.getName().length() / 2)); i++)
            Ui.print(" ");//Set correct spacing to get name in middle of box
        Ui.println(this.getName());
        Ui.println("Price: " + this.price + " coins");
        Ui.println("Chance of missing: " + this.chanceOfMissing + "%");
        Ui.println("Ammo Used: " + this.ammoUsed);
        Ui.println("Damage: " + this.getDamage());
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.pause();
        Ui.cls();
        //End of weapon Info
    }

    private String getDamage() {
        if (this.melee) {
            return (this.damageMin + " - " + this.damageMax);
        } else {
            if (this.chanceOfMissing == 0) {
                return String.valueOf((BULLET_DAMAGE * this.ammoUsed));
            } else {
                return ("0 - " + String.valueOf((BULLET_DAMAGE * this.ammoUsed)));
            }
        }
    }

    public boolean isBuyable() {
        return this.buyable;
    }

    public void buy() {
        if (!isBuyable()) {
            Ui.popup("이 무기는 구매할 수 없는 무기입니다!","경고",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (this.owns()) {
            Ui.popup("이미 이 무기를 가지고 있습니다!","안내",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (level > Xp.getLevel()) {
            Ui.popup("구매를 하기위한 레벨이 부족합니다! ", "경고",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (price > Coins.get()) {
            Ui.popup("무기를 구매하기위한 코인이 부족합니다!","경고",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //Buy
        Coins.set(-price, true);
        Stats.coinsSpentOnWeapons += price;
        this.owns = true;
        current = this;
        Ui.popup("구매성공! \n 자동으로 장착됩니다 \n\n 남은코인: "+ Coins.get(), "구매성공", JOptionPane.INFORMATION_MESSAGE);
    }

    public void buyAmmo() {

        Ui.cls();

        //Make sure player is a high enough level
        if (Xp.getLevel() < this.level) {
            Ui.println("You are not a high enough level. You need to be at least level " + this.level + ".");
            Ui.pause();
            return;
        }

        //Get amount of ammo user wants
        Ui.println("How much ammo would you like to buy?");
        Ui.println("1 ammo cost " + this.ammoPrice + " coins.");
        Ui.println("You have " + Coins.get() + " coins.");
        int ammoToBuy = Ui.getValidInt();
        int cost = ammoToBuy * ammoPrice;

        //Make sure player has enough coins
        if (Coins.get() < (cost)) {
            Ui.println("You don't have enough coins. You need " + (cost - Coins.get()) + " more coins.");
            Ui.pause();
            return;
        }

        this.ammo += ammoToBuy;
        Coins.set(-cost, true);
        Stats.coinsSpentOnWeapons += cost;

        Ui.println("You have bought " + ammoToBuy + " ammo.");
        Ui.pause();
    }

    public int getAmmoPrice() {
        return this.ammoPrice;
    }
}