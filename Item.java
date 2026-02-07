public class Item {
    protected String name;
    protected String description;
    protected String type;
    public static String[] itemTypes = {"Consumable", "Key", "Pet"};
    public static String[] itemNames = {"Karim's Homework", "Evan's Homework", "Samosas", "College Deferral", "Tiny Zingbah",
    "Spotted Mushroom", "Withheld Toothbrush", "Worn Football", "Chess Pawn", "Killer Bunny", "FISH!", "Heart Key", "Bomb Key", "Card Key", "Key Key"};

    public Item(String name) {
        switch(name) {
            case "Karim's Homework":
                this.name = name;
                this.description = "It's not done... (-1 Smartness, +5 Peace, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                break;
            case "Evan's Homework":
                this.name = name;
                this.description = "He's solved the three body problem (+5 Smartness, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                break;
            case "Samosas":
                this.name = name;
                this.description = "Tasty regen! (Heals 20 HP, spawns in Loot Rooms)";
                this.type = itemTypes[0];
                break;
            case "College Deferral":
                this.name = name;
                this.description = "Cross your fingers... (Rerolls your Life, Anger, Peace, Smartness and Finesse, sold in Shops)";
                this.type = itemTypes[0];
                break;
            case "Tiny Zingbah":
                this.name = name;
                this.description = "Boohbah, Boohbah! (From now on, miniature Zing Zing Zingbah attacks the enemy after you and deals your damage, might spawn when defeating a Boohbah)";
                this.type = itemTypes[2];
                break;
            case "Spotted Mushroom":
                this.name = name;
                this.description = "You can smell color (Doubles your MP, spawns in Loot Rooms rarely)";
                this.type = itemTypes[0];
                break;
            case "Withheld Toothbrush":
                this.name = name;
                this.description = "What's wrong with these dentists?! (-1 Peace, +5 Anger, spawns in Loot Rooms)";
                this.type = itemTypes[0];
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
            }
        }
    

    public String toString() {
        return "\n\tItem: " + name + "\n\tDescription: " + description;
    }
    
    public static String returnRandomItem() {
        int index = (int) (Math.random() * (itemTypes.length));
        return itemTypes[index];   
    }
}
