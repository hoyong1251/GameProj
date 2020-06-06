package com.hotmail.kalebmarc.textfighter.main;

import javax.swing.JOptionPane;

import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Xp;

public class Time {
	private static long offlinetime;
	private static long finishtime;
	
	public Time() {
		
	}
	public long cureentTime() {
		return System.currentTimeMillis();
	}
	public static void setFinishtime(long l) {
		Time.finishtime=l;
	}
	public static long getFinishtime() {
		return Time.finishtime;
	}
	public static long getofflinetime() {
		long currenttime=System.currentTimeMillis();
		Time.offlinetime=currenttime-finishtime;
		return offlinetime/1000;
	}
	
	public static void getreward(long offtime) {
		int coin=(int) (offtime*0.1);
		int xp=(int)(offtime*0.1);
		Coins.set(coin, true);
		Xp.set(xp, true);
		Ui.popup("오프라인 시간: "+offtime+" 초 \n  "+coin+" 코인 획득\n"+"  "+xp+" 경험치 획득", "오프라인 보상", JOptionPane.INFORMATION_MESSAGE);
	}
}
