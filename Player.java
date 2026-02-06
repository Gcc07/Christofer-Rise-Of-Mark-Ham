import java.util.Dictionary;

public class Player {
    protected boolean isDead = false;
    protected String name;
    protected int currentHP;
    protected int currentMP;
    protected float fleeChance;
    protected Dictionary<String, Integer> stats;
    protected Dictionary<String, Item> items;
    
    public Player(String name, Dictionary<String, Integer> stats, Dictionary<String, Integer> items) {
        this.stats = stats; // Life, Anger, Peace, Smartness, Finesse
        this.currentHP = stats.get("life") * 10; // * 10 to see how much health you have (EX. If you have 10 life stat, you have 100 HP)
        this.currentMP = stats.get("currentExperience"); // Should be 0 as you start out with 0 Mark Points.
        this.fleeChance = stats.get("Finesse") * 4;
    }

    public void takeDamage(int amount) {
        this.currentHP = currentHP - amount;
        if (currentHP < 0) {
            this.isDead = true;
        }
    }
}
