package com.hotmail.kalebmarc.textfighter.player;

import javax.swing.JOptionPane;

import com.hotmail.kalebmarc.textfighter.main.Ui;

public class Potion {
    public static int pUsed = 0;
    public static int pLevel;
    public static int pPrice;
    //TODO will eventually add potions to heal status ailments
    //TODO possibly add potion that gives player a temporary strength boost (Does more damage)
    //public static int rpUsed = 0;
    //public static int rpLevel;
    //public static int rpPrice;
    private static int myPotion; //potion that heals 50% of health
    //private static int recoveryPotion; //potion that heals 75% of health

    private Potion() {
    }

    public static int get() {
                return myPotion;
    }

    public static void set(int amount, boolean add) {
                if (!add) {
                    myPotion = amount;
                } else {
                    myPotion += amount;
                    if (myPotion < 0) myPotion = 0;
                }

    }

    public static void use() {
        if (get() <= 0) {

        	Ui.popup("남은 포션이 없습니다! \n 포션을 사용하고 싶다면 상점에서 추가로 구매하세요!", "경고", JOptionPane.INFORMATION_MESSAGE);
        } else if (Health.get() == 100) {
            Ui.popup("체력이 이미 많습니다! \n 포션을 사용할 필요가 없습니다!", "경고", JOptionPane.INFORMATION_MESSAGE);

        } else {
            set(-1, true);
            int heal = (int) Math.round(healBy());
            Health.gain(heal);
            used();

            String str="포션 1개를 사용했습니다! \n";
            str+="체력  : " + Health.getStr()+"\n";
            str+="남은 포션  : " + get();
            Ui.popup(str, "회복 성공", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public static double healBy() {

           return Health.getOutOf() * .50;

    }

    public static void used() {
            pUsed++;
    }

    public static void buy() {

        int level = getLevel();
        int price = getPrice();

        if (Xp.getLevel() < level) {
            Ui.println("You have to be at least level " + level + " to buy this!");
            Ui.pause();
        } else if (price <= Coins.get()) {
            Coins.set(-price, true);
            Stats.coinsSpentOnHealth += price;
            set(1, true);
            Ui.println("Thank you for your purchase. Come again soon! ");
            Ui.pause();
        } else {
            Ui.println("You do not have enough coins.");
            Ui.pause();
        }
    }

    public static int getLevel() {

                return pLevel;

    }

    public static int getPrice() {
                return pPrice;

    }
}
