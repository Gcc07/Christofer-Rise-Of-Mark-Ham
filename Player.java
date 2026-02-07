import java.util.ArrayList;
import java.util.Dictionary;

public class Player {
    protected int level;
    protected boolean isDead = false;
    protected String name;
    protected String fortune;
    protected int currentHP;
    protected int maximumHP;
    protected int currentMP;
    protected float fleeChance;
    protected Dictionary<String, Integer> stats;
    protected ArrayList<Item> items;
    
    public Player(String name, String fortune, Dictionary<String, Integer> stats, ArrayList<Item> items) {
        this.name = name;
        this.fortune = fortune;
        this.stats = stats; // Life, Anger, Peace, Smartness, Finesse
        this.items = items; // Life, Anger, Peace, Smartness, Finesse
        this.maximumHP = stats.get("Life") * 10; // * 10 to see how much health you have (EX. If you have 10 life stat, you have 100 HP)
        this.currentHP = maximumHP;
        this.currentMP = 0; // Should be 0 as you start out with 0 Mark Points.
        this.fleeChance = stats.get("Finesse") * 4;
    }


    public void takeDamage(int amount) {
        this.currentHP = currentHP - amount;
        if (currentHP < 0) {
            this.isDead = true;
        }
    }
    // I haven't implemented functionality for player fortune just yet. It's simply a reflection of how lucky you got with rolls.
    public static String getPlayerFortune(int startingStatTotal) {
        if (startingStatTotal <= 8) {        // Bottom ~5% (5-8)
            return GameFlow.ANSI_BLACK + "Accursed" + GameFlow.RESET;
        }
        if (startingStatTotal <= 15) {       // Next ~10% (9-15)
            return GameFlow.ANSI_RED + "Tormented" + GameFlow.RESET;
        }
        else if (startingStatTotal <= 35) { // Middle ~40% (16-35)
            return GameFlow.ANSI_YELLOW + "Unremarkable" + GameFlow.RESET;
        }
        else if (startingStatTotal <= 50) { // Next ~30% (36-50)
            return GameFlow.ANSI_GREEN + "Favored" + GameFlow.RESET;
        }
        else if (startingStatTotal <= 65) { // Next ~20% (51-65)
            return GameFlow.ANSI_BLUE + "Blessed" + GameFlow.RESET;
        }
        else {                                // Top ~5% (66-75)
            return GameFlow.ANSI_CYAN + "Moon-Blessed" + GameFlow.RESET;
        }
    }

    @Override
    public String toString() {
        return "\nName: " + name + " | Level: " + level + " | Fortune: " + fortune + "\n================================" + 
            "\nHealth: " + currentHP + " / " + maximumHP +
            "\nMark Points: " + currentMP + 
            "\nStats: " + 
            GameFlow.ANSI_PURPLE + "Life: " + GameFlow.ANSI_RESET + stats.get("Life") + " " +
            GameFlow.ANSI_RED + "Anger: " + GameFlow.ANSI_RESET + stats.get("Anger") + " " +
            GameFlow.ANSI_YELLOW + "Peace: " + GameFlow.ANSI_RESET + stats.get("Peace") + " " +
            GameFlow.ANSI_CYAN + "Smartness: " + GameFlow.ANSI_RESET + stats.get("Smartness") + " " +
            GameFlow.ANSI_GREEN + "Finesse: " + GameFlow.ANSI_RESET + stats.get("Finesse") +
            "\nItems: " + items.toString() + "\n";
    }   
}
