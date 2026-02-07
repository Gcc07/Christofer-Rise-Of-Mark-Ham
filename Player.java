import java.util.ArrayList;
import java.util.Dictionary;

public class Player {
    protected boolean isDead = false;
    protected String name;
    protected int currentHP;
    protected int currentMP;
    protected float fleeChance;
    protected Dictionary<String, Integer> stats;
    protected ArrayList<Item> items;
    
    public Player(String name, Dictionary<String, Integer> stats, ArrayList<Item> items) {
        this.name = name;
        this.stats = stats; // Life, Anger, Peace, Smartness, Finesse
        this.items = items; // Life, Anger, Peace, Smartness, Finesse
        this.currentHP = stats.get("Life") * 10; // * 10 to see how much health you have (EX. If you have 10 life stat, you have 100 HP)
        this.currentMP = 0; // Should be 0 as you start out with 0 Mark Points.
        this.fleeChance = stats.get("Finesse") * 4;
    }


    public void takeDamage(int amount) {
        this.currentHP = currentHP - amount;
        if (currentHP < 0) {
            this.isDead = true;
        }
    }

    @Override
    public String toString() {
        return "\nName: " + name + "\n================================" + 
            "\nHealth: " + currentHP + 
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
