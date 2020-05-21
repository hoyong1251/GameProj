package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.player.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Brendon Butler on 7/27/2016.
 */
public class Saves {

	private static DumperOptions options;
	private static File saveLocation;
	private static Map<String, Object> data;
	private static Representer representer;
	private static Scanner input;
	private static String path;
	private static Yaml yaml;

	public static void save() {
		path = Saves.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "username.TFsave";
		path = path.replace("username", "_" + User.name());
		path = path.replaceAll("%20", " ");

		setup();

		/*
		 * TODO: make a version checker that checks each part of a version ex: 1.4.1DEV
		 * then determine whether or not it's older, current or newer.
		 */
		set("Version", Version.getFull());

		//Health
		set("User.Health", Health.get());
		set("User.Max_Health", Health.getOutOf());
		set("Stats.TimesDied", Health.timesDied);

		//Coins
		set("User.Balance", Coins.get());
		set("Stats.Money_Spent.Coins", Stats.totalCoinsSpent);
		set("Stats.Money_Spent.Interest", Stats.coinsSpentOnBankInterest);
		set("Stats.Money_Spent.Weapons", Stats.coinsSpentOnWeapons);
		set("Stats.Money_Spent.Health", Stats.coinsSpentOnHealth);
		set("Stats.Money_Spent.XP", Stats.xpBought);

		//Xp
		set("User.XP.Level", Xp.getLevel());
		set("User.XP.Needed", Xp.getOutOf());
		set("User.XP.Amount", Xp.get());
		set("User.XP.Total", Xp.total);
		set("User.XP.battleXp", Xp.getBattleXp());

		//Potions
		set("Stats.Potions.Survival.Used", Potion.spUsed);
		set("Stats.Potions.Recovery.Used", Potion.rpUsed);
		set("User.Potions.Survival", Potion.get("survival"));
		set("User.Potions.Recovery", Potion.get("recovery"));

		//Settings
		set("Settings.Difficulty.Level", Settings.getDif());
		set("Settings.Difficulty.Locked", Settings.difLocked);
		set("Settings.Cheats.Enabled", Cheats.enabled());
		set("Settings.Cheats.Locked", Cheats.locked());
		set("Settings.GUI.Enabled", Ui.guiEnabled);

		//Combat
		set("Stats.Kills", Stats.kills);
		set("Stats.Total_Kills", Stats.totalKills);
		set("Stats.High_Score", Stats.highScore);
		set("User.Weapons.Current", Weapon.arrayWeapon.indexOf(Weapon.get()));


		for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
			if (Weapon.arrayWeapon.get(i).owns()) {
				set(("User.Weapons." + i), true);
			} else {
				set(("User.Weapons." + i), false);
			}
			set(("User.Weapons.Ammo." + i), Weapon.arrayWeapon.get(i).getAmmo());
		}


		set("Stats.Damage_Dealt", Stats.totalDamageDealt);
		set("Stats.Bullets_Fired", Stats.bulletsFired);
		set("Stats.Bullets_Hit", Stats.bulletsThatHit);

		List<Integer> ownedArmour = new ArrayList<>();

		for (int i = 0; i < Armour.getArmours().size(); i++)
			if (Armour.getArmours().get(i).isOwns())
				ownedArmour.add(i);
		set("User.Armour.Owns", ownedArmour);

		set("User.Armour.Current", Armour.get());

		//Enemy
		set("Battle.Current.Enemy", Enemy.arrayEnemy.indexOf(Enemy.get()));
		set("Battle.Current.Enemy_Health", Enemy.get().getHealth());
		set("Battle.Current.Enemy_Max_Health", Enemy.get().getHealthMax());
		set("Battle.Current.Enemy_Potion", Enemy.get().getEnemy_potion());

		//Achs


		//Other Stuff
		set("Settings.About_Viewed", About.viewed());
		set("Stats.Times_Cheated", Stats.timesCheated);
		set("Stats.Times_Quit", Stats.timesQuit);
		set("Stats.Items_Crafted", Stats.timesCheated);

