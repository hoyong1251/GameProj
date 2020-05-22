package com.hotmail.kalebmarc.textfighter.player;

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
        Ui.cls();

        if (get() <= 0) {

            Ui.println("----------------------------------------------------");
            Ui.println("You have no Potions left!");
            Ui.println("Go to the shop to buy some more.");
            Ui.println("----------------------------------------------------");
            Ui.pause();

        } else if (Health.get() == 100) {

            Ui.println("----------------------------------------------------");
            Ui.println("You already have full health!");
            Ui.println("You don't need to use a Potion!");
            Ui.println("----------------------------------------------------");
            Ui.println("Your health: " + Health.getStr());
            Ui.println(" Potions: " + get());
            Ui.println("----------------------------------------------------");
            Ui.pause();

        } else {

            set(-1, true);
            int heal = (int) Math.round(healBy());
            Health.gain(heal);
            used();

            Ui.println("----------------------------------------------------");
            Ui.println("You have used a Potion.");
            Ui.println("You've gained " + heal + " health.");
            Ui.println("----------------------------------------------------");
            Ui.println("Your health: " + Health.getStr());
            Ui.println(" Potions: " + get());
            Ui.println("----------------------------------------------------");
            Ui.pause();

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
