import java.util.Dictionary;
import java.util.Hashtable;

public class Item {
    protected String name;
    protected String description;
    protected String type;
    protected String useMessage;
    protected float rarity; // IDK if this is going to be implemented
    protected float dropChance; // IDK if this is going to be implemented

    protected int healingValue = 0;
    protected int markPointsValue = 0;
    protected Dictionary<String, Integer> statUpdateValue;
    public static String[] itemTypes = {"Consumable", "Key", "Pet"};
    public static String[] itemNames = {"Karim's Homework", "Evan's Homework", "Samosas", "College Deferral", "Lil' Zingbah",
    "Spotted Mushroom", "Withheld Toothbrush", "Worn Football", "Chess Pawn", "Killer Bunny", "FISH!",
    "Heart Key", "Bomb Key", "Card Key", "Key Key", "Glowing Apple", "Classroom Key", "Olive Branch"};

    public Item(String name) {
        this.name = name;
        this.statUpdateValue = new Hashtable<>();
        this.useMessage = " used ";
        switch(name) {
            case "Karim's Homework":
                this.name = name;
                this.description = "It's not done... (-1 Smartness, +5 Peace, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                this.statUpdateValue.put("Peace", 5);
                this.statUpdateValue.put("Smartness", -1);
                break;
            case "Evan's Homework":
                this.name = name;
                this.description = "He's solved the three body problem (+5 Smartness, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                this.statUpdateValue.put("Smartness", 5);
                break;
            case "Samosas":
                this.name = name;
                this.healingValue = 50;
                this.description = "Tasty regen! (Heals 50 HP, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                break;
            case "College Deferral":
                this.name = name;
                this.description = "Cross your fingers... (-10 Peace, + 10 Anger) "; // (Rerolls your Life, Anger, Peace, Smartness and Finesse, sold in Shops)";
                this.type = itemTypes[0];
                this.statUpdateValue.put("Peace", -10);
                this.statUpdateValue.put("Anger", 10);
                break;
            case "Lil' Zingbah":
                this.name = name;
                this.description = "Boohbah, Boohbah! (From now on, miniature Zing Zing Zingbah attacks the enemy after you and deals your damage, might spawn when defeating a Boohbah)";
                this.type = itemTypes[2];
                break;
            case "Spotted Mushroom":
                this.name = name;
                this.description = "You can smell color (+ 100 MP, spawns in Loot Rooms rarely)";
                this.markPointsValue = 100;
                this.type = itemTypes[0];
                break;
            case "Withheld Toothbrush":
                this.name = name;
                this.description = "What's wrong with these dentists?! (-3 Peace, +5 Anger, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                this.statUpdateValue.put("Peace", -3);
                this.statUpdateValue.put("Anger", 5);
                break;
            case "Worn Football":
                this.name = name;
                this.description = "American football is irresistable (An enemy of your choice fights on your side until it dies or the fight ends, usable 1x per fight & 3x per game, sold in Shops)";
                this.type = itemTypes[0];
                break;
            case "Chess Pawn":
                this.name = name;
                this.description = "Google en passant (After this is used, the next time you successfully flee, gain the MP rewards anyways, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                break;
            case "Killer Bunny":
                this.name = name;
                this.description = "Run away! Run away! (From now on, bunny attacks the enemy after you and deals 10 damage, spawns in Loot Rooms)";
                this.type = itemTypes[2];
                break;
            case "FISH!":
                this.name = name;
                this.description = "You know what that means! (-1 Finesse, +5 max HP, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                this.statUpdateValue.put("Finesse", -1);
                this.statUpdateValue.put("Life", 5);
                break;
            case "Heart Key":
                this.name = name;
                this.description = "Key of life";
                this.type = itemTypes[1];
                break;
            case "Bomb Key":
                this.name = name;
                this.description = "Key of death";
                this.type = itemTypes[1];
                break;
            case "Card Key":
                this.name = name;
                this.description = "Key of gathering magic";
                this.type = itemTypes[1];
                break;
            case "Key Key":
                this.name = name;
                this.description = "Key of key";
                this.type = itemTypes[1];
                break;
            case "Glowing Apple":
                this.name = name;
                this.description = "A shining fruit. It looks healthy. (+1 Life, Heals 20 HP)";
                this.type = itemTypes[0];
                this.healingValue = 20;
                this.statUpdateValue.put("Life", 1);
                break;
            case "Classroom Key":
                this.name = name;
                this.description = "Ol' reliable damage up, but doesn't open any doors you'd find... (+3 Anger, +3 Smartness)";
                this.type = itemTypes[0];
                this.statUpdateValue.put("Anger", 3);
                this.statUpdateValue.put("Smartness", 3);
                break;
            case "Olive Branch":
                this.name = name;
                this.description = "You feel tranquil (-3 Anger, +5 Peace, +1 Finesse, +1 Smartness)";
                this.type = itemTypes[0];
                this.statUpdateValue.put("Anger", -3);
                this.statUpdateValue.put("Peace", 5);
                this.statUpdateValue.put("Finesse", 1);
                this.statUpdateValue.put("Smartness", 1);
                break;
            }
        }
    
    @Override
    public String toString() {
        return "\n\t" + name + " | " + type + "\n\tDescription: " + description + "\n\t";
    }

    public String getUseMessage() {
        return useMessage;
    }
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getHealingValue() {
        return healingValue;
    }

    public int getMarkPointsValue() {
        return healingValue;
    }

    public Dictionary<String, Integer> getStatUpdateValue() {
        return statUpdateValue;
    }

    public static String returnRandom() {
        int index = (int) (Math.random() * (itemNames.length));
        return itemNames[index];   
    }
}
