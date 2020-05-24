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


    private Stats() {
    }

    public static String view() {

    	String msg="";

        msg+="---- ���� ���� ----\n";
   
        msg+="  �� ���� ������ : " + totalDamageDealt+"\n";
        msg+="  ���� ���� Ƚ�� : " + totalKills+"\n";
        msg+="---- �÷��̾� ���� ----"+"\n";
        msg+="  ���� : " + Coins.get()+"\n";
        msg+="  �� ���� �Һ� : " + totalCoinsSpent+"\n";
        msg+="  �������� �� ����ġ : " + xpBought+"\n";
        msg+="  ü�� : " + Health.getStr()+"\n";
        msg+="  ����� ���Ǽ� : " + Potion.pUsed+"\n";
        msg+="  ���� Ƚ�� : " + Health.timesDied+"\n";
        msg+="---- ���� ���� ----\n";
        msg+="   ���� : " + Xp.getLevel()+"\n";
        msg+="   ����ġ : " + Xp.getFull()+"\n";
        msg+="   �� ȹ���� ����ġ : " + Xp.total+"\n";
        msg+="   �������� Ƚ�� : " + timesQuit+"\n";
        return msg;
    }
}
