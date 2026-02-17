import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;

public class Player {
    protected int level;
    protected boolean isDead = false;
    protected String name;
    protected String fortune;
    protected String inspectDescription;
    protected int currentHP;
    protected int maximumHP;
    protected int currentMP;
    protected float fleeChance;
    protected float encounterChance;
    protected Dictionary<String, Integer> stats;
    protected ArrayList<Item> items;
    protected Weapon equippedWeapon;
    protected Pet adoptedPet;
    
    public Player(String name, String fortune, Dictionary<String, Integer> stats, ArrayList<Item> items) {
        this.name = name;
        this.fortune = fortune;
        this.stats = stats; // Life, Anger, Peace, Smartness, Finesse
        this.items = items; // Life, Anger, Peace, Smartness, Finesse
        this.maximumHP = 50 + stats.get("Life") * 10; // 50 + Life multiplied 10 to see how much health you have (EX. If you have 10 life stat, you have 150 HP)
        this.currentHP = maximumHP;
        this.currentMP = 0; // Should be 0 as you start out with 0 Mark Points.
        this.fleeChance = 0 
        + stats.get("Finesse") * .04f // at 25 finesse you have a 100% escape chance
        + stats.get("Smartness") * .02f;
        this.equippedWeapon = (Weapon)items.get(0); // Relies on weapon being first thing in inventory
        this.encounterChance = .5f // 50% encounter chance
        - (stats.get("Finesse") * .01f) // -.01 chance for each Finesse,
        - (stats.get("Peace") * .005f) // -.005 chance for each Peace,
        - (stats.get("Smartness") * .005f) // -.005 chance for each Smartness,
        + (stats.get("Anger") * .015f); // +.01 chance for each Anger.
    }

    public void addItemToInventory(Item item) {
        items.add(item);
    }

    public void takeDamage(int amount) {
        this.currentHP = currentHP - amount;
        if (currentHP <= 0) {
            isDead = true;
        }
    }

    // I haven't implemented functionality for player fortune just yet. It's simply a reflection of how lucky you got with rolls.
    public static String getPlayerFortune(int startingStatTotal) {
        if (startingStatTotal <= 35) {     
            return GameFlow.ANSI_BLACK + "Accursed" + GameFlow.RESET;
        }
        if (startingStatTotal <= 48) {     
            return GameFlow.ANSI_RED + "Tormented" + GameFlow.RESET;
        }
        else if (startingStatTotal <= 58) { 
            return GameFlow.ANSI_YELLOW + "Unremarkable" + GameFlow.RESET;
        }
        else if (startingStatTotal <= 74) { 
            return GameFlow.ANSI_GREEN + "Favored" + GameFlow.RESET;
        }
        else if (startingStatTotal <= 85) { 
            return GameFlow.ANSI_BLUE + "Blessed" + GameFlow.RESET;
        }
        else {                               
            return GameFlow.ANSI_CYAN + "Moon-Blessed" + GameFlow.RESET;
        }
    }

    public Dictionary<String, Integer> getStats() {
        return stats;
    }

    public float getEncounterChance() {
        return encounterChance;
    }

    public ArrayList<Item> getInventory() {
        return items;
    }

    public String getName() { 
        return name;
    }

    public boolean isDead() {
        return isDead;
    }

    public void useItem(Item item) {
        switch (item.getType()) {
            case "Consumable":
                consumeItem(item);
                break;
            case "Weapon":
                Weapon weapon = (Weapon)item;
                equipWeapon(weapon);
                break;
            case "Key":
                return;
            case "Pet":
                Pet pet = (Pet)item;
                adoptPet(pet);
                break;
            default:
                break;
        }
    }

    public void consumeItem(Item item) {
        currentHP = Math.min(currentHP + item.getHealingValue(), maximumHP);
        currentMP += item.getMarkPointsValue();

            // I used cursor here because I couldn't get the enhanced for loop to work.
        for (String statName : Collections.list(item.getStatUpdateValue().keys())) {
                int statChange = item.getStatUpdateValue().get(statName);
                stats.put(statName, stats.get(statName) + statChange);
        }
        dropItem(item);
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    public void adoptPet(Pet pet) {
        adoptedPet = pet;
    }

    public void dropItem(Item item) {
        //System.out.println(items);
        items.remove(item);
        //System.out.println(items);
    }


    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public String inspect() {
        if (currentHP <= maximumHP * .3) {
            inspectDescription = "Man, you're looking pretty rough.";
        } else if (currentHP <= maximumHP * .6) {
            inspectDescription = "You've looked worse.";
        } else if (currentHP <= maximumHP * .8){
            inspectDescription = "You're a little roughed up.";
        }
        else {
            inspectDescription = "You feel fine.";
        }  
        return inspectDescription;
    }

    public void applyEffect() {

    }

    public void gainMP(int markPointsValue) {
        currentMP += markPointsValue;
    }

    public float getFleeChance() {
        return fleeChance;
    }

    public String getFortune() {
        return fortune;
    }

    public int getHealth() {
        return currentHP;
    }

    public int getMaxHealth(){
        return maximumHP;
    }

    @Override
    public String toString() {
        String petName;
        if (adoptedPet == null) {petName = GameFlow.ANSI_BLACK + "None" + GameFlow.RESET;} else {petName = adoptedPet.getName();} // One liner assigning pet name
        return "\nName: " + name + " | Level: " + level + " | Fortune: " + fortune + "\n================================" + 
            "\nWeapon: " + GameFlow.RESET + equippedWeapon.name + GameFlow.RESET +
            "\nPet: " + GameFlow.RESET + petName + GameFlow.RESET +
            "\nHealth: " + currentHP + " / " + maximumHP +
            "\nMark Points: " + currentMP + 
            "\nStats: " + 
            GameFlow.ANSI_PURPLE + "Life: " + GameFlow.ANSI_RESET + stats.get("Life") + " " +
            GameFlow.ANSI_RED + "Anger: " + GameFlow.ANSI_RESET + stats.get("Anger") + " " +
            GameFlow.ANSI_YELLOW + "Peace: " + GameFlow.ANSI_RESET + stats.get("Peace") + " " +
            GameFlow.ANSI_CYAN + "Smartness: " + GameFlow.ANSI_RESET + stats.get("Smartness") + " " +
            GameFlow.ANSI_GREEN + "Finesse: " + GameFlow.ANSI_RESET + stats.get("Finesse") +
            "\nItems: " + items.toString() + "\n================================\n";
            

    }   
}
