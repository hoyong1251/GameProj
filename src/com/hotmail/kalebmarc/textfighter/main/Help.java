package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;

class Help {
    private Help() {
    }

    public static void view() {
        while (true) {
            Ui.cls();
            Ui.println("------------------------------------------------------------");
            Ui.println("                         HELP MENU                          ");
            Ui.println("Here you can find (almost) all the information you need to");
            Ui.println("know about Text-Fighter.");
            Ui.println("------------------------------------------------------------");
            Ui.println("1) Enemy");
            Ui.println("2) Armour");
            Ui.println("3) Weapon");
            Ui.println("4) Health");
            Ui.println("5) Food");
            Ui.println("6) XP");
            Ui.println("7) Cheats");
            Ui.println("8) Achievements x");
            Ui.println("9) Back");
            Ui.println("------------------------------------------------------------");
            switch (Ui.getValidInt()) {
                case 1:
                    info_enemy();
                    break;
                case 2:
                    info_armour();
                    break;
                case 3:
                    info_weapons();
                    break;
                case 4:
                    info_health();
                    break;
                case 5:
           //         info_food();
                    break;
                case 6:
                    info_xp();
                    break;
                case 7:
                    info_cheats();
                    break;
                case 8:
            //        info_achs();
                    break;
                case 9:
                    return;
            }
        }
    }

    private static void info_enemy() {
        while (true) {
            Ui.cls();
            Ui.println("------------------------------------------------------------");
            Ui.println("                         ENEMY INFO                         ");
            Ui.println("Which enemy would you like to know about?");
            Ui.println();
            for (int i = 0; i < Enemy.arrayEnemy.size(); i++) {
                Ui.println((i + 1) + ") " + Enemy.arrayEnemy.get(i).getName());
            }
            Ui.println((Enemy.arrayEnemy.size() + 1) + ") Back");
            Ui.println("------------------------------------------------------------");

            int menuItem = Ui.getValidInt();

            try {
                Enemy.arrayEnemy.get(menuItem - 1).viewAbout();
            } catch (Exception e) {
                if (menuItem == (Enemy.arrayEnemy.size() + 1)) return;
                Ui.println(menuItem + " is not an option.");
                Ui.pause();
            }
        }
    }

    private static void info_armour() {
        //Start of armour info
        while (true) {
            Ui.cls();
            Ui.println("--------------------------------------------------");
            Ui.println("                    ARMOUR INFO                   ");
            Ui.println("Which armour type would you like to know about?");
            Ui.println();
            for (int i = 0; i < Armour.getArmours().size(); i++) {
                Ui.println((i + 1) + ") " + Armour.getArmours().get(i).getName());
            }
            Ui.println((Armour.getArmours().size() + 1) + ") Back");
            Ui.println("--------------------------------------------------");

            int menuItem = Ui.getValidInt();

            try {
                Armour.getArmours().get(menuItem - 1).viewAbout();
            } catch (Exception e) {
                if (menuItem == (Armour.getArmours().size() + 1)) return;
                Ui.println(menuItem + " is not an option.");
                Ui.pause();
            }
        }
        //End of armour info
    }

    private static void info_weapons() {
        while (true) {
            Ui.cls();
            Ui.println("------------------------------------------------------------");
            Ui.println("                         WEAPON INFO                        ");
            Ui.println("Which weapon would you like to know about?");
            Ui.println();
            for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
                Ui.println((i + 1) + ") " + Weapon.arrayWeapon.get(i).getName());
            }
            Ui.println((Weapon.arrayWeapon.size() + 1) + ") Back");
            Ui.println("------------------------------------------------------------");

            int menuItem = Ui.getValidInt();

            try {
                Weapon.arrayWeapon.get(menuItem - 1).viewAbout();
            } catch (Exception e) {
                if (menuItem == (Weapon.arrayWeapon.size() + 1)) return;
                Ui.println(menuItem + " is not an option.");
                Ui.pause();
            }
        }
    }

    private static void info_health() {
        Ui.cls();
        Ui.println("------------------------------------------------------------");
        Ui.println("                        HEALTH INFO                         ");
        Ui.println("You will start off with 100 health, and you can upgrade your");
        Ui.println("health up to 200. Each upgrade will cost 100 coins on easy, ");
        Ui.println("and 150 coins on hard; and it will upgrade your health by 10.");
        Ui.println("You will be able to upgrade once per level.");
        //TODO Add more health info
        Ui.println("------------------------------------------------------------");
        Ui.pause();
    }



    private static void info_xp() {
        Ui.cls();
        Ui.println("------------------------------------------------------------");
        Ui.println("                              XP                            ");
        Ui.println("Getting XP levels you up, which unlocks more items to buy.  ");
        Ui.println("You start on level one, and you can get up to level 100.    ");
        Ui.println("You need more and more XP for each level. You start needing ");
        Ui.println("only 500 XP to reach level two, then each level you need 500");
        Ui.println("more. So you need 1000 for level 3, 1500 for level 4, etc.  ");
        Ui.println("Each time you level up, your XP gets reset back to 0. You   ");
        Ui.println("get an achievement for each level you reach up to level 10. ");
        Ui.println("You will also receive 100 coins (or 250 coins when you get  ");
        Ui.println("to level 10) Although you can get up to level 100, once you ");
        Ui.println("get to level 10, there's nothing else to unlock.");
        Ui.println();
        Ui.println("How to get XP..");
        Ui.println("There's a few different ways that you can get XP. The main   ");
        Ui.println("way you get XP is by fighting enemies. For every point of   ");
        Ui.println("damage you deal to an enemy, you get 1 XP. Another way is by");
        Ui.println("buying it. You can buy 1 XP for 1 coin. (You can buy as much");
        Ui.println("as you like/as much as you can afford). You will also get   ");
        Ui.println("100 XP for each achievement you unlock. Using a POWER will  ");
        Ui.println("give you 20 XP");
        Ui.println("------------------------------------------------------------");
        Ui.pause();
    }

    private static void info_cheats() {
        Ui.cls();
        Ui.println("------------------------------------------------------------------------");
        Ui.println("                            CHEATS                          ");
        Ui.println("To use a cheat code, make sure to be in the main game menu, ");
        Ui.println("then enter '0'. A star (*) should appear to indicate that   ");
        Ui.println("you can type in a cheat code.                               ");
        Ui.println("WARNING: Using cheats will disable all achievements and the ");
        Ui.println("XP system.                                                  ");
        Ui.println("*Tip: You can lock cheats off in the settings menu to       ");
        Ui.println("      prevent the use of cheats                             ");
        Ui.println();
        Ui.println("List of cheat codes:");
        Ui.println("   Code             | Description");
        Ui.println("                    |            ");
        Ui.println("   moneylover       | Gives you 1000 coins");
        Ui.println("   givemeitall      | Gives you 5000 of every item + all weapons");
        Ui.println("   weaponstash      | Gives you every weapon, and 5000 ammo");
        Ui.println("   nomorepain       | Gives you a bunch of healing supplies");
        Ui.println("   healme           | Sets your health to 100");
        Ui.println("   givemeachallenge | Gives enemy 1000 health");
        Ui.println("   lotsofkills      | Sets kill-streak to 5000");
        Ui.println("   suicide          | Kills you");
        Ui.println("   godmode          | Never dies");
        Ui.println("   loanshark        | Removes current loan");
        Ui.println("   thirstforfood    | Gives you 10 of each type of food");
        Ui.println("------------------------------------------------------------------------");
        Ui.pause();
    }

}