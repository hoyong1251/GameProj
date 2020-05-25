package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.player.*;

import javax.swing.*;
import java.util.ArrayList;

public class Enemy {

    //constants
    private static final int Potion_MIN = 0;
    private static final int Potion_MAX = 2;

    //Enemy List
    public static final ArrayList<Enemy> arrayEnemy = new ArrayList<>();

    //Static Variables
    private static Enemy current;

    //Properties (Constant)
    private String name;
    private int healthMax;
    private int coinDropMin;
    private int coinDropMax;
    private int damageMin;
    private int damageMax;
    private int xp;

    //Variables
    public int health;
    private int enemy_potion;

    public Enemy(String name, int healthMax, int coinDropMin, int coinDropMax,
                 int damageMin, int damageMax, int xp, boolean firstInit, boolean changeDif) {

        this.name = name;
        this.healthMax = healthMax;
        this.coinDropMin = coinDropMin;
        this.coinDropMax = coinDropMax;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.xp = xp;

        /*
         * Finding a way to only have one boolean here (either firstInit, changeDif, or something else) would be nicer.
         * If the arrays are in the firstInit block, then it would cause problems when loading from a save file; but
         * if they aren't in the !changeDif block, then they make more arrays whenever changing difficulties(which
         * results in duplicate arrays). changeDif and firstInit shouldn't ever both be true. Either only one of
         * them will be true, or none of them will be true (which leaves it to have 3 possible ways it could be,
         * so I don't know how I'd do that, as a boolean can only have 2 values; not 3). Using an integer could
         * possibly work- something like 1=firstInit, 2=changeDif, 3=Save/load. But, using integers like this
         * isn't a very good idea. It'd be too confusing. Maybe there could be a way to figure out one of the 3
         * options from in here, so it'd narrow it down to 2 options, which can be determined be a single boolean.
         * This would be a bit better than using an integer, but harder to implement. One more way is have more
         * than one initializer. Might cause confusion, though. TODO fix this before BETA
         * ===Don't forget to do this with the weapon class, as well===
         */
        if (!changeDif) {
            arrayEnemy.add(this);
        }
        if (firstInit) {//Only call if its the first time initializing the enemy. (Not if changing difficulties)
            this.health = healthMax;
        }
    }

    public static void set(int i) {
        current = arrayEnemy.get(i);
    }

    public static Enemy get() {
        return current;
    }

    public static int getIndex(Enemy i) {
        return arrayEnemy.indexOf(i);
    }

    public static void encounterNew() {

        current = arrayEnemy.get(Random.RInt(0, arrayEnemy.size() - 1));
        current.health = current.healthMax;
        current.enemy_potion = Random.RInt(Potion_MIN, Potion_MAX);
        com.hotmail.kalebmarc.textfighter.player.Xp.setBattleXp(0, false);
        Ui.popup(current.getName()+" 를 만났습니다! ", "몬스터 출현!", JOptionPane.INFORMATION_MESSAGE);

    }

    private static void testFoundPipe() {
        int found = Random.RInt(100);
        if (found <= 2 && !Game.pipe.owns) {
            Game.pipe.owns = true;
            Weapon.set(Game.pipe);
            Ui.popup("몬스터에게서 오래된 파이프를 얻었다!", "발견! ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            die();
            return true;
        }
        return false;
    }

    public String dealDamage() {
        int damage = Random.RInt(this.damageMin, this.damageMax);
       // Health.takeDamage(damage);
        String msg="";
        if (Enemy.get().getHealth() <= Enemy.get().getHealthMax() / 2){
            if( Enemy.get().usePotion()) {
             Ui.popup(" "+ Enemy.get().getName()+ " 가 포션을 사용했습니다. 몬스터 체력이 20 올랐습니다\n"+"\tEnemy health: "+Enemy.get().getHeathStr(), "", JOptionPane.INFORMATION_MESSAGE);
            }
           }
        Health.health-=damage;
        msg+="----------------------------------------------------\n";
        msg+=" " + Enemy.get().getName() + " 에게 공격 당했습니다! \n";
        msg+=User.name()+"이(가) " + damage + " 만큼 체력을 잃었습니다\n";
       	msg+="----------------------------------------------------\n";
        msg+="내 체력: " + Health.getStr()+"\n";
        msg+="몬스터 체력: " + Enemy.get().getHeathStr()+"\n";

        if(Health.health<=0) {Health.die(); msg="부활 합니다....";}
        return msg;
    }

    public void die() {

        //Get rewards & store in temp vars
        int tempCoin = Random.RInt(coinDropMin, coinDropMax);
        int tempHealth = Random.RInt(0, 1);
        xp += com.hotmail.kalebmarc.textfighter.player.Xp.getBattleXp();
        com.hotmail.kalebmarc.textfighter.player.Xp.setBattleXp(0, false);

        //Prompt enemy death
        Ui.popup("몬스터를 물리쳤습니다! " + tempCoin + " 코인과  " + xp + "경험치를 얻었습니다! ", "승리! ", JOptionPane.PLAIN_MESSAGE);

        //Rewards
        testFoundPipe();
        Coins.set(tempCoin, true);
        switch (tempHealth) {
            case 0:
                Health.gain(10);
                break;
            case 1:
                Potion.set(1, true);
                break;
        }
        Xp.set(xp, true);
        Stats.kills++;
        Stats.totalKills++;

       

        encounterNew();
    }

    public boolean usePotion(){
        if (this.enemy_potion <= 0) {
            return false;
        } else {
            this.enemy_potion--;
            this.takeDamage(-20);
          //  Ui.msg("The " + this.name + " has used a potion. They gained 20 health");
            return true;
        }
    }

    public int getEnemy_potion(){
        return this.enemy_potion;
    }

    public void setEnemy_potion(int amount){
        this.enemy_potion = amount;
    }

    public void setDamage(int min, int max) {
        this.damageMin = min;
        this.damageMax = max;
    }

    public void setCoinDrop(int min, int max) {
        this.coinDropMin = min;
        this.coinDropMax = max;
    }

    public void setHealth(int current, int max) {
        this.health = current;
        this.healthMax = max;
    }

    public int getHealth() {
        return health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public String getHeathStr() {
        return (health + "/" + healthMax);
    }

    public String getName() {
        return name;
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
        Ui.println("Health: " + this.getHealthMax());
        Ui.println("Damage: " + this.damageMin + "-" + this.damageMax);
        Ui.println("Coin Drop: " + this.coinDropMin + "-" + this.coinDropMax);
        Ui.println();
        Ui.println("XP Dropped: " + this.xp + "Xp");
        for (int i = 0; i < 39; i++) Ui.print("-");//Make line
        Ui.pause();
        Ui.cls();
        //End of weapon Info
    }
}
