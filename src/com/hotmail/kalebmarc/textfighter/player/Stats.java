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

        msg+="---- 전투 정보 ----\n";
   
        msg+="  총 가한 데미지 : " + totalDamageDealt+"\n";
        msg+="  몬스터 죽인 횟수 : " + totalKills+"\n";
        msg+="---- 플레이어 정보 ----"+"\n";
        msg+="  코인 : " + Coins.get()+"\n";
        msg+="  총 코인 소비 : " + totalCoinsSpent+"\n";
        msg+="  코인으로 산 경험치 : " + xpBought+"\n";
        msg+="  체력 : " + Health.getStr()+"\n";
        msg+="  사용한 포션수 : " + Potion.pUsed+"\n";
        msg+="  죽은 횟수 : " + Health.timesDied+"\n";
        msg+="---- 레벨 정보 ----\n";
        msg+="   레벨 : " + Xp.getLevel()+"\n";
        msg+="   경험치 : " + Xp.getFull()+"\n";
        msg+="   총 획득한 경험치 : " + Xp.total+"\n";
        msg+="   게임종료 횟수 : " + timesQuit+"\n";
        return msg;
    }
}
