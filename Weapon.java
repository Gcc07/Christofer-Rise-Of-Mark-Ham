import java.util.Dictionary;

public class Weapon extends Item{
    //N’s Odachi of the East - Samosas heals 200% more
    //Cursed Mouse - Either does 50% more or less damage (50 50 chance)
    //Evan’s Great Mace of Destruction - Highest Damaging Weapon in Game (scales with height)
    //Maxwell’s Spear of the Enraged - 20% more damage to Human enemies
    //Sixsev-un-chucks of Troy - always does a multiple of 6 or 7 damage
    //Karan’s Rageful Rapier - scales better with Anger
    //Phil’s Drumsticks
    //Katars of Humanity - Scales with peacefulness and anger
    //Unicycle 
    //A’s Javatana
    //Guatana
    //Sam’s Dagger
    //Mr. McCuen's Pearson Piercer
    //Mr. Gardner's Participation Point Piercer
    protected String name;
    protected String prefix;
    protected int damage;
    protected int currentHP;
    protected int currentMP;
    protected float fleeChance;
    protected Dictionary<String, Integer> stats;
    protected Dictionary<String, Item> items;
    public String[] weaponChoice = {"N’s Odachi of the East", "Cursed Mouse", "Evan’s Great Mace of Destruction", "Maxwell’s Spear of the Enraged", "Sixsev-un-chucks of Troy"};
    public Weapon(String name) { 

    }
}
