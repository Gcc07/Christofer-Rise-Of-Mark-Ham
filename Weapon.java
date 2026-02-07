import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;

public class Weapon extends Item{
    protected String prefix;
    protected float damage;
    protected float critChance; // IDK if this is going to be implemented
    
    protected Dictionary<String, Float> scalingType;

    public static String[] weaponNames = {
    "N's Odachi of the East", 
    "Cursed Mouse", 
    "Evan's Great Mace of Destruction", 
    "Maxwell's Spear of the Enraged", 
    "Sixsev-UN-chucks of Troy",
    "Karan's Quiet Rapier",
    "Phil's 'sticks",
    "Katars of Humanity",
    "Unicycle",
    "Gahaurtana",
    "Sam's Dagger",
    "Mr. McCuen's Pearson Piercer",
    "Mr. Gardner's Participation Point Piercer"
    };
    public Weapon(String name) { 
        super(name);
        this.type = "Weapon";
        this.name = name;
        this.scalingType = new Hashtable<>();
        switch(name) { // I used claude to create the switch cases after I made one singular case. - GABE
            // Also, I wrote all of the item descriptions.
            case "N's Odachi of the East":
                this.damage = 45;
                this.critChance = 0.15f;
                this.description = "An eastern artifact once wielded by a great warrior. It has equations engraved into it's hilt";
                // Special: Samosas heal 200% more
                break;
                
            case "Cursed Mouse":
                this.damage = 30;
                this.critChance = 0.10f;
                this.description = "A weapon forged of plasticite, it periodically phases in and out of reality.";
                this.scalingType.put("Anger", 0.20f);
                // Special: 50/50 chance for 50% more or less damage
                break;
                
            case "Evan's Great Mace of Destruction":
                this.damage = 100;
                this.critChance = 0.05f;
                this.description = "It is said in legend; there is only one man, harking from the lands of min-craftia, who might wield this tool of destruction to it's fullest potential.";
                // Special: Highest damage, scales with height
                break;
                
            case "Maxwell's Spear of the Enraged":
                this.damage = 50;
                this.critChance = 0.20f;
                this.description = "Rage pulsates throughout this spear. You feel angry simply wielding it.";
                // Special: 20% more damage to Human enemies
                break;
                
            case "Sixsev-UN-chucks of Troy":
                this.damage = 42; // Multiple of 6 and 7
                this.critChance = 0.12f;
                this.description = "A six-inch link of pure iron chained two a seven-inch hilt of gold. You feel indecisive in it's presence.";
                // Special: Always does multiple of 6 or 7 damage
                break;
                
            case "Karan's Quiet Rapier":
                this.damage = 40;
                this.critChance = 0.18f;
                this.description = "A rapier imbued with an calm presence. Silence fills the room when holding it in your hand.";
                // Special: Scales better with Anger
                break;
                
            case "Phil's 'sticks":
                this.damage = 35;
                this.critChance = 0.15f;
                this.description = "Wooden armaments meant two be dual wielded, clearly with the purpose of causing destruction in the air tonight.";
                break;
                
            case "Katars of Humanity":
                this.damage = 38;
                this.critChance = 0.14f;
                this.description = "Eastern Weapons fitted to ones palm, unmistakeabily drawing power from a balance of rage and calm.";
                // Special: Scales with peacefulness and anger
                break;
                
            case "Unicycle":
                this.damage = 25;
                this.critChance = 0.25f;
                this.description = "A weapon, perhaps from another time... It grants you speed untold.";
                break;
                
            case "Gahaurtana":
                this.damage = 52;
                this.critChance = 0.13f;
                this.description = "A simple blade with the engraving 'Bills <3' etched into the hilt";
                break;
                
            case "Sam's Dagger":
                this.damage = 20;
                this.critChance = 0.5f;
                this.description = "A dagger of unknown orgin. It has a tag hanging off to the side, proclaiming 'sam'";
                break;
                
            case "Mr. McCuen's Pearson Piercer":
                this.damage = 55;
                this.critChance = 0.11f;
                this.description = "A terrifying thing. A horrible thing. A thing wielded by a man whom yielded to no-one; yet he could not fight...\nPearson.";
                break;
                
            case "Mr. Gardner's Participation Point Piercer":
                this.damage = 28;
                this.critChance = 0.30f;
                this.description = "The lands of Earlycolegia only exist because of the Whim of Mr. Gardner. His defeat at the hands of the moon warrior was fate, yet he did not die. This weapon is proof of that.";
                break;
                
            default:
                this.damage = 10;
                this.critChance = 0.05f;
                this.description = "BASE WEAPON";
                break;
        }
    }
    public static String returnRandom() { // Returns a random weapon name from the nameChoices list
        int index = (int) (Math.random() * (weaponNames.length));
        return weaponNames[index];
    }

    @Override
    public String toString() {
        String scalingStringValues = "";
        if (scalingType != null && scalingType.size() > 0) { // Making sure there is a actually a scaling type.
            for (String key : Collections.list(scalingType.keys())) {
                Float value = scalingType.get(key);
                String color = "";
                switch(key) {
                    case "Life": color = GameFlow.ANSI_PURPLE; break;
                    case "Anger": color = GameFlow.ANSI_RED; break;
                    case "Peace": color = GameFlow.ANSI_YELLOW; break;
                    case "Smartness": color = GameFlow.ANSI_CYAN; break;
                    case "Finesse": color = GameFlow.ANSI_GREEN; break;
                }
                scalingStringValues += color + key + ": " + GameFlow.ANSI_RESET + value + " ";
            }
        } else {
            scalingStringValues =  GameFlow.ANSI_GREY + "None" +  GameFlow.RESET;
        }
        
        return "\n\t" + name + " | " + type + " | Scaling - " + scalingStringValues
             + "\n\tDescription: " + description + "\n\t";
    }
}
