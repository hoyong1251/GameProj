package com.hotmail.kalebmarc.textfighter.player;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.main.Enemy;
import com.hotmail.kalebmarc.textfighter.main.Ui;
import com.hotmail.kalebmarc.textfighter.main.User;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class Health {

    public static int timesDied;
    public static int health;
    private static int outOf;
    //Constants
    private static int UPGRADE_PRICE;

    private Health() {
    }

    public static String getStr() {
        return health + "/" + outOf;
    }

    public static int get() {
        return health;
    }

    public static int getOutOf() {
        return outOf;
    }

    public static void set(int h) {
        health = h;
    }

    public static void set(int h, int hOutOf) {
        health = h;
        outOf = hOutOf;
    }

    public static void setUpgradePrice(int price) {
        UPGRADE_PRICE = price;
    }

    public static void gain(int h) {
        health += h;
        if (health > outOf) {
            health = outOf;
        }
    }
/*
    private static void lose(int h) {
        health -= h;
        if (health <= 0) {
            die();
        }
    }
*/
    public static void die() {
        float randomCoinLoss = ThreadLocalRandom.current().nextInt(25, 51); //random between 25% and 50%
        int coinsLost = Math.round(Coins.get() * (randomCoinLoss / 100));
        Ui.popup("죽었습니다! 코인을 " + coinsLost + " 만큼 잃었습니다! ", "사망!", JOptionPane.WARNING_MESSAGE);
        Coins.set(-(coinsLost), true);
        Stats.kills = 0;
        Health.set(Health.getOutOf());
        timesDied++;
    }

    public static void takeDamage(int damage) {

        if (Settings.getGodMode()) {
            damage = 0;
        }
        String msg=null;
        double resist = Armour.getEquipped().getDamResist() / 100.0;
        damage = (int) (damage - (damage * resist));
        health-=damage;
        msg+="----------------------------------------------------\n";
        msg+=" " + Enemy.get().getName() + " 에게 공격 당했습니다! \n";
        msg+=User.name()+"이(가) " + damage + " 만큼 체력을 잃었습니다\n";
       	msg+="----------------------------------------------------\n";
        msg+="내 체력: " + getStr()+"\n";
        msg+="몬스터 체력: " + Enemy.get().getHeathStr()+"\n";
      //  Ui.println("----------------------------------------------------");
        if(health<=0) die();

    }

    private static int getLevel() {

        //TODO Possibly find a better way to calculate and execute this whole 'upgrade health' section later
        switch (getOutOf()) {
            case 50:
                return 0;
            case 60:
                return 1;
            case 70:
                return 2;
            case 80:
                return 3;
            case 90:
                return 4;
            case 100:
                return 5;
            default:
         //       Handle.error("Unable to get health level");
                return 0;
        }
    }

    public static void upgrade() {

            //Make sure player didn't already upgrade fully
            if (Health.getOutOf() == 100) {
            	 Ui.popup("이미 최대 체력에 도달했습니다!", "안내", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Calculate how much to upgrade to.
            int health = getOutOf() + 10;

            //Make sure health doesn't go over 200
            if (health > 100) {
                health = 100;
            }

           String msg="";

            msg+="최대 체력을 증가 시킬수 있습니다 \n 한번에 10만큼의 최대 체력이 증가하고 업그레이드 비용은 "+UPGRADE_PRICE+" 코인 입니다!\n";
			int option=JOptionPane.showConfirmDialog(null, msg+"구매 하시겠습니까?", "안내", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION) {
                int level = getLevel() + 1;

                if ((Xp.getLevel() >= level) && (Coins.get() >= UPGRADE_PRICE)) {

                    //Make sure user doesn't already have full health

                    //Upgrade health
                    Health.set(health, health);
                    Coins.set(-UPGRADE_PRICE, true);

                    Ui.popup("업그레이드 성공! \n 최대 체력이 10만큼 증가하고 체력이 회복됩니다","성공",JOptionPane.INFORMATION_MESSAGE);

                } else {
                /*
                 * Cannot upgrade, make sure you are at least level [level], and you have at least [coins] coins.
				 *
				 * Coins: [coins]
				 * Level: [level]
				 */
                	String failmsg="";
                	failmsg+="업그레이드 실패! \n 최소 레벨"+level+"이 필요 하고 "+UPGRADE_PRICE+" 코인이 필요합니다!\n";

                	failmsg+="Level: " + Xp.getLevel()+"\n";
                	failmsg+="Coins: " + Coins.get()+"\n";
                    Ui.popup(failmsg, "경고", JOptionPane.INFORMATION_MESSAGE);
                }//if
			}else {
				
			}

                //Level that player is trying to upgrade to,
                //and level needed to upgrade.

    }//upgrade
}//Health
