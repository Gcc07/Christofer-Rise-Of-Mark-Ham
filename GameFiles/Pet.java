import java.util.Dictionary;
import java.util.Hashtable;

public class Pet extends Item{
    protected String name;
    protected String description;
    protected String type;
    protected String useMessage;
    protected float rarity; // IDK if this is going to be implemented
    protected float dropChance; // IDK if this is going to be implemented

    protected Dictionary<String, Integer> statUpdateValue;
    public static String[] itemNames =

    {
    "Lil' Zingbah",
    "Killer Bunny", };


    public Pet(String name) {
        super(name);
        this.type = "Pet";
        this.name = name;
        this.statUpdateValue = new Hashtable<>();
        this.useMessage = " used ";
        switch(name) {
            case "Lil' Zingbah":
                this.name = name;
                this.description = "Boohbah, Boohbah! (From now on, miniature Zing Zing Zingbah attacks the enemy after you and deals your damage, might spawn when defeating a Boohbah)";
                break;
            case "Killer Bunny":
                this.name = name;
                this.description = "Run away! Run away! (From now on, bunny attacks the enemy after you and deals 10 damage)";
                break;
            }
        }
    
    @Override
    public String toString() {
        return "\n\t" + name + " | " + type + "\n\tDescription: " + description + "\n\t";
    }

}