		try {
			if (!saveLocation.exists())
				saveLocation.createNewFile();

			FileWriter writer = new FileWriter(saveLocation);

			writer.write(yaml.dump(data));
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static boolean load() {
		setup();

		FileReader reader = read(saveLocation);

		if (reader == null) {
			Ui.cls();
			Ui.println("------------------------------");
			Ui.println("Cannot find save file.  ");
			Ui.println("Starting a new game...  ");
			Ui.println("------------------------------");
			Ui.pause();

			data = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
			return true;
		}

		data = Collections.synchronizedMap((Map<String, Object>) yaml.load(reader));

		//Health
		Health.set(getInteger("User.Health"), getInteger("User.Max_Health"));
		Health.timesDied = getInteger("Stats.TimesDied");

		//Coins
		Coins.set(getInteger("User.Balance"), false);
		Stats.totalCoinsSpent = getInteger("Stats.Money_Spent.Coins");
		Stats.coinsSpentOnBankInterest = getInteger("Stats.Money_Spent.Interest");
		Stats.coinsSpentOnWeapons = getInteger("Stats.Money_Spent.Weapons");
		Stats.coinsSpentOnHealth = getInteger("Stats.Money_Spent.Health");
		Stats.xpBought = getInteger("Stats.Money_Spent.XP");

		//Xp
		Xp.setLevel(getInteger("User.XP.Level"));
		Xp.setOutOf(getInteger("User.XP.Needed"));
		Xp.set(getInteger("User.XP.Amount"), false);
		Xp.total = getInteger("User.XP.Total");
		Xp.setBattleXp(getInteger("User.XP.battleXp"), false);

		//Potions
		Potion.spUsed = getInteger("Stats.Potions.Survival.Used");
		Potion.rpUsed = getInteger("Stats.Potions.Recovery.Used");
		Potion.set("survival", getInteger("User.Potions.Survival"), false);
		Potion.set("recovery", getInteger("User.Potions.Recovery"), false);

		//Settings
		Settings.setDif(getString("Settings.Difficulty.Level"), false, false);
		Settings.difLocked = getBoolean("Settings.Difficulty.Locked");
		if(getBoolean("Settings.Cheats.Enabled")) Cheats.enable();
		if(getBoolean("Settings.Cheats.Locked")) Cheats.lock();
		Ui.guiEnabled = getBoolean("Settings.GUI.Enabled");

		//Combat
		Stats.kills = getInteger("Stats.Kills");
		Stats.highScore = getInteger("Stats.High_Score");
		Stats.totalKills = getInteger("Stats.Total_Kills");
		Weapon.set(getInteger("User.Weapons.Current"));

		for(int i = 0; i < Weapon.arrayWeapon.size(); i++){
			if (getBoolean("User.Weapons." + i)){
				Weapon.arrayWeapon.get(i).owns = true;
			}
			Weapon.arrayWeapon.get(i).setAmmo(getInteger("User.Weapons.Ammo." + i), false);
		}

		Stats.totalDamageDealt = getInteger("Stats.Damage_Dealt");
		Stats.bulletsFired = getInteger("Stats.Bullets_Fired");
		Stats.bulletsThatHit = getInteger("Stats.Bullets_Hit");

		List<Integer> armours = (List<Integer>) getList("User.Armour.Owns");

		for(int i = 0; i < armours.size(); i++)
			Armour.getArmours().get(i).setOwns(true);

		Armour.set(getInteger("User.Armour.Current"));

		//Enemy
		Enemy.set(getInteger("Battle.Current.Enemy"));
		Enemy.get().setHealth(getInteger("Battle.Current.Enemy_Health"), getInteger("Battle.Current.Enemy_Max_Health"));
	//	Enemy.get().setEnemy_potion(getInteger("Battle.Current.Enemy_First_Aid_Kit"));
		Enemy.get().setEnemy_potion(getInteger("Battle.Current.Enemy_Potion"));

		//Achs
		
		
		//Other Stuff
		About.setViewed(getBoolean("Settings.About_Viewed"));
		Stats.timesCheated = getInteger("Stats.Times_Cheated");
		Stats.timesQuit = getInteger("Stats.Times_Quit");

		return true;
	}

	private static void setup() {
		saveLocation = new File(path);

		if (!saveLocation.exists())
			try {
				saveLocation.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}

		setupDumper();

		yaml = new Yaml(representer, options);
		data = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
	}

	private static void setupDumper() {
		options = new DumperOptions();
		representer = new Representer();

		options.setIndent(2);
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setAllowUnicode(Charset.defaultCharset().name().contains("UTF"));
		representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
	}

	public static boolean savesPrompt() {
	//	User.promptNameSelection();
		path = Saves.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "username.TFsave";
		path = path.replace("username", "_" + User.name());
		path = path.replaceAll("%20", " ");

		Ui.cls();
		Ui.println("------------------------------");
		Ui.println("What would you like to do?");
		Ui.println("------------------------------");
		Ui.println("1) Load Save");
	//	Ui.println("2) Convert Old Save");
		Ui.println("2) New Game ");

		switch (Ui.getValidInt()) {
			case 1:
				load();
				break;
			case 2:
				return false;
			default:
				return false;
		}
		return true;
	}
	

	public static boolean contains(String key) {
		return data.containsKey(key);
	}

	public static boolean exists() {
		return data != null;
	}

	public static boolean getBoolean(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Boolean)
			return (Boolean) tempObject;
		if (tempObject instanceof String)
			if (tempObject.toString().equalsIgnoreCase("true") || tempObject.toString().equalsIgnoreCase("1"))
				return true;
			else if (tempObject.toString().equalsIgnoreCase("false") || tempObject.toString().equalsIgnoreCase("0"))
				return false;
		if (tempObject instanceof Number)
			if (((Number) tempObject).intValue() == 1)
				return true;
			else if (((Number) tempObject).intValue() == 0)
				return false;
		return false;
	}

