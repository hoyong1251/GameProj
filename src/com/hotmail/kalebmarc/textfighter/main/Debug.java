package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Settings;
import com.hotmail.kalebmarc.textfighter.player.Xp;

class Debug {

    private static boolean enabled = false;

    Debug() {
    }

    public static void enable() {
        Ui.println("Enabling debug menu..");
        enabled = true;
    }

    public static boolean enabled() {
        return enabled;
    }

    public static void menu() {

        //Validate
        if (!enabled()) {
            return;
        }

        while (true) {
            Ui.cls();
            Ui.println("==================");
            Ui.println("=== DEBUG MENU ===");
            Ui.println();
            Ui.println("1) Coins");
            Ui.println("2) Xp");
            Ui.println("3) Weapon");
            Ui.println("4) First-Aid x");
            Ui.println("5) Insta-health x");
            Ui.println("6) Encounter new");
            Ui.println("7) God Mode");
            Ui.println("8) Food (x10) x");
            Ui.println("9) Go back");
            switch (Ui.getValidInt()) {
                case 1:
                    Ui.cls();
                    Ui.println("How much?");
                    Coins.set(Ui.getValidInt(), false);
                    break;
                case 2:
                    Ui.cls();
                    Ui.println("How much?");
                    Xp.set(Ui.getValidInt(), false);
                    break;
                case 3:
                    for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
                        Weapon.arrayWeapon.get(i).owns = true;
                    }
          //          Power.set(100, true);
                    for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
                        Weapon.arrayWeapon.get(i).setAmmo(10000, false);
                    }
                    Ui.println("You now have all weapons");
                    Ui.pause();
                    break;
                case 4:
                  //  Ui.cls();
                 //   Ui.println("How much?");
                    break;
                case 5:
                 //   Ui.cls();
                //    Ui.println("How much?"); 
                    break;
                case 6:
                    Enemy.encounterNew();
                    break;
                case 7:
                    Settings.toggleGodMode();
                    break;
                case 8:
//                    Ui.cls();

                    break;
                case 9:
                    return;
            }
        }
    }
}
