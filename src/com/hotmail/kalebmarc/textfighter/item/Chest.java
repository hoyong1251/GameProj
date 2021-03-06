package com.hotmail.kalebmarc.textfighter.item;

import com.hotmail.kalebmarc.textfighter.main.Ui;
import com.hotmail.kalebmarc.textfighter.main.Weapon;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Potion;

public class Chest {
    private Chest() {
    }

    public static void view() {

        Ui.cls();
        Ui.println("------------------------------");
        Ui.println("          Item Chest          ");
        Ui.println();
        Ui.println("     Potions: "+Potion.get());
        Ui.println("Coins: " + Coins.get());
        for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
            if (Weapon.arrayWeapon.get(i).owns()) {
                Ui.println(Weapon.arrayWeapon.get(i).getName());
                if (!Weapon.arrayWeapon.get(i).melee)
                    Ui.println(" (Ammo: " + Weapon.arrayWeapon.get(i).getAmmo() + ")");
            }
        }
        for (int i = 1; i < Armour.getArmours().size(); i++) {
            if (Armour.getArmours().get(i).isOwns()) {
                Ui.println(Armour.getArmours().get(i).toString());
            }
        }
        Ui.println();
        Ui.println("------------------------------");
        Ui.pause();

    }
}