	public static boolean hasValue(String key) {
		Object tempObject = data.get(key);

		return (tempObject != null);
	}

	public static boolean isEmpty() {
		return data.isEmpty() || data == null;
	}

	public static byte getByte(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Byte)
			return (Byte) tempObject;
		if (tempObject instanceof String)
			if (Ui.isNumber(tempObject.toString()))
				return Byte.parseByte(tempObject.toString());
		if (tempObject instanceof Number)
			return Byte.parseByte(tempObject.toString());
		return -1;
	}

	public static char getChar(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Character)
			return (Character) tempObject;
		if (tempObject instanceof String)
			return tempObject.toString().charAt(0);
		if (tempObject instanceof Number)
			return tempObject.toString().charAt(0);
		return '\u0000';
	}

	public static double getDouble(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Double)
			return (Double) tempObject;
		if (tempObject instanceof String)
			if (Ui.isDecimalNumber(tempObject.toString()))
				return Double.parseDouble(tempObject.toString());
		if (tempObject instanceof Number)
			return Double.parseDouble(tempObject.toString());
		return -1;
	}

	public static int getInteger(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Integer)
			return (Integer) tempObject;
		if (tempObject instanceof String)
			if (Ui.isNumber(tempObject.toString()))
				return Integer.parseInt(tempObject.toString());
		if (tempObject instanceof Number)
			return Integer.parseInt(tempObject.toString());
		return -1;
	}

	public static List<?> getList(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof List<?>)
			return (List) tempObject;
		return null;
	}

	public static long getLong(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Long)
			return (Long) tempObject;
		if (tempObject instanceof String)
			if (Ui.isNumber(tempObject.toString()))
				return Long.parseLong(tempObject.toString());
		if (tempObject instanceof Number)
			return Long.parseLong(tempObject.toString());
		return -1;
	}

	public static Map<?, ?> getMap(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Map<?, ?>)
			return (Map) tempObject;
		return null;
	}

	public static Map<String, Object> getValues() {
		if (!isEmpty())
			return data;
		return null;
	}

	public static Object get(String key) {
		if (isEmpty())
			return null;

		final String[] nodes = key.split("\\.");
		Map curMap = data;

		for (int i = 0; i <= nodes.length - 1; ++i) {
			Object child = curMap.get(nodes[i]);

			if (child == null) return null;
			else if (!(child instanceof Map)) {
				if (i == nodes.length - 1)
					return child;
				else return null;
			}

			curMap = (Map) child;
		}
		return null;
	}

	public static Set<String> getKeys() {
		if (!isEmpty())
			return data.keySet();
		return new HashSet<>();
	}

	public static short getShort(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof Short)
			return (Short) tempObject;
		if (tempObject instanceof String)
			if (Ui.isNumber(tempObject.toString()))
				return Short.parseShort(tempObject.toString());
		if (tempObject instanceof Number)
			return Short.parseShort(tempObject.toString());
		return -1;
	}

	public static String getString(String key) {
		Object tempObject = get(key);

		if (tempObject instanceof String)
			return (String) tempObject;
		return null;
	}

	public static FileReader read(File file) {
		try {
			if (!file.exists())
				return null;
			return new FileReader(file);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @return the next line as an integer
	 */
	private static int readInt(){
		return Integer.parseInt(input.nextLine());
	}

	/**
	 *
	 * @return the next line as a boolean
	 */
	private static boolean readBoolean(){
		return Boolean.parseBoolean(input.nextLine());
	}

	/**
	 *
	 * @return the next line as a String
	 */
	private static String readString(){
		return input.nextLine();
	}

    //Thanks http://stackoverflow.com/a/38951302/3291305 !
    public static void set(String key, Object object) {

        if (!exists())
            return;

        final String[] nodes = key.split("\\.");

		Map<String, Object> cur = data;

        for (int i = 0; i <= nodes.length - 2; ++i) {
            Object val = cur.get(nodes[i]);
            if (val == null) {
                val = new LinkedHashMap();
                cur.put(nodes[i], val);
            } else if (!(val instanceof Map)) {
                Ui.print("There was a problem saving your game.");
            }
			cur = (Map<String, Object>) val;
		}
        cur.put(nodes[nodes.length - 1], object);
    }
}