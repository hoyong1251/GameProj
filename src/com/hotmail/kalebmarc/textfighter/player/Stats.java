package com.hotmail.kalebmarc.textfighter.player;

import com.hotmail.kalebmarc.textfighter.main.*;

public class Stats {
    //Battle Stats
    public static int highScore;
    public static int kills;
    public static int totalDamageDealt;
    public static int totalKills;
    public static int bulletsFired;
    public static int bulletsThatHit;
    //Coins
    public static int totalCoinsSpent;
    public static int coinsSpentOnWeapons;
    public static int coinsSpentOnHealth;
    public static int coinsSpentOnBankInterest;
    public static int xpBought;
    //Other
    public static int timesCheated;
    public static int timesQuit;

    private static String killDeathRatio;

    private Stats() {
    }

    public static void view() {

        updateKillDeathRatio();

        Ui.cls();
        Ui.println("-------------------------------------------------");
        Ui.println("                   PLAYER STATS                  ");
        Ui.println();
        Ui.println("Battle stats:");
        Ui.println("   High Score - " + highScore);
        Ui.println("   Current Kill Streak - " + kills);
        Ui.println("   Current Weapon - " + Weapon.get().getName());
        Ui.println("   Current Enemy - " + com.hotmail.kalebmarc.textfighter.main.Enemy.get().getName());
        Ui.println("   Total Damage Dealt - " + totalDamageDealt);
        Ui.println("   Total Kills - " + totalKills);
        Ui.println("   K:D - " + killDeathRatio);
        Ui.println();
        Ui.println("Coins:");
        Ui.println("   Coins - " + Coins.get());
        Ui.println("   Total coins spent - " + totalCoinsSpent);
        Ui.println("   XP bought - " + xpBought);
        Ui.println();
        Ui.println("Health:");
        Ui.println("   Health - " + Health.getStr());
        Ui.println("   Potions used - " + Potion.pUsed);
        Ui.println("   Times Died - " + Health.timesDied);
        Ui.println();
        Ui.println("Other: ");
        Ui.println("   Cheats Enabled? - " + Cheats.enabled()); //삭제
        Ui.println("   Level - " + Xp.getLevel());
        Ui.println("   Xp - " + Xp.getFull());
        Ui.println("   Total Xp gained - " + Xp.total);
        Ui.println("   Times cheated - " + timesCheated); //삭제
        Ui.println("   Times quit - " + timesQuit);
        Ui.println();
        Ui.println("-------------------------------------------------");
        Ui.pause();
    }

    private static void updateKillDeathRatio() {
        int i, gcm = 1, first = totalKills, second = Health.timesDied;

        i = (first >= second) ? first : second;

        while (i != 0) {
            if (first % i == 0 && second % i == 0) {
                gcm = i;
                break;
            }
            i--;
        }

        killDeathRatio = first / gcm + ":" + second / gcm;
    }
}
